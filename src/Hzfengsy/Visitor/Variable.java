package Hzfengsy.Visitor;

import Hzfengsy.Type.baseType;
import Hzfengsy.Exceptions.*;

import java.util.*;

public class Variable {

    private Map<String, baseType> varList = new HashMap<String, baseType>();

    public void insert (String varName, baseType varType) throws Exception
    {
        if (varList.containsKey(varName))
            throw new semanticException("already has a variable named" + "\"" + varName + "\"");
        varList.put(varName, varType);
    }

    public baseType query (String varName) throws Exception
    {
        if (!varList.containsKey(varName))
            throw new semanticException("cannot find a variable named" + "\"" + varName + "\"");
        return varList.get(varName);
    }

    public String rename (String varName)
    {
        if (!varList.containsKey(varName)) return varName;
        else for (int i = 0;; i++)
        {
            String newName = varName + String.valueOf(i);
            if (!varList.containsKey(newName)) return newName;
        }
    }

}
