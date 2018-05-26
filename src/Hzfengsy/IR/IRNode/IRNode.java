package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRExpr.*;

public class IRNode extends IRBase
{
    private IRBaseBlock head;
    private IRBaseBlock tail;
    private IRExpr result;

    public IRNode(IRBaseBlock head, IRBaseBlock tail) {
        this.head = head;
        this.tail = tail;
        result = tail.getResult();
    }

    public IRBaseBlock getHead() {
        return head;
    }

    public IRBaseBlock getTail() {
        return tail;
    }

    public IRExpr getResult() {
        return result;
    }

    public void setResult(IRExpr result) {
        this.result = result;
    }
}
