package Hzfengsy.Listener;

import Hzfengsy.Parser.MBaseVisitor;
import Hzfengsy.Parser.MParser;
import Hzfengsy.Type.baseType;
import Hzfengsy.Type.funcType;
import Hzfengsy.Type.userType;
import Hzfengsy.utility.IRBaseNode;
import Hzfengsy.utility.IRTypeListNode;
import Hzfengsy.utility.IRTypeNode;

import java.util.Stack;
import java.util.Vector;

public class classVisitor extends MBaseVisitor<IRBaseNode>
{

    private Classes classes;

    public classVisitor(Classes _classes)
    {
        classes = _classes;
    }

    private void error(String message)
    {
        System.err.println(message);
        System.exit(1);
    }

    @Override public IRBaseNode visitClas(MParser.ClasContext ctx)
    {
        try { classes.defineClass(ctx.id().getText()); }
        catch (Exception e) { error(e.getMessage()); }
        return null;
    }

    @Override public IRBaseNode visitFunc(MParser.FuncContext ctx)
    {
        return null;
    }
}
