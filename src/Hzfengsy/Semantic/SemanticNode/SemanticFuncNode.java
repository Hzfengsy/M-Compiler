package Hzfengsy.Semantic.SemanticNode;

import Hzfengsy.Semantic.Type.*;
import Hzfengsy.Semantic.Type.VarType.*;

public class SemanticFuncNode extends SemanticBaseNode
{
    public SemanticFuncNode(FuncType _type) { func = _type; }

    private FuncType func;

    public FuncType getFunc() { return func; }

    public BaseType getType() {
        return getFunc().getReturnType();
    }
}
