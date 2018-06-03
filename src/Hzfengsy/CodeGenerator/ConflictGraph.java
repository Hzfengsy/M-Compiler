package Hzfengsy.CodeGenerator;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.*;

import java.util.*;

public class ConflictGraph
{
    private Map<IRVar, Set<IRVar>> conflict = new HashMap<>();
    private Vector<IRVar> unhandled = new Vector<>();
    private final int regs = Register.registerNum();
    private StringData stringData = StringData.getInstance();

    public boolean containConflict(IRVar a, IRVar b) {
        return conflict.containsKey(a) && conflict.get(a).contains(b);
    }

    public void setConflict(IRVar a, IRVar b) {
        if (a.isGlobe() || b.isGlobe()) return;
        if (stringData.containLabel(a) || stringData.containLabel(b)) return;
        conflict.get(a).add(b);
        conflict.get(b).add(a);
    }

    public void setVar(IRVar var) {
        if (var.isGlobe()) return;
        unhandled.add(var);
        conflict.put(var, new HashSet<>());
    }

    public void allocate() {
        Collections.sort(unhandled);
        for (int registerIndex = 0; registerIndex < regs; registerIndex++) {
            for (IRVar var : unhandled) {
                if (RegisterAllocator.get(var) != null) continue;
                boolean flag = true;
                for (IRVar other : conflict.get(var))
                    if (RegisterAllocator.get(other) == Register.alloc(registerIndex)) {
                        flag = false;
                        break;
                    }
                if (flag) {
                    RegisterAllocator.put(var, Register.alloc(registerIndex));
                }
            }
        }
    }
}
