package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.*;
import Hzfengsy.IR.IRNode.*;

public class IRjumpInstruction extends IRBaseInstruction
{
    private IRExpr expr;
    private IRBasicBlock block;
    private IROperations.jmpOp op;

    public IRjumpInstruction(IRExpr expr, IROperations.jmpOp op, IRBasicBlock block) {
        this.expr = expr;
        this.block = block;
        this.op = op;
    }

    public IRjumpInstruction(IRBasicBlock block) {
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
        return block.updateLink().getLabel();
    }

    public IROperations.jmpOp getOp() {
        return op;
    }

    public IRBasicBlock getBlock() {
        return block;
    }

    @Override
    public void analyze() {
        useInst();
    }

    @Override
    public void useInst() {
        if (!this.used) {
            this.setUse(expr);
            this.used = true;
        }
    }
}
