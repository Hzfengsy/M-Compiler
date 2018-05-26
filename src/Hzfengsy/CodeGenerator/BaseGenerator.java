package Hzfengsy.CodeGenerator;


import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.IR.*;
import Hzfengsy.IR.IRNode.*;

import java.util.*;


public class BaseGenerator
{
    private IRProgNode program;
    private StackAlloc allocor;
    private StringBuilder ans = new StringBuilder();
    private IRLable endLable;

    public BaseGenerator(IRProgNode program) {
        this.program = program;
    }

    private void storeArgs(IRFuncNode func) {
        IRVar[] args = func.getArgs();
        for (int i = 0; i < args.length; i++) {
            store(args[i], Register.getParm(i));
        }
    }

    private String var2Mem(IRVar var) {
        if (var.isGlobe()) {
            return "qword [" + var.getName() + "]";
        }
        else {
            Integer offset = allocor.getOffset(var);
            String offsetString = offset < 0 ? offset.toString() : "+" + offset.toString();
            return "qword [" + Register.rbp + offsetString + "]";
        }
    }

    private void load(IRExpr var, Register reg) {
        if (var instanceof IRConst) {
            ans.append("\tmov\t" + reg + ", " + var + "\n");
        }
        else if (var instanceof IRVar) {
            ans.append("\tmov\t" + reg + ", " + var2Mem((IRVar) var) + "\n");
        }
        else {
            ans.append("\tmov\t" + reg + ", " + memAddr((IRMem) var) + "\n");
        }
    }

    private void store(IRExpr var, Register reg) {
        if (var instanceof IRVar) {
            ans.append("\tmov\t" + var2Mem((IRVar) var) + ", " + reg + "\n");
        }
        else {
            ans.append("\tmov\t" + memAddr((IRMem) var) + ", " + reg + "\n");
        }

    }


    private String memAddr(IRMem irMem) {
        IRExpr base = irMem.getAddr();
        IRExpr offset = irMem.getOffset();
        String baseAddr = base.toString();
        String offsetAddr = offset.toString();
        if (base instanceof IRVar) {
            load(base, Register.r14);
            baseAddr = Register.r14.toString();
        }
        if (offset instanceof IRVar) {
            load(offset, Register.r15);
            offsetAddr = Register.r15.toString();
        }
        return "qword [" + baseAddr + "+" + offsetAddr + " * 8]";
    }

    private void enterFunc() {
        ans.append("\tpush\trbp\n");
        ans.append("\tmov\trbp, rsp\n");
        ans.append("\tsub\trsp, " + Integer.toString(allocor.totalSize()) + "\n");
    }

    private void exitFunc() {
        ans.append("\tmov\trsp, rbp\n");
        ans.append("\tpop\trbp\n");
        ans.append("\tret\n");
        ans.append("\n");
    }

    private void addLikeOperator(IRExpr dest, IRExpr lhs, IROperations.binaryOp op, IRExpr rhs) {
        load(lhs, Register.rcx);
        if (rhs instanceof IRConst) {
            ans.append("\t" + op.toNASM() + "\trcx, " + rhs + "\n");
        }
        else {
            load(rhs, Register.r11);
            ans.append("\t" + op.toNASM() + "\trcx, r11\n");
        }
        if (dest instanceof IRVar)
            store( dest, Register.rcx);
        else ans.append("\tmov\t" + memAddr((IRMem) dest) + ", rcx\n");
    }

    private void cmpOperator(IRExpr dest, IRExpr lhs, IROperations.binaryOp op, IRExpr rhs) {
        load(lhs, Register.rcx);
        load(rhs, Register.r11);
        ans.append("\tcmp\trcx, r11\n");
        ans.append("\t" + op.toNASM() + "\tcl\n\tmovzx\trcx, cl\n");
        store( dest, Register.rcx);
    }

    private void divLikeOperator(IRExpr dest, IRExpr lhs, IROperations.binaryOp op, IRExpr rhs) {
        load(lhs, Register.rax);
        load(rhs, Register.rcx);
        ans.append("\txor\trdx, rdx\n");
        ans.append("\tcqo\n\tidiv\trcx\n");
        Register src = op.equals("div") ? Register.rax : Register.rdx;
        store( dest, src);
    }

    private void shiftOperator(IRExpr dest, IRExpr lhs, IROperations.binaryOp op, IRExpr rhs) {
        load(lhs, Register.r10);
        load(rhs, Register.r11);
        ans.append("\tmov\trax, r10\n");
        ans.append("\tmov\trcx, r11\n");
        ans.append("\t" + op.toNASM() + "\trax, cl\n");
        store( dest, Register.rax);
    }

    private void binaryOpeation(IRBinaryExprInstruction inst) {
        IROperations.binaryOp op = inst.getOperator();
        switch (op) {
            case ADD:
            case SUB:
            case MUL:
            case AND:
            case OR:
            case XOR:
                addLikeOperator(inst.getResult(), inst.getLeft(), op, inst.getRight());
                break;

            case EQ:
            case GE:
            case LE:
            case GT:
            case LT:
            case NE:
                cmpOperator(inst.getResult(), inst.getLeft(), op, inst.getRight());
                break;

            case DIV:
            case MOD:
                divLikeOperator(inst.getResult(), inst.getLeft(), op, inst.getRight());
                break;

            case LSHIFT:
            case RSHIFT:
                shiftOperator(inst.getResult(), inst.getLeft(), op, inst.getRight());
                break;
        }
    }

