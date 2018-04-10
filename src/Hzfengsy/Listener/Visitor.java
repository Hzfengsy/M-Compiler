package Hzfengsy.Listener;

import Hzfengsy.Parser.MBaseVisitor;
import Hzfengsy.Parser.MParser;
import Hzfengsy.utility.IRBaseNode;

public class Visitor extends MBaseVisitor<IRBaseNode>
{
    @Override
    public IRBaseNode visitSingleClass(MParser.SingleClassContext ctx)
    {
        return visitChildren(ctx);
    }
}
