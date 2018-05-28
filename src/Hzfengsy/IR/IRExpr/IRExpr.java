package Hzfengsy.IR.IRExpr;

import Hzfengsy.IR.*;

public class IRExpr extends IRBase
{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.toString() == o.toString();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
