package Hzfengsy.Visitor;

import Hzfengsy.Parser.MBaseVisitor;
import Hzfengsy.Parser.MParser;
import Hzfengsy.utility.IRBaseNode;

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
