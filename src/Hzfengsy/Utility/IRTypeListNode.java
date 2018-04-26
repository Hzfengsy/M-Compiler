package Hzfengsy.Utility;

import Hzfengsy.Type.*;

import java.util.*;

public class IRTypeListNode extends IRBaseNode
{
    public IRTypeListNode(Vector<BaseType> _type) { type = _type; }

    private Vector<BaseType> type;

    public Vector<BaseType> getTypeList() { return type; }
}
