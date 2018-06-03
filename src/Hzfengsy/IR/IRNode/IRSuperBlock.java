package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.IRInstruction.*;

import java.util.*;

public class IRSuperBlock
{
    private Vector<IRBasicBlock> blocks = new Vector<>();
    private IRBasicBlock head;
    private IRBasicBlock tail;

    public IRSuperBlock(IRBasicBlock head, IRBasicBlock tail) {
        this.head = head;
        this.tail = tail;
    }

    public void appendBlock(IRBasicBlock block) {
        blocks.add(block);
    }

    public Vector<IRBasicBlock> getBlocks() {
        return blocks;
    }

    public boolean analyze() {
        Set<IRVar> out = tail.instructions.size() == 0 ? new HashSet<>() : tail.instructions.elementAt(0).getIn();
        boolean flag = false;
        for (IRBasicBlock block : blocks) {
            for (IRBaseInstruction inst : block.getInstructions()) {
                if (inst instanceof IRCallInstruction || inst instanceof IRRetInstruction) {
                    flag = true;
                    break;
                }
                if ((inst.getResult() instanceof IRVar && ((IRVar)inst.getResult()).isGlobe()) || inst.getResult() instanceof IRMem ||out.contains(inst.getDef())) {
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        if (!flag) head.linkTo(tail);
        return flag;
    }
}
