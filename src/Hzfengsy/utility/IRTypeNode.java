package Hzfengsy.utility;

import Hzfengsy.Type.baseType;

public class IRTypeNode extends IRBaseNode
{
    public IRTypeNode(baseType _type, boolean _left) { type = _type;  left = _left;}
    private baseType type;
    private boolean left;
    public baseType getType() { return type; }

    public boolean isLeft()
    {
        return left;
    }
}
