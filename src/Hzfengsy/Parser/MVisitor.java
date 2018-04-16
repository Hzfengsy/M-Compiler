// Generated from /Users/fengsiyuan/IdeaProjects/Compiler/src/Hzfengsy/Parser/M.g4 by ANTLR 4.7
package Hzfengsy.Parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(MParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link MParser#clas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClas(MParser.ClasContext ctx);
	/**
	 * Visit a parse tree produced by {@link MParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(MParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code If_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_Stat(MParser.If_StatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfElse_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElse_Stat(MParser.IfElse_StatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code For_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_Stat(MParser.For_StatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code While_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_Stat(MParser.While_StatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Return_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_Stat(MParser.Return_StatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Break_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak_Stat(MParser.Break_StatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Continue_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinue_Stat(MParser.Continue_StatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Segment_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSegment_Stat(MParser.Segment_StatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Expr_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_Stat(MParser.Expr_StatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Define_Stat}
	 * labeled alternative in {@link MParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefine_Stat(MParser.Define_StatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code New}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNew(MParser.NewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Null}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull(MParser.NullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(MParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(MParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LAnd}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLAnd(MParser.LAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code True}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue(MParser.TrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Subscript}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscript(MParser.SubscriptContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Prefix}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefix(MParser.PrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code False}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalse(MParser.FalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LOr}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLOr(MParser.LOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Str}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr(MParser.StrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(MParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Function}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(MParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivMod}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivMod(MParser.MulDivModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(MParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equal}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqual(MParser.EqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Compare}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare(MParser.CompareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Identity}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentity(MParser.IdentityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Member}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMember(MParser.MemberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(MParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(MParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(MParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Postfix}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfix(MParser.PostfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Bitwise}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwise(MParser.BitwiseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code And}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(MParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Xor}
	 * labeled alternative in {@link MParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXor(MParser.XorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprListCombine}
	 * labeled alternative in {@link MParser#expr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprListCombine(MParser.ExprListCombineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprList}
	 * labeled alternative in {@link MParser#expr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(MParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatList}
	 * labeled alternative in {@link MParser#stat_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatList(MParser.StatListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatListCombine}
	 * labeled alternative in {@link MParser#stat_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatListCombine(MParser.StatListCombineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assign_Define}
	 * labeled alternative in {@link MParser#define}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_Define(MParser.Assign_DefineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Id_Define}
	 * labeled alternative in {@link MParser#define}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_Define(MParser.Id_DefineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RAWID}
	 * labeled alternative in {@link MParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRAWID(MParser.RAWIDContext ctx);
	/**
	 * Visit a parse tree produced by {@link MParser#class_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_name(MParser.Class_nameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Array}
	 * labeled alternative in {@link MParser#class_stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(MParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SingleClass}
	 * labeled alternative in {@link MParser#class_stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleClass(MParser.SingleClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link MParser#class_new}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_new(MParser.Class_newContext ctx);
}