//package Hzfengsy.Listener;
//
//import Hzfengsy.Parser.MBaseListener;
//import Hzfengsy.Parser.MParser;
//import Hzfengsy.Exceptions.*;
//import Hzfengsy.Type.*;
//import Hzfengsy.utility.*;
//
//import java.util.*;
//
//public class MyListener extends MBaseListener
//{
//    private Variable variables = new Variable();
//    private Function functions = new Function();
//    private Vector<Map<String, String>> localVar = new Vector<>();
//    private Stack<Vector<IRBaseNode>> IRStack = new Stack<>();
//    private Classes classes = new Classes();
//
//    private Boolean equalClass(baseType a, baseType b)
//    {
//        return a.getClass().equals(b.getClass());
//    }
//
//    private Boolean checkType(Vector<IRBaseNode> parameter, baseType ... args)
//    {
//        if (parameter.size() != args.length) return false;
//        for (int i = 0; i < args.length; i++)
//        {
//            baseType realType = parameter.elementAt(i).getType();
//            baseType requestType = args[i];
//            if (!equalClass(realType, requestType)) return false;
//        }
//        return true;
//    }
//
//    @Override public void enterProg(MParser.ProgContext ctx)
//    {
//        localVar.add(new HashMap<>());
//        IRStack.push(new Vector<>());
//    }
//
//    //IF STATEMENT
//    @Override public void enterIf_Stat(MParser.If_StatContext ctx)
//    {
//        localVar.add(new HashMap<>());
//    }
//    @Override public void exitIf_Stat(MParser.If_StatContext ctx)
//    {
//        localVar.remove(localVar.size() - 1);
//    }
//
//    //IF ELSE STATEMENT
//    @Override public void enterIfElse_Stat(MParser.IfElse_StatContext ctx)
//    {
//        localVar.add(new HashMap<>());
//    }
//    @Override public void exitIfElse_Stat(MParser.IfElse_StatContext ctx)
//    {
//        localVar.remove(localVar.size() - 1);
//    }
//
//    //FOR STATEMENT
//    @Override public void enterFor_Stat(MParser.For_StatContext ctx)
//    {
//        localVar.add(new HashMap<>());
//    }
//    @Override public void exitFor_Stat(MParser.For_StatContext ctx)
//    {
//        localVar.remove(localVar.size() - 1);
//    }
//
//    //WHILE STATEMENT
//    @Override public void enterWhile_Stat(MParser.While_StatContext ctx)
//    {
//        localVar.add(new HashMap<>());
//    }
//    @Override public void exitWhile_Stat(MParser.While_StatContext ctx) {localVar.remove(localVar.size() - 1); }
//
//    //FUNC STATEMENT
//    @Override public void enterFunc(MParser.FuncContext ctx)
//    {
//        localVar.add(new HashMap<>());
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitFunc(MParser.FuncContext ctx)
//    {
//        IRStack.pop();
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        if (parameter.size() != 1) System.exit(1);
//        localVar.remove(localVar.size() - 1);
//        String funcName = ctx.id().getText();
//        String className = ctx.class_stat().getText();
//        Vector<baseType> list = parameter.elementAt(0).getTypeList();
//        try
//        {
//            funcType type = new funcType(classes.getClass(className), list);
//            functions.insert(funcName, type);
//        }
//        catch (Exception e)
//        {
//            System.err.println(e.getMessage());
//            System.exit(1);
//        }
//    }
//
//    @Override public void enterStatListCombine(MParser.StatListCombineContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//
//    @Override public void exitStatListCombine(MParser.StatListCombineContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        IRStack.pop();
//        Vector<baseType> list = parameter.elementAt(0).getTypeList();
//        list.addAll(parameter.elementAt(1).getTypeList());
//        IRStack.peek().add(new IRTypeListNode(list));
//    }
//
//
//
//    //DEFINE
//    @Override public void enterAssign_Define(MParser.Assign_DefineContext ctx) {
//        String varName = ctx.id().getText();
//        String className = ctx.class_stat().getText();
//        if (localVar.elementAt(localVar.size() - 1).containsKey(varName))
//        {
//            System.err.println("variable " + varName + " has been defined");
//            System.exit(1);
//        }
//        String mappingName = variables.rename(varName);
//        try
//        {
//            variables.insert(mappingName, classes.getClass(className));
//        }
//        catch (Exception e)
//        {
//            System.err.println(e.getMessage());
//            System.exit(1);
//        }
//        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
//    }
//    @Override public void enterId_Define(MParser.Id_DefineContext ctx)
//    {
//        String varName = ctx.id().getText();
//        String className = ctx.class_stat().getText();
//        String mappingName = variables.rename(varName);
//        try
//        {
//            variables.insert(mappingName, classes.getClass(className));
//        }
//        catch (Exception e)
//        {
//            System.err.println(e.getMessage());
//            System.exit(1);
//        }
//        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
//    }
//
//    @Override public void enterRAWID(MParser.RAWIDContext ctx)
//    {
//        String varName = ctx.getText();
//        Boolean found = false;
//        String rename = "";
//        for (int i = localVar.size() - 1; i >= 0; i--)
//        {
//            Map<String, String> local = localVar.elementAt(i);
//            if (local.containsKey(varName))
//            {
//                found = true;
//                rename = local.get(varName);
//                break;
//            }
//        }
//        try
//        {
//            if (found) IRStack.peek().add(new IRTypeNode(variables.query(rename)));
//        }
//        catch (Exception e)
//        {
//            System.err.println(e.getMessage());
//            System.exit(1);
//        }
//        found |= functions.contain(varName);
//        if (!found)
//        {
//            System.err.println(("variable " + varName + " has not been defined"));
//            System.exit(1);
//        }
//    }
//
//    @Override public void enterSubscript(MParser.SubscriptContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//
//    @Override public void exitSubscript(MParser.SubscriptContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new arrayType(new voidType()), new intType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(parameter.elementAt(0).getType().getBaseTYpe()));
//    }
//
//    @Override public void enterStatList(MParser.StatListContext ctx)
//    {
//        if (ctx.getText().equals("")) return;
//        String varName = ctx.id().getText();
//        String className = ctx.class_stat().getText();
//        String mappingName = variables.rename(varName);
//        try
//        {
//            variables.insert(mappingName, classes.getClass(className));
//        }
//        catch (Exception e)
//        {
//            System.err.println(e.getMessage());
//            System.exit(1);
//        }
//        localVar.elementAt(localVar.size() - 1).put(varName, mappingName);
//    }
//
//    //EXPR
//    @Override public void enterPostfix(MParser.PostfixContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitPostfix(MParser.PostfixContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new intType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(new intType()));
//    }
//
//
//    @Override public void enterPrefix(MParser.PrefixContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitPrefix(MParser.PrefixContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new intType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(new intType()));
//    }
//
//    @Override public void enterUnary(MParser.UnaryContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitUnary(MParser.UnaryContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new intType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(new intType()));
//    }
//
//    @Override public void enterNot(MParser.NotContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitNot(MParser.NotContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, ctx.op.getText() == "!" ? new boolType() : new intType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");            System.exit(1);
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(ctx.op.getText() == "!" ? new boolType() : new intType()));
//    }
//
//    @Override public void enterMulDivMod(MParser.MulDivModContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitMulDivMod(MParser.MulDivModContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new intType(), new intType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(new intType()));
//    }
//
//    @Override public void enterAddSub(MParser.AddSubContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitAddSub(MParser.AddSubContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new intType(), new intType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(new intType()));
//    }
//
//    @Override public void enterBitwise(MParser.BitwiseContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitBitwise(MParser.BitwiseContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new intType(), new intType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(new intType()));
//    }
//
//    @Override public void enterCompare(MParser.CompareContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitCompare(MParser.CompareContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new intType(), new intType())
//                        | checkType(parameter, new stringType(), new stringType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(new boolType()));
//    }
//
//    @Override public void enterNumber(MParser.NumberContext ctx)
//    {
//        IRStack.peek().add(new IRTypeNode(new intType()));
//    }
//
//    @Override public void enterEqual(MParser.EqualContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitEqual(MParser.EqualContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new intType(), new intType())
//                | checkType(parameter, new stringType(), new stringType())
//                | checkType(parameter, new boolType(), new boolType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(new boolType()));
//    }
//
//    @Override public void enterAnd(MParser.AndContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitAnd(MParser.AndContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new intType(), new intType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(new intType()));
//    }
//
//    @Override public void enterXor(MParser.XorContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitXor(MParser.XorContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new intType(), new intType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(new intType()));
//    }
//
//    @Override public void enterOr(MParser.OrContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitOr(MParser.OrContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new intType(), new intType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(new intType()));
//    }
//
//    @Override public void enterLAnd(MParser.LAndContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitLAnd(MParser.LAndContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new boolType(), new boolType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(new boolType()));
//    }
//
//    @Override public void enterLOr(MParser.LOrContext ctx)
//    {
//        IRStack.push(new Vector<>());
//    }
//    @Override public void exitLOr(MParser.LOrContext ctx)
//    {
//        Vector<IRBaseNode> parameter = IRStack.peek();
//        Boolean checked = checkType(parameter, new boolType(), new boolType());
//        IRStack.pop();
//        if (!checked)
//        {
//            System.err.println("Type error occupied during expr \"" + ctx.getText() + "\"");
//            System.exit(1);
//        }
//        else IRStack.peek().add(new IRTypeNode(new boolType()));
//    }
//}
