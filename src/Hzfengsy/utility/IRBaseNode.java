package Hzfengsy.utility;

import Hzfengsy.Type.*;

import java.util.*;

public class IRBaseNode
{

    public baseType getType()
    {
        assert false;
        return new voidType();
    }

    public Vector<baseType> getTypeList()
    {
        assert false;
        return new Vector<>();
    }

    public boolean isLeft()
    {
        return false;
    }
}
