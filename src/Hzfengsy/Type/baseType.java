package Hzfengsy.Type;


import Hzfengsy.Exceptions.semanticException;

import java.util.*;

abstract public class baseType
{
    public baseType() { }
    Map<String, funcType> memberFunc = new HashMap<>();
    Map<String, baseType> memberVar = new HashMap<>();

    public baseType getBaseTYpe() { assert false; return new intType(); }

    @Override public boolean equals(Object x)
    {
        if(this == x) return true;
        if(x == null) return false;
        if (x instanceof nullType && (this instanceof arrayType || this instanceof userType)) return true;
        if (this instanceof nullType && (x instanceof arrayType || x instanceof userType)) return true;
        return this.getClass() == x.getClass();
    }

    public funcType query (String funcName) throws Exception
    {
        if (!memberFunc.containsKey(funcName))
            throw new semanticException("Undefined Functions");
        return memberFunc.get(funcName);
    }

    public Boolean contain (String funcName)
    {
        return memberFunc.containsKey(funcName);
    }

}
