package Hzfengsy.utility;

import Hzfengsy.Type.baseType;

public class IRTypeNode extends IRBaseNode
{
    public IRTypeNode(baseType _type) { type = _type; }
    private baseType type;
    public baseType getType() { return type; }
}
