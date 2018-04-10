package Hzfengsy.Type;

import java.util.Vector;

public class funcType
{
    private baseType returnType;
    private Vector<baseType> parameterList;

    funcType(baseType _returnType, Vector<baseType> list)
    {
        returnType = _returnType;
        parameterList = list;
    }
}
