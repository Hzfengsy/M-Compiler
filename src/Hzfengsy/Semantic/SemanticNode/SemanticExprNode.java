package Hzfengsy.Semantic.SemanticNode;

import Hzfengsy.Semantic.Type.VarType.*;

public class SemanticExprNode extends SemanticBaseNode
{
    private BaseType type;
    private Boolean left;

    public SemanticExprNode(BaseType _type, Boolean _left) {
        type = _type;
        left = _left;
    }

    public BaseType getType() { return type; }

    public boolean isLeft() {
        return left;
    }
}
