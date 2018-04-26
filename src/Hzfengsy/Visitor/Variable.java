package Hzfengsy.Visitor;

import Hzfengsy.Exceptions.*;
import Hzfengsy.Type.*;

import java.util.*;

public class Variable
{

    private Map<String, BaseType> varList = new HashMap<String, BaseType>();

    public void insert(String varName, BaseType varType) throws Exception {
        if (varList.containsKey(varName))
            throw new Exception("already has a variable named" + "\"" + varName + "\"");
        varList.put(varName, varType);
    }

    public BaseType query(String varName) throws Exception {
        if (!varList.containsKey(varName))
            throw new Exception("cannot find a variable named" + "\"" + varName + "\"");
        return varList.get(varName);
    }

    public String rename(String varName) {
        if (!varList.containsKey(varName)) return varName;
        else for (int i = 0; ; i++) {
            String newName = varName + String.valueOf(i);
            if (!varList.containsKey(newName)) return newName;
        }
    }

}
