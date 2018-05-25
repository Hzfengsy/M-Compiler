package Hzfengsy.Semantic;

import Hzfengsy.Semantic.Type.VarType.*;

import java.util.*;

public class Classes
{
    private static Classes classes = new Classes();
    private Map<String, BaseType> classList = new HashMap<>();
    public final BaseType intType = new IntType();
    public final BaseType stringType = new StringType();
    public final BaseType boolType = new BoolType();
    public final BaseType voidType = new VoidType();
    public final BaseType nullType = new NullType();

    public static Classes getInstance(){
        return classes;
    }


    private Classes() {
        classList.put("int", intType);
        classList.put("string", stringType);
        classList.put("bool", boolType);
        classList.put("void", voidType);
        classList.put("null", nullType);
    }

    public BaseType defineClass(String className) throws Exception {
        if (classList.containsKey(className))
            throw new Exception("already has a class named" + "\"" + className + "\"");
        BaseType clas = new UserType(className);
        classList.put(className, clas);
        return clas;
    }

    public BaseType getClass(String className) throws Exception {
        if (classList.containsKey(className)) return classList.get(className);
        if (className.length() > 2 && className.substring(className.length() - 2).equals("[]")) {
            BaseType Base = getClass(className.substring(0, className.length() - 2));
            BaseType Type = new ArrayType(Base);
            classList.put(className, Type);
            return Type;
        }
        throw new Exception("could not find a class called \"" + className + "\"");
    }

    public BaseType safeGetClass(String className) {
        try {
            return getClass(className);
        } catch (Exception ignore) {}
        return null;
    }

    public Boolean containClass(String className) {
        return classList.containsKey(className);
    }

    public Collection<BaseType> values() {
        return  classList.values();
    }
}
