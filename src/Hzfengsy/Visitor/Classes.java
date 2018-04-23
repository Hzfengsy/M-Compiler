package Hzfengsy.Visitor;

import java.util.*;

import Hzfengsy.Exceptions.SemanticException;
import Hzfengsy.Type.*;

public class Classes
{
    private Map<String, BaseType> classList = new HashMap<>();

    public Classes() {
        classList.put("int", new IntType()); classList.put("string", new StringType());
        classList.put("bool", new BoolType()); classList.put("void", new VoidType());
        classList.put("null", new NullType());
    }

    public BaseType defineClass(String className) throws Exception {
        if (classList.containsKey(className))
            throw new SemanticException("already has a class named" + "\"" + className + "\"");
        BaseType clas = new UserType(className); classList.put(className, clas); return clas;
    }

    public BaseType get(String className) {
        try { return getClass(className); } catch (Exception e) {} return null;
    }

    public BaseType getClass(String className) throws Exception {
        if (classList.containsKey(className)) return classList.get(className);
        if (className.length() > 2 && className.substring(className.length() - 2).equals("[]")) {
            BaseType Base = getClass(className.substring(0, className.length() - 2));
            BaseType Type = new ArrayType(Base); classList.put(className, Type); return Type;
        } throw new SemanticException("could not find a class called \"" + className + "\"");
        //        return classList.get(className);
    }

    public boolean containClass(String className) {
        return classList.containsKey(className);
    }
}
