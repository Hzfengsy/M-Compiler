package Hzfengsy.CodeGenerator;

import Hzfengsy.IR.IRExpr.*;
import Hzfengsy.IR.IRNode.*;
import Hzfengsy.IR.*;

import java.util.*;

public class GlobalVarOptim
{
    private IRProgNode progNode;

    public GlobalVarOptim(IRProgNode progNode) {
        this.progNode = progNode;
    }

    public boolean valid() {
        for (IRFuncNode func : progNode.getFuncs()) {
            if (!func.isExtend() && !func.getName().equals("main")) return false;
        }
        return true;
    }

    public void optim() {
        if (valid()) {
            Collection<IRVar> globe = IRVariables.getInstance().getGlobe();
            IRFuncNode func = IRGenerator.funcNodeMap.get("main");
            for (IRVar var : globe)
                func.addVar(var);
            IRVariables.getInstance().CleanGlobe();

        }
    }
}
