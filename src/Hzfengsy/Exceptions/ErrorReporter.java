package Hzfengsy.Exceptions;

import Hzfengsy.Type.BaseType;
import Hzfengsy.Type.FuncType;

import java.util.Vector;

public class ErrorReporter
{
    private Vector<SemanticError> errorList = new Vector<>();
    private Vector<String> lines = new Vector<>();

    public void reportError(String message, BaseType inClass, FuncType inFunction, Integer lineNum, Integer start, Integer stop) {
        String line = lines.elementAt(lineNum - 1);
        SemanticError error = new SemanticError(message, inClass, inFunction, line, lineNum, start, stop);
        errorList.add(error);
    }

    public void check() {
        if (errorList.isEmpty()) return; for (SemanticError x : errorList) System.err.println(x); System.exit(1);
    }

    public void putLine(String line) {
        lines.add(line);
    }
}
