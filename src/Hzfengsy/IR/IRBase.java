package Hzfengsy.IR;

import Hzfengsy.Semantic.Type.*;
import Hzfengsy.Semantic.Type.VarType.*;

import java.util.*;

public class IRBase
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
