package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.IRExpr.*;

public class IRAllocaInstruction extends IRBaseInstruction
{
    private IRExpr result;
    private Integer align;
    public IRAllocaInstruction(IRExpr result, Integer align) {
        this.result = result;
        this.align = align;
    }

    @Override
    public void setResult(IRExpr result) {
        this.result = result;
    }


    @Override
    public IRExpr getResult() {
        return result;
    }

    @Override
    public String toString() {
        return result + " = alloca align " + align;
    }
}
