package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.*;
import Hzfengsy.IR.IRExpr.*;

public class IRNode extends IRBase
{
    private IRBasicBlock head;
    private IRBasicBlock tail;
    private IRExpr result;

    public IRNode(IRBasicBlock head, IRBasicBlock tail) {
        this.head = head;
        this.tail = tail;
        result = tail.getResult();
    }

    public IRBasicBlock getHead() {
        return head;
    }

    public IRBasicBlock getTail() {
        return tail;
    }

    public IRExpr getResult() {
        return result;
    }

    public void setResult(IRExpr result) {
        this.result = result;
    }
}
