package Hzfengsy.Exceptions;

import Hzfengsy.Semantic.Type.*;
import Hzfengsy.Semantic.Type.VarType.*;

import java.util.*;

public class ErrorReporter
{
    private static ErrorReporter myReporter = new ErrorReporter();

    public static ErrorReporter getInstance() {
        return myReporter;
    }

    private Vector<SemanticError> errorList = new Vector<>();
    private Vector<String> lines = new Vector<>();

    private ErrorReporter() {}

    private Boolean errorExist(SemanticError e) {
        for (SemanticError x : errorList)
            if (x.equals(e)) return true;
        return false;
    }

    public void reportError(String message, BaseType inClass, FuncType inFunction, Integer lineNum, Integer start, Integer stop) {
        String line = lines.elementAt(lineNum - 1);
        SemanticError error = new SemanticError(message, inClass, inFunction, line, lineNum, start, stop);
        if (!errorExist(error)) errorList.add(error);
    }

    public Boolean check() {
        if (errorList.isEmpty()) return true;
        for (SemanticError x : errorList) System.err.println(x);
        System.exit(1);
        return false;
    }

    public void putLine(String line) {
        lines.add(line);
    }
}
