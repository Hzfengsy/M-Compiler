package Hzfengsy.Semantic.SemanticNode;

import Hzfengsy.IR.*;
import Hzfengsy.Type.*;
import Hzfengsy.Type.VarType.*;

import java.util.*;

public class SemanticBaseNode extends IRBase
{
    public BaseType getType() {
        assert false;
        return null;
    }

    public Vector<BaseType> getTypeList() {
        return null;
    }

    public FuncType getFunc() { return null;}

    public boolean isLeft() {
        return false;
    }
}
