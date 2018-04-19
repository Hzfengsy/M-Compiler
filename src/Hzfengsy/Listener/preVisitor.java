package Hzfengsy.Listener;

import Hzfengsy.Parser.MBaseVisitor;
import Hzfengsy.Parser.MParser;
import Hzfengsy.Type.arrayType;
import Hzfengsy.Type.baseType;
import Hzfengsy.Type.funcType;
import Hzfengsy.Type.userType;
import Hzfengsy.utility.IRBaseNode;
import Hzfengsy.utility.IRTypeListNode;
import Hzfengsy.utility.IRTypeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

public class preVisitor extends MBaseVisitor<IRBaseNode>
{

    private Function functions;
    private Classes classes;
    private Stack<IRBaseNode> classStack = new Stack<>();

    public preVisitor(Function _functions, Classes _classes)
    {
        functions = _functions; classes = _classes;
    }

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
        return new funcType(classes.get("int"), new Vector<>());
    }

    private funcType func_print()
    {
        Vector<baseType> parameter = new Vector<>();
        parameter.add(classes.get("string"));
        return new funcType(classes.get("string"), parameter);
    }

    private funcType func_println()
    {
        Vector<baseType> parameter = new Vector<>();
        parameter.add(classes.get("string"));
        return new funcType(classes.get("void"), parameter);
    }

    private funcType func_getString()
    {
        return new funcType(classes.get("string"), new Vector<>());
    }

    private funcType func_toString()
    {
        Vector<baseType> parameter = new Vector<>();
        parameter.add(classes.get("int"));
        return new funcType(classes.get("string"), parameter);
    }

    private void loadInsideFunction()
    {
        functions.insert("getInt", func_getInt());
        functions.insert("print", func_print());
        functions.insert("println", func_println());
        functions.insert("getString", func_getString());
        functions.insert("toString", func_toString());
    }

    @Override public IRBaseNode visitProg(MParser.ProgContext ctx)
    {
        if (classStack.empty()) loadInsideFunction();
        visitChildren(ctx);
        if (classStack.empty())
            if (!checkMainFunc()) error("main function error");
        return null;
    }

    @Override public IRBaseNode visitClas(MParser.ClasContext ctx)
    {
        baseType clas = null;
        try { clas = classes.defineClass(ctx.id().getText()); }
        catch (Exception e) { error(e.getMessage()); }
        IRBaseNode ans = new IRTypeNode(clas, true);
        classStack.push(ans);
        if (ctx.prog() != null) visit(ctx.prog());
        classStack.pop();
        return ans;
    }

    //IF STATEMENT

    @Override public IRBaseNode visitFunc(MParser.FuncContext ctx)
    {
        String funcName = ctx.id().getText();
        String className = null;
        if (ctx.class_stat() == null)
        {
            if (classStack.empty()) error("error construction function.");
            else
            {
                userType userClass = (userType) classStack.peek().getType();
                String userClassName = userClass.getName();
                if (userClassName.equals(funcName)) className = "void";
                else error("error construction function.");
            }
        }
        else className = ctx.class_stat().getText();
        Vector<baseType> list = visit(ctx.stat_list()).getTypeList();
        baseType returnType = null;
        try
        {
            returnType = classes.getClass(className);
            funcType type = new funcType(returnType, list);
            if (classStack.empty()) functions.insert(funcName, type);
            else
            {
                userType userClass = (userType) classStack.peek().getType();
                if (userClass.contain(funcName)) error("already have function in class");
                else userClass.insertMemberFunc(funcName, type);
            }
        }
        catch (Exception e) { error(e.getMessage()); }
        return null;
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
        String className = ctx.class_stat().getText();
        baseType type = null;
        try { type = classes.getClass(className); }
        catch (Exception e) { error(e.getMessage()); }
        Vector<baseType> list = new Vector<>();
        list.add(type);
        return new IRTypeListNode(list);
    }

    @Override public IRBaseNode visitId_Define(MParser.Id_DefineContext ctx)
    {
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        if (className.equals("void")) error("cannot define void variable");
        if (!classStack.empty())
            try
            {
                userType userClass = (userType)classStack.peek().getType();
                userClass.insertMemberVar(varName, classes.getClass(className));
            } catch (Exception e) { error(e.getMessage()); }
        return new IRBaseNode();
    }

}
