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
    private Stack<baseType> nowClass = new Stack<>();
    private Stack<IRBaseNode> loop = new Stack<>();

    private void error(String message)
    {
        System.err.println(message);
        System.exit(1);
    }

    private boolean checkMainFunc()
    {
        try
        {
            funcType funcMain = functions.query("main");
            if (funcMain.getReturnType() != classes.getClass("int")) return false;
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    private funcType func_getInt()
    {
        return new funcType(classes.getClass("int"), new Vector<>());
    }

    private funcType func_print()
    {
        Vector<baseType> parameter = new Vector<>();
        parameter.add(classes.getClass("string"));
        return new funcType(classes.getClass("string"), parameter);
    }

    private funcType func_println()
    {
        Vector<baseType> parameter = new Vector<>();
        parameter.add(classes.getClass("string"));
        return new funcType(classes.getClass("void"), parameter);
    }

    private funcType func_getString()
    {
        return new funcType(classes.getClass("string"), new Vector<>());
    }

    private funcType func_toString()
    {
        Vector<baseType> parameter = new Vector<>();
        parameter.add(classes.getClass("int"));
        return new funcType(classes.getClass("string"), parameter);
    }

    private void loadInsideFunction()
    {
        functions.insert("getInt", func_getInt());
        functions.insert("print", func_print());
        functions.insert("println", func_println());
        functions.insert("getString", func_getString());
        functions.insert("toString", func_toString());
    }

    private boolean equalClass(baseType a, baseType b)
    {
        return a.equals(b);
    }

    private boolean checkType(Vector<IRBaseNode> parameter, baseType ... args)
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
        visitChildren(ctx);
        if (!checkMainFunc()) error("main function error");
        return null;
    }

    @Override public IRBaseNode visitClas(MParser.ClasContext ctx)
    {
        baseType clas = null;
        try
        { clas = classes.defineClass(ctx.id().getText()); }
        catch (Exception e) { error(e.getMessage()); }
        nowClass.push(clas);
        return visit(ctx.prog());
    }

    //IF STATEMENT
    @Override public IRBaseNode visitIf_Stat(MParser.If_StatContext ctx)
    {
        localVar.add(new HashMap<>());
        IRBaseNode boolcheck = visit(ctx.expr());
        if (boolcheck.getType() != classes.getClass("bool"))
            error("error during if stat");
        IRBaseNode ans = visit(ctx.stat());
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override public IRBaseNode visitIfElse_Stat(MParser.IfElse_StatContext ctx)
    {
        localVar.add(new HashMap<>());
        IRBaseNode boolcheck = visit(ctx.expr());
        if (boolcheck.getType() != classes.getClass("bool"))
            error("error during if stat");
        IRBaseNode ans = visit(ctx.stat(0));
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override public IRBaseNode visitFor_Stat(MParser.For_StatContext ctx)
    {
        localVar.add(new HashMap<>());
        if (ctx.second != null)
        {
            IRBaseNode boolcheck = visit(ctx.second);
            if (boolcheck.getType() != classes.getClass("bool"))
                error("error during for stat");
        }
        IRBaseNode ans = new IRBaseNode();
        loop.push(ans);
        visit(ctx.stat());
        loop.pop();
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override public IRBaseNode visitWhile_Stat(MParser.While_StatContext ctx)
    {
        localVar.add(new HashMap<>());
        IRBaseNode boolcheck = visit(ctx.expr());
        if (boolcheck.getType() != classes.getClass("bool"))
            error("error during for stat");
        IRBaseNode ans = new IRBaseNode();
        loop.push(ans);
        visit(ctx.stat());
        loop.pop();
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
        catch (Exception e) { error(e.getMessage()); }
        visit(ctx.stat());
        localVar.remove(localVar.size() - 1);
        return new IRTypeNode(returnType, false);
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
        catch (Exception e) { error(e.getMessage()); }
        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
        Vector<baseType> list = new Vector<>();
        list.add(type);
        return new IRTypeListNode(list);
    }

    @Override public IRBaseNode visitAssign_Define(MParser.Assign_DefineContext ctx)
    {
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        if (className.equals("void")) error("cannot define void variable");
        if (localVar.elementAt(localVar.size() - 1).containsKey(varName))
            error("variable " + varName + " redefined");
        baseType exprType = visit(ctx.expr()).getType();
        if (localVar.elementAt(localVar.size() - 1).containsKey(varName))
            error("variable " + varName + " has been defined");
        String mappingName = variables.rename(varName);
        try
        {
            variables.insert(mappingName, classes.getClass(className));
            if (exprType != classes.getClass(className)) error("define variable " + varName + " error");
        }
        catch (Exception e) { error(e.getMessage()); }
        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
        return new IRBaseNode();
    }

    @Override public IRBaseNode visitBreak_Stat(MParser.Break_StatContext ctx)
    {
        if (loop.empty()) error("break outside any loop");
        return null;
    }

    @Override public IRBaseNode visitContinue_Stat(MParser.Continue_StatContext ctx)
    {
        if (loop.empty()) error("continue outside any loop");
        return null;
    }

    @Override public IRBaseNode visitId_Define(MParser.Id_DefineContext ctx)
    {
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        if (className.equals("void")) error("cannot define void variable");
        if (localVar.elementAt(localVar.size() - 1).containsKey(varName))
            error("variable " + varName + " redefined");
        String mappingName = variables.rename(varName);
        try { variables.insert(mappingName, classes.getClass(className));}
        catch (Exception e) { error(e.getMessage()); }

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
            if (found) return new IRTypeNode(variables.query(rename), true);
        }
        catch (Exception e) { error(e.getMessage()); }

        if (functions.contain(varName))
        {
            try { return new IRTypeNode(functions.query(varName).getReturnType(), true); }
            catch (Exception e) { error(e.getMessage()); }
        }
        error("variable " + varName + " has not been defined");
        return null;
    }

    @Override public IRBaseNode visitSubscript(MParser.SubscriptContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, new arrayType(null), classes.getClass("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(parameter.elementAt(0).getType().getBaseTYpe(), true);
    }

    @Override public IRBaseNode visitMembervar(MParser.MembervarContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr()));
        parameter.add(visit(ctx.id()));
        Boolean checked = checkType(parameter, new arrayType(null), classes.getClass("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(parameter.elementAt(0).getType().getBaseTYpe(), true);
    }

    @Override public IRBaseNode visitMemberfunc(MParser.MemberfuncContext ctx)
    {
        baseType Class = visit(ctx.expr()).getType();
        funcType func = null;
        try
        {
            func = Class.query(ctx.id().getText());
        }
        catch (Exception e) { error(e.getMessage()); }
        Vector<baseType> list = func.getParameterList();
        Vector<baseType> param = visit(ctx.expr_list()).getTypeList();
        if (!list.equals(param)) error("error function call at " + ctx.id().getText());
        return new IRTypeNode(func.getReturnType(), false);
    }

    @Override public IRBaseNode visitPostfix(MParser.PostfixContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr()));
        Boolean checked = checkType(parameter, classes.getClass("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(classes.getClass("int"), false);
    }

    @Override public IRBaseNode visitFunction(MParser.FunctionContext ctx)
    {
        funcType func = null;
        try
        {
            func = functions.query(ctx.id().getText());
        }
        catch (Exception e) { error(e.getMessage()); }
        Vector<baseType> list = func.getParameterList();
        Vector<baseType> param = visit(ctx.expr_list()).getTypeList();
        if (!list.equals(param)) error("error function call at " + ctx.id().getText());
        return new IRTypeNode(func.getReturnType(), false);
    }

    @Override public IRBaseNode visitPrefix(MParser.PrefixContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr()));
        Boolean checked = checkType(parameter, classes.getClass("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(classes.getClass("int"), false);
    }

    @Override public IRBaseNode visitUnary(MParser.UnaryContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr()));
        Boolean checked = checkType(parameter, classes.getClass("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(classes.getClass("int"), false);
    }

    @Override public IRBaseNode visitNot(MParser.NotContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr()));
        Boolean checked = checkType(parameter, ctx.op.getText().equals("!") ? classes.getClass("bool") : classes.getClass("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(ctx.op.getText().equals("!") ? classes.getClass("bool") : classes.getClass("int"), false);
    }

    @Override public IRBaseNode visitNew(MParser.NewContext ctx)
    {
        return visit(ctx.class_new());
    }

    @Override public IRBaseNode visitMulDivMod(MParser.MulDivModContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, classes.getClass("int"), classes.getClass("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(classes.getClass("int"), false);
    }

    @Override public IRBaseNode visitAddSub(MParser.AddSubContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, classes.getClass("int"), classes.getClass("int"));
        if (ctx.op.getType() == MParser.ADD) checked |= checkType(parameter, classes.getClass("string"), classes.getClass("string"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(parameter.elementAt(0).getType(), false);
    }

    @Override public IRBaseNode visitBitwise(MParser.BitwiseContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, classes.getClass("int"), classes.getClass("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(classes.getClass("int"), false);
    }

    @Override public IRBaseNode visitCompare(MParser.CompareContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, classes.getClass("int"), classes.getClass("int"))
                | checkType(parameter, classes.getClass("string"), classes.getClass("string"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(classes.getClass("bool"), false);
    }

    @Override public IRBaseNode visitEqual(MParser.EqualContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, classes.getClass("int"), classes.getClass("int"))
                | checkType(parameter, classes.getClass("string"), classes.getClass("string"))
                | checkType(parameter, classes.getClass("bool"), classes.getClass("bool"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(classes.getClass("bool"), false);
    }

    @Override public IRBaseNode visitAnd(MParser.AndContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, classes.getClass("int"), classes.getClass("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(classes.getClass("int"), false);
    }

    @Override public IRBaseNode visitXor(MParser.XorContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, classes.getClass("int"), classes.getClass("int"));
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(classes.getClass("int"), false);
    }

    @Override public IRBaseNode visitOr(MParser.OrContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, classes.getClass("int"), classes.getClass("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(classes.getClass("int"), false);
    }

    @Override public IRBaseNode visitLAnd(MParser.LAndContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, classes.getClass("bool"), classes.getClass("bool"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(classes.getClass("bool"), false);
    }

    @Override public IRBaseNode visitLOr(MParser.LOrContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        Boolean checked = checkType(parameter, classes.getClass("bool"), classes.getClass("bool"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"");
        return new IRTypeNode(classes.getClass("bool"), false);
    }

    @Override public IRBaseNode visitTrue(MParser.TrueContext ctx)
    {
        return new IRTypeNode(classes.getClass("bool"), false);
    }

    @Override public IRBaseNode visitFalse(MParser.FalseContext ctx)
    {
        return new IRTypeNode(classes.getClass("bool"), false);
    }

    @Override public IRBaseNode visitNumber(MParser.NumberContext ctx)
    {
        return new IRTypeNode(classes.getClass("int"), false);
    }

    @Override public IRBaseNode visitStr(MParser.StrContext ctx)
    {
        return new IRTypeNode(classes.getClass("string"), false);
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

    @Override public IRBaseNode visitClass_new(MParser.Class_newContext ctx)
    {
        String classname = ctx.class_name().getText();
        boolean check = false;
        for (MParser.DimensionContext x : ctx.dimension())
        {
            if (x.getText().equals("[]")) check = true;
            else if (check) error("Type error occupied during expr \"" + ctx.getText() + "\"");
            classname += "[]";
        }
        return new IRTypeNode(classes.getClass(classname), true);
    }

    @Override public IRBaseNode visitAssignment(MParser.AssignmentContext ctx)
    {
        IRBaseNode left = visit(ctx.expr(0));
        IRBaseNode right = visit(ctx.expr(1));
        if (!equalClass(left.getType(), right.getType())) error("assign type error in \"" + ctx.getText() + "\"");
        if (!left.isLeft()) error("left value error");
        return new IRTypeNode(left.getType(), false);
    }

    @Override public IRBaseNode visitNull(MParser.NullContext ctx)
    {
        return new IRTypeNode(classes.getClass("null"), false);
    }
}
