package Hzfengsy.IR.IRNode;

import Hzfengsy.CodeGenerator.*;
import Hzfengsy.IR.*;
import Hzfengsy.IR.IRExpr.*;

import java.util.*;

public class IRFuncNode extends IRBase
{
    private String funcName;
    private IRVar[] args;
    private Vector<IRBasicBlock> containBlocks = new Vector<>();
    private Vector<IRSuperBlock> superBlocks = new Vector<>();
    private Set<IRVar> usedVar = new HashSet<>();
    private StackAlloc alloc = new StackAlloc();
    private boolean extend = false;
    public boolean dp = false;

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
        for (IRBasicBlock node : containBlocks)
            ans.append(node.toString());
        return ans.toString();
    }

    public void appendBlock(IRBasicBlock nextNode) {
        this.containBlocks.add(nextNode);
    }

    public void appendSuperBlock(IRSuperBlock superBlock) {
        this.superBlocks.add(superBlock);
    }

    @Override
    public String toString() {
        if (extend) return "";
        return "define " + getFuncName() + "(" + argsToString() + ") {\n" + instToString() + "}\n\n";
    }

    public IRVar getThis() {
        return args[0];
    }

    public Vector<IRBasicBlock> getContainBlocks() {
        return containBlocks;
    }

    public void addVar(IRVar var) {
        usedVar.add(var);
    }

    public void allocStack() {
        for (IRVar var : usedVar) {
            if (RegisterAllocator.get(var) == null) alloc.SetVar(var);
        }
    }

    public Set<Register> getUsedReg() {
        Set<Register> ans = new HashSet<>();
        for (IRVar var : usedVar) {
            Register reg = RegisterAllocator.get(var);
            if (reg != null) ans.add(reg);
        }
        return ans;
    }

    public StackAlloc getAlloc() {
        return alloc;
    }

    public IRVar[] getArgs() {
        return args;
    }

    public boolean isExtend() {
        return extend;
    }

    public Set<IRVar> getUsedVar() {
        return usedVar;
    }

    public Vector<IRSuperBlock> getSuperBlocks() {
        return superBlocks;
    }
}
