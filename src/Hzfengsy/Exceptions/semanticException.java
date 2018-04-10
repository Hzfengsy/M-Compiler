package Hzfengsy.Exceptions;

public class semanticException extends Exception{
    private String message;
    public semanticException(String mes)
    {
        message = mes;
    }

    public String getMessage()
    {
        return message;
    }
}
