package Hzfengsy.IR.IRInstruction;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRType.*;

public class IRStoreInstruction
{
    private IRVar address;
    private IRExpr data;
    private IRBaseType dataType, addressType;
    private Integer align;

    public IRStoreInstruction(IRBaseType dataType, IRExpr data, IRBaseType addressType, IRVar address, Integer align)
    {
        this.dataType = dataType;
        this.data = data;
        this.addressType = addressType;
        this.address = address;
        this.align = align;
    }

    @Override
    public String toString() {
        return"store " + dataType + " " + data + ", " + addressType + " " + address + ", align " + align;
    }
}
