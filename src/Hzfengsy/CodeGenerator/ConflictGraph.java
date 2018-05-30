package Hzfengsy.CodeGenerator;

import Hzfengsy.IR.IRExpr.*;

import java.util.*;

public class ConflictGraph
{
    private Map<IRVar, Set<IRVar>> conflict = new HashMap<>();
    private Set<IRVar> unhandled = new HashSet<>();
    private final int regs = 4;

    public void setConflict(IRVar a, IRVar b) {
        conflict.get(a).add(b);
        conflict.get(b).add(a);
    }

    public void setVar(IRVar var) {
        unhandled.add(var);
        conflict.put(var, new HashSet<>());
    }

    public void allocate() {
        for (int registerIndex = 0; registerIndex < regs; registerIndex++) {
            for (IRVar var : unhandled) {
                if (var.register != null) continue;
                boolean flag = true;
                for (IRVar other : conflict.get(var))
                    if (other.register == Register.get(registerIndex)) {
                        flag = false;
                        break;
                    }
                if (flag) {
                    var.register = Register.get(registerIndex);
                }
            }
        }
    }
}
