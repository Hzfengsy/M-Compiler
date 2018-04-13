package Hzfengsy.utility;

import Hzfengsy.Type.baseType;

import java.util.Vector;

public class IRTypeListNode extends IRBaseNode
{
    public IRTypeListNode(Vector<baseType> _type) { type = _type; }
    private Vector<baseType>  type;
    public Vector<baseType> getTypeList() { return type; }
}
