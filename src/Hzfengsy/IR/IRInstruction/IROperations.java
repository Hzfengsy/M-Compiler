package Hzfengsy.IR.IRInstruction;

public class IROperations
{
    public enum binaryOp
    {
        ADD, SUB, MUL, DIV, MOD,
        LSHIFT, RSHIFT, LT, GT, LE, GE, EQ, NE,
        AND, XOR, OR;

        public String toNASM() {
            switch (this) {
                case ADD:
                    return "add";
                case SUB:
                    return "sub";
                case MUL:
                    return "imul";
                case DIV:
                    return "div";
                case MOD:
                    return "mod";
                case LSHIFT:
                    return "shl";
                case RSHIFT:
                    return "shr";
                case LT:
                    return "setl";
                case GT:
                    return "setg";
                case LE:
                    return "setle";
                case GE:
                    return "setge";
                case EQ:
                    return "sete";
                case NE:
                    return "setne";
                case AND:
                    return "and";
                case OR:
                    return "or";
                case XOR:
                    return "xor";
                default:
                    return null;
            }
        }
    }

    public enum unaryOp
    {
        MOV, NEG, NOT, LNOT;

        public String toNASM() {
            switch (this) {
                case MOV:
                    return "mov";
                case NEG:
                    return "neg";
                case NOT:
                    return "not";
                case LNOT:
                    return "lnot";
            }
            return null;
        }
    }

    public enum jmpOp
    {
        JMP,
        JG, JGE, JL, JLE, JE, JNE,
        JZ, JNZ;

        public String toNASM() {
            switch (this) {
                case JMP:
                    return "jmp";
                case JG:
                    return "jg";
                case JGE:
                    return "jge";
                case JL:
                    return "jl";
                case JLE:
                    return "jle";
                case JE:
                    return "je";
                case JNZ:
                    return "jnz";
                case JZ:
                    return "jz";
                case JNE:
                    return "jne";
            }
            return null;
        }
    }
}
