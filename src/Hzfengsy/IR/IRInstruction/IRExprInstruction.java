package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IROperation.*;

public class IRExprInstruction extends IRBaseInstruction
{
    IRVar result;
    IRBaseOp op;

    public IRExprInstruction(IRVar result, IRBaseOp op)
    {
        this.result = result; this.op = op;
    }

    public IRVar getResult() { return result; }

    @Override
    public String toString() {
        return result + " = " + op;
    }
}
