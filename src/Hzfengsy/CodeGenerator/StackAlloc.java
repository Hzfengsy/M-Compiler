package Hzfengsy.CodeGenerator;

import Hzfengsy.IR.IRExpr.*;

import java.util.*;

public class StackAlloc
{
    private Map<IRVar, Integer> location = new HashMap<>();
    private int index = -8;

    public void SetVar(IRVar var) {
        if (location.containsKey(var)) return;
        location.put(var, index);
        index -= 8;
    }

    public Integer totalSize(){
        return -index;
    }

    public Integer getOffset(IRVar var) {
        return location.get(var);
    }
}
