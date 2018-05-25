package Hzfengsy.IR;

public class IRLable extends IRBase
{
    private String lable;

    public IRLable(String lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return "#" + lable;
    }

    public String getName() {
        boolean isNum = lable.matches("[0-9]+");
        if (isNum) return "L_" + lable;
        else return lable;
    }
}
