// Generated from /Users/fengsiyuan/IdeaProjects/Compiler/src/Hzfengsy/Parser/M.g4 by ANTLR 4.7
package Hzfengsy.Parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "T__27", "BOOL", "INT", "STRING", "NULL", "VOID", "TRUE", 
		"FALSE", "IF", "ELSE", "FOR", "WHILE", "BREAK", "CONTI", "RETRN", "NEW", 
		"CLASS", "THIS", "NUM", "STR", "ESC", "COM", "MUL", "DIV", "ADD", "SUB", 
		"NAME", "WS"
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


	public MLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "M.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\29\u014a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22"+
		"\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36"+
		"\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3!\3!\3"+
		"!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3%"+
		"\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3"+
		"*\3*\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3-\3-\3-\3"+
		"-\3-\3-\3.\3.\3.\3.\3.\3/\6/\u0111\n/\r/\16/\u0112\3\60\3\60\3\60\7\60"+
		"\u0118\n\60\f\60\16\60\u011b\13\60\3\60\3\60\3\61\3\61\3\61\3\61\5\61"+
		"\u0123\n\61\3\62\3\62\3\62\3\62\7\62\u0129\n\62\f\62\16\62\u012c\13\62"+
		"\3\62\5\62\u012f\n\62\3\62\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65"+
		"\3\66\3\66\3\67\3\67\7\67\u013f\n\67\f\67\16\67\u0142\13\67\38\68\u0145"+
		"\n8\r8\168\u0146\38\38\4\u0119\u012a\29\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W"+
		"-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9\3\2\6\3\2\62;\4\2C\\c|\6\2\62"+
		";C\\aac|\5\2\13\f\17\17\"\"\2\u0151\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3"+
		"\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2"+
		"\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2"+
		"\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2["+
		"\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2"+
		"\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\3q\3\2\2\2\5s\3\2\2\2"+
		"\7u\3\2\2\2\tw\3\2\2\2\13y\3\2\2\2\r{\3\2\2\2\17~\3\2\2\2\21\u0081\3\2"+
		"\2\2\23\u0083\3\2\2\2\25\u0085\3\2\2\2\27\u0087\3\2\2\2\31\u0089\3\2\2"+
		"\2\33\u008b\3\2\2\2\35\u008d\3\2\2\2\37\u0090\3\2\2\2!\u0093\3\2\2\2#"+
		"\u0095\3\2\2\2%\u0098\3\2\2\2\'\u009a\3\2\2\2)\u009d\3\2\2\2+\u00a0\3"+
		"\2\2\2-\u00a3\3\2\2\2/\u00a5\3\2\2\2\61\u00a7\3\2\2\2\63\u00a9\3\2\2\2"+
		"\65\u00ac\3\2\2\2\67\u00af\3\2\2\29\u00b1\3\2\2\2;\u00b3\3\2\2\2=\u00b8"+
		"\3\2\2\2?\u00bc\3\2\2\2A\u00c3\3\2\2\2C\u00c8\3\2\2\2E\u00cd\3\2\2\2G"+
		"\u00d2\3\2\2\2I\u00d8\3\2\2\2K\u00db\3\2\2\2M\u00e0\3\2\2\2O\u00e4\3\2"+
		"\2\2Q\u00ea\3\2\2\2S\u00f0\3\2\2\2U\u00f9\3\2\2\2W\u0100\3\2\2\2Y\u0104"+
		"\3\2\2\2[\u010a\3\2\2\2]\u0110\3\2\2\2_\u0114\3\2\2\2a\u0122\3\2\2\2c"+
		"\u0124\3\2\2\2e\u0134\3\2\2\2g\u0136\3\2\2\2i\u0138\3\2\2\2k\u013a\3\2"+
		"\2\2m\u013c\3\2\2\2o\u0144\3\2\2\2qr\7}\2\2r\4\3\2\2\2st\7\177\2\2t\6"+
		"\3\2\2\2uv\7*\2\2v\b\3\2\2\2wx\7+\2\2x\n\3\2\2\2yz\7=\2\2z\f\3\2\2\2{"+
		"|\7-\2\2|}\7-\2\2}\16\3\2\2\2~\177\7/\2\2\177\u0080\7/\2\2\u0080\20\3"+
		"\2\2\2\u0081\u0082\7]\2\2\u0082\22\3\2\2\2\u0083\u0084\7_\2\2\u0084\24"+
		"\3\2\2\2\u0085\u0086\7\60\2\2\u0086\26\3\2\2\2\u0087\u0088\7\u0080\2\2"+
		"\u0088\30\3\2\2\2\u0089\u008a\7#\2\2\u008a\32\3\2\2\2\u008b\u008c\7\'"+
		"\2\2\u008c\34\3\2\2\2\u008d\u008e\7>\2\2\u008e\u008f\7>\2\2\u008f\36\3"+
		"\2\2\2\u0090\u0091\7@\2\2\u0091\u0092\7@\2\2\u0092 \3\2\2\2\u0093\u0094"+
		"\7>\2\2\u0094\"\3\2\2\2\u0095\u0096\7>\2\2\u0096\u0097\7?\2\2\u0097$\3"+
		"\2\2\2\u0098\u0099\7@\2\2\u0099&\3\2\2\2\u009a\u009b\7@\2\2\u009b\u009c"+
		"\7?\2\2\u009c(\3\2\2\2\u009d\u009e\7?\2\2\u009e\u009f\7?\2\2\u009f*\3"+
		"\2\2\2\u00a0\u00a1\7#\2\2\u00a1\u00a2\7?\2\2\u00a2,\3\2\2\2\u00a3\u00a4"+
		"\7(\2\2\u00a4.\3\2\2\2\u00a5\u00a6\7`\2\2\u00a6\60\3\2\2\2\u00a7\u00a8"+
		"\7~\2\2\u00a8\62\3\2\2\2\u00a9\u00aa\7(\2\2\u00aa\u00ab\7(\2\2\u00ab\64"+
		"\3\2\2\2\u00ac\u00ad\7~\2\2\u00ad\u00ae\7~\2\2\u00ae\66\3\2\2\2\u00af"+
		"\u00b0\7?\2\2\u00b08\3\2\2\2\u00b1\u00b2\7.\2\2\u00b2:\3\2\2\2\u00b3\u00b4"+
		"\7d\2\2\u00b4\u00b5\7q\2\2\u00b5\u00b6\7q\2\2\u00b6\u00b7\7n\2\2\u00b7"+
		"<\3\2\2\2\u00b8\u00b9\7k\2\2\u00b9\u00ba\7p\2\2\u00ba\u00bb\7v\2\2\u00bb"+
		">\3\2\2\2\u00bc\u00bd\7u\2\2\u00bd\u00be\7v\2\2\u00be\u00bf\7t\2\2\u00bf"+
		"\u00c0\7k\2\2\u00c0\u00c1\7p\2\2\u00c1\u00c2\7i\2\2\u00c2@\3\2\2\2\u00c3"+
		"\u00c4\7p\2\2\u00c4\u00c5\7w\2\2\u00c5\u00c6\7n\2\2\u00c6\u00c7\7n\2\2"+
		"\u00c7B\3\2\2\2\u00c8\u00c9\7x\2\2\u00c9\u00ca\7q\2\2\u00ca\u00cb\7k\2"+
		"\2\u00cb\u00cc\7f\2\2\u00ccD\3\2\2\2\u00cd\u00ce\7v\2\2\u00ce\u00cf\7"+
		"t\2\2\u00cf\u00d0\7w\2\2\u00d0\u00d1\7g\2\2\u00d1F\3\2\2\2\u00d2\u00d3"+
		"\7h\2\2\u00d3\u00d4\7c\2\2\u00d4\u00d5\7n\2\2\u00d5\u00d6\7u\2\2\u00d6"+
		"\u00d7\7g\2\2\u00d7H\3\2\2\2\u00d8\u00d9\7k\2\2\u00d9\u00da\7h\2\2\u00da"+
		"J\3\2\2\2\u00db\u00dc\7g\2\2\u00dc\u00dd\7n\2\2\u00dd\u00de\7u\2\2\u00de"+
		"\u00df\7g\2\2\u00dfL\3\2\2\2\u00e0\u00e1\7h\2\2\u00e1\u00e2\7q\2\2\u00e2"+
		"\u00e3\7t\2\2\u00e3N\3\2\2\2\u00e4\u00e5\7y\2\2\u00e5\u00e6\7j\2\2\u00e6"+
		"\u00e7\7k\2\2\u00e7\u00e8\7n\2\2\u00e8\u00e9\7g\2\2\u00e9P\3\2\2\2\u00ea"+
		"\u00eb\7d\2\2\u00eb\u00ec\7t\2\2\u00ec\u00ed\7g\2\2\u00ed\u00ee\7c\2\2"+
		"\u00ee\u00ef\7m\2\2\u00efR\3\2\2\2\u00f0\u00f1\7e\2\2\u00f1\u00f2\7q\2"+
		"\2\u00f2\u00f3\7p\2\2\u00f3\u00f4\7v\2\2\u00f4\u00f5\7k\2\2\u00f5\u00f6"+
		"\7p\2\2\u00f6\u00f7\7w\2\2\u00f7\u00f8\7g\2\2\u00f8T\3\2\2\2\u00f9\u00fa"+
		"\7t\2\2\u00fa\u00fb\7g\2\2\u00fb\u00fc\7v\2\2\u00fc\u00fd\7w\2\2\u00fd"+
		"\u00fe\7t\2\2\u00fe\u00ff\7p\2\2\u00ffV\3\2\2\2\u0100\u0101\7p\2\2\u0101"+
		"\u0102\7g\2\2\u0102\u0103\7y\2\2\u0103X\3\2\2\2\u0104\u0105\7e\2\2\u0105"+
		"\u0106\7n\2\2\u0106\u0107\7c\2\2\u0107\u0108\7u\2\2\u0108\u0109\7u\2\2"+
		"\u0109Z\3\2\2\2\u010a\u010b\7v\2\2\u010b\u010c\7j\2\2\u010c\u010d\7k\2"+
		"\2\u010d\u010e\7u\2\2\u010e\\\3\2\2\2\u010f\u0111\t\2\2\2\u0110\u010f"+
		"\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"^\3\2\2\2\u0114\u0119\7$\2\2\u0115\u0118\5a\61\2\u0116\u0118\13\2\2\2"+
		"\u0117\u0115\3\2\2\2\u0117\u0116\3\2\2\2\u0118\u011b\3\2\2\2\u0119\u011a"+
		"\3\2\2\2\u0119\u0117\3\2\2\2\u011a\u011c\3\2\2\2\u011b\u0119\3\2\2\2\u011c"+
		"\u011d\7$\2\2\u011d`\3\2\2\2\u011e\u011f\7^\2\2\u011f\u0123\7$\2\2\u0120"+
		"\u0121\7^\2\2\u0121\u0123\7^\2\2\u0122\u011e\3\2\2\2\u0122\u0120\3\2\2"+
		"\2\u0123b\3\2\2\2\u0124\u0125\7\61\2\2\u0125\u0126\7\61\2\2\u0126\u012a"+
		"\3\2\2\2\u0127\u0129\13\2\2\2\u0128\u0127\3\2\2\2\u0129\u012c\3\2\2\2"+
		"\u012a\u012b\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012a"+
		"\3\2\2\2\u012d\u012f\7\17\2\2\u012e\u012d\3\2\2\2\u012e\u012f\3\2\2\2"+
		"\u012f\u0130\3\2\2\2\u0130\u0131\7\f\2\2\u0131\u0132\3\2\2\2\u0132\u0133"+
		"\b\62\2\2\u0133d\3\2\2\2\u0134\u0135\7,\2\2\u0135f\3\2\2\2\u0136\u0137"+
		"\7\61\2\2\u0137h\3\2\2\2\u0138\u0139\7-\2\2\u0139j\3\2\2\2\u013a\u013b"+
		"\7/\2\2\u013bl\3\2\2\2\u013c\u0140\t\3\2\2\u013d\u013f\t\4\2\2\u013e\u013d"+
		"\3\2\2\2\u013f\u0142\3\2\2\2\u0140\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141"+
		"n\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u0145\t\5\2\2\u0144\u0143\3\2\2\2"+
		"\u0145\u0146\3\2\2\2\u0146\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u0148"+
		"\3\2\2\2\u0148\u0149\b8\2\2\u0149p\3\2\2\2\13\2\u0112\u0117\u0119\u0122"+
		"\u012a\u012e\u0140\u0146\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}