package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.IR.IRType.*;
import Hzfengsy.IR.*;
import Hzfengsy.Semantic.Type.VarType.*;

import java.net.*;
import java.util.*;

public class IRFuncNode extends IRBaseNode
{
    private String funcName;
    private IRBaseType returnType;
    private IRBaseType[] args;
    private IRVariables variables = new IRVariables();

    public IRFuncNode(IRBaseType returnType, String funcName, IRBaseType... args) {
        this.returnType = returnType;
        this.funcName = funcName;
        this.args = args;
        variables.setIndex(args.length + 1);
    }

    public String getFuncName() {
        return "@" + funcName;
    }

    private String argsToString() {
        String ans = new String();
        for (IRBaseType arg : this.args)
            ans += args.toString();
        return ans;
    }

    public void storeArgs() {
        Integer index = args.length + 1;
        for (int i = 0; i < args.length; i++) {
            IRBaseInstruction inst = new IRStoreInstruction(args[i], variables.query(i), new IRPointerType(args[i]), variables.query(i + index), 4);
            instructions.add(inst);
        }
    }


    @Override
    public String toString() {
        return "define " + returnType + getFuncName() + "(" + argsToString() + ") {\n" + ((IRBaseNode) this).toString() + "\n}";
    }

    public IRVar defineVar(BaseType type, String varName) {
        IRBaseType IRType = TypeMap.getInstance().exchange(type);
        IRVar result = variables.insertVar(varName);
        Integer align = 4;
        instructions.add(new IRAllocaInstruction(result, IRType, align));
        return result;
    }

    public IRVar tempVar(IRBaseType type) {
        return variables.insertTempVar();
    }
}
