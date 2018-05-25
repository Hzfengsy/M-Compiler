package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.*;
import Hzfengsy.IR.IRNode.*;

public class IRjumpInstruction extends IRBaseInstruction
{
    private IRExpr expr;
    private IRBaseBlock block;

    public IRjumpInstruction(IRExpr expr, IRBaseBlock block) {
        this.expr = expr;
        this.block = block;
    }

    @Override
    public String toString() {
        return "jmp " + expr + ", " + block.getLabel();
    }

    @Override
    public void setResult(IRExpr result) {
    }

    @Override
    public IRVar getResult() {
        return null;
    }

    public IRExpr getExpr() {
        return expr;
    }

    public IRLable getLable() {
        return block.getLabel();
    }
}
