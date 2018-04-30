package Hzfengsy.IR.IRType;

import Hzfengsy.Semantic.*;
import Hzfengsy.Semantic.Type.VarType.*;

import java.util.*;

public class TypeMap
{
    private static TypeMap typeMap = new TypeMap();
    private Classes classes = Classes.getInstance();
    public static TypeMap getInstance() {
        return typeMap;
    }
    private Map<BaseType, IRBaseType> map = new HashMap<>();
    private TypeMap() {
        map.put(classes.intType, new IRi32Type());
    }

    public IRBaseType exchange(BaseType type) {
        if (!map.containsKey(type)) {
            System.err.println("Cannot find Type" + type);
            System.exit(-1);
        }
        return map.get(type);
    }
}
