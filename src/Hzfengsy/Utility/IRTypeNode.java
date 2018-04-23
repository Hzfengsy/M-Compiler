package Hzfengsy.Utility;

import Hzfengsy.Type.BaseType;

public class IRTypeNode extends IRBaseNode
{
    public IRTypeNode(BaseType _type, boolean _left) { type = _type;  left = _left;}
    private BaseType type;
    private boolean left;
    public BaseType getType() { return type; }

    public boolean isLeft()
    {
        return left;
    }
}
