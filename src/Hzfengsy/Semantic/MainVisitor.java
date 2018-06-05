package Hzfengsy.Semantic;

import Hzfengsy.Exceptions.*;
import Hzfengsy.Parser.*;
import Hzfengsy.Semantic.SemanticNode.*;
import Hzfengsy.Semantic.Type.*;
import Hzfengsy.Semantic.Type.VarType.*;
import org.antlr.v4.runtime.*;

import java.util.*;

public class MainVisitor extends MBaseVisitor<SemanticBaseNode>
{
    private RenameMap renameMap = RenameMap.getInstance();
    private Variable variables = new Variable();
    private Functions functions = Functions.getInstance();
    private Vector<Map<String, String>> localVar = new Vector<>();
    private Classes classes = Classes.getInstance();
    private ErrorReporter reporter = ErrorReporter.getInstance();
    private Stack<SemanticBaseNode> loopStack = new Stack<>();
    private Stack<SemanticBaseNode> functionStack = new Stack<>();
    private Stack<SemanticBaseNode> classStack = new Stack<>();
    private TypeChecker typeChecker = TypeChecker.getInstance();
    private TypeRecorder typeRecorder = TypeRecorder.getInstance();
    private VarUses varUses = VarUses.getInstance();
    private boolean isLeft = false;

    private void error(String message, ParserRuleContext ctx) {
        BaseType inClass = classStack.empty() ? null : classStack.peek().getType();
        Integer start = ctx.getStart().getCharPositionInLine();
        Integer stop = ctx.getStop().getCharPositionInLine() + ctx.getStop().getText().length();
        FuncType inFunction = functionStack.empty() ? null : functionStack.peek().getFunc();
        reporter.reportError(message, inClass, inFunction, ctx.getStart().getLine(), start, stop);
    }

