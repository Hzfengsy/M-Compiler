package Hzfengsy.IR.IRType;

import Hzfengsy.Semantic.Type.VarType.*;

import java.util.*;

public class TypeMap
{
    private Map<BaseType, IRBaseType> map = new HashMap<>();
    public TypeMap() {
        map.put(new IntType(), new IRi32Type());
    }

    public IRBaseType exchange(BaseType type) {
        if (!map.containsKey(type)) {
            System.err.println("Cannot find Type" + type);
            System.exit(-1);
        }
        return map.get(type);
    }
}
