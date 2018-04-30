package Hzfengsy.Semantic.SemanticNode;

import Hzfengsy.Semantic.Type.VarType.*;

import java.util.*;

public class SemanticTypeListNode extends SemanticBaseNode
{
    public SemanticTypeListNode(Vector<BaseType> _type) { type = _type; }

    private Vector<BaseType> type;

    public Vector<BaseType> getTypeList() { return type; }
}
