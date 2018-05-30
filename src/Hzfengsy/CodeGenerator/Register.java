package Hzfengsy.CodeGenerator;

public class Register
{
    public static Register rax = new Register("rax", 0);
    public static Register rcx = new Register("rcx", 1);
    public static Register rdx = new Register("rdx", 2);
    public static Register rbx = new Register("rbx", 3);
    public static Register rsp = new Register("rsp", 4);
    public static Register rbp = new Register("rbp", 5);
    public static Register rsi = new Register("rsi", 6);
    public static Register rdi = new Register("rdi", 7);
    public static Register r8 = new Register("r8", 8);
    public static Register r9 = new Register("r9", 9);
    public static Register r10 = new Register("r10", 10);
    public static Register r11 = new Register("r11", 11);
    public static Register r12 = new Register("r12", 12);
    public static Register r13 = new Register("r13", 13);
    public static Register r14 = new Register("r14", 14);
    public static Register r15 = new Register("r15", 15);

    private String name;
    private Integer index;

    private Register(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public static Register get(int index) {
        switch (index) {
            case 0:
                return rax;
            case 1:
                return rcx;
            case 2:
                return rdx;
            case 3:
                return rbx;
            case 4:
                return rsp;
            case 5:
                return rbp;
            case 6:
                return rsi;
            case 7:
                return rdi;
            case 8:
                return r8;
            case 9:
                return r9;
            case 10:
                return r10;
            case 11:
                return r11;
            case 12:
                return r12;
            case 13:
                return r13;
            case 14:
                return r14;
            case 15:
                return r15;
        }
        return null;
    }

    public static Register getParm(int index) {
        switch (index) {
            case 0:
                return rdi;
            case 1:
                return rsi;
            case 2:
                return rdx;
            case 3:
                return rcx;
            case 4:
                return r8;
            case 5:
                return r9;
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Register alloc(int index) {
        switch (index) {
            case 0:
                return rdi;
            case 1:
                return rsi;
            case 2:
                return r8;
            case 3:
                return r9;
            default:
                return null;
        }
    }

    public static int allocIndex(Register reg) {
        if (reg == rdi) return 0;
        if (reg == rsi) return 1;
        if (reg == r8) return 2;
        if (reg == r9) return 3;
        return -1;
    }

    public static int registerNum() {
        return 4;
    }
}
