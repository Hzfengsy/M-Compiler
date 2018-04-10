package Hzfengsy.Function;

import Hzfengsy.Type.baseType;

import java.util.*;

public class funcType
{
    private String funcName = new String();
    private Vector<baseType> varList = new Vector<>();

    public funcType(String name, Vector<baseType> list)
    {
        funcName = name;
        varList =list;
    }
}
