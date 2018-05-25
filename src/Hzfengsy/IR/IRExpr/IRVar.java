package Hzfengsy.IR.IRExpr;

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
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IRVar var = (IRVar) o;
        return var.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
