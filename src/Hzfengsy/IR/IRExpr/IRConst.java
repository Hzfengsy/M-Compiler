package Hzfengsy.IR.IRExpr;

public class IRConst extends IRExpr
{
    private Integer value;
    public IRConst(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public Integer getValue() {
        return value;
    }
}
