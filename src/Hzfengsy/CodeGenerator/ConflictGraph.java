package Hzfengsy.CodeGenerator;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.*;

import java.util.*;

public class ConflictGraph
{
    private Map<IRVar, Vector<IRVar>> conflict = new HashMap<>();
    private Set<IRVar> unhandled = new HashSet<>();
    private final int regs = Register.registerNum();
    private StringData stringData = StringData.getInstance();

    public void setConflict(IRVar a, IRVar b) {
        if (a.isGlobe() || b.isGlobe()) return;
        if (stringData.containLabel(a) || stringData.containLabel(b)) return;
        conflict.get(a).add(b);
        conflict.get(b).add(a);
    }

    public void setVar(IRVar var) {
        if (var.isGlobe()) return;
        unhandled.add(var);
        conflict.put(var, new Vector<>());
    }

    public void allocate() {
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
