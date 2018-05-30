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
        for (IRBaseBlock baseBlock : funcNode.getContainNodes()) {
            Vector<IRBaseInstruction> blockInsts = baseBlock.getInstructions();
            instructions.addAll(blockInsts);
            for (int i = 0; i < blockInsts.size(); i++) {
                IRBaseInstruction inst = blockInsts.elementAt(i);
                if (inst instanceof IRjumpInstruction) {
                    IRjumpInstruction jmpInst = (IRjumpInstruction) inst;
                    if (jmpInst.getBlock().getInstructions().size() >0 )
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
        if (funcNode.getUsedVar().size() > 800) return;
        instructions.clear();
        getSucc(funcNode);
        for (IRVar var : funcNode.getUsedVar())
            graph.setVar(var);
        for (IRBaseInstruction instruction : instructions)
            instruction.analyze();
        boolean flag;
        do {
            flag = true;
            for (IRBaseInstruction instruction : instructions)
                flag &= instruction.update();
        } while (!flag);
        for (IRBaseInstruction instruction : instructions)
            instruction.setConflict(graph);
        graph.allocate();
    }


}
