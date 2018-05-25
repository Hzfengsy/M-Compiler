package Hzfengsy.Semantic.Type;

import Hzfengsy.Semantic.Type.VarType.*;
import javafx.util.*;

import java.util.*;

public class FuncType
{
    private BaseType returnType;
    private Vector<BaseType> parameterList;
    private Vector<String> parameterName;
    private String funcName, funcLine;
    private Vector<Pair<String, BaseType>> vars = new Vector<>();

    public FuncType(BaseType _returnType, Vector<BaseType> list, String _funcLine, String _funcName) {
        returnType = _returnType;
        parameterList = list;
        funcName = _funcName;
        funcLine = _funcLine;
    }

    public FuncType(BaseType _returnType, Vector<BaseType> list, Vector<String> name, String _funcLine, String _funcName) {
        returnType = _returnType;
        parameterList = list;
        parameterName = name;
        funcName = _funcName;
        funcLine = _funcLine;
    }

    public BaseType getReturnType() {
        return returnType;
    }

    public void setParameterName(Vector<String> parameterName) {
        this.parameterName = parameterName;
    }

    public Vector<BaseType> getParameterList() {
        return parameterList;
    }

    public Vector<String> getParameterName() {
        return parameterName;
    }

    public String getFuncLine() { return funcLine; }

    public String getFuncName() { return funcName; }

    public void insertVar(String name, BaseType type) {
        vars.add(new Pair<>(name, type));
    }

    public Vector<Pair<String, BaseType>> getVars() {
        return vars;
    }

}
