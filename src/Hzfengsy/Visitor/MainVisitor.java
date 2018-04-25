package Hzfengsy.Visitor;

import Hzfengsy.Exceptions.*;
import Hzfengsy.Parser.*;
import Hzfengsy.Type.*;
import Hzfengsy.Utility.*;
import org.antlr.v4.runtime.*;

import java.util.*;

public class MainVisitor extends MBaseVisitor<IRBaseNode>
{

    private Variable variables = new Variable();
    private Functions functions;
    private Vector<Map<String, String>> localVar = new Vector<>();
    private Classes classes;
    private ErrorReporter reporter;
    private Stack<IRBaseNode> loopStack = new Stack<>();
    private Stack<IRBaseNode> functionStack = new Stack<>();
    private Stack<IRBaseNode> classStack = new Stack<>();
    private TypeChecker typeChecker;

    public MainVisitor(Functions _functions, Classes _classes, ErrorReporter _reporter) {
        functions = _functions;
        classes = _classes;
        reporter = _reporter;
        typeChecker = new TypeChecker(classes);
    }

    private void error(String message, ParserRuleContext ctx) {
        BaseType inClass = classStack.empty() ? null : classStack.peek().getType();
        Integer start = ctx.getStart().getCharPositionInLine();
        Integer stop = ctx.getStop().getCharPositionInLine() + ctx.getStop().getText().length();
        FuncType inFunction = functionStack.empty() ? null : functionStack.peek().getFunc();
        reporter.reportError(message, inClass, inFunction, ctx.getStart().getLine(), start, stop);
    }

    private void typeError(BaseType expr, BaseType require, ParserRuleContext ctx) {
        error("expected a \'" + require + "\' expression instead of a \'" + expr + "\' expression", ctx);
    }

    private void operationError(String op, BaseType expr0, BaseType expr1, ParserRuleContext ctx) {
        error("could not do operation \'" + op + "\' between \'" + expr0 + "\' and \'" + expr1 + "\'", ctx);
    }

    private void leftError(String expr, ParserRuleContext ctx) {
        error("\'" + expr + "\' is not a left value", ctx);
    }

