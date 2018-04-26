package Hzfengsy.Utility;

import Hzfengsy.Type.*;

public class IRFuncNode extends IRBaseNode
{
    public IRFuncNode(FuncType _type) { func = _type; }

    private FuncType func;

    public FuncType getFunc() { return func; }

    public BaseType getType() {
        return getFunc().getReturnType();
    }
}
