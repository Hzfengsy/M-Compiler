package Hzfengsy.Type;


abstract public class baseType
{
    public baseType() { }

    public baseType getBaseTYpe() { assert false; return new intType(); }

    public boolean equals(Object x)
    {
        if(this == x) return true;
        if(x == null) return false;
//        Boolean ans = this.getClass() == x.getClass();
        return this.getClass() == x.getClass();
    }
}
