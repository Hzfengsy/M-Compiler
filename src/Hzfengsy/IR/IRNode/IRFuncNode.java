package Hzfengsy.IR.IRNode;

import Hzfengsy.CodeGenerator.*;
import Hzfengsy.IR.*;
import Hzfengsy.IR.IRExpr.*;

import java.util.*;

public class IRFuncNode extends IRBase
{
    private String funcName;
    private IRVar[] args;
    private Vector<IRBaseBlock> containNodes = new Vector<>();
    private StackAlloc alloc = new StackAlloc();
    private boolean extend = false;

    public IRFuncNode(String funcName, IRVar... args) {
        this.funcName = funcName;
        this.args = args;
    }

    public IRFuncNode(String funcName, boolean extend, IRVar... args) {
        this.funcName = funcName;
        this.args = args;
        this.extend = extend;
    }

    public String getFuncName() {
        return "@" + funcName;
    }

    public String getName() {
        return funcName;
    }

    private String argsToString() {
        String ans = new String();
        Boolean first = true;
        for (IRVar arg : this.args) {
            ans += (first ? "" : ", ") + arg.toString();
            first = false;
        }
        return ans;
    }

    private String instToString() {
        StringBuilder ans = new StringBuilder();
        for (IRBaseBlock node : containNodes)
            ans.append(node.toString());
        return ans.toString();
    }

    public void appendNode(IRBaseBlock nextNode) {
        this.containNodes.add(nextNode);
    }

    @Override
    public String toString() {
        if (extend) return "";
        return "define " + getFuncName() + "(" + argsToString() + ") {\n" + instToString() + "}\n\n";
    }

    public IRVar getThis() {
        return args[0];
    }

    public List<IRBaseBlock> getContainNodes() {
        return containNodes;
    }

    public void allocVar(IRVar var) {
        alloc.SetVar(var);
    }

    public StackAlloc getAlloc() {
        return alloc;
    }

    public IRVar[] getArgs() {
        return args;
    }
}
