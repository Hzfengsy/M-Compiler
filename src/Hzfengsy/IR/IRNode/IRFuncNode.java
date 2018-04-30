package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRType.*;

public class IRFuncNode extends IRBaseNode
{
    String funcName;
    IRBaseType returnType;
    IRBaseType[] args;
    public IRVariables vars = new IRVariables();

    public IRFuncNode(String funcName, IRBaseType... args) {
        this.funcName = funcName;
        this.args = args;
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