    private Boolean definable(BaseType type) {
        if (type instanceof ArrayType) return definable(type.getBaseType());
        else return !(type instanceof VoidType);
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

    private Boolean checkExprList(Vector<BaseType> a, Vector<BaseType> b) {
        if (a.size() != b.size()) return false;
        for (Integer i = 0; i < a.size(); i++) {
            if (!a.elementAt(i).assignCheck(b.elementAt(i))) return false;
        }
        return true;
    }

    @Override
    public SemanticBaseNode visitProg(MParser.ProgContext ctx) {
        localVar.add(new HashMap<>());
        visitChildren(ctx);
        return null;
    }

    @Override
    public SemanticBaseNode visitMain_prog(MParser.Main_progContext ctx) {
        return visit(ctx.prog());
    }

    @Override
    public SemanticBaseNode visitClas(MParser.ClasContext ctx) {
        localVar.add(new HashMap<>());
        try {
            BaseType clas = classes.getClass(ctx.id().getText());
            SemanticBaseNode ans = new SemanticExprNode(clas, true);
            classStack.push(ans);
            if (ctx.prog() != null) visit(ctx.prog());
            classStack.pop();
            localVar.remove(localVar.size() - 1);
            return ans;
        } catch (NullPointerException ignored) {} catch (Exception e) {
            error(e.getMessage(), ctx);
        }
        return null;
    }

    //IF STATEMENT
    @Override
    public SemanticBaseNode visitIf_Stat(MParser.If_StatContext ctx) {
        localVar.add(new HashMap<>());
        SemanticBaseNode boolCheck = visit(ctx.expr());
        if (boolCheck.getType() != classes.boolType)
            typeError(boolCheck.getType(), classes.boolType, ctx.expr());
        visit(ctx.stat());
        localVar.remove(localVar.size() - 1);
        return null;
    }

    @Override
    public SemanticBaseNode visitIfElse_Stat(MParser.IfElse_StatContext ctx) {
        localVar.add(new HashMap<>());
        SemanticBaseNode boolCheck = visit(ctx.expr());
        if (boolCheck.getType() != classes.boolType)
            typeError(boolCheck.getType(), classes.boolType, ctx.expr());
        visit(ctx.stat(0));
        visit(ctx.stat(1));
        localVar.remove(localVar.size() - 1);
        return null;
    }

    @Override
    public SemanticBaseNode visitFor_Stat(MParser.For_StatContext ctx) {
        localVar.add(new HashMap<>());
        if (ctx.first != null) visit(ctx.first);
        if (ctx.second != null) {
            SemanticBaseNode boolCheck = visit(ctx.second);
            if (boolCheck.getType() != classes.boolType)
                typeError(boolCheck.getType(), classes.boolType, ctx.second);
        }
        if (ctx.third != null) visit(ctx.third);
        SemanticBaseNode ans = new SemanticBaseNode();
        loopStack.push(ans);
        visit(ctx.stat());
        loopStack.pop();
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override
    public SemanticBaseNode visitWhile_Stat(MParser.While_StatContext ctx) {
        localVar.add(new HashMap<>());
        SemanticBaseNode boolCheck = visit(ctx.expr());
        if (boolCheck.getType() != classes.boolType)
            typeError(boolCheck.getType(), classes.boolType, ctx.expr());
        SemanticBaseNode ans = new SemanticBaseNode();
        loopStack.push(ans);
        visit(ctx.stat());
        loopStack.pop();
        localVar.remove(localVar.size() - 1);
        return ans;
    }

    @Override
    public SemanticBaseNode visitFunc(MParser.FuncContext ctx) {
        localVar.add(new HashMap<>());
        String funcName = ctx.id().getText();
        try {
            FuncType func;
            if (classStack.empty()) func = functions.query(funcName);
            else {
                UserType userClass = (UserType) classStack.peek().getType();
                func = userClass.queryFunc(funcName);
            }
            SemanticBaseNode ans = new SemanticFuncNode(func);
            functionStack.push(ans);
            if (ctx.stat_list() != null) {
                func.setParameterName(((SemanticTypeListNode) visit(ctx.stat_list())).getName());
            }
            for (MParser.StatContext x : ctx.stat()) visit(x);
            functionStack.pop();
            localVar.remove(localVar.size() - 1);
            return ans;
        } catch (NullPointerException ignored) {} catch (Exception e) {error(e.getMessage(), ctx);}
        return null;
    }

    @Override
    public SemanticBaseNode visitStatListCombine(MParser.StatListCombineContext ctx) {
        SemanticTypeListNode stat_0 = (SemanticTypeListNode) visit(ctx.stat_list(0));
        SemanticTypeListNode stat_1 = (SemanticTypeListNode) visit(ctx.stat_list(1));
        Vector<BaseType> list = stat_0.getTypeList();
        list.addAll(stat_1.getTypeList());
        Vector<String> name = stat_0.getName();
        name.addAll(stat_1.getName());
        return new SemanticTypeListNode(list, name);
    }

    @Override
    public SemanticBaseNode visitStatList(MParser.StatListContext ctx) {
        if (ctx.getText().equals(""))
            return new SemanticTypeListNode(new Vector<>(), new Vector<>());
        String varName = ctx.id().getText();
        if (functionStack.peek().getFunc().getFuncName().equals(varName)
            || localVar.elementAt(localVar.size() - 1).containsKey(varName)) {
            error("redefine variable \'" + varName + "\'", ctx);
            return null;
        }
        String className = ctx.class_stat().getText();
        String mappingName = variables.rename(varName);
        try {
            BaseType type = classes.getClass(className);
            if (!functionStack.empty())
                functionStack.peek().getFunc().insertVar(mappingName, type);
            variables.insert(mappingName, type);
            localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
            Vector<BaseType> list = new Vector<>();
            list.add(type);
            Vector<String> name = new Vector<>();
            name.add(mappingName);
            return new SemanticTypeListNode(list, name);
        } catch (NullPointerException ignored) {} catch (Exception e) { error(e.getMessage(), ctx);}
        return null;
    }

    @Override
    public SemanticBaseNode visitId_Define(MParser.Id_DefineContext ctx) {
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        try {
            BaseType type = classes.getClass(className);
            if (!definable(type)) error("cannot define a \'" + className + "\' variable", ctx);
        } catch (Exception e) {error(e.getMessage(), ctx);}

        if (localVar.elementAt(localVar.size() - 1).containsKey(varName) || functions.contain(varName))
            error("variable " + varName + " redefined", ctx);
        String mappingName = variables.rename(varName);
        renameMap.put(ctx, mappingName);
        try {
            if (!functionStack.empty())
                functionStack.peek().getFunc().insertVar(mappingName, classes.getClass(className));
            variables.insert(mappingName, classes.getClass(className));
        } catch (Exception e) { error(e.getMessage(), ctx); }

        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
        return new SemanticBaseNode();
    }

    @Override
    public SemanticBaseNode visitAssign_Define(MParser.Assign_DefineContext ctx) {
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        try {
            BaseType type = classes.getClass(className);
            if (!definable(type)) error("cannot define a \'" + className + "\' variable", ctx);
        } catch (Exception e) {error(e.getMessage(), ctx);}
        BaseType exprType = visit(ctx.expr()).getType();
        if (localVar.elementAt(localVar.size() - 1).containsKey(varName) || functions.contain(varName))
            error("variable \'" + varName + "\' has been defined", ctx);
        String mappingName = variables.rename(varName);
        renameMap.put(ctx, mappingName);
        try {
            if (!functionStack.empty())
                functionStack.peek().getFunc().insertVar(mappingName, classes.getClass(className));
            variables.insert(mappingName, classes.getClass(className));
            if (!classes.getClass(className).assignCheck(exprType))
                typeError(exprType, classes.getClass(className), ctx);
            localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
        } catch (NullPointerException ignored) {} catch (Exception e) {
            error(e.getMessage(), ctx);
        }
        return null;
    }

    @Override
    public SemanticBaseNode visitBreak_Stat(MParser.Break_StatContext ctx) {
        if (loopStack.empty()) error("could not use \'break\' outside loops", ctx);
        return null;
    }

    @Override
    public SemanticBaseNode visitContinue_Stat(MParser.Continue_StatContext ctx) {
        if (loopStack.empty()) error("could not use \'continue\' outside loops", ctx);
        return null;
    }

    @Override
    public SemanticBaseNode visitReturn_Stat(MParser.Return_StatContext ctx) {
        if (functionStack.empty()) error("could not use \'return\' outside functions", ctx);
        BaseType func = functionStack.peek().getType();
        if (func.equals(classes.voidType)) {
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
    public SemanticBaseNode visitSegment_Stat(MParser.Segment_StatContext ctx) {
        localVar.add(new HashMap<>());
        visitChildren(ctx);
        localVar.remove(localVar.size() - 1);
        return null;
    }

    @Override
    public SemanticBaseNode visitIdentity(MParser.IdentityContext ctx) {
        String varName = ctx.getText();
        Boolean found = false;
        String rename = "";
        BaseType var = null;
        for (Integer i = localVar.size() - 1; i >= 0; i--) {
            Map<String, String> local = localVar.elementAt(i);
            if (i == classStack.size() + 1 && !classStack.empty()) {
                UserType userClass = (UserType) classStack.peek().getType();
                try {
                    ASTSet.getInstance().putMemberVar(ctx);
                    var = userClass.queryVar(varName);
                    found = true;
                    break;
                } catch (Exception ignored) {}
                continue;
            }
            if (local.containsKey(varName)) {
                found = true;
                rename = local.get(varName);
                break;
            }
        }
        try {
            if (found) {
                if (!this.isLeft) varUses.add(rename);
                renameMap.put(ctx, rename);
                if (var == null) var = variables.query(rename);
                return new SemanticExprNode(var, true);
            }
        } catch (Exception e) { error(e.getMessage(), ctx); }
        error("variable \'" + varName + "\' has not been defined", ctx);
        return null;
    }

    @Override
    public SemanticBaseNode visitSubscript(MParser.SubscriptContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        this.isLeft = false;
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans0 = expr0 instanceof ArrayType;
        if (!ans0) {
            typeError(expr0, new ArrayType(null), ctx);
            return new SemanticExprNode(expr0, false);
        }
        else if (!typeChecker.typeCheck(typeChecker.OneInt, expr1))
            typeError(expr1, classes.intType, ctx);
        return new SemanticExprNode(expr0.getBaseType(), true);
    }

    @Override
    public SemanticBaseNode visitMembervar(MParser.MembervarContext ctx) {
        BaseType Class = visit(ctx.expr()).getType();
        this.isLeft = false;
        typeRecorder.put(ctx.expr(), Class);
        BaseType var = null;
        try { var = Class.queryVar(ctx.id().getText()); } catch (Exception e) {
            error(e.getMessage(), ctx);
        }
        return new SemanticExprNode(var, true);
    }

    @Override
    public SemanticBaseNode visitMemberfunc(MParser.MemberfuncContext ctx) {
        BaseType Class = visit(ctx.expr()).getType();
        typeRecorder.put(ctx.expr(), Class);
        FuncType func = null;
        try { func = Class.queryFunc(ctx.id().getText()); } catch (Exception e) {
            error(e.getMessage(), ctx);
        }
        Vector<BaseType> list = func.getParameterList();
        Vector<BaseType> param = visit(ctx.expr_list()).getTypeList();
        if (!checkExprList(list, param))
            error("error function call at \'" + ctx.id().getText() + "\'", ctx);
        return new SemanticExprNode(func.getReturnType(), false);
    }

    @Override
    public SemanticBaseNode visitPostfix(MParser.PostfixContext ctx) {
        SemanticBaseNode left = visit(ctx.expr());
        BaseType expr = left.getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.OneInt, expr);
        if (!ans) typeError(expr, classes.intType, ctx);
        if (!left.isLeft()) leftError(ctx.expr().getText(), ctx);
        ASTSet.getInstance().putLeftValue(ctx.expr());
        return new SemanticExprNode(expr, false);
    }

    @Override
    public SemanticBaseNode visitFunction(MParser.FunctionContext ctx) {
        FuncType func = null;
        try {
            if (classStack.empty()) func = functions.query(ctx.id().getText());
            else {
                UserType userClass = (UserType) classStack.peek().getType();
                try {
                    func = userClass.queryFunc(ctx.id().getText());
                    ASTSet.getInstance().putMemberVar(ctx);
                } catch (Exception e) {
                    func = functions.query(ctx.id().getText());
                }

            }
        } catch (Exception e) { error(e.getMessage(), ctx); }
        Vector<BaseType> list = func.getParameterList();
        Vector<BaseType> param = visit(ctx.expr_list()).getTypeList();
        if (!checkExprList(list, param))
            error("error function call at \'" + ctx.id().getText() + "\'", ctx);
        return new SemanticExprNode(func.getReturnType(), false);
    }

    @Override
    public SemanticBaseNode visitPrefix(MParser.PrefixContext ctx) {
        SemanticBaseNode left = visit(ctx.expr());
        BaseType expr = left.getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.OneInt, expr);
        if (!ans) typeError(expr, classes.intType, ctx);
        if (!left.isLeft()) leftError(ctx.expr().getText(), ctx);
        ASTSet.getInstance().putLeftValue(ctx.expr());
        return new SemanticExprNode(expr, false);
    }

    @Override
    public SemanticBaseNode visitUnary(MParser.UnaryContext ctx) {
        BaseType expr = visit(ctx.expr()).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.OneInt, expr);
        if (!ans) typeError(expr, classes.intType, ctx);
        return new SemanticExprNode(expr, false);
    }

    @Override
    public SemanticBaseNode visitNot(MParser.NotContext ctx) {
        BaseType expr = visit(ctx.expr()).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.OneInt, expr);
        if (!ans) typeError(expr, classes.intType, ctx);
        return new SemanticExprNode(expr, false);
    }

