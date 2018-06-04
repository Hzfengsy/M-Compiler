package Hzfengsy.CodeGenerator;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.*;

import java.util.*;

public class ConflictGraph
{
    private Map<IRVar, Set<IRVar>> conflict = new HashMap<>();
    private Set<IRVar> unhandled = new HashSet<>();
    private final int regs = Register.registerNum();
    private StringData stringData = StringData.getInstance();
    private Stack<IRVar> varStack = new Stack<>();
    private Map<IRVar, Integer> degree = new HashMap<>();
    private Queue<IRVar> toDeleteVars = new LinkedList<>();

    public boolean containConflict(IRVar a, IRVar b) {
        return conflict.containsKey(a) && conflict.get(a).contains(b);
    }

    public void setConflict(IRVar a, IRVar b) {
        if (a.isGlobe() || b.isGlobe()) return;
        if (stringData.containLabel(a) || stringData.containLabel(b)) return;
        if (!containConflict(a, b)) {
            conflict.get(a).add(b);
            degree.put(a, degree.get(a) + 1);
        }
        if (!containConflict(b, a)) {
            conflict.get(b).add(a);
            degree.put(b, degree.get(b) + 1);
        }
    }

    public void setVar(IRVar var) {
        if (var.isGlobe()) return;
        unhandled.add(var);
        degree.put(var, 0);
        conflict.put(var, new HashSet<>());
    }

    private void removeVar(IRVar var) {
        Set<IRVar> varConflict = conflict.get(var);
        for (IRVar neighbor : varConflict)
            if (unhandled.contains(neighbor)) {
                Integer varDegree = degree.get(neighbor) - 1;
                degree.put(neighbor, varDegree);
                if (varDegree < regs) toDeleteVars.add(neighbor);
            }
        varStack.push(var);
        unhandled.remove(var);
    }

    private void simplify() {
        while (!unhandled.isEmpty()) {
            while (!toDeleteVars.isEmpty()) {
                Iterator<IRVar> it = toDeleteVars.iterator();
                IRVar var = it.next();
                removeVar(var);
                toDeleteVars.remove(var);
            }
            Iterator<IRVar> it = unhandled.iterator();
            if (it.hasNext()) removeVar(it.next());
            else break;
        }
    }

    private void select() {
        Set<Register> neighborReg = new HashSet<>();
        while (!varStack.isEmpty()) {
            neighborReg.clear();
            IRVar var = varStack.pop();
            unhandled.add(var);
            Set<IRVar> varConflict = conflict.get(var);
            for (IRVar neighbor : varConflict)
                if (unhandled.contains(neighbor) && RegisterAllocator.get(neighbor) != null)
                    neighborReg.add(RegisterAllocator.get(neighbor));
            for (int i = 0; i < regs; i++) {
                Register reg = Register.alloc(i);
                if (neighborReg.contains(reg)) continue;
                RegisterAllocator.put(var, reg);
                break;
            }
        }
    }

    public void allocate() {
        simplify();
        select();
    }
}
