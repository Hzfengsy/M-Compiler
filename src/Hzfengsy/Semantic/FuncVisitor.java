package Hzfengsy.Semantic;

import Hzfengsy.Exceptions.*;
import Hzfengsy.IR.IRType.*;
import Hzfengsy.Parser.*;
import Hzfengsy.Semantic.SemanticNode.*;
import Hzfengsy.Semantic.Type.*;
import Hzfengsy.Semantic.Type.VarType.*;
import org.antlr.v4.runtime.*;

import java.util.*;

public class FuncVisitor extends MBaseVisitor<SemanticBaseNode>
{

    private Functions functions = Functions.getInstance();
    private Classes classes = Classes.getInstance();
    private ErrorReporter reporter = ErrorReporter.getInstance();
    private Stack<SemanticBaseNode> classStack = new Stack<>();


    private void error(String message, ParserRuleContext ctx) {
        BaseType inClass = classStack.empty() ? null : classStack.peek().getType();
        Integer start = ctx != null ? ctx.getStart().getCharPositionInLine() : 0;
        Integer stop = ctx != null ? ctx.getStop().getCharPositionInLine() + ctx.getStop().getText().length() : 0;
        reporter.reportError(message, inClass, null, ctx != null ? ctx.getStart().getLine() : 1, start, stop);
    }

    private boolean checkMainFunc() {
        try {
            FuncType funcMain = functions.query("main");
            if (funcMain.getReturnType() != classes.getClass("int")) return false;
            if (!funcMain.getParameterList().isEmpty()) return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private FuncType func_getInt() {
        return new FuncType(classes.intType, new Vector<>(), new Vector<>(), "int getInt()", "getInt");
    }

    private FuncType func_print() {
        Vector<BaseType> parameter = new Vector<>();
        parameter.add(classes.stringType);
        Vector<String> args = new Vector<>();
        args.add("str");
        return new FuncType(classes.voidType, parameter, args, "void print(string str)", "print");
    }

    private FuncType func_println() {
        Vector<BaseType> parameter = new Vector<>();
        parameter.add(classes.stringType);
        Vector<String> args = new Vector<>();
        args.add("str");
        return new FuncType(classes.voidType, parameter, args, "void println(string str)", "println");
    }

    private FuncType func_getString() {

        return new FuncType(classes.stringType, new Vector<>(), new Vector<>(), "string getString()", "getString");
    }

    private FuncType func_toString() {
        Vector<BaseType> parameter = new Vector<>();
        parameter.add(classes.intType);
        Vector<String> args = new Vector<>();
        args.add("i");
        return new FuncType(classes.stringType, parameter, args, "string toString(int i)", "toString");
    }

    private void loadInsideFunction() {
        functions.insert("getInt", func_getInt());
        functions.insert("print", func_print());
        functions.insert("println", func_println());
        functions.insert("getString", func_getString());
        functions.insert("toString", func_toString());
    }


    @Override
    public SemanticBaseNode visitMain_prog(MParser.Main_progContext ctx) {
        loadInsideFunction();
        visitChildren(ctx);
        if (!checkMainFunc())
            error("could not find a valid \'main\' function", null);
        return null;
    }

    @Override
    public SemanticBaseNode visitClas(MParser.ClasContext ctx) {
        BaseType clas = null;
        try { clas = classes.getClass(ctx.id().getText()); } catch (Exception e) {
            error(e.getMessage(), ctx);
        }
        SemanticBaseNode ans = new SemanticExprNode(clas, true);
        classStack.push(ans);
        if (ctx.prog() != null) visit(ctx.prog());
        classStack.pop();
        return ans;
    }

    //IF STATEMENT

    @Override
    public SemanticBaseNode visitFunc(MParser.FuncContext ctx) {
        String funcName = ctx.id().getText();
        String className = null;
        String funcLine = (ctx.class_stat() == null ? "" : ctx.class_stat().getText()) + ' ' + funcName + '(' + ctx.stat_list().getText() + ')';
        if (ctx.class_stat() == null) {
            if (classStack.empty()) error("error construction function.", ctx);
            else {
                UserType userClass = (UserType) classStack.peek().getType();
                String userClassName = userClass.getName();
                if (userClassName.equals(funcName)) {
                    className = "void";
                    userClass.construction = true;
                }
                else {
                    error("error construction function.", ctx);
                    return null;
                }
            }
        }
        else {
            if (classes.containClass(funcName)) {
                error("have a class called \'" + funcName + "\' that is same with the function name", ctx);
                return null;
            }
            className = ctx.class_stat().getText();
        }
        SemanticTypeListNode stat = (SemanticTypeListNode) visit(ctx.stat_list());
        Vector<BaseType> list = stat.getTypeList();
        Vector<String> args = stat.getName();
        try {
            BaseType returnType = classes.getClass(className);
            FuncType type = new FuncType(returnType, list, args, funcLine, funcName);
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
    public SemanticBaseNode visitStatListCombine(MParser.StatListCombineContext ctx) {
        Vector<BaseType> list = visit(ctx.stat_list(0)).getTypeList();
        list.addAll(visit(ctx.stat_list(1)).getTypeList());
        return new SemanticTypeListNode(list);
    }

    @Override
    public SemanticBaseNode visitStatList(MParser.StatListContext ctx) {
        if (ctx.getText().equals("")) return new SemanticTypeListNode(new Vector<>());
        String className = ctx.class_stat().getText();
        BaseType type = null;
        try { type = classes.getClass(className); } catch (Exception e) {
            error(e.getMessage(), ctx);
        }
        Vector<BaseType> list = new Vector<>();
        list.add(type);
        return new SemanticTypeListNode(list);
    }

    @Override
    public SemanticBaseNode visitId_Define(MParser.Id_DefineContext ctx) {
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        if (className.equals("void")) error("cannot define void variable", ctx);
        if (!classStack.empty()) try {
            UserType userClass = (UserType) classStack.peek().getType();
            userClass.insertMemberVar(varName, classes.getClass(className));
        } catch (Exception e) { error(e.getMessage(), ctx); }
        return new SemanticBaseNode();
    }

}
