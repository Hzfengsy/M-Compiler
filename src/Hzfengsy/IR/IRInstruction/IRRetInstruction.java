package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.IRExpr.*;

public class IRRetInstruction extends IRBaseInstruction
{
    private IRExpr right;

    public IRRetInstruction(IRExpr right) {
        this.right = right;
    }

    @Override
    public void setResult(IRExpr result) {
    }

    @Override
    public IRVar getResult() {
        return null;
    }

    @Override
    public String toString() {
        return "ret " + right;
    }

    public IRExpr getVal() {
        return right;
    }

    @Override
    public void analyze() {
        useInst();
    }

    @Override
    public void useInst() {
        if (!this.used) {
            this.setUse(right);
            this.used = true;
        }
    }
}
