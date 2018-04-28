package Hzfengsy.Type.VarType;

import Hzfengsy.Type.*;

public class UserType extends BaseType
{
    private String className;

    public UserType(String Name) { className = Name; }

    public void insertMemberFunc(String funcName, FuncType func) {
        memberFunc.put(funcName, func);
    }

    public String getName() {
        return className;
    }

    public void insertMemberVar(String varName, BaseType var) {
        memberVar.put(varName, var);
    }

    @Override
    public String toString() {
        return className;
    }
}
