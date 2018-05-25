package Hzfengsy.IR;

import java.util.*;

public class IRLabelList
{
    private Map<String, IRLable> labelList = new HashMap<>();
    private Integer temp = 0;


    public IRLable query(String name) {
        return labelList.get(name);
    }

    public IRLable insert(String name) {
        IRLable lable = new IRLable(name);
        labelList.put(name, lable);
        return lable;
    }

    public IRLable insertTemp() {
        String name = (temp++).toString();
        IRLable lable = new IRLable(name);
        labelList.put(name, lable);
        return lable;
    }
}
