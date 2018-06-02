package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.IRInstruction.*;

import java.util.*;

public class IRBasicBlock extends IRBase
{
    protected Vector<IRBaseInstruction> instructions = new Vector<>();
    private IRExpr result;
    private IRLable label = null;
    private Vector<IRBasicBlock> nextNodes = new Vector<>();
    private IRBasicBlock linkTo = this;

    private void updateResult() {
        if (instructions.isEmpty()) return;
        result = instructions.get(instructions.size() - 1).getResult();
    }

    public void appendNext(IRBasicBlock node) {
        nextNodes.add(node);
    }

    public IRBasicBlock() {}

    public IRBasicBlock(IRBasicBlock node) {
        instructions.addAll(node.getInstructions());
        updateResult();
    }

    public IRBasicBlock(IRBaseInstruction... instructions) {
        for (IRBaseInstruction inst : instructions)
            this.instructions.add(inst);
        updateResult();
    }

    public void join(IRBasicBlock node) {
        if (node == null) return;
        instructions.addAll(node.instructions);
        updateResult();
    }

    public void join(IRBaseInstruction inst) {
        instructions.add(inst);
        updateResult();
    }

    private String instToString() {
        StringBuilder ans = new StringBuilder();
        for (IRBaseInstruction inst : instructions) {
            ans.append(inst.toString());
            ans.append('\n');
        }
        return ans.toString();
    }

    public void setLabel(IRLable label) {
        this.label = label;
    }

    public IRLable getLabel() {
        return label;
    }

    @Override
    public String toString() {
        String ans = instToString();
        if (this.label != null) ans = "\n" + this.label.toString() + ":\n" + ans;
        return ans;
    }

    public Vector<IRBaseInstruction> getInstructions() {
        return instructions;
    }

    public IRExpr getResult() {
        return result;
    }

    public void setResult(IRExpr result) {
        this.result = result;
    }

    public boolean AssignValid() {
        return result == instructions.get(instructions.size() - 1).getResult();
    }

    public void setLastInstResult(IRExpr result) {
        instructions.get(instructions.size() - 1).setResult(result);
        setResult(result);
    }

    public IRBasicBlock updateLink() {
        if (linkTo == null && instructions.size() == 1) {
            IRBaseInstruction inst = instructions.elementAt(0);
            if (inst instanceof IRjumpInstruction && ((IRjumpInstruction) inst).getExpr() == null)
                linkTo = ((IRjumpInstruction) inst).getBlock();
        }
        if (linkTo == this) return this;
        return linkTo = linkTo.updateLink();
    }

    public void linkTo(IRBasicBlock block) {
        linkTo = block;
    }

}
