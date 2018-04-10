package Hzfengsy.Listener;

import Hzfengsy.Exceptions.semanticException;
import Hzfengsy.Type.baseType;

import java.util.HashMap;
import java.util.Map;

public class Function {

    private Map<String, baseType> funcList = new HashMap<String, baseType>();

    public void insert (String varName, baseType varType)
    {
        assert (!funcList.containsKey(varName));
        funcList.put(varName, varType);
    }

    public baseType query (String funcName) throws Exception
    {
        if (!funcList.containsKey(funcName))
            throw new semanticException("Undefined Functions");
        return funcList.get(funcName);
    }

    public Boolean contain (String funcName)
    {
        return funcList.containsKey(funcName);
    }

//    public String rename (String funcName)
//    {
//        if (!funcList.containsKey(funcName)) return funcName;
//        else for (int i = 0;; i++)
//            if (!funcList.containsKey(funcName + String.valueOf(i))) return funcName;
//    }

}
