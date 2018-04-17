package Hzfengsy.Type;


import Hzfengsy.Exceptions.semanticException;

import java.util.*;

abstract public class baseType
{
    public baseType() { }
    Map<String, funcType> memberFunc = new HashMap<>();
    Map<String, baseType> memberVar = new HashMap<>();

    public baseType getBaseType() { assert false; return null; }

    @Override public boolean equals(Object x)
    {
        if(this == x) return true;
        if(x == null) return false;
        if (x instanceof nullType && (this instanceof arrayType || this instanceof userType)) return true;
        if (this instanceof nullType && (x instanceof arrayType || x instanceof userType)) return true;
        return this.getClass() == x.getClass();
    }

    public funcType queryFunc(String funcName) throws Exception
    {
        if (!memberFunc.containsKey(funcName))
            throw new semanticException("Undefined Function");
        return memberFunc.get(funcName);
    }

    public baseType queryVar(String varName) throws Exception
    {
        if (!memberVar.containsKey(varName))
            throw new semanticException("Undefined Variable");
        return memberVar.get(varName);
    }

    public Boolean contain (String Name)
    {
        return memberFunc.containsKey(Name) || memberVar.containsKey(Name);
    }

}
