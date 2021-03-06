package Hzfengsy.IR;

import Hzfengsy.IR.IRExpr.*;

import java.util.*;

public class StringData
{
    private Map<String, IRExpr> pool = new HashMap<>();
    private Set<String> labels = new HashSet<>();


    private StringData() {}

    private static StringData stringData = new StringData();

    public static StringData getInstance() {
        return stringData;
    }

    public void insert(IRExpr label, String string) {
        pool.put(string, label);
        labels.add(label.toString());
    }

    public IRExpr getLabel(String string) {
        return pool.get(string);
    }

    public boolean containLabel(IRExpr label) {
        return labels.contains(label.toString());
    }

    public Collection<Map.Entry<String,IRExpr>> getEntry(){
        return pool.entrySet();
    }
}
