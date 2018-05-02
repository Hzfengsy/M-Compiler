package Hzfengsy.IR;

import Hzfengsy.IR.IRType.*;

public class IRVar extends IRExpr
{
    IRBaseType type;
    private String name;
    private Boolean globle;

    public IRVar(String name, Boolean globle) {
        this.name = name;
        this.globle = globle;
    }

    public IRVar(Integer name, Boolean globle) {
        this.name = name.toString();
        this.globle = globle;
    }

    public IRBaseType getType() {
        return type;
    }

    @Override
    public String toString() {
        return  (globle ? "@" : "%") + name;
    }
}
