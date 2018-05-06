package Hzfengsy.IR;

import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.IR.IRNode.*;
import Hzfengsy.IR.IRType.*;
import Hzfengsy.Parser.*;
import Hzfengsy.Semantic.*;
import Hzfengsy.Semantic.Type.*;
import Hzfengsy.Semantic.Type.VarType.*;
import javafx.util.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.*;


public class IRGenerator extends MBaseVisitor<IRBaseNode>
{
    private TypeMap typeMap = TypeMap.getInstance();
    private Classes classes = Classes.getInstance();
    private Functions functions = Functions.getInstance();
    private Stack<IRFuncNode> funcStack = new Stack<>();

    private IRBaseType[] getIRParameter(FuncType func) {
        Vector<BaseType> parameterList = func.getParameterList();
        IRBaseType[] ans = new IRBaseType[parameterList.size()];
        for (int i = 0; i < parameterList.size(); i++)
            ans[i] = typeMap.exchange(parameterList.elementAt(i));
        return ans;
    }

    @Override
    public IRBaseNode visitMain_prog(MParser.Main_progContext ctx) {
        System.out.println(visit(ctx.prog()).toString());
        return null;
    }

    @Override
    public IRBaseNode visitProg(MParser.ProgContext ctx) {
        IRBaseNode ans = new IRBaseNode();
        for (ParseTree x : ctx.func()) {
            ans.appendNextNode(visit(x));
        }
        return ans;
    }

    @Override
    public IRBaseNode visitClas(MParser.ClasContext ctx) {
        return null;
    }

    @Override
    public IRBaseNode visitFunc(MParser.FuncContext ctx) {
        String funcName = ctx.id().getText();
        FuncType funcType = functions.safeQuery(funcName);
        IRBaseType[] parameter = getIRParameter(funcType);
        IRFuncNode func = new IRFuncNode(typeMap.exchange(funcType.getReturnType()), funcName, parameter);
        for (Pair<String, BaseType> x : funcType.getVars()) {
            func.defineVar(x.getValue(), x.getKey());
        }
        func.storeArgs();
        funcStack.push(func);
        for (MParser.StatContext x : ctx.stat()) visit(x);
        return func;
    }

    @Override
    public IRBaseNode visitIdentity(MParser.IdentityContext ctx) {
        IRVar result = funcStack.peek().tempVar(classes.intType);
//        IRLoadInstruction inst = new IRLoadInstruction(result, type,  ,4);
//        return IR
        return null;
    }
}
