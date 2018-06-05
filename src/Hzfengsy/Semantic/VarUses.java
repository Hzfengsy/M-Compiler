package Hzfengsy.Semantic;

import java.util.*;

public class VarUses
{
    private Map<String, Integer> count = new HashMap<>();
    private static VarUses varUses = new VarUses();

    public static VarUses getInstance() {
        return varUses;
    }

    private VarUses() {}

    public void add(String name) {
        if (!count.containsKey(name))
            count.put(name, 1);
        else count.put(name, count.get(name) + 1);
    }

    public void sub(String name) {
        if (!count.containsKey(name))
            count.put(name, -1);
        else count.put(name, count.get(name) - 1);
    }

    public boolean valid(String name) {
        return (count.containsKey(name) && count.get(name) != 0);
    }
}
