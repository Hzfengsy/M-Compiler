package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.Type.VarType.*;

public class IRExprNode extends IRBaseNode
{
    public IRVar result;
    private BaseType type;
    private Boolean left;

    public IRExprNode(BaseType type, Boolean left, IRExprInstruction inst, IRBaseNode...nodes)
    {
        for (IRBaseNode node : nodes) join(node);
        join(inst);
        result = inst.getResult();
        nextNode = null;
        this.type = type;
        this.left = left;
    }

    public IRExprNode(BaseType _type, Boolean _left) {
        type = _type;
        left = _left;
    }

    public BaseType getType() { return type; }

    public boolean isLeft() {
        return left;
    }
}
