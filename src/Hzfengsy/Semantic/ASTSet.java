package Hzfengsy.Semantic;

import org.antlr.v4.runtime.*;

import java.util.*;

public class ASTSet
{
    private ASTSet() {}

    private Set<ParserRuleContext> memberVarSet = new HashSet<>();
    private Set<ParserRuleContext> leftValueSet = new HashSet<>();

    private static ASTSet astSet = new ASTSet();

    public static ASTSet getInstance() { return astSet; }

    public void putMemberVar(ParserRuleContext ctx) {
        memberVarSet.add(ctx);
    }

    public Boolean getMembervar(ParserRuleContext ctx) {
        return memberVarSet.contains(ctx);
    }

    public void putLeftValue(ParserRuleContext ctx) {
        leftValueSet.add(ctx);
    }

    public Boolean getLeftValue(ParserRuleContext ctx) {
        return leftValueSet.contains(ctx);
    }
}
