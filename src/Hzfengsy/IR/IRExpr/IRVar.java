package Hzfengsy.IR.IRExpr;

import Hzfengsy.CodeGenerator.*;

import java.util.regex.*;

public class IRVar extends IRExpr
{
    private String name;
    private Boolean globe;

    public IRVar(String name, Boolean globe) {
        this.name = name;
        this.globe = globe;
    }

    public boolean isGlobe() {
        return globe;
    }

    @Override
    public String toString() {
        return (globe ? "@" : "%") + name;
    }

    public String getName() {
        return (globe ? "_" : "") + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IRVar var = (IRVar) o;
        return this.toString().equals(var.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public boolean isTemp() {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(name).matches();
    }
}
