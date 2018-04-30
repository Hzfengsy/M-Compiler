package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.Semantic.Type.*;
import Hzfengsy.Semantic.Type.VarType.*;

import java.util.*;

public class IRBaseNode extends IRBase
{
    IRBaseNode nextNode;
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
}
