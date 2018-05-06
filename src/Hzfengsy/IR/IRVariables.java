package Hzfengsy.IR;

import java.util.*;

public class IRVariables
{
    private Vector<IRVar> varList = new Vector<>();
    private Map<String, IRVar> variables = new HashMap<>();
    private Integer index = 0;

    public IRVar insertVar(String name) {
        IRVar ans = new IRVar(index++, false);
        varList.add(ans);
        variables.put(name, ans);
        return ans;
    }

    public IRVar insertTempVar() {
        IRVar ans = new IRVar(index++, false);
        varList.add(ans);
        return ans;
    }

    public IRVar query(Integer index) { return varList.elementAt(index); }

    public IRVar query(String varName){
        return variables.get(varName);
    }

    public void setIndex(Integer index) {
        for (int i = this.index; i < index; i++) varList.add(null);
        this.index = index;
    }
}

