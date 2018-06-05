package Hzfengsy.IR;

import Hzfengsy.IR.IRExpr.*;

import java.util.*;

public class IRVariables
{
    private Map<String, IRVar> variables = new HashMap<>();
    private Set<IRVar> globe = new HashSet<>();
    private Integer index = 0;
    private Integer stringIndex = 0;
    private static IRVariables irVariables = new IRVariables();

    private IRVariables() {}

    public static IRVariables getInstance() {
        return irVariables;
    }

    public IRVar insertVar(String name, Boolean globe) {
        IRVar ans = new IRVar(name, globe);
        variables.put(name, ans);
        if (globe) this.globe.add(ans);
        return ans;
    }

    public IRVar insertTempVar() {
        String Index = (index++).toString();
        IRVar ans = new IRVar(Index, false);
        variables.put(Index, ans);
        return ans;
    }

    public IRVar insertTempString() {
        String Index = "__String_" + (stringIndex++).toString();
        IRVar ans = new IRVar(Index, false);
        variables.put(Index, ans);
        return ans;
    }

    public IRVar query(String varName) {
        return variables.get(varName);
    }

    public Collection<IRVar> getGlobe() {
        return globe;
    }

    public void CleanGlobe() {
        globe.clear();
        for (IRVar var : variables.values()) var.cleanGlobe();
    }
}

