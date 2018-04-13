package Hzfengsy.Listener;

import Hzfengsy.Parser.MBaseVisitor;
import Hzfengsy.Parser.MParser;
import Hzfengsy.Type.*;
import Hzfengsy.utility.*;

import java.util.*;

public class Visitor extends MBaseVisitor<IRBaseNode>
{

    private Variable variables = new Variable();
    private Function functions = new Function();
    private Vector<Map<String, String>> localVar = new Vector<>();
    private Classes classes = new Classes();

    private funcType func_getInt()
    {
        return new funcType(new intType(), new Vector<>());
    }

    private funcType func_print()
    {
        Vector<baseType> parameter = new Vector<>();
        parameter.add(new stringType());
        return new funcType(new voidType(), parameter);
    }

    private funcType func_println()
    {
        Vector<baseType> parameter = new Vector<>();
        parameter.add(new stringType());
        return new funcType(new voidType(), parameter);
    }

    private funcType func_getString()
    {
        return new funcType(new stringType(), new Vector<>());
    }

    private funcType func_toString()
    {
        Vector<baseType> parameter = new Vector<>();
        parameter.add(new intType());
        return new funcType(new stringType(), parameter);
    }

    private void loadInsideFunction()
    {
        functions.insert("getInt", func_getInt());
        functions.insert("print", func_print());
        functions.insert("println", func_println());
        functions.insert("getString", func_getString());
        functions.insert("toString", func_toString());
    }

    private Boolean equalClass(baseType a, baseType b)
    {
        return a.getClass().equals(b.getClass());
    }

    private Boolean checkType(Vector<IRBaseNode> parameter, baseType ... args)
    {
        if (parameter.size() != args.length) return false;
        for (int i = 0; i < args.length; i++)
        {
            baseType realType = parameter.elementAt(i).getType();
            baseType requestType = args[i];
            if (!equalClass(realType, requestType)) return false;
        }
        return true;
    }
    @Override public IRBaseNode visitProg(MParser.ProgContext ctx)
    {
        localVar.add(new HashMap<>());
        loadInsideFunction();
        return visitChildren(ctx);
    }

