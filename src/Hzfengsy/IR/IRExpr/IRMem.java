package Hzfengsy.IR.IRExpr;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.IRInstruction.*;

import java.util.*;

public class IRMem extends IRExpr
{
    private IRExpr addr;
    private IRExpr offset;
    private Integer align = 8;

    public IRMem(IRExpr addr, IRExpr offset) {
        this.addr = addr;
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "[" + addr + " + " + offset + " * " + align + "]";
    }

    public IRExpr getAddr(){
        return addr;
    }

    public IRExpr getOffset() {
        return offset;
    }

    public Set<IRVar> getUse(){
        Set<IRVar> ans = new HashSet<>();
        if (addr instanceof IRVar) ans.add((IRVar) addr);
        if (offset instanceof IRVar) ans.add((IRVar) offset);
        return ans;
    }
}
