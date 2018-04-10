package Hzfengsy.Type;

import java.util.*;

public class userType extends baseType
{
    private Map<String, baseType> memberVar = new HashMap<>();
    private String className;
    public userType(String Name) { className = Name; }

}
