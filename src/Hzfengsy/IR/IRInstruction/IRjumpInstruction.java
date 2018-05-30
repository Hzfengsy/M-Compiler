package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.*;
import Hzfengsy.IR.IRNode.*;

public class IRjumpInstruction extends IRBaseInstruction
{
    private IRExpr expr;
    private IRBaseBlock block;
    private IROperations.jmpOp op;

    public IRjumpInstruction(IRExpr expr, IROperations.jmpOp op, IRBaseBlock block) {
        this.expr = expr;
        this.block = block;
        this.op = op;
    }

    public IRjumpInstruction(IRBaseBlock block) {
        this.expr = null;
        this.block = block;
        this.op = IROperations.jmpOp.JMP;
    }

    @Override
    public String toString() {
        if (op == IROperations.jmpOp.JMP)
            return op + " " + block.getLabel();
        else
            return op + " " + expr + ", " + block.getLabel();
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

    public IROperations.jmpOp getOp() {
        return op;
    }

    public IRBaseBlock getBlock() {
        return block;
    }

    @Override
    public void analyze() {
        this.setUse(expr);
    }
}
