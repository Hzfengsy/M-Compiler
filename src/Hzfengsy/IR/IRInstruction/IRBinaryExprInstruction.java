package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.IRExpr.*;

public class IRBinaryExprInstruction extends IRBaseInstruction
{
    private IRExpr result;
    private IRExpr left, right;
    private IROperations.binaryOp operator;

    public IRBinaryExprInstruction(IRExpr result, IROperations.binaryOp op, IRExpr left, IRExpr right) {
        this.result = result;
        this.operator = op;
        this.left = left;
        this.right = right;
    }

    public IRExpr getResult() {
        return result;
    }

    @Override
    public void setResult(IRExpr result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return result + " = " + operator.name() + " " + left + ", " + right;
    }

    public IROperations.binaryOp getOperator() {
        return operator;
    }

    public IRExpr getLeft(){
        return left;
    }

    public IRExpr getRight() {
        return right;
    }

    @Override
    public void analyze() {
        this.setUse(left);
        this.setUse(right);
        this.setDef(result);
    }
}
