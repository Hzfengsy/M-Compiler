package Hzfengsy.CodeGenerator;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.IR.IRNode.*;

import java.util.*;

public class ConstOptim
{
    private Map<IRVar, IRConst> constVar = new HashMap<>();
    private IRProgNode progNode;

    public ConstOptim(IRProgNode progNode) {
        this.progNode = progNode;
    }

    private IRExpr toConst(IRExpr expr) {
        if (expr instanceof IRVar && constVar.containsKey(expr)) {
            return constVar.get(expr);
        }
        else return expr;
    }

    public void optim() {
        for (IRFuncNode funcNode : progNode.getFuncs())
            for (IRBasicBlock block : funcNode.getContainBlocks()) {
                constVar.clear();

                for (int i = 0; i < block.getInstructions().size(); i++) {
                    IRBaseInstruction inst = block.getInstructions().elementAt(i);
                    if (inst instanceof IRUnaryExprInstruction) {
                        IRUnaryExprInstruction instruction = (IRUnaryExprInstruction) inst;
                        IRExpr expr = toConst(instruction.getRight());
                        if (expr instanceof IRConst) {
                            IRConst exprConst = (IRConst) expr;
                            IRConst temp = null;
                            switch (instruction.getOperator()) {
                                case MOV:
                                    temp = exprConst;
                                    break;
                                case NOT:
                                    temp = new IRConst(~exprConst.getValue());
                                    break;
                                case LNOT:
                                    temp = new IRConst(exprConst.getValue() == 1 ? 0 : 1);
                                    break;
                                case NEG:
                                    temp = new IRConst(-exprConst.getValue());
                                    break;
                            }
                            if (inst.getResult() instanceof IRVar)
                                constVar.put((IRVar) inst.getResult(), temp);
                            block.getInstructions().setElementAt(new IRUnaryExprInstruction(inst.getResult(), IROperations.unaryOp.MOV, temp), i);
                        }
                        else constVar.remove(instruction.getResult());
                    }
                    else if (inst instanceof IRBinaryExprInstruction) {
                        IRBinaryExprInstruction instruction = (IRBinaryExprInstruction) inst;
                        IRExpr expr_1 = toConst(instruction.getLeft());
                        IRExpr expr_2 = toConst(instruction.getRight());
                        if (expr_1 instanceof IRConst && expr_2 instanceof IRConst) {
                            IRConst exprConst_1 = (IRConst) expr_1;
                            IRConst exprConst_2 = (IRConst) expr_2;
                            IRConst temp = null;
                            switch (instruction.getOperator()) {
                                case ADD:
                                    temp = new IRConst(exprConst_1.getValue() + exprConst_2.getValue());
                                    break;
                                case SUB:
                                    temp = new IRConst(exprConst_1.getValue() - exprConst_2.getValue());
                                    break;
                                case MUL:
                                    temp = new IRConst(exprConst_1.getValue() * exprConst_2.getValue());
                                    break;
                                case DIV:
                                    if (exprConst_2.getValue() == 0) continue;
                                    temp = new IRConst(exprConst_1.getValue() / exprConst_2.getValue());
                                    break;
                                case MOD:
                                    if (exprConst_2.getValue() == 0) continue;
                                    temp = new IRConst(exprConst_1.getValue() % exprConst_2.getValue());
                                    break;
                                case LSHIFT:
                                    temp = new IRConst(exprConst_1.getValue() << exprConst_2.getValue());
                                    break;
                                case RSHIFT:
                                    temp = new IRConst(exprConst_1.getValue() >> exprConst_2.getValue());
                                    break;
                                case XOR:
                                    temp = new IRConst(exprConst_1.getValue() ^ exprConst_2.getValue());
                                    break;
                                case OR:
                                    temp = new IRConst(exprConst_1.getValue() | exprConst_2.getValue());
                                    break;
                                case AND:
                                    temp = new IRConst(exprConst_1.getValue() & exprConst_2.getValue());
                                    break;
                                default:
                                    continue;
                            }
                            if (inst.getResult() instanceof IRVar)
                                constVar.put((IRVar) inst.getResult(), temp);
                            block.getInstructions().setElementAt(new IRUnaryExprInstruction(inst.getResult(), IROperations.unaryOp.MOV, temp), i);
                        }
                        else constVar.remove(instruction.getResult());
                    }
                    else if (inst.getResult() != null) constVar.remove(inst.getResult());
                }
            }
    }
}
