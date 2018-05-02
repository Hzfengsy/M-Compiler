package Hzfengsy.Semantic;

import org.antlr.v4.runtime.*;

import java.util.*;

public class RenameMap
{
    private RenameMap() {}

    private Map<ParserRuleContext, String> map = new HashMap<>();

    private static RenameMap renameMap = new RenameMap();

    public static RenameMap getInstance() { return renameMap; }

    public void put(ParserRuleContext ctx, String name) {
        map.put(ctx, name);
    }

    public String get(ParserRuleContext ctx) {
        return map.get(ctx);
    }
}
