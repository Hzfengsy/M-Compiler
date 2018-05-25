package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.IRExpr.*;

public abstract class IRBaseInstruction
{
    public abstract IRExpr getResult();
    public abstract void setResult(IRExpr result);
}
