package Hzfengsy.Type;

import java.util.*;

public class arrayType extends baseType
{
    private funcType func_size()
    {
        return new funcType(new intType(), new Vector<>());
    }

    private baseType Base;
    public arrayType(baseType _Base)
    {
        Base = _Base;
        memberFunc.put("size", func_size());
    }

    public baseType getBaseTYpe() { return Base; }
}
