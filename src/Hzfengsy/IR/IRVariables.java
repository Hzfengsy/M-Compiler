package Hzfengsy.IR;

import java.util.*;

public class IRVariables
{
    private Map<String, IRVar> variables = new HashMap<>();
    private Vector<IRVar> tempVars = new Vector<>();
    Integer index = 0;

    public IRVar insertVar(String name) {
        IRVar ans = new IRVar(index++, false);
        variables.put(name, ans);
        return ans;
    }

    public IRVar insertTempVar() {
        IRVar ans = new IRVar(index++, false);
        tempVars.add(ans);
        return ans;

    }
}

