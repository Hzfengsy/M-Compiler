package Hzfengsy.Semantic.SemanticNode;

import Hzfengsy.Semantic.Type.VarType.*;

import java.util.*;

public class SemanticTypeListNode extends SemanticBaseNode
{
    public SemanticTypeListNode(Vector<BaseType> type, Vector<String> name) {
        this.type = type;
        this.name = name;
    }

    public SemanticTypeListNode(Vector<BaseType> type) {
        this.type = type;
        this.name = null;
    }

    private Vector<BaseType> type;
    private Vector<String> name;

    public Vector<BaseType> getTypeList() {
        return type;
    }

    public Vector<String> getName() {
        return name;
    }
}
