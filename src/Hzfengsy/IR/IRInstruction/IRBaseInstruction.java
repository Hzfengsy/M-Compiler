package Hzfengsy.IR.IRInstruction;

import Hzfengsy.CodeGenerator.*;
import Hzfengsy.IR.IRExpr.*;

import java.util.*;

public abstract class IRBaseInstruction
{
    public abstract IRExpr getResult();

    public abstract void setResult(IRExpr result);

    private Set<IRVar> in = new HashSet<>();
    private Set<IRVar> out = new HashSet<>();
    public Set<IRBaseInstruction> succ = new HashSet<>();
    private Set<IRVar> use = new HashSet<>();
    private IRVar def = null;
    protected boolean used = false;

    public abstract void analyze();

    void setUse(IRExpr expr) {
        if (expr == null) return;
        if (expr instanceof IRVar)
            this.use.add((IRVar) expr);
        else if (expr instanceof IRMem)
            this.use.addAll(((IRMem) expr).getUse());
    }

    void setDef(IRExpr expr) {
        if (expr == null) return;
        if (expr instanceof IRVar)
            this.def = (IRVar) expr;
        else if (expr instanceof IRMem)
            this.use.addAll(((IRMem) expr).getUse());
    }

    public abstract void useInst();

    private boolean setEqual(Set<IRVar> lhs, Set<IRVar> rhs) {
        if (lhs.size() != rhs.size()) return false;
        for (IRVar x : lhs) {
            if (!rhs.contains(x)) return false;
        }
        return true;
    }

    public boolean update() {
        Set<IRVar> _in = new HashSet<>();
        _in.addAll(in);
        Set<IRVar> _out = new HashSet<>();
        _out.addAll(out);
        in.clear();
        if (out.contains(def)) useInst();
        in.addAll(use);
        for (IRVar var : out) {
            if (!var.equals(def)) in.add(var);
        }
        out.clear();
        for (IRBaseInstruction inst : succ) {
            out.addAll(inst.in);
        }
        return setEqual(in, _in) && setEqual(out, _out);
    }

    public void setConflict(ConflictGraph graph) {
        if (def == null) return;
        for (IRVar b : out)
            if (!def.equals(b)) graph.setConflict(def, b);
    }

    public boolean isUsed() {
        return used;
    }
}
