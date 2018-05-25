package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.*;

public class IRNode extends IRBase
{
    private IRBaseBlock head;
    private IRBaseBlock tail;

    public IRNode(IRBaseBlock head, IRBaseBlock tail) {
        this.head = head;
        this.tail = tail;
    }

    public IRBaseBlock getHead() {
        return head;
    }

    public IRBaseBlock getTail() {
        return tail;
    }
}
