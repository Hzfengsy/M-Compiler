package Hzfengsy.IR.IRExpr;

import java.util.regex.*;

public class IRVar extends IRExpr implements Comparable
{
    private String name;
    private Boolean globe;
    private Integer liveTime = 0;
    public int uses = 0;
    public int conflicts = 0;

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

    public void updateLive() {
        liveTime++;
    }

    public double getPriority() {
        if (conflicts == 0) return 0;
        return ((double)uses) / conflicts;
    }

    @Override
    public int compareTo(Object o) {
        IRVar var = (IRVar) o;
        if (this.getPriority() == var.getPriority()) return 0;
        return this.getPriority() < var.getPriority() ? 1 : -1;
    }

    public void cleanGlobe() {
        globe = false;
    }
}
