package Hzfengsy.Visitor;

import Hzfengsy.Exceptions.*;
import Hzfengsy.Type.*;

import java.util.*;

public class Classes
{
    private Map<String, BaseType> classList = new HashMap<>();
    public final BaseType intType = new IntType();
    public final BaseType stringType = new StringType();
    public final BaseType boolType = new BoolType();
    public final BaseType voidType = new VoidType();
    public final BaseType nullType = new NullType();


    public Classes() {
        classList.put("int", intType);
        classList.put("string", stringType);
        classList.put("bool", boolType);
        classList.put("void", voidType);
        classList.put("null", nullType);
    }

    BaseType defineClass(String className) throws Exception {
        if (classList.containsKey(className))
            throw new SemanticException("already has a class named" + "\"" + className + "\"");
        BaseType clas = new UserType(className);
        classList.put(className, clas);
        return clas;
    }

    //    public BaseType get(String className) {
    //        try { return getClass(className); } catch (Exception e) {} return null;
    //    }

    BaseType getClass(String className) throws Exception {
        if (classList.containsKey(className)) return classList.get(className);
        if (className.length() > 2 && className.substring(className.length() - 2).equals("[]")) {
            BaseType Base = getClass(className.substring(0, className.length() - 2));
            BaseType Type = new ArrayType(Base);
            classList.put(className, Type);
            return Type;
        }
        throw new SemanticException("could not find a class called \"" + className + "\"");
        //        return classList.get(className);
    }

    Boolean containClass(String className) {
        return classList.containsKey(className);
    }
}
