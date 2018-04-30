package Hzfengsy.Semantic;

import Hzfengsy.Semantic.Type.*;

import java.util.*;

public class Functions
{
    private static Functions functions = new Functions();

    public static Functions getInstance() {
        return functions;
    }

    private Map<String, FuncType> funcList = new HashMap<>();

    public void insert(String funcName, FuncType type) {
        assert (!funcList.containsKey(funcName));
        funcList.put(funcName, type);
    }

    public FuncType query(String funcName) throws Exception {
        if (!funcList.containsKey(funcName))
            throw new Exception("Undefined Functions \"" + funcName + "\"");
        return funcList.get(funcName);
    }

    public Boolean contain(String funcName) {
        return funcList.containsKey(funcName);
    }


}
