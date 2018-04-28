package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.*;
import Hzfengsy.Type.*;
import Hzfengsy.Type.VarType.*;

public class IRFuncNode extends IRBaseNode
{
    public IRVariables IRVars = new IRVariables();
    public IRFuncNode(FuncType _type) { func = _type; }

    private FuncType func;

    public FuncType getFunc() { return func; }

    public BaseType getType() {
        return getFunc().getReturnType();
    }
}
