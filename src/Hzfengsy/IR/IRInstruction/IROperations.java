package Hzfengsy.IR.IRInstruction;

public class IROperations
{
    public enum binaryOp
    {
        ADD, SUB, MUL, DIV, MOD,
        LSHIFT, RSHIFT, LT, GT, LE, GE, EQ, NE,
        AND, XOR, OR
    }

    public enum memOp {
        LOAD, STORE
    }
}