    private void moveOperator(IRExpr dest, IRExpr rhs) {
        if (dest instanceof IRVar) {
            if (rhs instanceof IRVar) {
                load(rhs, Register.rcx);
                store( dest, Register.rcx);
            }
            else if (rhs instanceof IRMem) {
                ans.append("\tmov\trcx, " + memAddr((IRMem) rhs) + "\n");
                store( dest, Register.rcx);
            }
            else if (rhs instanceof IRConst) {
                ans.append("\tmov\t" + var2Mem((IRVar) dest) + ", " + rhs + "\n");
            }
        }
        else {
            if (rhs instanceof IRVar) {
                load(rhs, Register.rcx);
                ans.append("\tmov\t" + memAddr((IRMem) dest) + ", rcx\n");
            }
            else if (rhs instanceof IRMem) {
                ans.append("\tmov\trcx, " + memAddr((IRMem) rhs) + "\n");
                ans.append("\tmov\t" + memAddr((IRMem) dest) + ", rcx\n");
            }
            else if (rhs instanceof IRConst) {
                ans.append("\tmov\t" + memAddr((IRMem) dest) + ", " + rhs + "\n");
            }
        }

    }

    private void unaryOpeartor(IRExpr dest, IROperations.unaryOp op, IRExpr rhs) {
        load(rhs, Register.r10);
        ans.append("\t" + op.toNASM() + "\t" + Register.r10 + "\n");
        store( dest, Register.r10);
    }

    private void unaryOpeation(IRUnaryExprInstruction inst) {
        IROperations.unaryOp op = inst.getOperator();
        switch (op) {
            case MOV:
                moveOperator(inst.getResult(), inst.getRight());
                break;
            case LNOT:
                addLikeOperator(inst.getResult(), inst.getRight(), IROperations.binaryOp.XOR, new IRConst(1));
                break;
            default:
                unaryOpeartor(inst.getResult(), op, inst.getRight());

        }
    }

    private void jumpOperator(IRjumpInstruction inst) {
        if (inst.getExpr() instanceof IRConst) {
            ans.append("\tjmp\t" + inst.getLable().getName() + "\n");
        }
        else {
            load(inst.getExpr(), Register.rcx);
            ans.append("\tcmp\trcx, 0\n");
            ans.append("\tjnz\t" + inst.getLable().getName() + "\n");
        }
    }

    private void retOperator(IRRetInstruction inst) {
        if (inst.getVal() != null)
            load(inst.getVal(), Register.rax);
        ans.append("\tjmp\t" + endLable.getName() + "\n");
    }

    private void call(IRCallInstruction inst) {
        IRExpr[] args = inst.getArgs();
        if (args.length <= 6) {
            for (int i = 0; i < args.length; i++) {
                load(args[i], Register.getParm(i));
            }
        }
        ans.append("\tcall " + inst.getFunc().getName() + "\n");
        if (inst.getResult() != null) {
            store( inst.getResult(), Register.rax);
        }

    }

    public String genrate() {
        ans.append("default rel\n" +
                   "\n" +
                   "global main\n" +
                   "\n" +
                   "extern malloc\n" +
                   "\n" +
                   "\n" +
                   "SECTION .text   \n" +
                   "\n");
        for (IRFuncNode funcNode : program.getFuncs()) {
            if (funcNode.getContainNodes().size() == 0) continue;
            allocor = funcNode.getAlloc();
            Integer size = funcNode.getContainNodes().size();
            endLable = funcNode.getContainNodes().get(size - 1).getLabel();
            boolean first = true;
            for (IRBaseBlock baseBlock : funcNode.getContainNodes()) {
                ans.append(baseBlock.getLabel().getName() + ":\n");
                if (first) {
                    enterFunc();
                    storeArgs(funcNode);

                    first = false;
                }
                for (IRBaseInstruction inst : baseBlock.getInstructions()) {
                    if (inst instanceof IRBinaryExprInstruction)
                        binaryOpeation((IRBinaryExprInstruction) inst);
                    else if (inst instanceof IRUnaryExprInstruction)
                        unaryOpeation((IRUnaryExprInstruction) inst);
                    else if (inst instanceof IRjumpInstruction)
                        jumpOperator((IRjumpInstruction) inst);
                    else if (inst instanceof IRRetInstruction)
                        retOperator((IRRetInstruction) inst);
                    else if (inst instanceof IRCallInstruction)
                        call((IRCallInstruction) inst);
                }
            }
            exitFunc();
        }
        ans.append("\n" +
                   "\n" +
                   "SECTION .data   \n" +
                   "\n" +
                   "\n" +
                   "SECTION .bss    \n");
        Collection<IRVar> globe = IRVariables.getInstance().getGlobe();
        for (IRVar var : globe) {
            ans.append(var.getName() + ":\tresq\t1\n");
        }
        return ans.toString();
    }
}