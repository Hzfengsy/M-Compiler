package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.Semantic.Type.VarType.*;

import java.util.*;

public class IRBaseBlock extends IRBase
{
    protected List<IRBaseInstruction> instructions = new LinkedList<>();
    private IRExpr result;
    private IRLable label = null;
    private Vector<IRBaseBlock> nextNodes = new Vector<>();

    private void updateResult() {
        if (instructions.isEmpty()) return;
        result = instructions.get(instructions.size() - 1).getResult();
    }

    public void appendNext(IRBaseBlock node) {
        nextNodes.add(node);
    }

    public IRBaseBlock() {}

    public IRBaseBlock(IRBaseBlock node) {
        instructions.addAll(node.getInstructions());
        updateResult();
    }

    public IRBaseBlock(IRBaseInstruction... instructions) {
        for (IRBaseInstruction inst : instructions)
            this.instructions.add(inst);
        updateResult();
    }

    public void join(IRBaseBlock node) {
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

    public List<IRBaseInstruction> getInstructions() {
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
}