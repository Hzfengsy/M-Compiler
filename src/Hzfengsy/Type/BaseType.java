package Hzfengsy.Type;


import Hzfengsy.Exceptions.SemanticException;

import java.util.*;

abstract public class BaseType
{
    public BaseType() { }

    Map<String, FuncType> memberFunc = new HashMap<>();
    Map<String, BaseType> memberVar = new HashMap<>();

    public BaseType getBaseType() { assert false; return null; }

    public boolean assign_check(BaseType x) {
        if (this == x) return true; if (x == null) return false; if (this instanceof NullType) return false;
        if (this instanceof VoidType || x instanceof VoidType) return false;
        if (x instanceof NullType && (this instanceof ArrayType || this instanceof UserType)) return true;
        if (this instanceof UserType || x instanceof UserType) {
            if (this instanceof UserType && x instanceof UserType)
                return ((UserType) x).getName() == ((UserType) this).getName();
            else return false;
        } return this.getClass() == x.getClass();
    }

    public FuncType queryFunc(String funcName) throws Exception {
        if (!memberFunc.containsKey(funcName))
            throw new SemanticException("Undefined Member Functions \"" + funcName + "\"");
        return memberFunc.get(funcName);
    }

    public BaseType queryVar(String varName) throws Exception {
        if (!memberVar.containsKey(varName))
            throw new SemanticException("Undefined Member Variable \"" + varName + "\""); return memberVar.get(varName);
    }

    public Boolean contain(String Name) {
        return memberFunc.containsKey(Name) || memberVar.containsKey(Name);
    }

}
