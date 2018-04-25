package Hzfengsy.Visitor;

import Hzfengsy.Exceptions.*;
import Hzfengsy.Parser.*;
import Hzfengsy.Utility.*;
import org.antlr.v4.runtime.*;

public class ClassVisitor extends MBaseVisitor<IRBaseNode>
{

    private Classes classes;
    private ErrorReporter reporter;

    public ClassVisitor(Classes _classes, ErrorReporter _repoter) {
        classes = _classes;
        reporter = _repoter;
    }

    private void error(String message, ParserRuleContext ctx) {
        Integer start = ctx.getStart().getCharPositionInLine();
        Integer stop = ctx.getStop().getCharPositionInLine() + ctx.getStop().getText().length();
        reporter.reportError(message, null, null, ctx.getStart().getLine(), start, stop);
    }

    @Override
    public IRBaseNode visitClas(MParser.ClasContext ctx) {
        try { classes.defineClass(ctx.id().getText()); } catch (Exception e) {
            error(e.getMessage(), ctx);
        }
        return null;
    }

    @Override
    public IRBaseNode visitFunc(MParser.FuncContext ctx) {
        return null;
    }
}
