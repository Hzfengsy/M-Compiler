package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRType.*;

public class IRLoadInstruction extends IRBaseInstruction
{
    private IRVar result;
    private IRExpr addr;
    private IRBaseType type, addrType;
    private Integer align;

    public IRLoadInstruction(IRVar result, IRBaseType type, IRExpr addr, Integer align)
    {
        this.result = result;
        this.type = type; this.addr = addr;
        this.align = align;
    }

    public IRVar getResult() { return result; }

    @Override
    public String toString() {
        return result + " = load " + type + ", " + addrType + " " + addr + ", align" + align;
    }
}