    private boolean checkExprList(Vector<BaseType> a, Vector<BaseType> b) {
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++) {
            if (!a.elementAt(i).assignCheck(b.elementAt(i))) return false;
        }
        return true;
    }

    @Override
    public IRBaseNode visitProg(MParser.ProgContext ctx) {
        localVar.add(new HashMap<>());
        visitChildren(ctx);
        return null;
    }

    @Override
    public IRBaseNode visitMain_prog(MParser.Main_progContext ctx) {
        visitChildren(ctx);
        return null;
    }

    @Override
    public IRBaseNode visitClas(MParser.ClasContext ctx) {
        localVar.add(new HashMap<>());
        try {
            BaseType clas = classes.getClass(ctx.id().getText());
            IRBaseNode ans = new IRTypeNode(clas, true);
            classStack.push(ans);
            if (ctx.prog() != null) visit(ctx.prog());
            classStack.pop();
            localVar.remove(localVar.size() - 1);
            return ans;
        } catch (NullPointerException e) {} catch (Exception e) { error(e.getMessage(), ctx); }
        return null;
    }

    //IF STATEMENT
    @Override
    public IRBaseNode visitIf_Stat(MParser.If_StatContext ctx) {
        localVar.add(new HashMap<>());
        IRBaseNode boolCheck = visit(ctx.expr());
        if (boolCheck.getType() != classes.get("bool"))
            typeError(boolCheck.getType(), classes.get("bool"), ctx.expr());
        IRBaseNode ans = visit(ctx.stat());
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override
    public IRBaseNode visitIfElse_Stat(MParser.IfElse_StatContext ctx) {
        localVar.add(new HashMap<>());
        IRBaseNode boolCheck = visit(ctx.expr());
        if (boolCheck.getType() != classes.get("bool"))
            typeError(boolCheck.getType(), classes.get("bool"), ctx.expr());
        IRBaseNode ans = visit(ctx.stat(0));
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override
    public IRBaseNode visitFor_Stat(MParser.For_StatContext ctx) {
        localVar.add(new HashMap<>());
        if (ctx.first != null) visit(ctx.first);
        if (ctx.second != null) {
            IRBaseNode boolCheck = visit(ctx.second);
            if (boolCheck.getType() != classes.get("bool"))
                typeError(boolCheck.getType(), classes.get("bool"), ctx.second);
        }
        if (ctx.third != null) visit(ctx.third);
        IRBaseNode ans = new IRBaseNode();
        loopStack.push(ans);
        visit(ctx.stat());
        loopStack.pop();
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override
    public IRBaseNode visitWhile_Stat(MParser.While_StatContext ctx) {
        localVar.add(new HashMap<>());
        IRBaseNode boolCheck = visit(ctx.expr());
        if (boolCheck.getType() != classes.get("bool"))
            typeError(boolCheck.getType(), classes.get("bool"), ctx.expr());
        IRBaseNode ans = new IRBaseNode();
        loopStack.push(ans);
        visit(ctx.stat());
        loopStack.pop();
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override
    public IRBaseNode visitFunc(MParser.FuncContext ctx) {
        localVar.add(new HashMap<>());
        String funcName = ctx.id().getText();
        FuncType func = null;
        try {
            if (classStack.empty()) func = functions.query(funcName);
            else {
                UserType userClass = (UserType) classStack.peek().getType();
                func = userClass.queryFunc(funcName);
            }
            IRBaseNode ans = new IRFuncNode(func);
            visit(ctx.stat_list());
            functionStack.push(ans);
            for (MParser.StatContext x : ctx.stat()) visit(x);
            functionStack.pop();
            localVar.remove(localVar.size() - 1);
            return ans;
        } catch (NullPointerException e) {} catch (Exception e) {}
        return null;
    }

    @Override
    public IRBaseNode visitStatListCombine(MParser.StatListCombineContext ctx) {
        Vector<BaseType> list = visit(ctx.stat_list(0)).getTypeList();
        list.addAll(visit(ctx.stat_list(1)).getTypeList());
        return new IRTypeListNode(list);
    }

    @Override
    public IRBaseNode visitStatList(MParser.StatListContext ctx) {
        if (ctx.getText().equals("")) return new IRTypeListNode(new Vector<>());
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        String mappingName = variables.rename(varName);
        BaseType type = null;
        try {
            type = classes.getClass(className);
            variables.insert(mappingName, type);
            localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
            Vector<BaseType> list = new Vector<>();
            list.add(type);
            return new IRTypeListNode(list);
        } catch (NullPointerException e) {} catch (Exception e) { error(e.getMessage(), ctx);}
        return null;
    }

    @Override
    public IRBaseNode visitId_Define(MParser.Id_DefineContext ctx) {
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        if (className.equals("void")) error("cannot define void variable", ctx);
        if (localVar.elementAt(localVar.size() - 1).containsKey(varName) || functions.contain(varName))
            error("variable " + varName + " redefined", ctx);
        String mappingName = variables.rename(varName);
        try {
            variables.insert(mappingName, classes.getClass(className));
            if (!classStack.empty()) {
                UserType userClass = (UserType) classStack.peek().getType();
                userClass.insertMemberVar(varName, classes.getClass(className));
            }
        } catch (Exception e) { error(e.getMessage(), ctx); }

        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
        return new IRBaseNode();
    }

    @Override
    public IRBaseNode visitAssign_Define(MParser.Assign_DefineContext ctx) {
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        if (className.equals("void")) error("cannot define void variable", ctx);
        BaseType exprType = visit(ctx.expr()).getType();
        if (localVar.elementAt(localVar.size() - 1).containsKey(varName) || functions.contain(varName))
            error("variable \'" + varName + "\' has been defined", ctx);
        String mappingName = variables.rename(varName);
        try {
            variables.insert(mappingName, classes.getClass(className));
            if (!classes.getClass(className).assignCheck(exprType))
                typeError(exprType, classes.getClass(className), ctx);
            localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
        } catch (NullPointerException e) {} catch (Exception e) { error(e.getMessage(), ctx); }
        return null;
    }

    @Override
    public IRBaseNode visitBreak_Stat(MParser.Break_StatContext ctx) {
        if (loopStack.empty()) error("could not use \'break\' outside loops", ctx);
        return null;
    }

    @Override
    public IRBaseNode visitContinue_Stat(MParser.Continue_StatContext ctx) {
        if (loopStack.empty()) error("could not use \'continue\' outside loops", ctx);
        return null;
    }

    @Override
    public IRBaseNode visitReturn_Stat(MParser.Return_StatContext ctx) {
        if (functionStack.empty()) error("could not use \'return\' outside functions", ctx);
        BaseType func = functionStack.peek().getType();
        if (func.equals(classes.get("void"))) {
            if (ctx.expr() != null) error("void function cannot have a return value.", ctx);
        }
        else {
            if (ctx.expr() == null) error("Functions must have a return value.", ctx);
            BaseType exprType = visit(ctx.expr()).getType();
            if (!func.assignCheck(exprType))
                typeError(exprType, func, ctx);
        }
        return null;

    }

    @Override
    public IRBaseNode visitSegment_Stat(MParser.Segment_StatContext ctx) {
        localVar.add(new HashMap<>());
        visitChildren(ctx);
        localVar.remove(localVar.size() - 1);
        return null;
    }

    @Override
    public IRBaseNode visitRAWID(MParser.RAWIDContext ctx) {
        String varName = ctx.getText();
        boolean found = false;
        String rename = new String();
        BaseType var = null;
        for (int i = localVar.size() - 1; i >= 0; i--) {
            if (i == 1 && !classStack.empty()) {
                UserType userClass = (UserType) classStack.peek().getType();
                try {
                    var = userClass.queryVar(varName);
                    found = true;
                    break;
                } catch (Exception e) {}
                continue;
            }
            Map<String, String> local = localVar.elementAt(i);
            if (local.containsKey(varName)) {
                found = true;
                rename = local.get(varName);
                break;
            }
        }
        try {
            if (found) {
                if (var == null) var = variables.query(rename);
                return new IRTypeNode(var, true);
            }
        } catch (Exception e) { error(e.getMessage(), ctx); }

        if (functions.contain(varName)) {
            try {
                return new IRTypeNode(functions.query(varName).getReturnType(), true);
            } catch (Exception e) { error(e.getMessage(), ctx); }
        }
        error("variable \'" + varName + "\' has not been defined", ctx);
        return null;
    }

    @Override
    public IRBaseNode visitSubscript(MParser.SubscriptContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans0 = expr0 instanceof ArrayType;
        if (!ans0) {
            typeError(expr0, new ArrayType(null), ctx);
            return new IRTypeNode(expr0, false);
        }
        else if (typeChecker.typeCheck(typeChecker.OneInt, expr0))
            typeError(expr0, classes.get("int"), ctx);
        return new IRTypeNode(expr0.getBaseType(), true);
    }

    @Override
    public IRBaseNode visitMembervar(MParser.MembervarContext ctx) {
        BaseType Class = visit(ctx.expr()).getType();
        BaseType var = null;
        try { var = Class.queryVar(ctx.id().getText()); } catch (Exception e) {
            error(e.getMessage(), ctx);
        }
        return new IRTypeNode(var, true);
    }

    @Override
    public IRBaseNode visitMemberfunc(MParser.MemberfuncContext ctx) {
        BaseType Class = visit(ctx.expr()).getType();
        FuncType func = null;
        try { func = Class.queryFunc(ctx.id().getText()); } catch (Exception e) {
            error(e.getMessage(), ctx);
        }
        Vector<BaseType> list = func.getParameterList();
        Vector<BaseType> param = visit(ctx.expr_list()).getTypeList();
        if (!checkExprList(list, param))
            error("error function call at \'" + ctx.id().getText() + "\'", ctx);
        return new IRTypeNode(func.getReturnType(), false);
    }

    @Override
    public IRBaseNode visitPostfix(MParser.PostfixContext ctx) {
        IRBaseNode left = visit(ctx.expr());
        BaseType expr = left.getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.OneInt, expr);
        if (!ans) typeError(expr, classes.get("int"), ctx);
        if (!left.isLeft()) leftError(ctx.expr().getText(), ctx);
        return new IRTypeNode(expr, false);
    }

    @Override
    public IRBaseNode visitFunction(MParser.FunctionContext ctx) {
        FuncType func = null;
        try {
            if (classStack.empty()) func = functions.query(ctx.id().getText());
            else {
                UserType userClass = (UserType) classStack.peek().getType();
                try { func = userClass.queryFunc(ctx.id().getText()); } catch (Exception e) {
                    func = functions.query(ctx.id().getText());
                }
            }
        } catch (Exception e) { error(e.getMessage(), ctx); }
        Vector<BaseType> list = func.getParameterList();
        Vector<BaseType> param = visit(ctx.expr_list()).getTypeList();
        if (!checkExprList(list, param))
            error("error function call at \'" + ctx.id().getText() + "\'", ctx);
        return new IRTypeNode(func.getReturnType(), false);
    }

    @Override
    public IRBaseNode visitPrefix(MParser.PrefixContext ctx) {
        IRBaseNode left = visit(ctx.expr());
        BaseType expr = left.getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.OneInt, expr);
        if (!ans) typeError(expr, classes.get("int"), ctx);
        if (!left.isLeft()) leftError(ctx.expr().getText(), ctx);
        return new IRTypeNode(expr, false);
    }

    @Override
    public IRBaseNode visitUnary(MParser.UnaryContext ctx) {
        BaseType expr = visit(ctx.expr()).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.OneInt, expr);
        if (!ans) typeError(expr, classes.get("int"), ctx);
        return new IRTypeNode(expr, false);
    }

    @Override
    public IRBaseNode visitNot(MParser.NotContext ctx) {
        BaseType expr = visit(ctx.expr()).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.OneInt, expr);
        if (!ans) typeError(expr, classes.get("int"), ctx);
        return new IRTypeNode(expr, false);
    }

    @Override
    public IRBaseNode visitLNot(MParser.LNotContext ctx) {
        BaseType expr = visit(ctx.expr()).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.LNot, expr);
        if (!ans) typeError(expr, classes.get("bool"), ctx);
        return new IRTypeNode(expr, false);
    }

    @Override
    public IRBaseNode visitNew(MParser.NewContext ctx) {
        return visit(ctx.class_new());
    }

    @Override
    public IRBaseNode visitMulDivMod(MParser.MulDivModContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryArithmetic, expr0, expr1);
        if (!ans) operationError(ctx.op.getText(), expr0, expr1, ctx);
        return new IRTypeNode(expr0, false);
    }

    @Override
    public IRBaseNode visitAddSub(MParser.AddSubContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans;
        if (ctx.op.getText().equals("+"))
            ans = typeChecker.typeCheck(typeChecker.Plus, expr0, expr1);
        else ans = typeChecker.typeCheck(typeChecker.BinaryArithmetic, expr0, expr1);
        if (!ans) operationError(ctx.op.getText(), expr0, expr1, ctx);
        return new IRTypeNode(expr0, false);
    }

    @Override
    public IRBaseNode visitBitwise(MParser.BitwiseContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryArithmetic, expr0, expr1);
        if (!ans) operationError(ctx.op.getText(), expr0, expr1, ctx);
        return new IRTypeNode(expr0, false);
    }

    @Override
    public IRBaseNode visitCompare(MParser.CompareContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.Compare, expr0, expr1);
        if (!ans) operationError(ctx.op.getText(), expr0, expr1, ctx);
        return new IRTypeNode(classes.get("bool"), false);
    }

    @Override
    public IRBaseNode visitEqual(MParser.EqualContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.Equal, expr0, expr1);
        if (!ans) operationError(ctx.op.getText(), expr0, expr1, ctx);
        return new IRTypeNode(classes.get("bool"), false);
    }

    @Override
    public IRBaseNode visitAnd(MParser.AndContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryArithmetic, expr0, expr1);
        if (!ans) operationError("&", expr0, expr1, ctx);
        return new IRTypeNode(expr0, false);
    }

    @Override
    public IRBaseNode visitXor(MParser.XorContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryArithmetic, expr0, expr1);
        if (!ans) operationError("^", expr0, expr1, ctx);
        return new IRTypeNode(expr0, false);
    }

    @Override
    public IRBaseNode visitOr(MParser.OrContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryArithmetic, expr0, expr1);
        if (!ans) operationError("|", expr0, expr1, ctx);
        return new IRTypeNode(expr0, false);
    }

    @Override
    public IRBaseNode visitLAnd(MParser.LAndContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryLogical, expr0, expr1);
        if (!ans) operationError("&&", expr0, expr1, ctx);
        return new IRTypeNode(expr0, false);
    }

    @Override
    public IRBaseNode visitLOr(MParser.LOrContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryLogical, expr0, expr1);
        if (!ans) operationError("||", expr0, expr1, ctx);
        return new IRTypeNode(expr0, false);
    }

    @Override
    public IRBaseNode visitTrue(MParser.TrueContext ctx) {
        return new IRTypeNode(classes.get("bool"), false);
    }

    @Override
    public IRBaseNode visitFalse(MParser.FalseContext ctx) {
        return new IRTypeNode(classes.get("bool"), false);
    }

    @Override
    public IRBaseNode visitNumber(MParser.NumberContext ctx) {
        return new IRTypeNode(classes.get("int"), false);
    }

    @Override
    public IRBaseNode visitStr(MParser.StrContext ctx) {
        return new IRTypeNode(classes.get("string"), false);
    }

    @Override
    public IRBaseNode visitParens(MParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public IRBaseNode visitExprListCombine(MParser.ExprListCombineContext ctx) {
        Vector<BaseType> list = visit(ctx.expr_list(0)).getTypeList();
        list.addAll(visit(ctx.expr_list(1)).getTypeList());
        return new IRTypeListNode(list);
    }

    @Override
    public IRBaseNode visitExprList(MParser.ExprListContext ctx) {
        Vector<BaseType> list = new Vector<>();
        if (!ctx.getText().equals("")) list.add(visit(ctx.expr()).getType());
        return new IRTypeListNode(list);
    }

    @Override
    public IRBaseNode visitClass_new(MParser.Class_newContext ctx) {
        String classname = ctx.class_name().getText();
        boolean check = false;
        for (MParser.DimensionContext x : ctx.dimension()) {
            if (x.getText().equals("[]")) check = true;
            else if (check) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
            classname += "[]";
        }
        try { return new IRTypeNode(classes.getClass(classname), true); } catch (Exception e) {
            error(e.getMessage(), ctx);
        }
        return null;
    }

    @Override
    public IRBaseNode visitAssignment(MParser.AssignmentContext ctx) {
        IRBaseNode left = visit(ctx.expr(0));
        IRBaseNode right = visit(ctx.expr(1));
        if (!typeChecker.typeCheck(typeChecker.Assign, left.getType(), right.getType()))
            operationError("=", left.getType(), right.getType(), ctx);
        if (!left.isLeft()) leftError(ctx.expr(0).getText(), ctx);
        return new IRTypeNode(left.getType(), false);
    }

    @Override
    public IRBaseNode visitNull(MParser.NullContext ctx) {
        return new IRTypeNode(classes.get("null"), false);
    }

    @Override
    public IRBaseNode visitThis(MParser.ThisContext ctx) {
        return new IRTypeNode(classStack.peek().getType(), false);
    }
}
