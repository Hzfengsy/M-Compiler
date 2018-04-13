package Hzfengsy.utility;

import Hzfengsy.Type.baseType;

public class IRFuncNode extends IRBaseNode
{
    public IRFuncNode(baseType _type) { type = _type; }
    private baseType type;
    public baseType getType() { return type; }
}
