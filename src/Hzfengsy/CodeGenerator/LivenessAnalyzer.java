package Hzfengsy.CodeGenerator;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.IR.IRNode.*;

import java.util.*;

public class LivenessAnalyzer
{
    private Vector<IRBaseInstruction> instructions = new Vector<>();
    private ConflictGraph graph = new ConflictGraph();

    private void getSucc(IRFuncNode funcNode) {
        for (IRBasicBlock baseBlock : funcNode.getContainBlocks()) {
            Vector<IRBaseInstruction> blockInsts = baseBlock.getInstructions();
            instructions.addAll(blockInsts);
            for (int i = 0; i < blockInsts.size(); i++) {
                IRBaseInstruction inst = blockInsts.elementAt(i);
                if (inst instanceof IRjumpInstruction) {
                    IRjumpInstruction jmpInst = (IRjumpInstruction) inst;
                    if (jmpInst.getBlock().getInstructions().size() > 0)
                        inst.succ.add(jmpInst.getBlock().getInstructions().elementAt(0));
                    if (jmpInst.getOp() != IROperations.jmpOp.JMP) {
                        if (i < blockInsts.size() - 1)
                            inst.succ.add(blockInsts.elementAt(i + 1));
                    }
                }
                else if (!(inst instanceof IRRetInstruction)) {
                    if (i < blockInsts.size() - 1)
                        inst.succ.add(blockInsts.elementAt(i + 1));
                }
            }
        }

    }

    public void analyze(IRFuncNode funcNode) {
        if (funcNode.isExtend()) return;
        if (funcNode.getUsedVar().size() > 800) return;
        instructions.clear();
        getSucc(funcNode);
        for (IRVar var : funcNode.getUsedVar())
            graph.setVar(var);
        for (IRBaseInstruction instruction : instructions) {
            instruction.analyze();
            instruction.useInst();
        }
        boolean flag;
        do {
            flag = true;
            for (int i = instructions.size() - 1; i >= 0; i--)
                flag &= instructions.elementAt(i).update();
        } while (!flag);
        for (IRBasicBlock block : funcNode.getContainBlocks()) {
            Vector<IRBaseInstruction> insts = block.getInstructions();
            for (int i = 0; i < insts.size(); i++) {
                IRBaseInstruction inst = insts.elementAt(i);
                inst.updateLive();
                inst.setConflict(graph);
            }
        }
        graph.allocate();


        if (funcNode.getUsedVar().size() > 300) return;
        for (IRBaseInstruction instruction : instructions) {
            instruction.clear();
            instruction.analyze();
        }

        do {
            flag = true;
            for (int i = instructions.size() - 1; i >= 0; i--)
                flag &= instructions.elementAt(i).update();
        } while (!flag);
        for (IRSuperBlock superBlock : funcNode.getSuperBlocks())
            if (!superBlock.analyze())
                for (IRBasicBlock block : superBlock.getBlocks())
                    funcNode.getContainBlocks().remove(block);

        for (IRBasicBlock block : funcNode.getContainBlocks()) {
            Vector<IRBaseInstruction> insts = block.getInstructions();
            for (int i = 0; i < insts.size(); i++) {
                IRBaseInstruction inst = insts.elementAt(i);
                if (!inst.isUsed()) {
                    block.getInstructions().remove(inst);
                    i--;
                }
            }
        }
    }


}
