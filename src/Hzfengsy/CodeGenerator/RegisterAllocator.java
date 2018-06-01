package Hzfengsy.CodeGenerator;

import Hzfengsy.IR.IRExpr.*;

import java.util.*;

public class RegisterAllocator
{
    static private Map<IRVar, Register> registerMap = new HashMap<>();

    public static void put(IRVar var, Register reg) {
        registerMap.put(var, reg);
    }

    public static Register get(IRVar var) {
        return registerMap.get(var);
    }

    static public String print() {
        StringBuilder ans = new StringBuilder();
        for (Map.Entry<IRVar, Register> entry : registerMap.entrySet())
            ans.append(entry.getKey().toString() + " " + entry.getValue().toString() + "\n");
        return ans.toString();
    }
}
