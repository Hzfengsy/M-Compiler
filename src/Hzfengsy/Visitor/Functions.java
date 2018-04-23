package Hzfengsy.Visitor;

import Hzfengsy.Exceptions.SemanticException;
import Hzfengsy.Type.FuncType;

import java.util.HashMap;
import java.util.Map;

public class Functions
{

    private Map<String, FuncType> funcList = new HashMap();

    public void insert(String funcName, FuncType type) {
        assert (!funcList.containsKey(funcName)); funcList.put(funcName, type);
    }

    public FuncType query(String funcName) throws Exception {
        if (!funcList.containsKey(funcName)) throw new SemanticException("Undefined Functions \"" + funcName + "\"");
        return funcList.get(funcName);
    }

    public Boolean contain(String funcName) {
        return funcList.containsKey(funcName);
    }


}
