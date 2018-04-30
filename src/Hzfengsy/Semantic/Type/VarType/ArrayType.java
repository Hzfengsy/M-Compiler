package Hzfengsy.Semantic.Type.VarType;

import Hzfengsy.Semantic.Type.*;

import java.util.*;

public class ArrayType extends BaseType
{
    private FuncType func_size() {
        return new FuncType(new IntType(), new Vector<>(), "int size()", "size");
    }

    private BaseType Base;

    public ArrayType(BaseType _Base) {
        Base = _Base;
        memberFunc.put("size", func_size());
    }

    public BaseType getBaseType() { return Base; }

    @Override
    public String toString() {
        return Base + "[]";
    }

}
