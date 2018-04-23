package Hzfengsy.Visitor;

import Hzfengsy.Exceptions.ErrorReporter;
import Hzfengsy.Parser.MBaseVisitor;
import Hzfengsy.Parser.MParser;
import Hzfengsy.Utility.IRBaseNode;

public class ClassVisitor extends MBaseVisitor<IRBaseNode>
{

    private Classes classes;
    private ErrorReporter reporter;

    public ClassVisitor(Classes _classes, ErrorReporter _repoter) {
        classes = _classes; reporter = _repoter;
    }

    private void error(String message) {
        System.err.println(message); System.exit(1);
    }

    @Override
    public IRBaseNode visitClas(MParser.ClasContext ctx) {
        try { classes.defineClass(ctx.id().getText()); } catch (Exception e) { error(e.getMessage()); } return null;
    }

    @Override
    public IRBaseNode visitFunc(MParser.FuncContext ctx) {
        return null;
    }
}
