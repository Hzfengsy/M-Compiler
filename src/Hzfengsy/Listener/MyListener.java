package Hzfengsy.Listener;

import Hzfengsy.Parser.MBaseListener;
import Hzfengsy.Parser.MParser;
import Hzfengsy.Exceptions.*;
import Hzfengsy.Type.*;
import Hzfengsy.utility.IRBaseNode;
import Hzfengsy.utility.IRTypeNode;

import java.util.*;

public class MyListener extends MBaseListener
{
    private Variable variables = new Variable();
    private Function functions = new Function();
    private Vector<Map<String, String>> localVar = new Vector<>();
    private Stack<Vector<IRBaseNode>> IRStack = new Stack<>();
    private Classes classes = new Classes();
    public MyListener()
    {

    }
    @Override public void enterProg(MParser.ProgContext ctx)
    {
        localVar.add(new HashMap<>());
        IRStack.push(new Vector<>());
    }
    @Override public void enterAssign(MParser.AssignContext ctx)
    {

    }

    //IF STATEMENT
    @Override public void enterIf_Stat(MParser.If_StatContext ctx)
    {
        localVar.add(new HashMap<>());
    }
    @Override public void exitIf_Stat(MParser.If_StatContext ctx)
    {
        localVar.remove(localVar.size() - 1);
    }

    //IF ELSE STATEMENT
    @Override public void enterIfElse_Stat(MParser.IfElse_StatContext ctx)
    {
        localVar.add(new HashMap<>());
    }
    @Override public void exitIfElse_Stat(MParser.IfElse_StatContext ctx)
    {
        localVar.remove(localVar.size() - 1);
    }

    //FOR STATEMENT
    @Override public void enterFor_Stat(MParser.For_StatContext ctx)
    {
        localVar.add(new HashMap<>());
    }
    @Override public void exitFor_Stat(MParser.For_StatContext ctx)
    {
        localVar.remove(localVar.size() - 1);
    }

    //WHILE STATEMENT
    @Override public void enterWhile_Stat(MParser.While_StatContext ctx)
    {
        localVar.add(new HashMap<>());
    }
    @Override public void exitWhile_Stat(MParser.While_StatContext ctx) {localVar.remove(localVar.size() - 1); }

    //FUNC STATEMENT
    @Override public void enterFunc(MParser.FuncContext ctx)
    {
        localVar.add(new HashMap<>());
        String funcName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        try
        {
            functions.insert(funcName, classes.getClass(className));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }
    }
    @Override public void exitFunc(MParser.FuncContext ctx)
    {
        localVar.remove(localVar.size() - 1);
    }

    //DEFINE
    @Override public void enterAssign_Define(MParser.Assign_DefineContext ctx) {
        String varName = ctx.assign().id().getText();
        String className = ctx.class_stat().getText();
        if (localVar.elementAt(localVar.size() - 1).containsKey(varName))
        {
            System.out.println("variable " + varName + " has been defined");
            return;
        }
        String mappingName = variables.rename(varName);
        try
        {
            variables.insert(mappingName, classes.getClass(className));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }
        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
    }
    @Override public void enterId_Define(MParser.Id_DefineContext ctx)
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
            System.out.println(e.getMessage());
            return;
        }
        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
    }

    @Override public void enterRAWID(MParser.RAWIDContext ctx)
    {
        String varName = ctx.getText();
        Boolean found = false;
        String rename = "";
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
            if (found) IRStack.peek().add(new IRTypeNode(variables.query(rename)));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        found |= functions.contain(varName);
        if (!found) System.out.println(("variable " + varName + " has not been defined"));
    }

    @Override public void enterSubscript(MParser.SubscriptContext ctx)
    {
        IRStack.push(new Vector<>());
    }

    @Override public void exitSubscript(MParser.SubscriptContext ctx)
    {
        Vector<IRBaseNode> parameter = IRStack.peek();
        assert (parameter.size() == 1);
        baseType idType = parameter.elementAt(0).getType();
        if (!(idType instanceof arrayType)) {
            System.out.println("variable " + ctx.id().getText() + " is not a array type");
            return;
        }
        IRStack.pop();
        IRStack.peek().add(new IRTypeNode(idType.getBaseTYpe()));
    }

    @Override public void enterStatList(MParser.StatListContext ctx)
    {
        if (ctx.getText().equals("")) return;
        String varName = ctx.id().getText();
        String className = ctx.class_stat().getText();
        String mappingName = variables.rename(varName);
        try
        {
            variables.insert(mappingName, classes.getClass(className));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }
        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
    }

    //EXPR

}
