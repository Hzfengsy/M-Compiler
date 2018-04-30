package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRType.*;

public class IRExprInstruction extends IRBaseInstruction
{
    private IRVar result;
    private IRExpr left, right;
    private IROperations.binaryOp operator;
    private IRBaseType type;

    public IRExprInstruction(IRVar result, IROperations.binaryOp op, IRBaseType type, IRExpr left, IRExpr right)
    {
        this.result = result; this.operator = op;
        this.type = type; this.left = left; this.right = right;
    }

    public IRVar getResult() { return result; }

    @Override
    public String toString() {
        return result + " = " + operator.name() + " " + type + " " + left + ", " + right;
    }
}
