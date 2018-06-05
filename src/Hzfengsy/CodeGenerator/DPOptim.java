package Hzfengsy.CodeGenerator;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.*;
import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.IR.IRNode.*;

import java.util.*;

public class DPOptim
{
    private IRProgNode progNode;
    private final int Size = 300;
    private Map<IRFuncNode, IRVar> dpVar = new HashMap<>();

    public DPOptim(IRProgNode progNode) {
        this.progNode = progNode;
    }

    private boolean getValid(IRFuncNode funcNode) {
        if (funcNode.getArgs().length != 1) return false;
        boolean recursion = false;
        for (IRBasicBlock block : funcNode.getContainBlocks()) {
            for (IRBaseInstruction inst : block.getInstructions())
                if (inst instanceof IRCallInstruction) {
                    IRCallInstruction call = (IRCallInstruction) inst;
                    if (call.getFunc() != funcNode) return false;
                    else recursion = true;
                }
        }
        return funcNode.dp = recursion;
    }

    private void updateFunc(IRFuncNode funcNode) {
        IRVar index = funcNode.getArgs()[0];
        IRVar dpVar = this.dpVar.get(funcNode);
        Set<IRBasicBlock> basicBlocks = new HashSet<>();

        for (int k = 0 ; k < funcNode.getContainBlocks().size(); k++) {
            IRBasicBlock block = funcNode.getContainBlocks().elementAt(k);
            if (basicBlocks.contains(block)) continue;
            Vector<IRBaseInstruction> instructions = block.getInstructions();
            for (int i  = 0 ; i < instructions.size(); i++) {
                IRBaseInstruction inst = instructions.elementAt(i);
                if (inst instanceof IRRetInstruction) {
                    IRExpr expr = ((IRRetInstruction)inst).getVal();
                    IRBasicBlock newBlock = new IRBasicBlock();
                    while (i < instructions.size()) {
                        newBlock.join(instructions.elementAt(i));
                        instructions.remove(i);
                    }
                    funcNode.getContainBlocks().insertElementAt(newBlock, ++k);
                    newBlock.setLabel(IRLabelList.getInstance().insertTemp());
                    IRVar result = IRVariables.getInstance().insertTempVar();
                    funcNode.addVar(result);
                    block.join(new IRBinaryExprInstruction(result, IROperations.binaryOp.LT, index, new IRConst(0)));
                    block.join(new IRjumpInstruction(result, IROperations.jmpOp.JNZ, newBlock));
                    result = IRVariables.getInstance().insertTempVar();
                    funcNode.addVar(result);
                    block.join(new IRBinaryExprInstruction(result, IROperations.binaryOp.GE, index, new IRConst(Size)));
                    block.join(new IRjumpInstruction(result, IROperations.jmpOp.JNZ, newBlock));
                    block.join(new IRUnaryExprInstruction(new IRMem(dpVar, index), IROperations.unaryOp.MOV, expr));
                    block.join(new IRjumpInstruction(newBlock));
                }
            }
        }


        IRBasicBlock newBlock = new IRBasicBlock();
        IRBasicBlock start = funcNode.getContainBlocks().elementAt(0);
        start.setLabel(IRLabelList.getInstance().insert("_main_" + funcNode.getName()));
        IRVar result = IRVariables.getInstance().insertTempVar();
        funcNode.addVar(result);
        newBlock.join(new IRBinaryExprInstruction(result, IROperations.binaryOp.LT, index, new IRConst(0)));
        newBlock.join(new IRjumpInstruction(result, IROperations.jmpOp.JNZ, start));
        result = IRVariables.getInstance().insertTempVar();
        funcNode.addVar(result);
        newBlock.join(new IRBinaryExprInstruction(result, IROperations.binaryOp.GE, index, new IRConst(Size)));
        newBlock.join(new IRjumpInstruction(result, IROperations.jmpOp.JNZ, start));
        result = IRVariables.getInstance().insertTempVar();
        funcNode.addVar(result);
        newBlock.join(new IRUnaryExprInstruction(result, IROperations.unaryOp.MOV, new IRMem(dpVar, index)));
        newBlock.join(new IRjumpInstruction(result, IROperations.jmpOp.JZ, start));
        newBlock.join(new IRRetInstruction(result));
        newBlock.join(new IRjumpInstruction(start));
        newBlock.setLabel(IRLabelList.getInstance().insert(funcNode.getName()));
        funcNode.getContainBlocks().insertElementAt(newBlock, 0);
    }

    public void optim() {
        IRBasicBlock newBlock = new IRBasicBlock();
        newBlock.setLabel(IRLabelList.getInstance().insert("main"));
        for (IRFuncNode func : progNode.getFuncs()) {
            if (!getValid(func)) continue;
            IRVar var = IRVariables.getInstance().insertVar("_dp_" + func.getName(), true);
            dpVar.put(func, var);
            newBlock.join(new IRCallInstruction(var, IRGenerator.funcNodeMap.get("malloc"), new IRConst(Size * 8)));
            updateFunc(func);
        }

        IRFuncNode mainFunc = IRGenerator.funcNodeMap.get("main");
        newBlock.join(mainFunc.getContainBlocks().elementAt(0));
        mainFunc.getContainBlocks().setElementAt(newBlock, 0);
    }
}
