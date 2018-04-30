package Hzfengsy.IR;

import Hzfengsy.IR.IRNode.*;
import Hzfengsy.IR.IRType.*;
import Hzfengsy.Parser.*;
import Hzfengsy.Semantic.*;

public class IRGenerater extends MBaseVisitor<IRBaseNode>
{
    private TypeMap typeMap = new TypeMap();
    private Classes classes = Classes.getInstance();
    @Override
    public IRBaseNode visitClas(MParser.ClasContext ctx) {
        return null;
    }

    public IRBaseNode visitFunc(MParser.FuncContext ctx)
    {
        String funcName = ctx.id().getText();

        IRBaseNode func = new IRFuncNode(funcName, new IRi32Type());
        return null;
    }
}
