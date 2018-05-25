package Hzfengsy.Semantic.Type.VarType;

import Hzfengsy.Semantic.Type.*;

public class UserType extends BaseType
{
    private String className;
    private int index = 0;
    public boolean construction = false;

    public UserType(String Name) { className = Name; }

    public void insertMemberFunc(String funcName, FuncType func) {
        memberFunc.put(funcName, func);
    }

    public String getName() {
        return className;
    }

    public void insertMemberVar(String varName, BaseType var) {
        memberVar.put(varName, var);
        varIndex.put(varName, index++);
    }

    @Override
    public String toString() {
        return className;
    }
}