    //IF STATEMENT
    @Override public IRBaseNode visitIf_Stat(MParser.If_StatContext ctx)
    {
        localVar.add(new HashMap<>());
        IRBaseNode ans = visitChildren(ctx);
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override public IRBaseNode visitIfElse_Stat(MParser.IfElse_StatContext ctx)
    {
        localVar.add(new HashMap<>());
        IRBaseNode ans = visitChildren(ctx);
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override public IRBaseNode visitFor_Stat(MParser.For_StatContext ctx)
    {
        localVar.add(new HashMap<>());
        IRBaseNode ans = visitChildren(ctx);
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override public IRBaseNode visitWhile_Stat(MParser.While_StatContext ctx)
    {
        localVar.add(new HashMap<>());
        IRBaseNode ans = visitChildren(ctx);
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override public IRBaseNode visitFunc(MParser.FuncContext ctx)
    {
        localVar.add(new HashMap<>());
        String funcName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        Vector<baseType> list = visit(ctx.stat_list()).getTypeList();
        baseType returnType = null;
        try
        {
            returnType = classes.getClass(className);
            funcType type = new funcType(returnType, list);
            functions.insert(funcName, type);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        visit(ctx.stat());
        localVar.remove(localVar.size() - 1);
        return new IRTypeNode(returnType);
    }

    @Override public IRBaseNode visitStatListCombine(MParser.StatListCombineContext ctx)
    {
        Vector<baseType> list = visit(ctx.stat_list(0)).getTypeList();
        list.addAll(visit(ctx.stat_list(1)).getTypeList());
        return new IRTypeListNode(list);
    }

    @Override public IRBaseNode visitStatList(MParser.StatListContext ctx)
    {
        if (ctx.getText().equals("")) return new IRTypeListNode(new Vector<>());
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        String mappingName = variables.rename(varName);
        baseType type = null;
        try
        {
            type = classes.getClass(className);
            variables.insert(mappingName, type);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
        Vector<baseType> list = new Vector<>();
        list.add(type);
        return new IRTypeListNode(list);
    }

    @Override public IRBaseNode visitAssign_Define(MParser.Assign_DefineContext ctx)
    {
        String varName = ctx.assign().id().getText();
        String className = ctx.class_stat().getText();
        if (localVar.elementAt(localVar.size() - 1).containsKey(varName))
        {
            System.err.println("variable " + varName + " has been defined");
            System.exit(1);
        }
        String mappingName = variables.rename(varName);
        try
        {
            variables.insert(mappingName, classes.getClass(className));
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
        return new IRBaseNode();
    }

    @Override public IRBaseNode visitId_Define(MParser.Id_DefineContext ctx)
    {
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        String mappingName = variables.rename(varName);
        try
        {
            variables.insert(mappingName, classes.getClass(className));
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
        return new IRBaseNode();
    }

    @Override public IRBaseNode visitRAWID(MParser.RAWIDContext ctx)
    {
        String varName = ctx.getText();
        Boolean found = false;
        String rename = new String();
        for (int i = localVar.size() - 1; i >= 0; i--)
        {
            Map<String, String> local = localVar.elementAt(i);
            if (local.containsKey(varName))
            {
                found = true;
                rename = local.get(varName);
                break;
            }
        }
        try
        {
            if (found) return new IRTypeNode(variables.query(rename));
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        if (functions.contain(varName))
        {
            try
            {
                return new IRTypeNode(functions.query(varName).getReturnType());
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }

        System.err.println(("variable " + varName + " has not been defined"));
        System.exit(1);
        return new IRBaseNode();
    }

    @Override public IRBaseNode visitSubscript(MParser.SubscriptContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.id()));
        parameter.add(visit(ctx.expr()));
        Boolean checked = checkType(parameter, new arrayType(new voidType()), new intType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(parameter.elementAt(0).getType().getBaseTYpe());
    }

    @Override public IRBaseNode visitPostfix(MParser.PostfixContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr()));
        Boolean checked = checkType(parameter, new intType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new intType());
    }

    @Override public IRBaseNode visitFunction(MParser.FunctionContext ctx)
    {
        funcType func = null;
        try
        {
            func = functions.query(ctx.id().getText());
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        Vector<baseType> list = func.getParameterList();
        Vector<baseType> param = visit(ctx.expr_list()).getTypeList();
        if (!list.equals(param))
        {
            System.err.println("error function call at " + ctx.id().getText());
            System.exit(1);
        }
        return new IRTypeNode(func.getReturnType());
    }

    @Override public IRBaseNode visitPrefix(MParser.PrefixContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr()));
        Boolean checked = checkType(parameter, new intType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new intType());
    }

    @Override public IRBaseNode visitUnary(MParser.UnaryContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr()));
        Boolean checked = checkType(parameter, new intType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new intType());
    }

    @Override public IRBaseNode visitNot(MParser.NotContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr()));
        Boolean checked = checkType(parameter, ctx.op.getText() == "!" ? new boolType() : new intType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new intType());
    }

    @Override public IRBaseNode visitMulDivMod(MParser.MulDivModContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, new intType(), new intType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new intType());
    }

    @Override public IRBaseNode visitAddSub(MParser.AddSubContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, new intType(), new intType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new intType());
    }

    @Override public IRBaseNode visitBitwise(MParser.BitwiseContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, new intType(), new intType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new intType());
    }

    @Override public IRBaseNode visitCompare(MParser.CompareContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, new intType(), new intType())
                | checkType(parameter, new stringType(), new stringType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new boolType());
    }

    @Override public IRBaseNode visitEqual(MParser.EqualContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, new intType(), new intType())
                | checkType(parameter, new stringType(), new stringType())
                | checkType(parameter, new boolType(), new boolType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new boolType());
    }

    @Override public IRBaseNode visitAnd(MParser.AndContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, new intType(), new intType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new intType());
    }

    @Override public IRBaseNode visitXor(MParser.XorContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, new intType(), new intType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new intType());
    }

    @Override public IRBaseNode visitOr(MParser.OrContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, new intType(), new intType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new intType());
    }

    @Override public IRBaseNode visitLAnd(MParser.LAndContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, new boolType(), new boolType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new boolType());
    }

    @Override public IRBaseNode visitLOr(MParser.LOrContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, new boolType(), new boolType());
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(new boolType());
    }

    @Override public IRBaseNode visitNumber(MParser.NumberContext ctx)
    {
        return new IRTypeNode(new intType());
    }

    @Override public IRBaseNode visitStr(MParser.StrContext ctx)
    {
        return new IRTypeNode(new stringType());
    }

    @Override public IRBaseNode visitParens(MParser.ParensContext ctx)
    {
        return visit(ctx.expr());
    }



    @Override public IRBaseNode visitExprListCombine(MParser.ExprListCombineContext ctx)
    {
        Vector<baseType> list = visit(ctx.expr_list(0)).getTypeList();
        list.addAll(visit(ctx.expr_list(1)).getTypeList());
        return new IRTypeListNode(list);
    }

    @Override public IRBaseNode visitExprList(MParser.ExprListContext ctx)
    {
        Vector<baseType> list = new Vector<>();
        if (!ctx.getText().equals(""))
            list.add(visit(ctx.expr()).getType());
        return new IRTypeListNode(list);
    }
}
