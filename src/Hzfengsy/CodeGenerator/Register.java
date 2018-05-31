package Hzfengsy.CodeGenerator;

public class Register
{
    public static Register rax = new Register("rax", "eax", 0);
    public static Register rcx = new Register("rcx", "ecx", 1);
    public static Register rdx = new Register("rdx", "edx", 2);
    public static Register rbx = new Register("rbx", "ebx", 3);
    public static Register rsp = new Register("rsp", "esp", 4);
    public static Register rbp = new Register("rbp", "ebp", 5);
    public static Register rsi = new Register("rsi", "esi", 6);
    public static Register rdi = new Register("rdi", "edi", 7);
    public static Register r8  = new Register("r8",  "r8d", 8);
    public static Register r9  = new Register("r9",  "r9d", 9);
    public static Register r10 = new Register("r10", "r10d", 10);
    public static Register r11 = new Register("r11", "r11d", 11);
    public static Register r12 = new Register("r12", "r12d", 12);
    public static Register r13 = new Register("r13", "r13d", 13);
    public static Register r14 = new Register("r14", "r14d", 14);
    public static Register r15 = new Register("r15", "r15d", 15);

    private String name;
    private String name32;
    private Integer index;


    private Register(String name, String name32, Integer index) {
        this.name = name;
        this.name32 = name32;
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
                return r8;
            case 1:
                return r9;
            case 2:
                return r10;
            case 3:
                return r11;
            case 4:
                return r12;
            case 5:
                return r13;
            case 6:
                return r14;
            case 7:
                return r15;
            default:
                return null;
        }
    }

    public static int allocIndex(Register reg) {
        if (reg == rdi) return 0;
        if (reg == rsi) return 1;
        if (reg == r8) return 2;
        if (reg == r9) return 3;
        if (reg == r10) return 4;
        if (reg == r11) return 5;
        if (reg == r12) return 6;
        if (reg == r13) return 7;
        return -1;
    }

    public String Reg32() {
        return this.name32;
    }

    public static int registerNum() {
        return 8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return o.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
