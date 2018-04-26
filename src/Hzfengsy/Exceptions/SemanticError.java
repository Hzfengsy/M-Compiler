package Hzfengsy.Exceptions;

import Hzfengsy.Type.*;

public class SemanticError
{
    private BaseType inClass;
    private FuncType inFunction;
    private String codeLine;
    private Integer left, right, line;
    private String message;

    public SemanticError(String _message, BaseType _class, FuncType _function, String _line, Integer lineNum, Integer _left, Integer _right) {
        message = _message;
        inClass = _class;
        inFunction = _function;
        codeLine = _line;
        left = _left;
        right = _right;
        line = lineNum;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof SemanticError)) return false;
        SemanticError other = (SemanticError) obj;
        return left.equals(other.left) && right.equals(other.right) && line.equals(other.line) && message.equals(other.message);
    }

    private String redText(String source) {
        return "\033[31m" + source + "\033[0m";
    }

    public String toString() {
        String ans = new String();
        if (inClass != null && inClass instanceof UserType)
            ans += "In class \'" + ((UserType) inClass).getName() + "\'\n";
        if (inFunction != null) ans += "In function \'" + inFunction.getFuncLine() + "\'\n";
        ans += redText("error:" + line.toString() + ":" + left.toString() + ": ") + message + '\n';
        ans += "    " + codeLine.substring(0, left) + redText(codeLine.substring(left, right)) + codeLine.substring(right) + "\n";
        return ans;
    }
}
