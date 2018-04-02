// Generated from /Users/fengsiyuan/IdeaProjects/Compiler/src/Hzfengsy/Parser/M.g4 by ANTLR 4.7
package Hzfengsy.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MParser}.
 */
public interface MListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(MParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(MParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#clas}.
	 * @param ctx the parse tree
	 */
	void enterClas(MParser.ClasContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#clas}.
	 * @param ctx the parse tree
	 */
	void exitClas(MParser.ClasContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(MParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(MParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code If_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIf_Stat(MParser.If_StatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code If_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIf_Stat(MParser.If_StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfElse_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIfElse_Stat(MParser.IfElse_StatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfElse_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIfElse_Stat(MParser.IfElse_StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code For_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterFor_Stat(MParser.For_StatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code For_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitFor_Stat(MParser.For_StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code While_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWhile_Stat(MParser.While_StatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code While_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWhile_Stat(MParser.While_StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Return_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterReturn_Stat(MParser.Return_StatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Return_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitReturn_Stat(MParser.Return_StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Break_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBreak_Stat(MParser.Break_StatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Break_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBreak_Stat(MParser.Break_StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Continue_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterContinue_Stat(MParser.Continue_StatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Continue_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitContinue_Stat(MParser.Continue_StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Segment_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterSegment_Stat(MParser.Segment_StatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Segment_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitSegment_Stat(MParser.Segment_StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Expr_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterExpr_Stat(MParser.Expr_StatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Expr_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitExpr_Stat(MParser.Expr_StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Define_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterDefine_Stat(MParser.Define_StatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Define_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitDefine_Stat(MParser.Define_StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code New}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNew(MParser.NewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code New}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNew(MParser.NewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Null}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNull(MParser.NullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Null}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNull(MParser.NullContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Or}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOr(MParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOr(MParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(MParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(MParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParens(MParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParens(MParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LAnd}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLAnd(MParser.LAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LAnd}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLAnd(MParser.LAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code True}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTrue(MParser.TrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code True}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTrue(MParser.TrueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Prefix}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPrefix(MParser.PrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Prefix}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPrefix(MParser.PrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code False}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFalse(MParser.FalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code False}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFalse(MParser.FalseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnary(MParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnary(MParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LOr}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLOr(MParser.LOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LOr}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLOr(MParser.LOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Str}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStr(MParser.StrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Str}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStr(MParser.StrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(MParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(MParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Function}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunction(MParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Function}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunction(MParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Not}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNot(MParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNot(MParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivMod}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivMod(MParser.MulDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivMod}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivMod(MParser.MulDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Postfix}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPostfix(MParser.PostfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Postfix}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPostfix(MParser.PostfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Bitwise}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBitwise(MParser.BitwiseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Bitwise}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBitwise(MParser.BitwiseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Number}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumber(MParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumber(MParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Equal}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqual(MParser.EqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Equal}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqual(MParser.EqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code And}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAnd(MParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code And}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAnd(MParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Compare}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompare(MParser.CompareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Compare}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompare(MParser.CompareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Xor}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterXor(MParser.XorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Xor}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitXor(MParser.XorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Identity}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdentity(MParser.IdentityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Identity}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdentity(MParser.IdentityContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list(MParser.Expr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list(MParser.Expr_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#stat_list}.
	 * @param ctx the parse tree
	 */
	void enterStat_list(MParser.Stat_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#stat_list}.
	 * @param ctx the parse tree
	 */
	void exitStat_list(MParser.Stat_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(MParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(MParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#define}.
	 * @param ctx the parse tree
	 */
	void enterDefine(MParser.DefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#define}.
	 * @param ctx the parse tree
	 */
	void exitDefine(MParser.DefineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RAWID}
	 * labeled alternative in {@link MParser#id}.
	 * @param ctx the parse tree
	 */
	void enterRAWID(MParser.RAWIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RAWID}
	 * labeled alternative in {@link MParser#id}.
	 * @param ctx the parse tree
	 */
	void exitRAWID(MParser.RAWIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Subscript}
	 * labeled alternative in {@link MParser#id}.
	 * @param ctx the parse tree
	 */
	void enterSubscript(MParser.SubscriptContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Subscript}
	 * labeled alternative in {@link MParser#id}.
	 * @param ctx the parse tree
	 */
	void exitSubscript(MParser.SubscriptContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Member}
	 * labeled alternative in {@link MParser#id}.
	 * @param ctx the parse tree
	 */
	void enterMember(MParser.MemberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Member}
	 * labeled alternative in {@link MParser#id}.
	 * @param ctx the parse tree
	 */
	void exitMember(MParser.MemberContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#class_name}.
	 * @param ctx the parse tree
	 */
	void enterClass_name(MParser.Class_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#class_name}.
	 * @param ctx the parse tree
	 */
	void exitClass_name(MParser.Class_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#class_stat}.
	 * @param ctx the parse tree
	 */
	void enterClass_stat(MParser.Class_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#class_stat}.
	 * @param ctx the parse tree
	 */
	void exitClass_stat(MParser.Class_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link MParser#class_new}.
	 * @param ctx the parse tree
	 */
	void enterClass_new(MParser.Class_newContext ctx);
	/**
	 * Exit a parse tree produced by {@link MParser#class_new}.
	 * @param ctx the parse tree
	 */
	void exitClass_new(MParser.Class_newContext ctx);
}