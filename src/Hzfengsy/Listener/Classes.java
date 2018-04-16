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
    }

    public void defineClass(String className) throws Exception
    {
        if (classList.containsKey(className))
            throw new semanticException("already has a class named" + "\"" + className + "\"");
        classList.put(className, new userType(className));
    }

    public baseType getClass(String className)
    {
        if (className.substring(className.length() - 2).equals("[]"))
        {
            baseType Base = getClass(className.substring(0, className.length() - 2));
            baseType Type = new arrayType(Base);
            classList.put(className, Type);
            return Type;
        }
        return classList.get(className);
    }

    public boolean containClass(String className)
    {
        return classList.containsKey(className);
    }
}
