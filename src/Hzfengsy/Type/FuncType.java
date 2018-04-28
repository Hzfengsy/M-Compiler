package Hzfengsy.Type;

import Hzfengsy.Type.VarType.*;

import java.util.Vector;

public class FuncType
{
    private BaseType returnType;
    private Vector<BaseType> parameterList;
    private String funcName, funcLine;

    public FuncType(BaseType _returnType, Vector<BaseType> list, String _funcLine, String _funcName) {
        returnType = _returnType; parameterList = list; funcName = _funcName; funcLine = _funcLine;
    }

    public BaseType getReturnType() {
        return returnType;
    }

    public Vector<BaseType> getParameterList() {
        return parameterList;
    }

    public String getFuncLine() { return funcLine; }

    public String getFuncName() { return funcName; }
}
