package Hzfengsy.Listener;

import Hzfengsy.Exceptions.semanticException;
import Hzfengsy.Type.baseType;
import Hzfengsy.Type.funcType;

import java.util.HashMap;
import java.util.Map;

public class Function {

    private Map<String, funcType> funcList = new HashMap();

    public void insert (String funcName, funcType type)
    {
        assert (!funcList.containsKey(funcName));
        funcList.put(funcName, type);
    }

    public funcType query (String funcName) throws Exception
    {
        if (!funcList.containsKey(funcName))
            throw new semanticException("Undefined Functions");
        return funcList.get(funcName);
    }

    public Boolean contain (String funcName)
    {
        return funcList.containsKey(funcName);
    }


}
