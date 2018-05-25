package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.*;

import java.util.*;

public class IRProgNode extends IRBase
{
    private Vector<IRFuncNode> funcs = new Vector<>();

    public void insertFunc(IRFuncNode node) {
        funcs.add(node);
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        for (IRFuncNode node : funcs)
            ans.append(node);
        return ans.toString();
    }

    public Collection<IRFuncNode> getFuncs() {
        return funcs;
    }
}
