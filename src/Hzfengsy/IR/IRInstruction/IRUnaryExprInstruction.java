package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.IRExpr.*;

public class IRUnaryExprInstruction extends IRBaseInstruction
{
    private IRExpr result;
    private IRExpr right;
    private IROperations.unaryOp operator;

    public IRUnaryExprInstruction(IRExpr result, IROperations.unaryOp op, IRExpr right) {
        this.result = result;
        this.operator = op;
        this.right = right;
    }

    @Override
    public void setResult(IRExpr result) {
        this.result = result;
    }

    public IRExpr getResult() {
        return result;
    }

    @Override
    public String toString() {
        return result + " = " + operator.name() + " " + right;
    }

    public IROperations.unaryOp getOperator() {
        return operator;
    }

    public IRExpr getRight() {
        return right;
    }

    @Override
    public void analyze() {
        this.setDef(result);
        if (result instanceof IRMem || (result instanceof IRVar && ((IRVar) result).isGlobe())) {
            useInst();
        }
    }

    @Override
    public void useInst() {
        if (!this.used) {
            this.setUse(right);
            this.used = true;
        }
    }
}
