package Hzfengsy.Visitor;

import Hzfengsy.Parser.MBaseVisitor;
import Hzfengsy.Parser.MParser;
import Hzfengsy.Type.*;
import Hzfengsy.utility.*;
import org.antlr.v4.runtime.ParserRuleContext;

import javax.imageio.stream.IIOByteBuffer;
import java.util.*;

public class mainVisitor extends MBaseVisitor<IRBaseNode>
{

    private Variable variables = new Variable();
    private Function functions;
    private Vector<Map<String, String>> localVar = new Vector<>();
    private Classes classes;
    private Stack<IRBaseNode> loopStack = new Stack<>();
    private Stack<IRBaseNode> functionStack = new Stack<>();
    private Stack<IRBaseNode> classStack = new Stack<>();
    private boolean hasError = false;

    public mainVisitor(Function _functions, Classes _classes)
    {
        functions = _functions; classes = _classes;
    }

    private void error(String message, ParserRuleContext ctx)
    {
        Integer line = ctx.getStart().getLine();
        Integer row = ctx.getStart().getCharPositionInLine();
        String location = line.toString() + ":" + row.toString();
        System.err.println(location + " " + message);
        hasError = true;
    }
    
    private boolean checkExprList(Vector<baseType> a, Vector<baseType> b)
    {
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++)
        {
            if (!checkAssignment(a.elementAt(i), b.elementAt(i))) return false;
        }
        return true;
    }

    private boolean checkAssignment(baseType a, baseType b)
    {
        return a.assign_check(b);
    }
    
    private boolean checkCompear(baseType a, baseType b)
    {
        return a.assign_check(b) || b.assign_check(a);
    }

    private boolean checkType(Vector<IRBaseNode> parameter, baseType ... args)
    {
        if (parameter.size() != args.length) return false;
        for (int i = 0; i < args.length; i++)
        {
            baseType realType = parameter.elementAt(i).getType();
            baseType requestType = args[i];
            if (!checkAssignment(requestType, realType)) return false;
        }
        return true;
    }

    @Override public IRBaseNode visitProg(MParser.ProgContext ctx)
    {
        localVar.add(new HashMap<>());
        visitChildren(ctx);
        return null;
    }

    @Override public IRBaseNode visitMain_prog(MParser.Main_progContext ctx)
    {
        visitChildren(ctx);
        if (hasError) System.exit(1);
        return null;
    }

    @Override public IRBaseNode visitClas(MParser.ClasContext ctx)
    {
        localVar.add(new HashMap<>());
        baseType clas = null;
        try { clas = classes.getClass(ctx.id().getText()); }
        catch (Exception e) { error(e.getMessage(), ctx); }
        IRBaseNode ans = new IRTypeNode(clas, true);
        classStack.push(ans);
        if (ctx.prog() != null) visit(ctx.prog());
        classStack.pop();
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    //IF STATEMENT
    @Override public IRBaseNode visitIf_Stat(MParser.If_StatContext ctx)
    {
        localVar.add(new HashMap<>());
        IRBaseNode boolcheck = visit(ctx.expr());
        try
        {
            if (boolcheck.getType() != classes.get("bool"))
                error("error during if stat", ctx);
        }catch (Exception e) { error(e.getMessage(), ctx); }

        IRBaseNode ans = visit(ctx.stat());
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override public IRBaseNode visitIfElse_Stat(MParser.IfElse_StatContext ctx)
    {
        localVar.add(new HashMap<>());
        IRBaseNode boolcheck = visit(ctx.expr());
        try
        {
            if (boolcheck.getType() != classes.get("bool"))
                error("error during if stat", ctx);
        }catch (Exception e) { error(e.getMessage(), ctx); }
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
            if (boolcheck.getType() != classes.get("bool"))
                error("error during for stat", ctx);
        }
        IRBaseNode ans = new IRBaseNode();
        loopStack.push(ans);
        visit(ctx.stat());
        loopStack.pop();
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override public IRBaseNode visitWhile_Stat(MParser.While_StatContext ctx)
    {
        localVar.add(new HashMap<>());
        IRBaseNode boolcheck = visit(ctx.expr());
        if (boolcheck.getType() != classes.get("bool"))
            error("error during for stat", ctx);
        IRBaseNode ans = new IRBaseNode();
        loopStack.push(ans);
        visit(ctx.stat());
        loopStack.pop();
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override public IRBaseNode visitFunc(MParser.FuncContext ctx)
    {
        localVar.add(new HashMap<>());
        String funcName = ctx.id().getText();
        funcType func = null;
        try
        {
            if (classStack.empty()) func = functions.query(funcName);
            else
            {
                userType userClass = (userType) classStack.peek().getType();
                func = userClass.queryFunc(funcName);
            }
        }
        catch (Exception e) {error(e.getMessage(), ctx);}
        visit(ctx.stat_list());
        IRBaseNode ans = new IRTypeNode(func.getReturnType(), false);
        functionStack.push(ans);
        for (MParser.StatContext x : ctx.stat()) visit(x);
        functionStack.pop();
        localVar.remove(localVar.size() - 1);
        return ans;
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
        if (localVar.elementAt(localVar.size() - 1).containsKey(varName) || functions.contain(varName))
            error("variable " + varName + " has been defined", ctx);
        try
        {
            if (!classes.containClass(className)) error("cannot find a class called \"" + className + "\"", ctx);
            type = classes.getClass(className);
            variables.insert(mappingName, type);
        }
        catch (Exception e) { error(e.getMessage(), ctx); }
        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
        Vector<baseType> list = new Vector<>();
        list.add(type);
        return new IRTypeListNode(list);
    }

    @Override public IRBaseNode visitAssign_Define(MParser.Assign_DefineContext ctx)
    {
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        if (className.equals("void")) error("cannot define void variable", ctx);
        baseType exprType = visit(ctx.expr()).getType();
        if (localVar.elementAt(localVar.size() - 1).containsKey(varName) || functions.contain(varName))
            error("variable " + varName + " has been defined", ctx);
        String mappingName = variables.rename(varName);
        try
        {
            variables.insert(mappingName, classes.getClass(className));
            if (!classes.getClass(className).assign_check(exprType)) error("define variable " + varName + " error", ctx);
        }
        catch (Exception e) { error(e.getMessage(), ctx); }
        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
        return new IRBaseNode();
    }

    @Override public IRBaseNode visitBreak_Stat(MParser.Break_StatContext ctx)
    {
        if (loopStack.empty()) error("break outside any loopStack", ctx);
        return null;
    }

    @Override public IRBaseNode visitContinue_Stat(MParser.Continue_StatContext ctx)
    {
        if (loopStack.empty()) error("continue outside any loopStack", ctx);
        return null;
    }

    @Override public IRBaseNode visitReturn_Stat(MParser.Return_StatContext ctx)
    {
        if (functionStack.empty()) error("return outside any loopStack", ctx);
        baseType func = functionStack.peek().getType();
        if (func.equals(classes.get("void")))
        {
            if (ctx.expr() != null) error("void function cannot have return value.", ctx);
        }
        else
        {
            if (ctx.expr() == null) error("Function must have a return value.", ctx);
            baseType exprType = visit(ctx.expr()).getType();
            if (!func.assign_check(exprType)) error("return value does not match.", ctx);
        }
        return null;

    }

    @Override public IRBaseNode visitSegment_Stat(MParser.Segment_StatContext ctx)
    {
        localVar.add(new HashMap<>());
        visitChildren(ctx);
        localVar.remove(localVar.size() - 1);
        return null;
    }

    @Override public IRBaseNode visitId_Define(MParser.Id_DefineContext ctx)
    {
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        if (className.equals("void")) error("cannot define void variable", ctx);
        if (localVar.elementAt(localVar.size() - 1).containsKey(varName) || functions.contain(varName))
            error("variable " + varName + " redefined", ctx);
        String mappingName = variables.rename(varName);
        try
        {
            variables.insert(mappingName, classes.getClass(className));
            if (!classStack.empty())
            {
                userType userClass = (userType)classStack.peek().getType();
                userClass.insertMemberVar(varName, classes.getClass(className));
            }
        }
        catch (Exception e) { error(e.getMessage(), ctx); }

        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
        return new IRBaseNode();
    }

    @Override public IRBaseNode visitRAWID(MParser.RAWIDContext ctx)
    {
        String varName = ctx.getText();
        boolean found = false;
        String rename = new String();
        baseType var = null;
        for (int i = localVar.size() - 1; i >= 0; i--)
        {
            if (i == 1 && !classStack.empty())
            {
                userType userClass = (userType) classStack.peek().getType();
                try
                {
                    var = userClass.queryVar(varName);
                    found = true;
                    break;
                }
                catch (Exception e) {}
                continue;
            }
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
            if (found)
            {
                if (var == null) var = variables.query(rename);
                return new IRTypeNode(var, true);
            }
        }
        catch (Exception e) { error(e.getMessage(), ctx); }

        if (functions.contain(varName))
        {
            try { return new IRTypeNode(functions.query(varName).getReturnType(), true); }
            catch (Exception e) { error(e.getMessage(), ctx); }
        }
        error("variable " + varName + " has not been defined", ctx);
        return null;
    }

    @Override public IRBaseNode visitSubscript(MParser.SubscriptContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        boolean checked = checkType(parameter, new arrayType(null), classes.get("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(parameter.elementAt(0).getType().getBaseType(), true);
    }

    @Override public IRBaseNode visitMembervar(MParser.MembervarContext ctx)
    {
        baseType Class = visit(ctx.expr()).getType();
        baseType var = null;
        try { var = Class.queryVar(ctx.id().getText()); }
        catch (Exception e) { error(e.getMessage(), ctx); }
        return new IRTypeNode(var, true);
    }

    @Override public IRBaseNode visitMemberfunc(MParser.MemberfuncContext ctx)
    {
        baseType Class = visit(ctx.expr()).getType();
        funcType func = null;
        try { func = Class.queryFunc(ctx.id().getText()); }
        catch (Exception e) { error(e.getMessage(), ctx); }
        Vector<baseType> list = func.getParameterList();
        Vector<baseType> param = visit(ctx.expr_list()).getTypeList();
        if (!checkExprList(list, param)) error("error function call at " + ctx.id().getText(), ctx);
        return new IRTypeNode(func.getReturnType(), false);
    }

    @Override public IRBaseNode visitPostfix(MParser.PostfixContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr()));
        if (!parameter.elementAt(0).isLeft()) error("left value error in \"" + ctx.getText() + "\"", ctx);
        boolean checked = checkType(parameter, classes.get("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(classes.get("int"), false);
    }

    @Override public IRBaseNode visitFunction(MParser.FunctionContext ctx)
    {
        funcType func = null;
        try
        {
            if (classStack.empty()) func = functions.query(ctx.id().getText());
            else
            {
                userType userClass = (userType)classStack.peek().getType();
                try { func = userClass.queryFunc(ctx.id().getText()); }
                catch (Exception e) { func = functions.query(ctx.id().getText()); }
            }
        }
        catch (Exception e) { error(e.getMessage(), ctx); }
        Vector<baseType> list = func.getParameterList();
        Vector<baseType> param = visit(ctx.expr_list()).getTypeList();
        if (!checkExprList(list, param)) error("error function call at " + ctx.id().getText(), ctx);
        return new IRTypeNode(func.getReturnType(), false);
    }

    @Override public IRBaseNode visitPrefix(MParser.PrefixContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr()));
        if (!parameter.elementAt(0).isLeft()) error("left value error in \"" + ctx.getText() + "\"", ctx);
        boolean checked = checkType(parameter, classes.get("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(classes.get("int"), false);
    }

    @Override public IRBaseNode visitUnary(MParser.UnaryContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr()));
        boolean checked = checkType(parameter, classes.get("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(classes.get("int"), false);
    }

    @Override public IRBaseNode visitNot(MParser.NotContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr()));
        boolean checked = checkType(parameter, ctx.op.getText().equals("!") ? classes.get("bool") : classes.get("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(ctx.op.getText().equals("!") ? classes.get("bool") : classes.get("int"), false);
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
        boolean checked = checkType(parameter, classes.get("int"), classes.get("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(classes.get("int"), false);
    }

    @Override public IRBaseNode visitAddSub(MParser.AddSubContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        boolean checked = checkType(parameter, classes.get("int"), classes.get("int"));
        if (ctx.op.getType() == MParser.ADD) checked |= checkType(parameter, classes.get("string"), classes.get("string"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(parameter.elementAt(0).getType(), false);
    }

    @Override public IRBaseNode visitBitwise(MParser.BitwiseContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        boolean checked = checkType(parameter, classes.get("int"), classes.get("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(classes.get("int"), false);
    }

    @Override public IRBaseNode visitCompare(MParser.CompareContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        boolean checked = checkType(parameter, classes.get("int"), classes.get("int"))
                | checkType(parameter, classes.get("string"), classes.get("string"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(classes.get("bool"), false);
    }

    @Override public IRBaseNode visitEqual(MParser.EqualContext ctx)
    {
        boolean checked = checkCompear(visit(ctx.expr(0)).getType(), visit(ctx.expr(1)).getType());
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(classes.get("bool"), false);
    }

    @Override public IRBaseNode visitAnd(MParser.AndContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        boolean checked = checkType(parameter, classes.get("int"), classes.get("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(classes.get("int"), false);
    }

    @Override public IRBaseNode visitXor(MParser.XorContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        boolean checked = checkType(parameter, classes.get("int"), classes.get("int"));
        if (!checked)
        {
            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
            System.exit(1);
        }
        return new IRTypeNode(classes.get("int"), false);
    }

    @Override public IRBaseNode visitOr(MParser.OrContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        boolean checked = checkType(parameter, classes.get("int"), classes.get("int"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(classes.get("int"), false);
    }

    @Override public IRBaseNode visitLAnd(MParser.LAndContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        boolean checked = checkType(parameter, classes.get("bool"), classes.get("bool"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(classes.get("bool"), false);
    }

    @Override public IRBaseNode visitLOr(MParser.LOrContext ctx)
    {
        Vector<IRBaseNode> parameter = new Vector<>();
        parameter.add(visit(ctx.expr(0)));
        parameter.add(visit(ctx.expr(1)));
        boolean checked = checkType(parameter, classes.get("bool"), classes.get("bool"));
        if (!checked) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
        return new IRTypeNode(classes.get("bool"), false);
    }

    @Override public IRBaseNode visitTrue(MParser.TrueContext ctx)
    {
        return new IRTypeNode(classes.get("bool"), false);
    }

    @Override public IRBaseNode visitFalse(MParser.FalseContext ctx)
    {
        return new IRTypeNode(classes.get("bool"), false);
    }

    @Override public IRBaseNode visitNumber(MParser.NumberContext ctx)
    {
        return new IRTypeNode(classes.get("int"), false);
    }

    @Override public IRBaseNode visitStr(MParser.StrContext ctx)
    {
        return new IRTypeNode(classes.get("string"), false);
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
            else if (check) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
            classname += "[]";
        }
        try { return new IRTypeNode(classes.getClass(classname), true); }
        catch (Exception e) { error(e.getMessage(), ctx); }
        return null;
    }

    @Override public IRBaseNode visitAssignment(MParser.AssignmentContext ctx)
    {
        IRBaseNode left = visit(ctx.expr(0));
        IRBaseNode right = visit(ctx.expr(1));
        if (!checkAssignment(left.getType(), right.getType())) error("assign type error in \"" + ctx.getText() + "\"", ctx);
        if (!left.isLeft()) error("left value error", ctx);
        return new IRTypeNode(left.getType(), false);
    }

    @Override public IRBaseNode visitNull(MParser.NullContext ctx)
    {
        return new IRTypeNode(classes.get("null"), false);
    }

    @Override public IRBaseNode visitThis(MParser.ThisContext ctx)
    {
        return new IRTypeNode(classStack.peek().getType(), false);
    }
}
