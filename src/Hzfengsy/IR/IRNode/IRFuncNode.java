package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRType.*;

public class IRFuncNode extends IRBaseNode
{
    private String funcName;
    private IRBaseType returnType;
    private IRBaseType[] args;
    private IRVariables vars = new IRVariables();

    public IRFuncNode(IRBaseType returnType, String funcName, IRBaseType... args) {
        this.returnType = returnType;
        this.funcName = funcName;
        this.args = args;
    }

    public IRVariables getVars() {
        return vars;
    }

    public String getFuncName() {
        return "@" + funcName;
    }

    private String argsToString() {
        String ans = new String();
        for (IRBaseType arg : this.args)
            ans += args.toString();
        return ans;
    }


    @Override
    public String toString() {
        return "define " + returnType + getFuncName() + "(" + argsToString() + ") {\n" + ((IRBaseNode) this).toString() + "\n}";
    }
}
