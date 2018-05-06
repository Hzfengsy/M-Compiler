package Hzfengsy.IR.IRNode;

import Hzfengsy.IR.IRInstruction.*;
import Hzfengsy.IR.IRType.*;
import Hzfengsy.IR.*;
import Hzfengsy.Semantic.Type.VarType.*;

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
        for (IRBaseType x : args) {
            tempVar(x);
        }
        variables.setIndex(args.length + 1);
    }

    public String getFuncName() {
        return "@" + funcName;
    }

    private String argsToString() {
        String ans = new String();
        Boolean first = true;
        for (IRBaseType arg : this.args) {
            ans += (first ? "" : ", ") + arg.toString();
            first = false;
        }
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
        return "define " + returnType + " " + getFuncName() + "(" + argsToString() + ") {\n" + instsToString() + "}\n\n";
    }

    public IRVar defineVar(BaseType type, String varName) {
        IRBaseType IRType = TypeMap.getInstance().exchange(type);
        IRVar result = variables.insertVar(varName);
        Integer align = 4;
        instructions.add(new IRAllocaInstruction(result, IRType, align));
        return result;
    }

    private IRVar tempVar(IRBaseType type) {
        return variables.insertTempVar();
    }

    public IRVar tempVar(BaseType type) {
        IRBaseType IRType = TypeMap.getInstance().exchange(type);
        return variables.insertTempVar();
    }
}
