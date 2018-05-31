package Hzfengsy.IR;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.IR.IRNode.*;

import java.util.*;

public class InlineOptim
{
    private IRProgNode progNode;
    private int threshold = 100;

    //    private Map<IRFuncNode, Set<IRFuncNode>> request;
    private Set<IRFuncNode> inlineable = new HashSet<>();
    private IRVariables variables = IRVariables.getInstance();

    public InlineOptim(IRProgNode progNode) {
        this.progNode = progNode;
    }

    private boolean InlineAble(IRFuncNode funcNode) {
        int insts = 0;
        if (funcNode.isExtend()) return false;
        if (funcNode.getName().equals("main")) return false;
        if (funcNode.getContainNodes().size() > 2) return false;
        for (IRBaseBlock block : funcNode.getContainNodes()) {
//            for (IRBaseInstruction inst : block.getInstructions())
//                if (inst instanceof IRCallInstruction) return false;
            insts += block.getInstructions().size();
        }
        if (insts < threshold) return true;
        return false;
    }

    private IRExpr get(Map<IRVar, IRVar> varMap, IRExpr expr) {
        if (expr instanceof IRConst)
            return expr;
        else if (expr instanceof IRVar) {
            if (((IRVar) expr).isGlobe()) return expr;
            else return varMap.get(expr);
        }
        else {
            IRMem mem = (IRMem) expr;
            return new IRMem(get(varMap, mem.getAddr()), get(varMap, mem.getOffset()));
        }

    }

    private boolean setInline(IRFuncNode funcNode, IRBaseBlock block) {
        Vector<IRBaseInstruction> insts = block.getInstructions();
        boolean flag = false;
        for (int i = 0; i < insts.size(); i++) {
            IRBaseInstruction inst = block.getInstructions().elementAt(i);
            if (!(inst instanceof IRCallInstruction)) continue;
            IRCallInstruction call = (IRCallInstruction) inst;
            if (!inlineable.contains(call.getFunc())) continue;
            IRFuncNode func = call.getFunc();
            if (func.getContainNodes().size() == 0) continue;
            Map<IRVar, IRVar> varMap = new HashMap<>();
            for (IRVar var : func.getUsedVar()) {
                IRVar tempVar = variables.insertTempVar();
                varMap.put(var, tempVar);
                funcNode.addVar(tempVar);
            }
            if (funcNode == call.getFunc()) continue;;

            flag = true;
            IRBaseBlock newBlock = new IRBaseBlock();
            for (int j = 0; j < call.getArgs().length; j++) {
                newBlock.join(new IRUnaryExprInstruction(varMap.get(func.getArgs()[j]), IROperations.unaryOp.MOV, call.getArgs()[j]));
            }
            for (IRBaseInstruction funcInst: func.getContainNodes().elementAt(0).getInstructions()) {
                if (funcInst instanceof IRRetInstruction) {
                    IRRetInstruction instruction = (IRRetInstruction) funcInst;
                    newBlock.join(new IRUnaryExprInstruction(call.getResult(), IROperations.unaryOp.MOV, get(varMap, instruction.getVal())));
                }
                else if (funcInst instanceof IRBinaryExprInstruction){
                    IRBinaryExprInstruction instruction = (IRBinaryExprInstruction) funcInst;
                    newBlock.join(new IRBinaryExprInstruction(get(varMap, instruction.getResult()), instruction.getOperator(), get(varMap, instruction.getLeft()), get(varMap, instruction.getRight())));
                }
                else if (funcInst instanceof IRUnaryExprInstruction) {
                    IRUnaryExprInstruction instruction = (IRUnaryExprInstruction) funcInst;
                    newBlock.join(new IRUnaryExprInstruction(get(varMap, instruction.getResult()), instruction.getOperator(), get(varMap, instruction.getRight())));
                }
                else if (funcInst instanceof IRCallInstruction) {
                    IRCallInstruction instruction = (IRCallInstruction) funcInst;
                    IRExpr[] args = new IRExpr[instruction.getArgs().length];
                    for (int k = 0; k < instruction.getArgs().length; k++)
                        args[k] = get(varMap, instruction.getArgs()[k]);
                    newBlock.join(new IRCallInstruction(get(varMap, instruction.getResult()), instruction.getFunc(), args));
                }
            }
            insts.remove(i);
            for (int j = 0; j < newBlock.getInstructions().size(); j++) {
                insts.insertElementAt(newBlock.getInstructions().elementAt(j), i++);
            }
            i--;
        }
        return flag;
    }


    public void optim() {
        for (IRFuncNode func : progNode.getFuncs()) {
            if (InlineAble(func)) inlineable.add(func);
        }
        if (inlineable.size() == 0) return;
        boolean flag;
        do {
            flag = false;
            for (IRFuncNode func : progNode.getFuncs()) {
                for (IRBaseBlock block : func.getContainNodes())
                    flag |= setInline(func, block);
            }
        } while (flag);
        for (IRFuncNode func : inlineable) {
            progNode.getFuncs().remove(func);
        }
    }
}
