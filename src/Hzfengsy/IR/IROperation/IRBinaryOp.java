package Hzfengsy.IR.IROperation;

import Hzfengsy.IR.*;

public class IRBinaryOp extends IRBaseOp
{
    public enum BinaryOp {
        ADD, SUB, MUL, DIV, MOD,
        LSHIFT, RSHIFT, LT, GT, LE, GE, EQ, NE,
        AND, XOR, OR,
    }
    private IRExpr left, right;
    private BinaryOp operator;

    public IRBinaryOp(IRExpr left, BinaryOp operator, IRExpr right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator.name() + " " +  left + " " + right;
    }
}
