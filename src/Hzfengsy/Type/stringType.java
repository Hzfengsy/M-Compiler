package Hzfengsy.Type;

import java.util.*;

public class stringType extends baseType
{
    private funcType func_length()
    {
        return new funcType(new intType(), new Vector<>());
    }
    private funcType func_substring()
    {
        Vector<baseType> parameter = new Vector<>();
        parameter.add(new intType());
        parameter.add(new intType());
        return new funcType(this, parameter);
    }
    private funcType func_parseInt()
    {
        return new funcType(new intType(), new Vector<>());
    }
    private funcType func_ord()
    {
        Vector<baseType> parameter = new Vector<>();
        parameter.add(new intType());
        return new funcType(new intType(), parameter);
    }

    public stringType()
    {
        memberFunc.put("length", func_length());
        memberFunc.put("substring", func_substring());
        memberFunc.put("parseInt", func_parseInt());
        memberFunc.put("ord", func_ord());
    }
}
