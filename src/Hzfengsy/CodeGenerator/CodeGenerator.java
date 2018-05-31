package Hzfengsy.CodeGenerator;


import Hzfengsy.Exceptions.*;
import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.IR.*;
import Hzfengsy.IR.IRNode.*;

import java.io.*;
import java.util.*;


public class CodeGenerator
{
    private IRProgNode program;
    private StackAlloc allocor;
    private StringBuilder ans = new StringBuilder();
    private IRLable endLable;
    private StringData stringData = StringData.getInstance();
    private LivenessAnalyzer analyzer = new LivenessAnalyzer();

    private Register var2Reg(IRVar var) {
        if (var.isGlobe()) return null;
        return RegisterAllocator.get(var);
    }

    public CodeGenerator(IRProgNode program) {
        this.program = program;
    }

    private String str2Data(String str) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            Integer x = 0;
            if (str.charAt(i) == '\\') {
                i++;
                switch (str.charAt(i)) {
                    case 'n':
                        x = 10;
                        break;
                    case '\\':
                        x = 92;
                        break;
                    case '\"':
                        x = 34;
                        break;
                }
            }
            else {
                x = (int) str.charAt(i);
            }
            String temp = Long.toHexString(x).toUpperCase();
            if (temp.length() < 2) ans.append("0" + temp + "H, ");
            else ans.append(temp + "H, ");
        }
        ans.append("00H");
        return ans.toString();
    }

    private String var2Mem(IRVar var) {
        Integer offset = allocor.getOffset(var);
        String offsetString = offset < 0 ? offset.toString() : "+" + offset.toString();
        return "qword [" + Register.rbp + offsetString + "]";
    }

    private void dataSection() {
        for (Map.Entry<String, IRExpr> entry : stringData.getEntry()) {
            ans.append(((IRVar) entry.getValue()).getName() + ":\n");
            ans.append("\tdb " + str2Data(entry.getKey()) + "\n");
        }
    }

    private String readFile(String filePath) {
        String ans = new String();
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                ErrorReporter.getInstance().putLine(tempString);
                ans += tempString + '\n';
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return ans;
    }

    private void storeArgs(IRFuncNode func) {
        IRVar[] args = func.getArgs();
        for (int i = 0; i < args.length && i < 2; i++) {
            Register reg = RegisterAllocator.get(args[i]);
            if (reg != null) ans.append("\tmov\t" + reg + ", " + Register.getParm(i) + "\n");
            else ans.append("\tmov\t" + var2Mem(args[i]) + ", " + Register.getParm(i) + "\n");
        }
        Integer offset = 8;
        for (int i = 2; i < args.length; i++) {
            offset += 8;
            Register reg = RegisterAllocator.get(args[i]);
            if (reg != null) {
                ans.append("\tmov\t" + reg + ", [rbp+" + offset.toString() + "]\n");
            }
            else {
                ans.append("\tmov\trax, [rbp+" + offset.toString() + "]\n");
                ans.append("\tmov\t" + var2Mem(args[i]) + ", " + Register.rax + "\n");
            }
        }
    }

    private String var2Str(IRVar var) {
        if (stringData.containLabel(var)) {
            return var.getName();
        }
        else if (var.isGlobe()) {
            return "qword [" + var.getName() + "]";
        }
        else if (RegisterAllocator.get(var) != null) {
            return RegisterAllocator.get(var).toString();
        }
        else {
            Integer offset = allocor.getOffset(var);
            String offsetString = offset < 0 ? offset.toString() : "+" + offset.toString();
            return "qword [" + Register.rbp + offsetString + "]";
        }
    }

    private String var2Str32(IRVar var) {
        if (stringData.containLabel(var)) {
            return var.getName();
        }
        else if (var.isGlobe()) {
            return "dword [" + var.getName() + "]";
        }
        else if (RegisterAllocator.get(var) != null) {
            return RegisterAllocator.get(var).Reg32();
        }
        else {
            Integer offset = allocor.getOffset(var);
            String offsetString = offset < 0 ? offset.toString() : "+" + offset.toString();
            return "dword [" + Register.rbp + offsetString + "]";
        }
    }

    private void load(IRExpr var, Register reg) {
        if (var instanceof IRConst) {
            ans.append("\tmov\t" + reg + ", " + var + "\n");
        }
        else if (var instanceof IRVar) {
            if (RegisterAllocator.get((IRVar) var) == reg) return;
            ans.append("\tmov\t" + reg + ", " + var2Str((IRVar) var) + "\n");
        }
        else {
            ans.append("\tmov\t" + reg + ", " + memAddr((IRMem) var) + "\n");
        }
    }

    private void store(IRExpr var, Register reg) {
        if (var instanceof IRVar) {
            if (RegisterAllocator.get((IRVar) var) == reg) return;
            ans.append("\tmov\t" + var2Str((IRVar) var) + ", " + reg + "\n");
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
            if (var2Reg((IRVar) base) != null) {
                baseAddr = var2Reg((IRVar) base).toString();
            }
            else {
                load(base, Register.rdx);
                baseAddr = Register.rdx.toString();
            }
        }
        if (offset instanceof IRVar) {
            if (var2Reg((IRVar) offset) != null) {
                offsetAddr = var2Reg((IRVar) offset).toString();
            }
            else {
                load(offset, Register.rbx);
                offsetAddr = Register.rbx.toString();
            }
        }
        return "qword [" + baseAddr + " + " + offsetAddr + " * 8]";
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
        Register destination = Register.rax;
        load(lhs, destination);
        if (rhs instanceof IRConst) {
            ans.append("\t" + op.toNASM() + "\t" + destination.Reg32() + ", " + rhs + "\n");
        }
        else {
            ans.append("\t" + op.toNASM() + "\t" + destination.Reg32() + ", " + var2Str32((IRVar) rhs) + "\n");
        }
        if (dest instanceof IRVar)
            store(dest, destination);
        else ans.append("\tmov\t" + memAddr((IRMem) dest) + ", " + destination + "\n");
    }

    private void cmpOperator(IRExpr dest, IRExpr lhs, IROperations.binaryOp op, IRExpr rhs) {
        Register reg1 = (lhs instanceof IRVar && var2Reg((IRVar) lhs) != null) ? var2Reg((IRVar) lhs) : Register.rax;
        Register reg2 = (rhs instanceof IRVar && var2Reg((IRVar) rhs) != null) ? var2Reg((IRVar) rhs) : Register.rcx;
        load(lhs, reg1);
        load(rhs, reg2);
        ans.append("\tcmp\t" + reg1 + ", " + reg2 + "\n");
        Register destReg = (dest instanceof IRVar && var2Reg((IRVar) dest) != null) ? var2Reg((IRVar) dest) : Register.rcx;
        ans.append("\t" + op.toNASM() + "\tcl\n\tmovzx\t" + destReg + ", cl" + "\n");
        store(dest, destReg);
    }

    private void divLikeOperator(IRExpr dest, IRExpr lhs, IROperations.binaryOp op, IRExpr rhs) {
        load(lhs, Register.rax);
        Register reg2 = (rhs instanceof IRVar && var2Reg((IRVar) rhs) != null) ? var2Reg((IRVar) rhs) : Register.rcx;
        load(rhs, reg2);
//        ans.append("\txor\trdx, rdx\n");
        ans.append("\tcqo\n\tidiv\t" + reg2.Reg32() + "\n");
        Register src = op.toNASM().equals("div") ? Register.rax : Register.rdx;
        store(dest, src);
    }

    private void shiftOperator(IRExpr dest, IRExpr lhs, IROperations.binaryOp op, IRExpr rhs) {
        load(lhs, Register.rax);
        load(rhs, Register.rcx);
        ans.append("\t" + op.toNASM() + "\trax, cl\n");
        store(dest, Register.rax);
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
            Register destination = Register.rax;
            boolean allocated = var2Reg((IRVar) dest) != null;
            if (allocated) destination = var2Reg((IRVar) dest);
            if (rhs instanceof IRVar) {
                load(rhs, destination);
                if (!allocated) store(dest, destination);
            }
            else if (rhs instanceof IRMem) {
                ans.append("\tmov\t" + destination + ", " + memAddr((IRMem) rhs) + "\n");
                if (!allocated) store(dest, Register.rax);
            }
            else if (rhs instanceof IRConst) {
                if (allocated) ans.append("\tmov\t" + destination + ", " + rhs + "\n");
                else ans.append("\tmov\t" + var2Str((IRVar) dest) + ", " + rhs + "\n");
            }
        }
        else {
            if (rhs instanceof IRVar) {
                if (var2Reg((IRVar) rhs) != null) {
                    ans.append("\tmov\t" + memAddr((IRMem) dest) + ", " + var2Reg((IRVar) rhs) + "\n");
                }
                else {
                    load(rhs, Register.rax);
                    ans.append("\tmov\t" + memAddr((IRMem) dest) + ", rax\n");
                }
            }
            else if (rhs instanceof IRMem) {
                ans.append("\tmov\trbx, " + memAddr((IRMem) rhs) + "\n");
                ans.append("\tmov\t" + memAddr((IRMem) dest) + ", rbx\n");
            }
            else if (rhs instanceof IRConst) {
                ans.append("\tmov\t" + memAddr((IRMem) dest) + ", " + rhs + "\n");
            }
        }

    }

    private void unaryOpeartor(IRExpr dest, IROperations.unaryOp op, IRExpr rhs) {
        load(rhs, Register.rax);
        ans.append("\t" + op.toNASM() + "\t" + Register.rax + "\n");
        store(dest, Register.rax);
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
        if (inst.getExpr() == null) {
            ans.append("\tjmp\t" + inst.getLable().getName() + "\n");
        }
        else {
            IRExpr expr = inst.getExpr();
            Register reg = (expr instanceof IRVar && (var2Reg((IRVar) expr)) != null) ? var2Reg((IRVar) expr) : Register.rbx;
            load(inst.getExpr(), reg);
            ans.append("\tcmp\t" + reg + ", 0\n");
            ans.append("\t" + inst.getOp().toNASM() + "\t" + inst.getLable().getName() + "\n");
        }
    }

    private void retOperator(IRRetInstruction inst) {
        if (inst.getVal() != null)
            load(inst.getVal(), Register.rax);
        ans.append("\tjmp\t" + endLable.getName() + "\n");
    }

    private void call(IRCallInstruction inst) {
        IRExpr[] args = inst.getArgs();
        Set<Register> usedRegs = inst.getFunc().getUsedReg();
        for (int i = 0; i < Register.registerNum(); i++) {
            if (!inst.getFunc().isExtend() && !usedRegs.contains(Register.alloc(i))) continue;
            ans.append("\tpush\t" + Register.alloc(i) + "\n");
        }
        for (int i = 0; i < args.length && i < 2; i++) {
            load(args[i], Register.getParm(i));
        }
        for (int i = args.length - 1; i >= 2; i--) {
            if (args[i] instanceof IRConst) {
                ans.append("\tpush\t" + args[i] + "\n");
            }
            else {
                ans.append("\tpush\t" + var2Str((IRVar) args[i]) + "\n");

            }
        }
        ans.append("\tcall\t" + inst.getFunc().getName() + "\n");
        if (args.length > 2)
            ans.append("\tadd\trsp, " + Integer.toString((args.length - 2) * 8) + "\n");
        for (int i = Register.registerNum() - 1; i >= 0; i--) {
            if (!inst.getFunc().isExtend() && !usedRegs.contains(Register.alloc(i))) continue;
            ans.append("\tpop\t" + Register.alloc(i) + "\n");
        }
        if (inst.getResult() != null) {
            store(inst.getResult(), Register.rax);
        }
    }

    public String generate() {
        ans.append("default rel\n" +
                   "\n" +
                   "global main\n" +
                   "\n" +
                   "extern malloc\n" +
                   "extern puts\n" +
                   "extern printf\n" +
                   "extern sprintf\n" +
                   "extern scanf\n" +
                   "extern strlen\n" +
                   "extern strcpy\n" +
                   "extern strncpy\n" +
                   "extern strcat\n" +
                   "extern getline\n" +
                   "extern stdin\n" +
                   "extern strcmp\n" +
                   "extern __stack_chk_fail\n" +
                   "extern __printf_chk\n" +
                   "extern memcpy\n" +
                   "extern __sprintf_chk\n" +
                   "\n" +
                   "\n" +
                   "SECTION .text   \n" +
                   "\n");
        for (IRFuncNode funcNode : program.getFuncs())
            analyzer.analyze(funcNode);
        ans.append(readFile("buildin.asm"));
        for (IRFuncNode funcNode : program.getFuncs()) {
            if (funcNode.getContainNodes().size() == 0) continue;
            funcNode.allocStack();
            allocor = funcNode.getAlloc();
            Integer size = funcNode.getContainNodes().size();
            endLable = funcNode.getContainNodes().get(size - 1).getLabel();
            boolean first = true;
            for (int i = 0; i < funcNode.getContainNodes().size(); i++) {
                IRBaseBlock baseBlock = funcNode.getContainNodes().elementAt(i);
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
                    else if (inst instanceof IRjumpInstruction) {
                        if (inst == baseBlock.getInstructions().elementAt(baseBlock.getInstructions().size() - 1)
                            && ((IRjumpInstruction) inst).getExpr() == null
                            && i < funcNode.getContainNodes().size() - 1
                            && ((IRjumpInstruction) inst).getBlock() == funcNode.getContainNodes().elementAt(i + 1))
                            continue;
                        jumpOperator((IRjumpInstruction) inst);
                    }
                    else if (inst instanceof IRRetInstruction) {
                        if (inst == baseBlock.getInstructions().elementAt(baseBlock.getInstructions().size() - 1)
                            && i == funcNode.getContainNodes().size() - 1)
                            continue;
                        retOperator((IRRetInstruction) inst);
                    }
                    else if (inst instanceof IRCallInstruction)
                        call((IRCallInstruction) inst);
                }
            }
            exitFunc();
        }
        ans.append("\n" +
                   "\n" +
                   "SECTION .data   \n");
        dataSection();
        ans.append("\n" +
                   "\n" +
                   "SECTION .bss    \n");
        Collection<IRVar> globe = IRVariables.getInstance().getGlobe();
        for (IRVar var : globe) {
            ans.append("\t" + var.getName() + ":\tresq\t1\n");
        }

        ans.append("\n\nSECTION .rodata    \n");
        ans.append(readFile("rodata.asm"));
        return ans.toString();
    }
}