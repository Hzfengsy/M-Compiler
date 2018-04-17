package Hzfengsy.Type;

import java.util.*;

public class userType extends baseType
{
    private String className;
    public userType(String Name) { className = Name; }

    public void insertMemberFunc(String funcName, funcType func)
    {
        memberFunc.put(funcName, func);
    }

    public void insertMemberVar(String varName, baseType var)
    {
        memberVar.put(varName, var);
    }
}
