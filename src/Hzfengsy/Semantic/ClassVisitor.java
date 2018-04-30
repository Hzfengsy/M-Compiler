package Hzfengsy.Semantic;

import Hzfengsy.Exceptions.*;
import Hzfengsy.Semantic.SemanticNode.*;
import Hzfengsy.Parser.*;
import org.antlr.v4.runtime.*;

public class ClassVisitor extends MBaseVisitor<SemanticBaseNode>
{

    private Classes classes = Classes.getInstance();
    private ErrorReporter reporter = ErrorReporter.getInstance();

    private void error(String message, ParserRuleContext ctx) {
        Integer start = ctx.getStart().getCharPositionInLine();
        Integer stop = ctx.getStop().getCharPositionInLine() + ctx.getStop().getText().length();
        reporter.reportError(message, null, null, ctx.getStart().getLine(), start, stop);
    }

    @Override
    public SemanticBaseNode visitClas(MParser.ClasContext ctx) {
        try { classes.defineClass(ctx.id().getText()); } catch (Exception e) {
            error(e.getMessage(), ctx);
        }
        return null;
    }

    @Override
    public SemanticBaseNode visitFunc(MParser.FuncContext ctx) {
        return null;
    }
}
