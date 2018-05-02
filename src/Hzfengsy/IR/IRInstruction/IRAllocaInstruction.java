package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRType.*;

public class IRAllocaInstruction extends IRBaseInstruction
{
    private IRVar result;
    private IRBaseType type;
    private Integer align;
    public IRAllocaInstruction(IRVar result, IRBaseType type, Integer align) {
        this.result = result;
        this.type = type;
        this.align = align;
    }

    @Override
    public String toString() {
        return result + " = alloca " + type + ", align " + align;
    }
}
