package Hzfengsy.Listener;
import java.util.*;

import Hzfengsy.Exceptions.semanticException;
import Hzfengsy.Type.*;

public class Classes
{
    private Map<String, baseType> classList = new HashMap<>();

    public Classes()
    {
        classList.put("int", new intType());
        classList.put("string", new stringType());
        classList.put("bool", new boolType());
        classList.put("void", new voidType());
        classList.put("null", new nullType());
    }

    public baseType defineClass(String className) throws Exception
    {
        if (classList.containsKey(className))
            throw new semanticException("already has a class named" + "\"" + className + "\"");
        baseType clas = new userType(className);
        classList.put(className, clas);
        return clas;
    }

    public baseType get(String className)
    {
        try { return getClass(className); }
        catch (Exception e) {}
        return null;
    }

    public baseType getClass(String className) throws Exception
    {
        if (classList.containsKey(className)) return classList.get(className);
        if (className.length() > 2 && className.substring(className.length() - 2).equals("[]"))
        {
            baseType Base = getClass(className.substring(0, className.length() - 2));
            baseType Type = new arrayType(Base);
            classList.put(className, Type);
            return Type;
        }
        throw new semanticException("cannot find a class called \"" + className + "\"" );
//        return classList.get(className);
    }

    public boolean containClass(String className)
    {
        return classList.containsKey(className);
    }
}
