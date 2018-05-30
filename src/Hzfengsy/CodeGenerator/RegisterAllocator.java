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
}
