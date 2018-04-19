// Generated from /Users/fengsiyuan/IdeaProjects/Compiler/src/Hzfengsy/Parser/M.g4 by ANTLR 4.7
package Hzfengsy.Parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, BOOL=30, INT=31, STRING=32, 
		NULL=33, VOID=34, TRUE=35, FALSE=36, IF=37, ELSE=38, FOR=39, WHILE=40, 
		BREAK=41, CONTI=42, RETRN=43, NEW=44, CLASS=45, THIS=46, NUM=47, STR=48, 
		ESC=49, COM=50, MUL=51, DIV=52, ADD=53, SUB=54, NAME=55, WS=56;
	public static final int
		RULE_prog = 0, RULE_clas = 1, RULE_func = 2, RULE_stat = 3, RULE_expr = 4, 
		RULE_expr_list = 5, RULE_stat_list = 6, RULE_define = 7, RULE_id = 8, 
		RULE_class_name = 9, RULE_class_stat = 10, RULE_dimension = 11, RULE_class_new = 12;
	public static final String[] ruleNames = {
		"prog", "clas", "func", "stat", "expr", "expr_list", "stat_list", "define", 
		"id", "class_name", "class_stat", "dimension", "class_new"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "'('", "')'", "';'", "'++'", "'--'", "'['", "']'", 
		"'.'", "'!'", "'~'", "'%'", "'<<'", "'>>'", "'<'", "'<='", "'>'", "'>='", 
		"'=='", "'!='", "'&'", "'^'", "'|'", "'&&'", "'||'", "'='", "','", "'[]'", 
		"'bool'", "'int'", "'string'", "'null'", "'void'", "'true'", "'false'", 
		"'if'", "'else'", "'for'", "'while'", "'break'", "'continue'", "'return'", 
		"'new'", "'class'", "'this'", null, null, null, null, "'*'", "'/'", "'+'", 
		"'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, "BOOL", "INT", "STRING", "NULL", "VOID", 
		"TRUE", "FALSE", "IF", "ELSE", "FOR", "WHILE", "BREAK", "CONTI", "RETRN", 
		"NEW", "CLASS", "THIS", "NUM", "STR", "ESC", "COM", "MUL", "DIV", "ADD", 
		"SUB", "NAME", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "M.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public List<DefineContext> define() {
			return getRuleContexts(DefineContext.class);
		}
		public DefineContext define(int i) {
			return getRuleContext(DefineContext.class,i);
		}
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public List<ClasContext> clas() {
			return getRuleContexts(ClasContext.class);
		}
		public ClasContext clas(int i) {
			return getRuleContext(ClasContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(29);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(26);
					define();
					}
					break;
				case 2:
					{
					setState(27);
					func();
					}
					break;
				case 3:
					{
					setState(28);
					clas();
					}
					break;
				}
				}
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << CLASS) | (1L << NAME))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClasContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(MParser.CLASS, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public ClasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterClas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitClas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitClas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClasContext clas() throws RecognitionException {
		ClasContext _localctx = new ClasContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_clas);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(CLASS);
			setState(34);
			id();
			setState(35);
			match(T__0);
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << CLASS) | (1L << NAME))) != 0)) {
				{
				setState(36);
				prog();
				}
			}

			setState(39);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Stat_listContext stat_list() {
			return getRuleContext(Stat_listContext.class,0);
		}
		public Class_statContext class_stat() {
			return getRuleContext(Class_statContext.class,0);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(41);
				class_stat(0);
				}
				break;
			}
			setState(44);
			id();
			setState(45);
			match(T__2);
			setState(46);
			stat_list(0);
			setState(47);
			match(T__3);
			setState(48);
			match(T__0);
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << NULL) | (1L << VOID) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << BREAK) | (1L << CONTI) | (1L << RETRN) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
				{
				{
				setState(49);
				stat();
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Break_StatContext extends StatContext {
		public TerminalNode BREAK() { return getToken(MParser.BREAK, 0); }
		public Break_StatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterBreak_Stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitBreak_Stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitBreak_Stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class If_StatContext extends StatContext {
		public TerminalNode IF() { return getToken(MParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public If_StatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterIf_Stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitIf_Stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitIf_Stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Return_StatContext extends StatContext {
		public TerminalNode RETRN() { return getToken(MParser.RETRN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_StatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterReturn_Stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitReturn_Stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitReturn_Stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class While_StatContext extends StatContext {
		public TerminalNode WHILE() { return getToken(MParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public While_StatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterWhile_Stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitWhile_Stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitWhile_Stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Continue_StatContext extends StatContext {
		public TerminalNode CONTI() { return getToken(MParser.CONTI, 0); }
		public Continue_StatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterContinue_Stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitContinue_Stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitContinue_Stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Define_StatContext extends StatContext {
		public DefineContext define() {
			return getRuleContext(DefineContext.class,0);
		}
		public Define_StatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterDefine_Stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitDefine_Stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitDefine_Stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class For_StatContext extends StatContext {
		public ExprContext first;
		public ExprContext second;
		public ExprContext third;
		public TerminalNode FOR() { return getToken(MParser.FOR, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public For_StatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterFor_Stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitFor_Stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitFor_Stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Segment_StatContext extends StatContext {
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public Segment_StatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterSegment_Stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitSegment_Stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitSegment_Stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_StatContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_StatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterExpr_Stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitExpr_Stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitExpr_Stat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfElse_StatContext extends StatContext {
		public TerminalNode IF() { return getToken(MParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MParser.ELSE, 0); }
		public IfElse_StatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterIfElse_Stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitIfElse_Stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitIfElse_Stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stat);
		int _la;
		try {
			setState(114);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				_localctx = new If_StatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				match(IF);
				setState(58);
				match(T__2);
				setState(59);
				expr(0);
				setState(60);
				match(T__3);
				setState(61);
				stat();
				}
				break;
			case 2:
				_localctx = new IfElse_StatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				match(IF);
				setState(64);
				match(T__2);
				setState(65);
				expr(0);
				setState(66);
				match(T__3);
				setState(67);
				stat();
				setState(68);
				match(ELSE);
				setState(69);
				stat();
				}
				break;
			case 3:
				_localctx = new For_StatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				match(FOR);
				setState(72);
				match(T__2);
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
					{
					setState(73);
					((For_StatContext)_localctx).first = expr(0);
					}
				}

				setState(76);
				match(T__4);
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
					{
					setState(77);
					((For_StatContext)_localctx).second = expr(0);
					}
				}

				setState(80);
				match(T__4);
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
					{
					setState(81);
					((For_StatContext)_localctx).third = expr(0);
					}
				}

				setState(84);
				match(T__3);
				setState(85);
				stat();
				}
				break;
			case 4:
				_localctx = new While_StatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(86);
				match(WHILE);
				setState(87);
				match(T__2);
				setState(88);
				expr(0);
				setState(89);
				match(T__3);
				setState(90);
				stat();
				}
				break;
			case 5:
				_localctx = new Return_StatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(92);
				match(RETRN);
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
					{
					setState(93);
					expr(0);
					}
				}

				setState(96);
				match(T__4);
				}
				break;
			case 6:
				_localctx = new Break_StatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(97);
				match(BREAK);
				setState(98);
				match(T__4);
				}
				break;
			case 7:
				_localctx = new Continue_StatContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(99);
				match(CONTI);
				setState(100);
				match(T__4);
				}
				break;
			case 8:
				_localctx = new Segment_StatContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(101);
				match(T__0);
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << NULL) | (1L << VOID) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << BREAK) | (1L << CONTI) | (1L << RETRN) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
					{
					{
					setState(102);
					stat();
					}
					}
					setState(107);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(108);
				match(T__1);
				}
				break;
			case 9:
				_localctx = new Expr_StatContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
					{
					setState(109);
					expr(0);
					}
				}

				setState(112);
				match(T__4);
				}
				break;
			case 10:
				_localctx = new Define_StatContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(113);
				define();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewContext extends ExprContext {
		public Class_newContext class_new() {
			return getRuleContext(Class_newContext.class,0);
		}
		public NewContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterNew(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitNew(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitNew(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullContext extends ExprContext {
		public TerminalNode NULL() { return getToken(MParser.NULL, 0); }
		public NullContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitNull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitNull(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OrContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParensContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParensContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterParens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitParens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitParens(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LAndContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LAndContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterLAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitLAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitLAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TrueContext extends ExprContext {
		public TerminalNode TRUE() { return getToken(MParser.TRUE, 0); }
		public TrueContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterTrue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitTrue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitTrue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubscriptContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public SubscriptContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterSubscript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitSubscript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitSubscript(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrefixContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrefixContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitPrefix(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FalseContext extends ExprContext {
		public TerminalNode FALSE() { return getToken(MParser.FALSE, 0); }
		public FalseContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitFalse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LOrContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LOrContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterLOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitLOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitLOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StrContext extends ExprContext {
		public TerminalNode STR() { return getToken(MParser.STR, 0); }
		public StrContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterStr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitStr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitStr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignmentContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AssignmentContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionContext extends ExprContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Expr_listContext expr_list() {
			return getRuleContext(Expr_listContext.class,0);
		}
		public FunctionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulDivModContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MulDivModContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterMulDivMod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitMulDivMod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitMulDivMod(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberContext extends ExprContext {
		public TerminalNode NUM() { return getToken(MParser.NUM, 0); }
		public NumberContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public EqualContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberfuncContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Expr_listContext expr_list() {
			return getRuleContext(Expr_listContext.class,0);
		}
		public MemberfuncContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterMemberfunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitMemberfunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitMemberfunc(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisContext extends ExprContext {
		public TerminalNode THIS() { return getToken(MParser.THIS, 0); }
		public ThisContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterThis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitThis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitThis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompareContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CompareContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterCompare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitCompare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitCompare(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentityContext extends ExprContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public IdentityContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterIdentity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitIdentity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitIdentity(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MembervarContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public MembervarContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterMembervar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitMembervar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitMembervar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AddSubContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnaryContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitUnary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitUnary(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PostfixContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PostfixContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterPostfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitPostfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitPostfix(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitwiseContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BitwiseContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterBitwise(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitBitwise(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitBitwise(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AndContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XorContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public XorContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterXor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitXor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitXor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				_localctx = new FunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(117);
				id();
				setState(118);
				match(T__2);
				setState(119);
				expr_list(0);
				setState(120);
				match(T__3);
				}
				break;
			case 2:
				{
				_localctx = new PrefixContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122);
				((PrefixContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==T__6) ) {
					((PrefixContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(123);
				expr(23);
				}
				break;
			case 3:
				{
				_localctx = new UnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(124);
				((UnaryContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUB) ) {
					((UnaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(125);
				expr(22);
				}
				break;
			case 4:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(126);
				((NotContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__10 || _la==T__11) ) {
					((NotContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(127);
				expr(21);
				}
				break;
			case 5:
				{
				_localctx = new NewContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				match(NEW);
				setState(129);
				class_new();
				}
				break;
			case 6:
				{
				_localctx = new NumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				match(NUM);
				}
				break;
			case 7:
				{
				_localctx = new TrueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(131);
				match(TRUE);
				}
				break;
			case 8:
				{
				_localctx = new FalseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				match(FALSE);
				}
				break;
			case 9:
				{
				_localctx = new NullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(133);
				match(NULL);
				}
				break;
			case 10:
				{
				_localctx = new StrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134);
				match(STR);
				}
				break;
			case 11:
				{
				_localctx = new ThisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(135);
				match(THIS);
				}
				break;
			case 12:
				{
				_localctx = new IdentityContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136);
				id();
				}
				break;
			case 13:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(137);
				match(T__2);
				setState(138);
				expr(0);
				setState(139);
				match(T__3);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(195);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(193);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivModContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(143);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(144);
						((MulDivModContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << MUL) | (1L << DIV))) != 0)) ) {
							((MulDivModContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(145);
						expr(20);
						}
						break;
					case 2:
						{
						_localctx = new AddSubContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(146);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(147);
						((AddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((AddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(148);
						expr(19);
						}
						break;
					case 3:
						{
						_localctx = new BitwiseContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(149);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(150);
						((BitwiseContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__13 || _la==T__14) ) {
							((BitwiseContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(151);
						expr(18);
						}
						break;
					case 4:
						{
						_localctx = new CompareContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(152);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(153);
						((CompareContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
							((CompareContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(154);
						expr(17);
						}
						break;
					case 5:
						{
						_localctx = new EqualContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(155);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(156);
						((EqualContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__20) ) {
							((EqualContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(157);
						expr(16);
						}
						break;
					case 6:
						{
						_localctx = new AndContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(158);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(159);
						match(T__21);
						setState(160);
						expr(15);
						}
						break;
					case 7:
						{
						_localctx = new XorContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(161);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(162);
						match(T__22);
						setState(163);
						expr(14);
						}
						break;
					case 8:
						{
						_localctx = new OrContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(164);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(165);
						match(T__23);
						setState(166);
						expr(13);
						}
						break;
					case 9:
						{
						_localctx = new LAndContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(167);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(168);
						match(T__24);
						setState(169);
						expr(12);
						}
						break;
					case 10:
						{
						_localctx = new LOrContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(170);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(171);
						match(T__25);
						setState(172);
						expr(11);
						}
						break;
					case 11:
						{
						_localctx = new AssignmentContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(173);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(174);
						match(T__26);
						setState(175);
						expr(1);
						}
						break;
					case 12:
						{
						_localctx = new PostfixContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(176);
						if (!(precpred(_ctx, 28))) throw new FailedPredicateException(this, "precpred(_ctx, 28)");
						setState(177);
						((PostfixContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__6) ) {
							((PostfixContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 13:
						{
						_localctx = new SubscriptContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(178);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(179);
						match(T__7);
						setState(180);
						expr(0);
						setState(181);
						match(T__8);
						}
						break;
					case 14:
						{
						_localctx = new MembervarContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(183);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(184);
						match(T__9);
						setState(185);
						id();
						}
						break;
					case 15:
						{
						_localctx = new MemberfuncContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(186);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(187);
						match(T__9);
						setState(188);
						id();
						setState(189);
						match(T__2);
						setState(190);
						expr_list(0);
						setState(191);
						match(T__3);
						}
						break;
					}
					} 
				}
				setState(197);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Expr_listContext extends ParserRuleContext {
		public Expr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list; }
	 
		public Expr_listContext() { }
		public void copyFrom(Expr_listContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExprListCombineContext extends Expr_listContext {
		public List<Expr_listContext> expr_list() {
			return getRuleContexts(Expr_listContext.class);
		}
		public Expr_listContext expr_list(int i) {
			return getRuleContext(Expr_listContext.class,i);
		}
		public ExprListCombineContext(Expr_listContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterExprListCombine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitExprListCombine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitExprListCombine(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprListContext extends Expr_listContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprListContext(Expr_listContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitExprList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_listContext expr_list() throws RecognitionException {
		return expr_list(0);
	}

	private Expr_listContext expr_list(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr_listContext _localctx = new Expr_listContext(_ctx, _parentState);
		Expr_listContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expr_list, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ExprListContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(200);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(199);
				expr(0);
				}
				break;
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(207);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprListCombineContext(new Expr_listContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expr_list);
					setState(202);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(203);
					match(T__27);
					setState(204);
					expr_list(3);
					}
					} 
				}
				setState(209);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Stat_listContext extends ParserRuleContext {
		public Stat_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat_list; }
	 
		public Stat_listContext() { }
		public void copyFrom(Stat_listContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StatListContext extends Stat_listContext {
		public Class_statContext class_stat() {
			return getRuleContext(Class_statContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public StatListContext(Stat_listContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterStatList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitStatList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitStatList(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatListCombineContext extends Stat_listContext {
		public List<Stat_listContext> stat_list() {
			return getRuleContexts(Stat_listContext.class);
		}
		public Stat_listContext stat_list(int i) {
			return getRuleContext(Stat_listContext.class,i);
		}
		public StatListCombineContext(Stat_listContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterStatListCombine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitStatListCombine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitStatListCombine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Stat_listContext stat_list() throws RecognitionException {
		return stat_list(0);
	}

	private Stat_listContext stat_list(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Stat_listContext _localctx = new Stat_listContext(_ctx, _parentState);
		Stat_listContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_stat_list, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new StatListContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(214);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(211);
				class_stat(0);
				setState(212);
				id();
				}
				break;
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(221);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatListCombineContext(new Stat_listContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_stat_list);
					setState(216);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(217);
					match(T__27);
					setState(218);
					stat_list(3);
					}
					} 
				}
				setState(223);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DefineContext extends ParserRuleContext {
		public DefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_define; }
	 
		public DefineContext() { }
		public void copyFrom(DefineContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Assign_DefineContext extends DefineContext {
		public Class_statContext class_stat() {
			return getRuleContext(Class_statContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Assign_DefineContext(DefineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterAssign_Define(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitAssign_Define(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitAssign_Define(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Id_DefineContext extends DefineContext {
		public Class_statContext class_stat() {
			return getRuleContext(Class_statContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Id_DefineContext(DefineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterId_Define(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitId_Define(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitId_Define(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineContext define() throws RecognitionException {
		DefineContext _localctx = new DefineContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_define);
		try {
			setState(234);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new Assign_DefineContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(224);
				class_stat(0);
				setState(225);
				id();
				setState(226);
				match(T__26);
				setState(227);
				expr(0);
				setState(228);
				match(T__4);
				}
				break;
			case 2:
				_localctx = new Id_DefineContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
				class_stat(0);
				setState(231);
				id();
				setState(232);
				match(T__4);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
	 
		public IdContext() { }
		public void copyFrom(IdContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RAWIDContext extends IdContext {
		public TerminalNode NAME() { return getToken(MParser.NAME, 0); }
		public RAWIDContext(IdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterRAWID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitRAWID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitRAWID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_id);
		try {
			_localctx = new RAWIDContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_nameContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(MParser.BOOL, 0); }
		public TerminalNode INT() { return getToken(MParser.INT, 0); }
		public TerminalNode STRING() { return getToken(MParser.STRING, 0); }
		public TerminalNode VOID() { return getToken(MParser.VOID, 0); }
		public TerminalNode NAME() { return getToken(MParser.NAME, 0); }
		public Class_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterClass_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitClass_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitClass_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_nameContext class_name() throws RecognitionException {
		Class_nameContext _localctx = new Class_nameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_class_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << NAME))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_statContext extends ParserRuleContext {
		public Class_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_stat; }
	 
		public Class_statContext() { }
		public void copyFrom(Class_statContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayContext extends Class_statContext {
		public Class_statContext class_stat() {
			return getRuleContext(Class_statContext.class,0);
		}
		public ArrayContext(Class_statContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SingleClassContext extends Class_statContext {
		public Class_nameContext class_name() {
			return getRuleContext(Class_nameContext.class,0);
		}
		public SingleClassContext(Class_statContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterSingleClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitSingleClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitSingleClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_statContext class_stat() throws RecognitionException {
		return class_stat(0);
	}

	private Class_statContext class_stat(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Class_statContext _localctx = new Class_statContext(_ctx, _parentState);
		Class_statContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_class_stat, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new SingleClassContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(241);
			class_name();
			}
			_ctx.stop = _input.LT(-1);
			setState(248);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArrayContext(new Class_statContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_class_stat);
					setState(243);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(244);
					match(T__7);
					setState(245);
					match(T__8);
					}
					} 
				}
				setState(250);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DimensionContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DimensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimension; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterDimension(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitDimension(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitDimension(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DimensionContext dimension() throws RecognitionException {
		DimensionContext _localctx = new DimensionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_dimension);
		int _la;
		try {
			setState(257);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(251);
				match(T__7);
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
					{
					setState(252);
					expr(0);
					}
				}

				setState(255);
				match(T__8);
				}
				break;
			case T__28:
				enterOuterAlt(_localctx, 2);
				{
				setState(256);
				match(T__28);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_newContext extends ParserRuleContext {
		public Class_nameContext class_name() {
			return getRuleContext(Class_nameContext.class,0);
		}
		public List<DimensionContext> dimension() {
			return getRuleContexts(DimensionContext.class);
		}
		public DimensionContext dimension(int i) {
			return getRuleContext(DimensionContext.class,i);
		}
		public Class_newContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_new; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterClass_new(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitClass_new(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitClass_new(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_newContext class_new() throws RecognitionException {
		Class_newContext _localctx = new Class_newContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_class_new);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			class_name();
			setState(263);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(260);
					dimension();
					}
					} 
				}
				setState(265);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 5:
			return expr_list_sempred((Expr_listContext)_localctx, predIndex);
		case 6:
			return stat_list_sempred((Stat_listContext)_localctx, predIndex);
		case 10:
			return class_stat_sempred((Class_statContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 19);
		case 1:
			return precpred(_ctx, 18);
		case 2:
			return precpred(_ctx, 17);
		case 3:
			return precpred(_ctx, 16);
		case 4:
			return precpred(_ctx, 15);
		case 5:
			return precpred(_ctx, 14);
		case 6:
			return precpred(_ctx, 13);
		case 7:
			return precpred(_ctx, 12);
		case 8:
			return precpred(_ctx, 11);
		case 9:
			return precpred(_ctx, 10);
		case 10:
			return precpred(_ctx, 1);
		case 11:
			return precpred(_ctx, 28);
		case 12:
			return precpred(_ctx, 26);
		case 13:
			return precpred(_ctx, 25);
		case 14:
			return precpred(_ctx, 24);
		}
		return true;
	}
	private boolean expr_list_sempred(Expr_listContext _localctx, int predIndex) {
		switch (predIndex) {
		case 15:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean stat_list_sempred(Stat_listContext _localctx, int predIndex) {
		switch (predIndex) {
		case 16:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean class_stat_sempred(Class_statContext _localctx, int predIndex) {
		switch (predIndex) {
		case 17:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u010d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\6\2 \n\2\r\2\16\2!\3\3\3\3\3"+
		"\3\3\3\5\3(\n\3\3\3\3\3\3\4\5\4-\n\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\65\n"+
		"\4\f\4\16\48\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\5\5M\n\5\3\5\3\5\5\5Q\n\5\3\5\3\5\5\5U\n\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5a\n\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\7\5j\n\5\f\5\16\5m\13\5\3\5\3\5\5\5q\n\5\3\5\3\5\5\5u\n\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0090\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00c4\n\6\f\6\16\6\u00c7\13\6\3\7\3\7"+
		"\5\7\u00cb\n\7\3\7\3\7\3\7\7\7\u00d0\n\7\f\7\16\7\u00d3\13\7\3\b\3\b\3"+
		"\b\3\b\5\b\u00d9\n\b\3\b\3\b\3\b\7\b\u00de\n\b\f\b\16\b\u00e1\13\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00ed\n\t\3\n\3\n\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\7\f\u00f9\n\f\f\f\16\f\u00fc\13\f\3\r\3\r\5\r\u0100"+
		"\n\r\3\r\3\r\5\r\u0104\n\r\3\16\3\16\7\16\u0108\n\16\f\16\16\16\u010b"+
		"\13\16\3\16\2\6\n\f\16\26\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\n\3\2"+
		"\b\t\3\2\678\3\2\r\16\4\2\17\17\65\66\3\2\20\21\3\2\22\25\3\2\26\27\5"+
		"\2 \"$$99\2\u0138\2\37\3\2\2\2\4#\3\2\2\2\6,\3\2\2\2\bt\3\2\2\2\n\u008f"+
		"\3\2\2\2\f\u00c8\3\2\2\2\16\u00d4\3\2\2\2\20\u00ec\3\2\2\2\22\u00ee\3"+
		"\2\2\2\24\u00f0\3\2\2\2\26\u00f2\3\2\2\2\30\u0103\3\2\2\2\32\u0105\3\2"+
		"\2\2\34 \5\20\t\2\35 \5\6\4\2\36 \5\4\3\2\37\34\3\2\2\2\37\35\3\2\2\2"+
		"\37\36\3\2\2\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\3\3\2\2\2#$\7/\2\2$"+
		"%\5\22\n\2%\'\7\3\2\2&(\5\2\2\2\'&\3\2\2\2\'(\3\2\2\2()\3\2\2\2)*\7\4"+
		"\2\2*\5\3\2\2\2+-\5\26\f\2,+\3\2\2\2,-\3\2\2\2-.\3\2\2\2./\5\22\n\2/\60"+
		"\7\5\2\2\60\61\5\16\b\2\61\62\7\6\2\2\62\66\7\3\2\2\63\65\5\b\5\2\64\63"+
		"\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28\66\3\2\2"+
		"\29:\7\4\2\2:\7\3\2\2\2;<\7\'\2\2<=\7\5\2\2=>\5\n\6\2>?\7\6\2\2?@\5\b"+
		"\5\2@u\3\2\2\2AB\7\'\2\2BC\7\5\2\2CD\5\n\6\2DE\7\6\2\2EF\5\b\5\2FG\7("+
		"\2\2GH\5\b\5\2Hu\3\2\2\2IJ\7)\2\2JL\7\5\2\2KM\5\n\6\2LK\3\2\2\2LM\3\2"+
		"\2\2MN\3\2\2\2NP\7\7\2\2OQ\5\n\6\2PO\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RT\7\7"+
		"\2\2SU\5\n\6\2TS\3\2\2\2TU\3\2\2\2UV\3\2\2\2VW\7\6\2\2Wu\5\b\5\2XY\7*"+
		"\2\2YZ\7\5\2\2Z[\5\n\6\2[\\\7\6\2\2\\]\5\b\5\2]u\3\2\2\2^`\7-\2\2_a\5"+
		"\n\6\2`_\3\2\2\2`a\3\2\2\2ab\3\2\2\2bu\7\7\2\2cd\7+\2\2du\7\7\2\2ef\7"+
		",\2\2fu\7\7\2\2gk\7\3\2\2hj\5\b\5\2ih\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3"+
		"\2\2\2ln\3\2\2\2mk\3\2\2\2nu\7\4\2\2oq\5\n\6\2po\3\2\2\2pq\3\2\2\2qr\3"+
		"\2\2\2ru\7\7\2\2su\5\20\t\2t;\3\2\2\2tA\3\2\2\2tI\3\2\2\2tX\3\2\2\2t^"+
		"\3\2\2\2tc\3\2\2\2te\3\2\2\2tg\3\2\2\2tp\3\2\2\2ts\3\2\2\2u\t\3\2\2\2"+
		"vw\b\6\1\2wx\5\22\n\2xy\7\5\2\2yz\5\f\7\2z{\7\6\2\2{\u0090\3\2\2\2|}\t"+
		"\2\2\2}\u0090\5\n\6\31~\177\t\3\2\2\177\u0090\5\n\6\30\u0080\u0081\t\4"+
		"\2\2\u0081\u0090\5\n\6\27\u0082\u0083\7.\2\2\u0083\u0090\5\32\16\2\u0084"+
		"\u0090\7\61\2\2\u0085\u0090\7%\2\2\u0086\u0090\7&\2\2\u0087\u0090\7#\2"+
		"\2\u0088\u0090\7\62\2\2\u0089\u0090\7\60\2\2\u008a\u0090\5\22\n\2\u008b"+
		"\u008c\7\5\2\2\u008c\u008d\5\n\6\2\u008d\u008e\7\6\2\2\u008e\u0090\3\2"+
		"\2\2\u008fv\3\2\2\2\u008f|\3\2\2\2\u008f~\3\2\2\2\u008f\u0080\3\2\2\2"+
		"\u008f\u0082\3\2\2\2\u008f\u0084\3\2\2\2\u008f\u0085\3\2\2\2\u008f\u0086"+
		"\3\2\2\2\u008f\u0087\3\2\2\2\u008f\u0088\3\2\2\2\u008f\u0089\3\2\2\2\u008f"+
		"\u008a\3\2\2\2\u008f\u008b\3\2\2\2\u0090\u00c5\3\2\2\2\u0091\u0092\f\25"+
		"\2\2\u0092\u0093\t\5\2\2\u0093\u00c4\5\n\6\26\u0094\u0095\f\24\2\2\u0095"+
		"\u0096\t\3\2\2\u0096\u00c4\5\n\6\25\u0097\u0098\f\23\2\2\u0098\u0099\t"+
		"\6\2\2\u0099\u00c4\5\n\6\24\u009a\u009b\f\22\2\2\u009b\u009c\t\7\2\2\u009c"+
		"\u00c4\5\n\6\23\u009d\u009e\f\21\2\2\u009e\u009f\t\b\2\2\u009f\u00c4\5"+
		"\n\6\22\u00a0\u00a1\f\20\2\2\u00a1\u00a2\7\30\2\2\u00a2\u00c4\5\n\6\21"+
		"\u00a3\u00a4\f\17\2\2\u00a4\u00a5\7\31\2\2\u00a5\u00c4\5\n\6\20\u00a6"+
		"\u00a7\f\16\2\2\u00a7\u00a8\7\32\2\2\u00a8\u00c4\5\n\6\17\u00a9\u00aa"+
		"\f\r\2\2\u00aa\u00ab\7\33\2\2\u00ab\u00c4\5\n\6\16\u00ac\u00ad\f\f\2\2"+
		"\u00ad\u00ae\7\34\2\2\u00ae\u00c4\5\n\6\r\u00af\u00b0\f\3\2\2\u00b0\u00b1"+
		"\7\35\2\2\u00b1\u00c4\5\n\6\3\u00b2\u00b3\f\36\2\2\u00b3\u00c4\t\2\2\2"+
		"\u00b4\u00b5\f\34\2\2\u00b5\u00b6\7\n\2\2\u00b6\u00b7\5\n\6\2\u00b7\u00b8"+
		"\7\13\2\2\u00b8\u00c4\3\2\2\2\u00b9\u00ba\f\33\2\2\u00ba\u00bb\7\f\2\2"+
		"\u00bb\u00c4\5\22\n\2\u00bc\u00bd\f\32\2\2\u00bd\u00be\7\f\2\2\u00be\u00bf"+
		"\5\22\n\2\u00bf\u00c0\7\5\2\2\u00c0\u00c1\5\f\7\2\u00c1\u00c2\7\6\2\2"+
		"\u00c2\u00c4\3\2\2\2\u00c3\u0091\3\2\2\2\u00c3\u0094\3\2\2\2\u00c3\u0097"+
		"\3\2\2\2\u00c3\u009a\3\2\2\2\u00c3\u009d\3\2\2\2\u00c3\u00a0\3\2\2\2\u00c3"+
		"\u00a3\3\2\2\2\u00c3\u00a6\3\2\2\2\u00c3\u00a9\3\2\2\2\u00c3\u00ac\3\2"+
		"\2\2\u00c3\u00af\3\2\2\2\u00c3\u00b2\3\2\2\2\u00c3\u00b4\3\2\2\2\u00c3"+
		"\u00b9\3\2\2\2\u00c3\u00bc\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2"+
		"\2\2\u00c5\u00c6\3\2\2\2\u00c6\13\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00ca"+
		"\b\7\1\2\u00c9\u00cb\5\n\6\2\u00ca\u00c9\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb"+
		"\u00d1\3\2\2\2\u00cc\u00cd\f\4\2\2\u00cd\u00ce\7\36\2\2\u00ce\u00d0\5"+
		"\f\7\5\u00cf\u00cc\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1"+
		"\u00d2\3\2\2\2\u00d2\r\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4\u00d8\b\b\1\2"+
		"\u00d5\u00d6\5\26\f\2\u00d6\u00d7\5\22\n\2\u00d7\u00d9\3\2\2\2\u00d8\u00d5"+
		"\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00df\3\2\2\2\u00da\u00db\f\4\2\2\u00db"+
		"\u00dc\7\36\2\2\u00dc\u00de\5\16\b\5\u00dd\u00da\3\2\2\2\u00de\u00e1\3"+
		"\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\17\3\2\2\2\u00e1"+
		"\u00df\3\2\2\2\u00e2\u00e3\5\26\f\2\u00e3\u00e4\5\22\n\2\u00e4\u00e5\7"+
		"\35\2\2\u00e5\u00e6\5\n\6\2\u00e6\u00e7\7\7\2\2\u00e7\u00ed\3\2\2\2\u00e8"+
		"\u00e9\5\26\f\2\u00e9\u00ea\5\22\n\2\u00ea\u00eb\7\7\2\2\u00eb\u00ed\3"+
		"\2\2\2\u00ec\u00e2\3\2\2\2\u00ec\u00e8\3\2\2\2\u00ed\21\3\2\2\2\u00ee"+
		"\u00ef\79\2\2\u00ef\23\3\2\2\2\u00f0\u00f1\t\t\2\2\u00f1\25\3\2\2\2\u00f2"+
		"\u00f3\b\f\1\2\u00f3\u00f4\5\24\13\2\u00f4\u00fa\3\2\2\2\u00f5\u00f6\f"+
		"\4\2\2\u00f6\u00f7\7\n\2\2\u00f7\u00f9\7\13\2\2\u00f8\u00f5\3\2\2\2\u00f9"+
		"\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\27\3\2\2"+
		"\2\u00fc\u00fa\3\2\2\2\u00fd\u00ff\7\n\2\2\u00fe\u0100\5\n\6\2\u00ff\u00fe"+
		"\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0104\7\13\2\2"+
		"\u0102\u0104\7\37\2\2\u0103\u00fd\3\2\2\2\u0103\u0102\3\2\2\2\u0104\31"+
		"\3\2\2\2\u0105\u0109\5\24\13\2\u0106\u0108\5\30\r\2\u0107\u0106\3\2\2"+
		"\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\33"+
		"\3\2\2\2\u010b\u0109\3\2\2\2\32\37!\',\66LPT`kpt\u008f\u00c3\u00c5\u00ca"+
		"\u00d1\u00d8\u00df\u00ec\u00fa\u00ff\u0103\u0109";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}