package Hzfengsy.IR.IRType;

public class IRPointerType extends IRBaseType
{
    IRBaseType baseType;

    public IRPointerType(IRBaseType baseType) {
        this.baseType = baseType;
    }
}
