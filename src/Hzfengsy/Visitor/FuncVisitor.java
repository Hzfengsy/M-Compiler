package Hzfengsy.Visitor;

import Hzfengsy.Exceptions.ErrorReporter;
import Hzfengsy.Parser.MBaseVisitor;
import Hzfengsy.Parser.MParser;
import Hzfengsy.Type.BaseType;
import Hzfengsy.Type.FuncType;
import Hzfengsy.Type.UserType;
import Hzfengsy.Utility.IRBaseNode;
import Hzfengsy.Utility.IRTypeListNode;
import Hzfengsy.Utility.IRTypeNode;
import org.antlr.v4.runtime.*;

import java.util.Stack;
import java.util.Vector;

public class FuncVisitor extends MBaseVisitor<IRBaseNode>
{

    private Functions functions;
    private Classes classes;
    private ErrorReporter reporter;
    private Stack<IRBaseNode> classStack = new Stack<>();

    public FuncVisitor(Functions _functions, Classes _classes, ErrorReporter _reporter) {
        functions = _functions;
        classes = _classes;
        reporter = _reporter;
    }

    private void error(String message, ParserRuleContext ctx) {
        BaseType inClass = classStack.empty() ? null : classStack.peek().getType();
        Integer start = ctx.getStart().getStartIndex();
        Integer stop = ctx.getStop().getStopIndex();
        reporter.reportError(message, inClass, null, ctx.getStart().getLine(), start, stop + 1);
    }

    private boolean checkMainFunc() {
        try {
            FuncType funcMain = functions.query("main");
            if (funcMain.getReturnType() != classes.getClass("int")) return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private FuncType func_getInt() {
        return new FuncType(classes.get("int"), new Vector<>(), "int getInt()", "getInt");
    }

    private FuncType func_print() {
        Vector<BaseType> parameter = new Vector<>();
        parameter.add(classes.get("string"));
        return new FuncType(classes.get("void"), parameter, "void print(string str)", "print");
    }

    private FuncType func_println() {
        Vector<BaseType> parameter = new Vector<>();
        parameter.add(classes.get("string"));
        return new FuncType(classes.get("void"), parameter, "void println(string str)", "println");
    }

    private FuncType func_getString() {
        return new FuncType(classes.get("string"), new Vector<>(), "string getString()", "getString");
    }

    private FuncType func_toString() {
        Vector<BaseType> parameter = new Vector<>();
        parameter.add(classes.get("int"));
        return new FuncType(classes.get("string"), parameter, "string toString(int i)", "toString");
    }

    private void loadInsideFunction() {
        functions.insert("getInt", func_getInt());
        functions.insert("print", func_print());
        functions.insert("println", func_println());
        functions.insert("getString", func_getString());
        functions.insert("toString", func_toString());
    }

    @Override
    public IRBaseNode visitProg(MParser.ProgContext ctx) {
        if (classStack.empty()) loadInsideFunction();
        visitChildren(ctx);
        if (classStack.empty() && !checkMainFunc())
            error("could not find a main function with \'int\' return value", ctx);
        return null;
    }

    @Override
    public IRBaseNode visitClas(MParser.ClasContext ctx) {
        BaseType clas = null;
        try { clas = classes.getClass(ctx.id().getText()); } catch (Exception e) { error(e.getMessage(), ctx); }
        IRBaseNode ans = new IRTypeNode(clas, true);
        classStack.push(ans);
        if (ctx.prog() != null) visit(ctx.prog());
        classStack.pop();
        return ans;
    }

    //IF STATEMENT

    @Override
    public IRBaseNode visitFunc(MParser.FuncContext ctx) {
        String funcName = ctx.id().getText();
        String className = null;
        String funcLine = (ctx.class_stat() == null ? "" : ctx.class_stat().getText()) + ' ' + funcName + '(' + ctx.stat_list().getText() + ')';
        if (ctx.class_stat() == null) {
            if (classStack.empty()) error("error construction function.", ctx);
            else {
                UserType userClass = (UserType) classStack.peek().getType();
                String userClassName = userClass.getName();
                if (userClassName.equals(funcName)) className = "void";
                else error("error construction function.", ctx);
            }
        }
        else className = ctx.class_stat().getText();
        Vector<BaseType> list = visit(ctx.stat_list()).getTypeList();
        try {
            BaseType returnType = classes.getClass(className);
            FuncType type = new FuncType(returnType, list, funcLine, funcName);
            if (classStack.empty()) functions.insert(funcName, type);
            else {
                UserType userClass = (UserType) classStack.peek().getType();
                if (userClass.contain(funcName)) error("already have function in class", ctx);
                else userClass.insertMemberFunc(funcName, type);
            }
        } catch (Exception e) { error(e.getMessage(), ctx); }
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
        String className = ctx.class_stat().getText();
        BaseType type = null;
        try { type = classes.getClass(className); } catch (Exception e) { error(e.getMessage(), ctx); }
        Vector<BaseType> list = new Vector<>();
        list.add(type);
        return new IRTypeListNode(list);
    }

    @Override
    public IRBaseNode visitId_Define(MParser.Id_DefineContext ctx) {
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        if (className.equals("void")) error("cannot define void variable", ctx);
        if (!classStack.empty()) try {
            UserType userClass = (UserType) classStack.peek().getType();
            userClass.insertMemberVar(varName, classes.getClass(className));
        } catch (Exception e) { error(e.getMessage(), ctx); }
        return new IRBaseNode();
    }

}
