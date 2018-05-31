package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.IRExpr.*;

import java.util.*;

public class IRArgs extends IRBasicBlock
{
    private Vector<IRExpr> args = new Vector<>();

    public IRArgs() {}
    public IRArgs(IRArgs other) {
        instructions.addAll(other.getInstructions());
        this.args.addAll(other.args);
    }

    public IRArgs(IRBasicBlock other) {
        instructions.addAll(other.getInstructions());
        this.args.add(other.getResult());
    }

    public void join(IRArgs args) {
        if (args == null) return;
        instructions.addAll(args.instructions);
        this.args.addAll(args.args);
    }

    public void join(IRBasicBlock block) {
        if (args == null) return;
        instructions.addAll(block.instructions);
        this.args.add(block.getResult());
    }

    public void addArg(IRExpr arg) {
        this.args.add(arg);
    }

    public IRExpr[] getArgs() {
        IRExpr[] ans = new IRExpr[args.size()];
        args.copyInto(ans);
        return ans;
    }

}
