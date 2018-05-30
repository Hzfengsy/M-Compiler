package Hzfengsy.IR;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.IR.IRNode.*;

import java.util.*;

public class InlineOptim
{
    private IRProgNode progNode;
    private int threshold = 10;

    //    private Map<IRFuncNode, Set<IRFuncNode>> request;
    private Set<IRFuncNode> inlineable = new HashSet<>();
    private IRVariables variables = IRVariables.getInstance();

    public InlineOptim(IRProgNode progNode) {
        this.progNode = progNode;
    }

    private boolean InlineAble(IRFuncNode funcNode) {
        int insts = 0;
        if (funcNode.getName().equals("main")) return false;
        if (funcNode.getContainNodes().size() > 2) return false;
        for (IRBaseBlock block : funcNode.getContainNodes()) {
            for (IRBaseInstruction inst : block.getInstructions())
                if (inst instanceof IRCallInstruction) return false;
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

    private void setInline(IRFuncNode funcNode, IRBaseBlock block) {
        Vector<IRBaseInstruction> insts = block.getInstructions();
        for (int i = 0; i < insts.size(); i++) {
            IRBaseInstruction inst = block.getInstructions().elementAt(i);
            if (!(inst instanceof IRCallInstruction)) continue;
            IRCallInstruction call = (IRCallInstruction) inst;
            if (!inlineable.contains(call.getFunc())) continue;
            IRFuncNode func = call.getFunc();
            Map<IRVar, IRVar> varMap = new HashMap<>();
            for (IRVar var : func.getUsedVar()) {
                IRVar tempVar = variables.insertTempVar();
                varMap.put(var, tempVar);
                funcNode.addVar(tempVar);
            }

            IRBaseBlock newBlock = new IRBaseBlock();
            for (int j = 0; j < call.getArgs().length; j++) {
                newBlock.join(new IRUnaryExprInstruction(varMap.get(func.getArgs()[j]), IROperations.unaryOp.MOV, call.getArgs()[j]));
            }
            if (func.getContainNodes().size() == 0) continue;
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
            }
            insts.remove(i);
            for (int j = 0; j < newBlock.getInstructions().size(); j++) {
                insts.insertElementAt(newBlock.getInstructions().elementAt(j), i++);
            }

        }
    }


    public void optim() {
        for (IRFuncNode func : progNode.getFuncs()) {
            if (InlineAble(func)) inlineable.add(func);
        }
        for (IRFuncNode func : progNode.getFuncs()) {
            for (IRBaseBlock block : func.getContainNodes())
                setInline(func, block);
        }
        for (IRFuncNode func : inlineable) {
            progNode.getFuncs().remove(func);
        }
    }
}
