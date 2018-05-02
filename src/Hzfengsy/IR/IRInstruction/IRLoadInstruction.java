package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRType.*;

public class IRLoadInstruction extends IRBaseInstruction
{
    private IRVar result;
    private IRVar address;
    private IRBaseType type, addressType;
    private Integer align;

    public IRLoadInstruction(IRVar result, IRBaseType type, IRVar address, Integer align)
    {
        this.result = result;
        this.type = type; this.address = address;
        this.align = align; this.addressType = address.getType();
    }

    public IRVar getResult() { return result; }

    @Override
    public String toString() {
        return result + " = load " + type + ", " + addressType + " " + address + ", align " + align;
    }
}
