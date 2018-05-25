package Hzfengsy.Semantic.Type.VarType;

import Hzfengsy.Semantic.Type.*;

import java.util.*;

public class StringType extends BaseType
{
    private FuncType func_length() {
        return new FuncType(new IntType(), new Vector<>(), new Vector<>(), "int length()", "length");
    }

    private FuncType func_substring() {
        Vector<BaseType> parameter = new Vector<>();
        parameter.add(new IntType());
        parameter.add(new IntType());
        Vector<String> args = new Vector<>();
        args.add("left");
        args.add("right");
        return new FuncType(this, parameter, args, "string substring(int left, int right)", "substring");
    }

    private FuncType func_parseInt() {
        return new FuncType(new IntType(), new Vector<>(), new Vector<>(), "int parseInt()", "parseInt");
    }

    private FuncType func_ord() {
        Vector<BaseType> parameter = new Vector<>();
        parameter.add(new IntType());
        Vector<String> args = new Vector<>();
        args.add("pos");
        return new FuncType(new IntType(), parameter, args, "int ord(int pos)", "ord");
    }

    public StringType() {
        memberFunc.put("length", func_length());
        memberFunc.put("substring", func_substring());
        memberFunc.put("parseInt", func_parseInt());
        memberFunc.put("ord", func_ord());
    }

    @Override
    public String toString() {
        return "string";
    }
}
