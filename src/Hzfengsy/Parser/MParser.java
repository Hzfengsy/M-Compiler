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
		T__24=25, T__25=26, T__26=27, T__27=28, BOOL=29, INT=30, STRING=31, NULL=32, 
		VOID=33, TRUE=34, FALSE=35, IF=36, ELSE=37, FOR=38, WHILE=39, BREAK=40, 
		CONTI=41, RETRN=42, NEW=43, CLASS=44, THIS=45, NUM=46, STR=47, ESC=48, 
		COM=49, MUL=50, DIV=51, ADD=52, SUB=53, NAME=54, WS=55;
	public static final int
		RULE_main_prog = 0, RULE_prog = 1, RULE_clas = 2, RULE_func = 3, RULE_stat = 4, 
		RULE_expr = 5, RULE_expr_list = 6, RULE_stat_list = 7, RULE_define = 8, 
		RULE_id = 9, RULE_class_name = 10, RULE_class_stat = 11, RULE_dimension = 12, 
		RULE_class_new = 13;
	public static final String[] ruleNames = {
		"main_prog", "prog", "clas", "func", "stat", "expr", "expr_list", "stat_list", 
		"define", "id", "class_name", "class_stat", "dimension", "class_new"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "'('", "')'", "';'", "'++'", "'--'", "'['", "']'", 
		"'.'", "'~'", "'!'", "'%'", "'<<'", "'>>'", "'<'", "'<='", "'>'", "'>='", 
		"'=='", "'!='", "'&'", "'^'", "'|'", "'&&'", "'||'", "'='", "','", "'bool'", 
		"'int'", "'string'", "'null'", "'void'", "'true'", "'false'", "'if'", 
		"'else'", "'for'", "'while'", "'break'", "'continue'", "'return'", "'new'", 
		"'class'", "'this'", null, null, null, null, "'*'", "'/'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "BOOL", "INT", "STRING", "NULL", "VOID", 
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
	public static class Main_progContext extends ParserRuleContext {
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public Main_progContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterMain_prog(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitMain_prog(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitMain_prog(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Main_progContext main_prog() throws RecognitionException {
		Main_progContext _localctx = new Main_progContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_main_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			prog();
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
		enterRule(_localctx, 2, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(33);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(30);
					define();
					}
					break;
				case 2:
					{
					setState(31);
					func();
					}
					break;
				case 3:
					{
					setState(32);
					clas();
					}
					break;
				}
				}
				setState(35); 
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
		enterRule(_localctx, 4, RULE_clas);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			match(CLASS);
			setState(38);
			id();
			setState(39);
			match(T__0);
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << CLASS) | (1L << NAME))) != 0)) {
				{
				setState(40);
				prog();
				}
			}

			setState(43);
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
		enterRule(_localctx, 6, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(45);
				class_stat(0);
				}
				break;
			}
			setState(48);
			id();
			setState(49);
			match(T__2);
			setState(50);
			stat_list(0);
			setState(51);
			match(T__3);
			setState(52);
			match(T__0);
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << NULL) | (1L << VOID) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << BREAK) | (1L << CONTI) | (1L << RETRN) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
				{
				{
				setState(53);
				stat();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
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
		enterRule(_localctx, 8, RULE_stat);
		int _la;
		try {
			setState(118);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				_localctx = new If_StatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				match(IF);
				setState(62);
				match(T__2);
				setState(63);
				expr(0);
				setState(64);
				match(T__3);
				setState(65);
				stat();
				}
				break;
			case 2:
				_localctx = new IfElse_StatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				match(IF);
				setState(68);
				match(T__2);
				setState(69);
				expr(0);
				setState(70);
				match(T__3);
				setState(71);
				stat();
				setState(72);
				match(ELSE);
				setState(73);
				stat();
				}
				break;
			case 3:
				_localctx = new For_StatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				match(FOR);
				setState(76);
				match(T__2);
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
					{
					setState(77);
					((For_StatContext)_localctx).first = expr(0);
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
					((For_StatContext)_localctx).second = expr(0);
					}
				}

				setState(84);
				match(T__4);
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
					{
					setState(85);
					((For_StatContext)_localctx).third = expr(0);
					}
				}

				setState(88);
				match(T__3);
				setState(89);
				stat();
				}
				break;
			case 4:
				_localctx = new While_StatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(90);
				match(WHILE);
				setState(91);
				match(T__2);
				setState(92);
				expr(0);
				setState(93);
				match(T__3);
				setState(94);
				stat();
				}
				break;
			case 5:
				_localctx = new Return_StatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(96);
				match(RETRN);
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
					{
					setState(97);
					expr(0);
					}
				}

				setState(100);
				match(T__4);
				}
				break;
			case 6:
				_localctx = new Break_StatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(101);
				match(BREAK);
				setState(102);
				match(T__4);
				}
				break;
			case 7:
				_localctx = new Continue_StatContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(103);
				match(CONTI);
				setState(104);
				match(T__4);
				}
				break;
			case 8:
				_localctx = new Segment_StatContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(105);
				match(T__0);
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << NULL) | (1L << VOID) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << BREAK) | (1L << CONTI) | (1L << RETRN) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
					{
					{
					setState(106);
					stat();
					}
					}
					setState(111);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(112);
				match(T__1);
				}
				break;
			case 9:
				_localctx = new Expr_StatContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
					{
					setState(113);
					expr(0);
					}
				}

				setState(116);
				match(T__4);
				}
				break;
			case 10:
				_localctx = new Define_StatContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(117);
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
		public TerminalNode NEW() { return getToken(MParser.NEW, 0); }
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
	public static class LNotContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LNotContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).enterLNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MListener ) ((MListener)listener).exitLNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MVisitor ) return ((MVisitor<? extends T>)visitor).visitLNot(this);
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
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				_localctx = new FunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(121);
				id();
				setState(122);
				match(T__2);
				setState(123);
				expr_list(0);
				setState(124);
				match(T__3);
				}
				break;
			case 2:
				{
				_localctx = new PrefixContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(126);
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
				setState(127);
				expr(24);
				}
				break;
			case 3:
				{
				_localctx = new UnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
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
				setState(129);
				expr(23);
				}
				break;
			case 4:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				match(T__10);
				setState(131);
				expr(22);
				}
				break;
			case 5:
				{
				_localctx = new LNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				match(T__11);
				setState(133);
				expr(21);
				}
				break;
			case 6:
				{
				_localctx = new NewContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134);
				match(NEW);
				setState(135);
				class_new();
				}
				break;
			case 7:
				{
				_localctx = new NumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136);
				match(NUM);
				}
				break;
			case 8:
				{
				_localctx = new TrueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(137);
				match(TRUE);
				}
				break;
			case 9:
				{
				_localctx = new FalseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(138);
				match(FALSE);
				}
				break;
			case 10:
				{
				_localctx = new NullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(139);
				match(NULL);
				}
				break;
			case 11:
				{
				_localctx = new StrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(140);
				match(STR);
				}
				break;
			case 12:
				{
				_localctx = new ThisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141);
				match(THIS);
				}
				break;
			case 13:
				{
				_localctx = new IdentityContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(142);
				id();
				}
				break;
			case 14:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143);
				match(T__2);
				setState(144);
				expr(0);
				setState(145);
				match(T__3);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(201);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(199);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivModContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(149);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(150);
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
						setState(151);
						expr(20);
						}
						break;
					case 2:
						{
						_localctx = new AddSubContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(152);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(153);
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
						setState(154);
						expr(19);
						}
						break;
					case 3:
						{
						_localctx = new BitwiseContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(155);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(156);
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
						setState(157);
						expr(18);
						}
						break;
					case 4:
						{
						_localctx = new CompareContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(158);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(159);
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
						setState(160);
						expr(17);
						}
						break;
					case 5:
						{
						_localctx = new EqualContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(161);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(162);
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
						setState(163);
						expr(16);
						}
						break;
					case 6:
						{
						_localctx = new AndContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(164);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(165);
						match(T__21);
						setState(166);
						expr(15);
						}
						break;
					case 7:
						{
						_localctx = new XorContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(167);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(168);
						match(T__22);
						setState(169);
						expr(14);
						}
						break;
					case 8:
						{
						_localctx = new OrContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(170);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(171);
						match(T__23);
						setState(172);
						expr(13);
						}
						break;
					case 9:
						{
						_localctx = new LAndContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(173);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(174);
						match(T__24);
						setState(175);
						expr(11);
						}
						break;
					case 10:
						{
						_localctx = new LOrContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(176);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(177);
						match(T__25);
						setState(178);
						expr(10);
						}
						break;
					case 11:
						{
						_localctx = new AssignmentContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(179);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(180);
						match(T__26);
						setState(181);
						expr(1);
						}
						break;
					case 12:
						{
						_localctx = new PostfixContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(182);
						if (!(precpred(_ctx, 29))) throw new FailedPredicateException(this, "precpred(_ctx, 29)");
						setState(183);
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
						setState(184);
						if (!(precpred(_ctx, 27))) throw new FailedPredicateException(this, "precpred(_ctx, 27)");
						setState(185);
						match(T__7);
						setState(186);
						expr(0);
						setState(187);
						match(T__8);
						}
						break;
					case 14:
						{
						_localctx = new MembervarContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(189);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(190);
						match(T__9);
						setState(191);
						id();
						}
						break;
					case 15:
						{
						_localctx = new MemberfuncContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(192);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(193);
						match(T__9);
						setState(194);
						id();
						setState(195);
						match(T__2);
						setState(196);
						expr_list(0);
						setState(197);
						match(T__3);
						}
						break;
					}
					} 
				}
				setState(203);
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
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expr_list, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ExprListContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(206);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(205);
				expr(0);
				}
				break;
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(213);
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
					setState(208);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(209);
					match(T__27);
					setState(210);
					expr_list(3);
					}
					} 
				}
				setState(215);
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
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_stat_list, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new StatListContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(220);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(217);
				class_stat(0);
				setState(218);
				id();
				}
				break;
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(227);
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
					setState(222);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(223);
					match(T__27);
					setState(224);
					stat_list(3);
					}
					} 
				}
				setState(229);
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
		enterRule(_localctx, 16, RULE_define);
		try {
			setState(240);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new Assign_DefineContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(230);
				class_stat(0);
				setState(231);
				id();
				setState(232);
				match(T__26);
				setState(233);
				expr(0);
				setState(234);
				match(T__4);
				}
				break;
			case 2:
				_localctx = new Id_DefineContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				class_stat(0);
				setState(237);
				id();
				setState(238);
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
		enterRule(_localctx, 18, RULE_id);
		try {
			_localctx = new RAWIDContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
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
		enterRule(_localctx, 20, RULE_class_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
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
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_class_stat, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new SingleClassContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(247);
			class_name();
			}
			_ctx.stop = _input.LT(-1);
			setState(254);
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
					setState(249);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(250);
					match(T__7);
					setState(251);
					match(T__8);
					}
					} 
				}
				setState(256);
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
		enterRule(_localctx, 24, RULE_dimension);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(T__7);
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__6) | (1L << T__10) | (1L << T__11) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << NUM) | (1L << STR) | (1L << ADD) | (1L << SUB) | (1L << NAME))) != 0)) {
				{
				setState(258);
				expr(0);
				}
			}

			setState(261);
			match(T__8);
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
		enterRule(_localctx, 26, RULE_class_new);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			class_name();
			setState(274);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(267);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(264);
						dimension();
						}
						} 
					}
					setState(269);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				}
				}
				break;
			case 2:
				{
				setState(272);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(270);
					match(T__2);
					setState(271);
					match(T__3);
					}
					break;
				}
				}
				break;
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
		case 5:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 6:
			return expr_list_sempred((Expr_listContext)_localctx, predIndex);
		case 7:
			return stat_list_sempred((Stat_listContext)_localctx, predIndex);
		case 11:
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
			return precpred(_ctx, 29);
		case 12:
			return precpred(_ctx, 27);
		case 13:
			return precpred(_ctx, 26);
		case 14:
			return precpred(_ctx, 25);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\39\u0117\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\3\6\3$\n\3\r"+
		"\3\16\3%\3\4\3\4\3\4\3\4\5\4,\n\4\3\4\3\4\3\5\5\5\61\n\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\7\59\n\5\f\5\16\5<\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6Q\n\6\3\6\3\6\5\6U\n\6\3\6"+
		"\3\6\5\6Y\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6e\n\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\7\6n\n\6\f\6\16\6q\13\6\3\6\3\6\5\6u\n\6\3\6\3\6"+
		"\5\6y\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0096\n\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u00ca\n\7\f\7"+
		"\16\7\u00cd\13\7\3\b\3\b\5\b\u00d1\n\b\3\b\3\b\3\b\7\b\u00d6\n\b\f\b\16"+
		"\b\u00d9\13\b\3\t\3\t\3\t\3\t\5\t\u00df\n\t\3\t\3\t\3\t\7\t\u00e4\n\t"+
		"\f\t\16\t\u00e7\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00f3"+
		"\n\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00ff\n\r\f\r\16\r"+
		"\u0102\13\r\3\16\3\16\5\16\u0106\n\16\3\16\3\16\3\17\3\17\7\17\u010c\n"+
		"\17\f\17\16\17\u010f\13\17\3\17\3\17\5\17\u0113\n\17\5\17\u0115\n\17\3"+
		"\17\2\6\f\16\20\30\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\t\3\2\b\t"+
		"\3\2\66\67\4\2\17\17\64\65\3\2\20\21\3\2\22\25\3\2\26\27\5\2\37!##88\2"+
		"\u0143\2\36\3\2\2\2\4#\3\2\2\2\6\'\3\2\2\2\b\60\3\2\2\2\nx\3\2\2\2\f\u0095"+
		"\3\2\2\2\16\u00ce\3\2\2\2\20\u00da\3\2\2\2\22\u00f2\3\2\2\2\24\u00f4\3"+
		"\2\2\2\26\u00f6\3\2\2\2\30\u00f8\3\2\2\2\32\u0103\3\2\2\2\34\u0109\3\2"+
		"\2\2\36\37\5\4\3\2\37\3\3\2\2\2 $\5\22\n\2!$\5\b\5\2\"$\5\6\4\2# \3\2"+
		"\2\2#!\3\2\2\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\5\3\2\2\2\'("+
		"\7.\2\2()\5\24\13\2)+\7\3\2\2*,\5\4\3\2+*\3\2\2\2+,\3\2\2\2,-\3\2\2\2"+
		"-.\7\4\2\2.\7\3\2\2\2/\61\5\30\r\2\60/\3\2\2\2\60\61\3\2\2\2\61\62\3\2"+
		"\2\2\62\63\5\24\13\2\63\64\7\5\2\2\64\65\5\20\t\2\65\66\7\6\2\2\66:\7"+
		"\3\2\2\679\5\n\6\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;=\3\2\2\2"+
		"<:\3\2\2\2=>\7\4\2\2>\t\3\2\2\2?@\7&\2\2@A\7\5\2\2AB\5\f\7\2BC\7\6\2\2"+
		"CD\5\n\6\2Dy\3\2\2\2EF\7&\2\2FG\7\5\2\2GH\5\f\7\2HI\7\6\2\2IJ\5\n\6\2"+
		"JK\7\'\2\2KL\5\n\6\2Ly\3\2\2\2MN\7(\2\2NP\7\5\2\2OQ\5\f\7\2PO\3\2\2\2"+
		"PQ\3\2\2\2QR\3\2\2\2RT\7\7\2\2SU\5\f\7\2TS\3\2\2\2TU\3\2\2\2UV\3\2\2\2"+
		"VX\7\7\2\2WY\5\f\7\2XW\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z[\7\6\2\2[y\5\n\6\2"+
		"\\]\7)\2\2]^\7\5\2\2^_\5\f\7\2_`\7\6\2\2`a\5\n\6\2ay\3\2\2\2bd\7,\2\2"+
		"ce\5\f\7\2dc\3\2\2\2de\3\2\2\2ef\3\2\2\2fy\7\7\2\2gh\7*\2\2hy\7\7\2\2"+
		"ij\7+\2\2jy\7\7\2\2ko\7\3\2\2ln\5\n\6\2ml\3\2\2\2nq\3\2\2\2om\3\2\2\2"+
		"op\3\2\2\2pr\3\2\2\2qo\3\2\2\2ry\7\4\2\2su\5\f\7\2ts\3\2\2\2tu\3\2\2\2"+
		"uv\3\2\2\2vy\7\7\2\2wy\5\22\n\2x?\3\2\2\2xE\3\2\2\2xM\3\2\2\2x\\\3\2\2"+
		"\2xb\3\2\2\2xg\3\2\2\2xi\3\2\2\2xk\3\2\2\2xt\3\2\2\2xw\3\2\2\2y\13\3\2"+
		"\2\2z{\b\7\1\2{|\5\24\13\2|}\7\5\2\2}~\5\16\b\2~\177\7\6\2\2\177\u0096"+
		"\3\2\2\2\u0080\u0081\t\2\2\2\u0081\u0096\5\f\7\32\u0082\u0083\t\3\2\2"+
		"\u0083\u0096\5\f\7\31\u0084\u0085\7\r\2\2\u0085\u0096\5\f\7\30\u0086\u0087"+
		"\7\16\2\2\u0087\u0096\5\f\7\27\u0088\u0089\7-\2\2\u0089\u0096\5\34\17"+
		"\2\u008a\u0096\7\60\2\2\u008b\u0096\7$\2\2\u008c\u0096\7%\2\2\u008d\u0096"+
		"\7\"\2\2\u008e\u0096\7\61\2\2\u008f\u0096\7/\2\2\u0090\u0096\5\24\13\2"+
		"\u0091\u0092\7\5\2\2\u0092\u0093\5\f\7\2\u0093\u0094\7\6\2\2\u0094\u0096"+
		"\3\2\2\2\u0095z\3\2\2\2\u0095\u0080\3\2\2\2\u0095\u0082\3\2\2\2\u0095"+
		"\u0084\3\2\2\2\u0095\u0086\3\2\2\2\u0095\u0088\3\2\2\2\u0095\u008a\3\2"+
		"\2\2\u0095\u008b\3\2\2\2\u0095\u008c\3\2\2\2\u0095\u008d\3\2\2\2\u0095"+
		"\u008e\3\2\2\2\u0095\u008f\3\2\2\2\u0095\u0090\3\2\2\2\u0095\u0091\3\2"+
		"\2\2\u0096\u00cb\3\2\2\2\u0097\u0098\f\25\2\2\u0098\u0099\t\4\2\2\u0099"+
		"\u00ca\5\f\7\26\u009a\u009b\f\24\2\2\u009b\u009c\t\3\2\2\u009c\u00ca\5"+
		"\f\7\25\u009d\u009e\f\23\2\2\u009e\u009f\t\5\2\2\u009f\u00ca\5\f\7\24"+
		"\u00a0\u00a1\f\22\2\2\u00a1\u00a2\t\6\2\2\u00a2\u00ca\5\f\7\23\u00a3\u00a4"+
		"\f\21\2\2\u00a4\u00a5\t\7\2\2\u00a5\u00ca\5\f\7\22\u00a6\u00a7\f\20\2"+
		"\2\u00a7\u00a8\7\30\2\2\u00a8\u00ca\5\f\7\21\u00a9\u00aa\f\17\2\2\u00aa"+
		"\u00ab\7\31\2\2\u00ab\u00ca\5\f\7\20\u00ac\u00ad\f\16\2\2\u00ad\u00ae"+
		"\7\32\2\2\u00ae\u00ca\5\f\7\17\u00af\u00b0\f\r\2\2\u00b0\u00b1\7\33\2"+
		"\2\u00b1\u00ca\5\f\7\r\u00b2\u00b3\f\f\2\2\u00b3\u00b4\7\34\2\2\u00b4"+
		"\u00ca\5\f\7\f\u00b5\u00b6\f\3\2\2\u00b6\u00b7\7\35\2\2\u00b7\u00ca\5"+
		"\f\7\3\u00b8\u00b9\f\37\2\2\u00b9\u00ca\t\2\2\2\u00ba\u00bb\f\35\2\2\u00bb"+
		"\u00bc\7\n\2\2\u00bc\u00bd\5\f\7\2\u00bd\u00be\7\13\2\2\u00be\u00ca\3"+
		"\2\2\2\u00bf\u00c0\f\34\2\2\u00c0\u00c1\7\f\2\2\u00c1\u00ca\5\24\13\2"+
		"\u00c2\u00c3\f\33\2\2\u00c3\u00c4\7\f\2\2\u00c4\u00c5\5\24\13\2\u00c5"+
		"\u00c6\7\5\2\2\u00c6\u00c7\5\16\b\2\u00c7\u00c8\7\6\2\2\u00c8\u00ca\3"+
		"\2\2\2\u00c9\u0097\3\2\2\2\u00c9\u009a\3\2\2\2\u00c9\u009d\3\2\2\2\u00c9"+
		"\u00a0\3\2\2\2\u00c9\u00a3\3\2\2\2\u00c9\u00a6\3\2\2\2\u00c9\u00a9\3\2"+
		"\2\2\u00c9\u00ac\3\2\2\2\u00c9\u00af\3\2\2\2\u00c9\u00b2\3\2\2\2\u00c9"+
		"\u00b5\3\2\2\2\u00c9\u00b8\3\2\2\2\u00c9\u00ba\3\2\2\2\u00c9\u00bf\3\2"+
		"\2\2\u00c9\u00c2\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb"+
		"\u00cc\3\2\2\2\u00cc\r\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00d0\b\b\1\2"+
		"\u00cf\u00d1\5\f\7\2\u00d0\u00cf\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d7"+
		"\3\2\2\2\u00d2\u00d3\f\4\2\2\u00d3\u00d4\7\36\2\2\u00d4\u00d6\5\16\b\5"+
		"\u00d5\u00d2\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8"+
		"\3\2\2\2\u00d8\17\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00de\b\t\1\2\u00db"+
		"\u00dc\5\30\r\2\u00dc\u00dd\5\24\13\2\u00dd\u00df\3\2\2\2\u00de\u00db"+
		"\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e5\3\2\2\2\u00e0\u00e1\f\4\2\2\u00e1"+
		"\u00e2\7\36\2\2\u00e2\u00e4\5\20\t\5\u00e3\u00e0\3\2\2\2\u00e4\u00e7\3"+
		"\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\21\3\2\2\2\u00e7"+
		"\u00e5\3\2\2\2\u00e8\u00e9\5\30\r\2\u00e9\u00ea\5\24\13\2\u00ea\u00eb"+
		"\7\35\2\2\u00eb\u00ec\5\f\7\2\u00ec\u00ed\7\7\2\2\u00ed\u00f3\3\2\2\2"+
		"\u00ee\u00ef\5\30\r\2\u00ef\u00f0\5\24\13\2\u00f0\u00f1\7\7\2\2\u00f1"+
		"\u00f3\3\2\2\2\u00f2\u00e8\3\2\2\2\u00f2\u00ee\3\2\2\2\u00f3\23\3\2\2"+
		"\2\u00f4\u00f5\78\2\2\u00f5\25\3\2\2\2\u00f6\u00f7\t\b\2\2\u00f7\27\3"+
		"\2\2\2\u00f8\u00f9\b\r\1\2\u00f9\u00fa\5\26\f\2\u00fa\u0100\3\2\2\2\u00fb"+
		"\u00fc\f\4\2\2\u00fc\u00fd\7\n\2\2\u00fd\u00ff\7\13\2\2\u00fe\u00fb\3"+
		"\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"\31\3\2\2\2\u0102\u0100\3\2\2\2\u0103\u0105\7\n\2\2\u0104\u0106\5\f\7"+
		"\2\u0105\u0104\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108"+
		"\7\13\2\2\u0108\33\3\2\2\2\u0109\u0114\5\26\f\2\u010a\u010c\5\32\16\2"+
		"\u010b\u010a\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2\2\2\u010d\u010e"+
		"\3\2\2\2\u010e\u0115\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u0111\7\5\2\2\u0111"+
		"\u0113\7\6\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0115\3\2"+
		"\2\2\u0114\u010d\3\2\2\2\u0114\u0112\3\2\2\2\u0115\35\3\2\2\2\33#%+\60"+
		":PTXdotx\u0095\u00c9\u00cb\u00d0\u00d7\u00de\u00e5\u00f2\u0100\u0105\u010d"+
		"\u0112\u0114";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}