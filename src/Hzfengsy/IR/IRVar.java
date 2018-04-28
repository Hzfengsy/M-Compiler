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

    @Override
    public String toString() {
        return type + " " + (globle ? "@" : "%") + " " + name;
    }
}
