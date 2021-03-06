package Hzfengsy.Semantic.Type;

import Hzfengsy.Semantic.Type.VarType.*;
import Hzfengsy.Semantic.*;

import java.util.*;

public class TypeChecker
{
    class Kind
    {
        String kind;

        Kind(String _kind) { kind = _kind; }
    }

    private static TypeChecker typeChecker = new TypeChecker();

    public static TypeChecker getInstance() {
        return typeChecker;
    }

    private Map<Kind, Vector<BaseType[]>> operators = new HashMap<>();
    private Classes classes = Classes.getInstance();

    private Vector<BaseType[]> SingleMatch(BaseType... baseType) {
        Vector<BaseType[]> ans = new Vector<>();
        ans.add(baseType);
        return ans;
    }

    private Vector<BaseType[]> IntStringMatch() {
        Vector<BaseType[]> ans = new Vector<>();
        ans.add(new BaseType[] { classes.intType, classes.intType });
        ans.add(new BaseType[] { classes.stringType, classes.stringType });
        return ans;
    }

    private Boolean check(Vector<BaseType[]> standard, BaseType... types) {
        for (BaseType[] request : standard) {
            if (request.length != types.length) continue;
            Boolean flag = true;
            for (int i = 0; i < request.length; i++)
                if (!types[i].assignCheck(request[i])) {
                    flag = false;
                    break;
                }
            if (flag) return true;
        }
        return false;
    }

    public Kind OneInt = new Kind("OneInt");
    public Kind Array = new Kind("Array");
    public Kind LNot = new Kind("LNot");
    public Kind BinaryArithmetic = new Kind("BinaryArithmetic");
    public Kind BinaryLogical = new Kind("BinaryLogical");
    public Kind Plus = new Kind("Plus");
    public Kind Compare = new Kind("Compare");
    public Kind Equal = new Kind("Equal");
    public Kind Assign = new Kind("Assign");

    public TypeChecker() {
        operators.put(OneInt, SingleMatch(classes.intType));
        operators.put(Array, SingleMatch(new ArrayType(null)));
        operators.put(LNot, SingleMatch(classes.boolType));
        operators.put(BinaryArithmetic, SingleMatch(classes.intType, classes.intType));
        operators.put(BinaryLogical, SingleMatch(classes.boolType, classes.boolType));
        operators.put(Plus, IntStringMatch());
        operators.put(Compare, IntStringMatch());
    }

    public boolean typeCheck(Kind kind, BaseType... types) {
        if (kind == Equal) {
            return types.length == 2 && types[0].compareCheck(types[1]);
        } else if (kind == Assign)
            return types.length == 2 && types[0].assignCheck(types[1]);
        if (operators.containsKey(kind)) return check(operators.get(kind), types);
        else return false;
    }
}
