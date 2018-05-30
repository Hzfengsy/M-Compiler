package Hzfengsy.CodeGenerator;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRExpr.*;

import java.util.*;

public class ConflictGraph
{
    private Map<IRVar, Set<IRVar>> conflict = new HashMap<>();
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
        conflict.put(var, new HashSet<>());
    }

    public void allocate() {
        for (int registerIndex = 0; registerIndex < regs; registerIndex++) {
            for (IRVar var : unhandled) {
                if (var.register != null) continue;
                boolean flag = true;
                for (IRVar other : conflict.get(var))
                    if (other.register == Register.alloc(registerIndex)) {
                        flag = false;
                        break;
                    }
                if (flag) {
                    var.register = Register.alloc(registerIndex);
                }
            }
        }
    }
}
