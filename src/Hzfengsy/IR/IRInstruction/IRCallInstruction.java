package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.IRNode.*;

public class IRCallInstruction extends IRBaseInstruction
{
    private IRExpr result;
    private IRFuncNode func;
    private IRExpr[] args;

    public IRCallInstruction(IRExpr result, IRFuncNode function, IRExpr... args) {
        this.result = result;
        this.func = function;
        this.args = args;
    }

    @Override
    public void setResult(IRExpr result) {
        this.result = result;
    }

    @Override
    public IRExpr getResult() {
        return result;
    }

    private String exprToString() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0 ; i < args.length; i++)
        {
            if (i != 0) ans.append(", ");
            ans.append(args[i]);
        }
        return ans.toString();
    }

    @Override
    public String toString() {
        return (result == null ? "" : result + " = ") + "call " + func.getFuncName() + "(" + exprToString() + ")";
    }

    public IRExpr[] getArgs() {
        return args;
    }

    public IRFuncNode getFunc() {
        return func;
    }

    @Override
    public void analyze() {
        for (IRExpr expr : args) this.setUse(expr);
        this.setDef(result);
    }
}
