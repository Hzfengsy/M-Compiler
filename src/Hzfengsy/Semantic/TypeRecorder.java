package Hzfengsy.Semantic;

import Hzfengsy.Semantic.Type.VarType.*;
import org.antlr.v4.runtime.*;

import java.util.*;

public class TypeRecorder
{
    private TypeRecorder() {}

    private Map<ParserRuleContext, BaseType> map = new HashMap<>();

    private static TypeRecorder typeRecorder = new TypeRecorder();

    public static TypeRecorder getInstance() { return typeRecorder; }

    public void put(ParserRuleContext ctx, BaseType type) {
        map.put(ctx, type);
    }

    public BaseType get(ParserRuleContext ctx) {
        return map.get(ctx);
    }
}
