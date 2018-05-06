package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.Semantic.Type.*;
import Hzfengsy.Semantic.Type.VarType.*;

import java.util.*;

public class IRBaseNode extends IRBase
{
    Vector<IRBaseNode> nextNode = new Vector<>();
    List<IRBaseInstruction> instructions = new LinkedList<>();

    public void join(IRBaseNode node) { instructions.addAll(node.instructions); }

    public void join(IRBaseInstruction inst) { instructions.add(inst); }

    public BaseType getType() {
        assert false;
        return null;
    }

    public Vector<BaseType> getTypeList() {
        return null;
    }

    public FuncType getFunc() { return null;}

    public boolean isLeft() {
        return false;
    }

    String instsToString() {
        StringBuilder ans = new StringBuilder();
        for (IRBaseInstruction inst : instructions) {
            ans.append(inst.toString());
            ans.append('\n');
        }
        return ans.toString();
    }

    public void appendNextNode(IRBaseNode nextNode) {
        this.nextNode.add(nextNode);
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        ans.append(instsToString());
        for (IRBaseNode node : nextNode) {
            ans.append(node);
        }
        return ans.toString();
    }
}