    @Override
    public SemanticBaseNode visitLNot(MParser.LNotContext ctx) {
        BaseType expr = visit(ctx.expr()).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.LNot, expr);
        if (!ans) typeError(expr, classes.boolType, ctx);
        return new SemanticExprNode(expr, false);
    }

    @Override
    public SemanticBaseNode visitNew(MParser.NewContext ctx) {
        return visit(ctx.class_new());
    }

    @Override
    public SemanticBaseNode visitMulDivMod(MParser.MulDivModContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryArithmetic, expr0, expr1);
        if (!ans) operationError(ctx.op.getText(), expr0, expr1, ctx);
        return new SemanticExprNode(expr0, false);
    }

    @Override
    public SemanticBaseNode visitAddSub(MParser.AddSubContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        typeRecorder.put(ctx.expr(0), expr0);
        typeRecorder.put(ctx.expr(1), expr1);
        Boolean ans;
        if (ctx.op.getText().equals("+"))
            ans = typeChecker.typeCheck(typeChecker.Plus, expr0, expr1);
        else ans = typeChecker.typeCheck(typeChecker.BinaryArithmetic, expr0, expr1);
        if (!ans) operationError(ctx.op.getText(), expr0, expr1, ctx);
        return new SemanticExprNode(expr0, false);
    }

    @Override
    public SemanticBaseNode visitBitwise(MParser.BitwiseContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryArithmetic, expr0, expr1);
        if (!ans) operationError(ctx.op.getText(), expr0, expr1, ctx);
        return new SemanticExprNode(expr0, false);
    }

    @Override
    public SemanticBaseNode visitCompare(MParser.CompareContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        typeRecorder.put(ctx.expr(0), expr0);
        typeRecorder.put(ctx.expr(1), expr1);
        Boolean ans = typeChecker.typeCheck(typeChecker.Compare, expr0, expr1);
        if (!ans) operationError(ctx.op.getText(), expr0, expr1, ctx);
        return new SemanticExprNode(classes.boolType, false);
    }

    @Override
    public SemanticBaseNode visitEqual(MParser.EqualContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.Equal, expr0, expr1);
        if (!ans) operationError(ctx.op.getText(), expr0, expr1, ctx);
        return new SemanticExprNode(classes.boolType, false);
    }

    @Override
    public SemanticBaseNode visitAnd(MParser.AndContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryArithmetic, expr0, expr1);
        if (!ans) operationError("&", expr0, expr1, ctx);
        return new SemanticExprNode(expr0, false);
    }

    @Override
    public SemanticBaseNode visitXor(MParser.XorContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryArithmetic, expr0, expr1);
        if (!ans) operationError("^", expr0, expr1, ctx);
        return new SemanticExprNode(expr0, false);
    }

    @Override
    public SemanticBaseNode visitOr(MParser.OrContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryArithmetic, expr0, expr1);
        if (!ans) operationError("|", expr0, expr1, ctx);
        return new SemanticExprNode(expr0, false);
    }

    @Override
    public SemanticBaseNode visitLAnd(MParser.LAndContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryLogical, expr0, expr1);
        if (!ans) operationError("&&", expr0, expr1, ctx);
        return new SemanticExprNode(expr0, false);
    }

    @Override
    public SemanticBaseNode visitLOr(MParser.LOrContext ctx) {
        BaseType expr0 = visit(ctx.expr(0)).getType();
        BaseType expr1 = visit(ctx.expr(1)).getType();
        Boolean ans = typeChecker.typeCheck(typeChecker.BinaryLogical, expr0, expr1);
        if (!ans) operationError("||", expr0, expr1, ctx);
        return new SemanticExprNode(expr0, false);
    }

    @Override
    public SemanticBaseNode visitTrue(MParser.TrueContext ctx) {
        return new SemanticExprNode(classes.boolType, false);
    }

    @Override
    public SemanticBaseNode visitFalse(MParser.FalseContext ctx) {
        return new SemanticExprNode(classes.boolType, false);
    }

    @Override
    public SemanticBaseNode visitNumber(MParser.NumberContext ctx) {
        return new SemanticExprNode(classes.intType, false);
    }

    @Override
    public SemanticBaseNode visitStr(MParser.StrContext ctx) {
        return new SemanticExprNode(classes.stringType, false);
    }

    @Override
    public SemanticBaseNode visitParens(MParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public SemanticBaseNode visitExprListCombine(MParser.ExprListCombineContext ctx) {
        Vector<BaseType> list = visit(ctx.expr_list(0)).getTypeList();
        list.addAll(visit(ctx.expr_list(1)).getTypeList());
        return new SemanticTypeListNode(list);
    }

    @Override
    public SemanticBaseNode visitExprList(MParser.ExprListContext ctx) {
        Vector<BaseType> list = new Vector<>();
        if (!ctx.getText().equals("")) list.add(visit(ctx.expr()).getType());
        return new SemanticTypeListNode(list);
    }

    @Override
    public SemanticBaseNode visitClass_new(MParser.Class_newContext ctx) {
        String classname = ctx.class_name().getText();
        Boolean check = false;
        for (MParser.DimensionContext x : ctx.dimension()) {
            if (x.getText().equals("[]")) check = true;
            else {
                if (check) error("Type error occupied during expr \"" + ctx.getText() + "\"", ctx);
                BaseType type = visit(x.expr()).getType();
                Boolean ans = typeChecker.typeCheck(typeChecker.OneInt, type);
                if (!ans) error("dimension is not a int", ctx);
            }
            classname = new StringBuilder(classname).append("[]").toString();
        }
        try {
            BaseType type = classes.getClass(classname);
            if (!definable(type)) error("can not new a \'" + classname + "\' variable", ctx);
            return new SemanticExprNode(type, true);
        } catch (Exception e) {
            error(e.getMessage(), ctx);
        }
        return null;
    }

    @Override
    public SemanticBaseNode visitAssignment(MParser.AssignmentContext ctx) {
        this.isLeft = true;
        SemanticBaseNode left = visit(ctx.expr(0));
        this.isLeft = false;
        SemanticBaseNode right = visit(ctx.expr(1));
        if (!typeChecker.typeCheck(typeChecker.Assign, left.getType(), right.getType()))
            operationError("=", left.getType(), right.getType(), ctx);
        if (!left.isLeft()) leftError(ctx.expr(0).getText(), ctx);
        ASTSet.getInstance().putLeftValue(ctx.expr(0));
        return new SemanticExprNode(left.getType(), false);
    }

    @Override
    public SemanticBaseNode visitNull(MParser.NullContext ctx) {
        return new SemanticExprNode(classes.nullType, false);
    }

    @Override
    public SemanticBaseNode visitThis(MParser.ThisContext ctx) {
        return new SemanticExprNode(classStack.peek().getType(), false);
    }
}
