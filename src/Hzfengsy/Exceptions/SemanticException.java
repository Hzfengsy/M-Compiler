package Hzfengsy.Exceptions;

public class SemanticException extends Exception
{
    private String message;

    public SemanticException(String mes) {
        message = mes;
    }

    public String getMessage() {
        return message;
    }
}
