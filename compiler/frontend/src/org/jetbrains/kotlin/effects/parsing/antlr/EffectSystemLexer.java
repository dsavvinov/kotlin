// Generated from EffectSystem.g4 by ANTLR 4.6
package org.jetbrains.kotlin.effects.parsing.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EffectSystemLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, BooleanLiteral=17, 
		NullLiteral=18, UnknownLiteral=19, UnitLiteral=20, StringLiteral=21, IntegerLiteral=22, 
		SimpleName=23, WS=24, EOL=25, SEMI=26, LT=27, GT=28, LEQ=29, GEQ=30, PLUS=31, 
		MINUS=32, MUL=33, DIV=34, PERC=35, PLUSPLUS=36, MINUSMINUS=37, NOT=38, 
		EXCLEXCL=39, EQEQ=40, EXCLEQ=41;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "BooleanLiteral", 
		"NullLiteral", "UnknownLiteral", "UnitLiteral", "StringLiteral", "StringCharacters", 
		"StringCharacter", "EscapeSequence", "OctalEscape", "UnicodeEscape", "ZeroToThree", 
		"IntegerLiteral", "DecimalIntegerLiteral", "IntegerTypeSuffix", "DecimalNumeral", 
		"Digits", "Digit", "NonZeroDigit", "DigitOrUnderscore", "Underscores", 
		"OctalDigit", "HexDigit", "SimpleName", "JavaLetter", "JavaLetterOrDigit", 
		"WS", "EOL", "SEMI", "LT", "GT", "LEQ", "GEQ", "PLUS", "MINUS", "MUL", 
		"DIV", "PERC", "PLUSPLUS", "MINUSMINUS", "NOT", "EXCLEXCL", "EQEQ", "EXCLEQ"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'->'", "'('", "')'", "'||'", "'&&'", "','", "'in'", "'!in'", "'is'", 
		"'!is'", "'at'", "'Throws'", "'Returns'", "'Calls'", "'Hints'", "'typeOf'", 
		null, "'null'", "'unknown'", "'unit'", null, null, null, null, null, "';'", 
		"'<'", "'>'", "'<='", "'>='", "'+'", "'-'", "'*'", "'/'", "'%'", "'++'", 
		"'--'", "'!'", "'!!'", "'=='", "'!='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "BooleanLiteral", "NullLiteral", "UnknownLiteral", 
		"UnitLiteral", "StringLiteral", "IntegerLiteral", "SimpleName", "WS", 
		"EOL", "SEMI", "LT", "GT", "LEQ", "GEQ", "PLUS", "MINUS", "MUL", "DIV", 
		"PERC", "PLUSPLUS", "MINUSMINUS", "NOT", "EXCLEXCL", "EQEQ", "EXCLEQ"
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


	public EffectSystemLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "EffectSystem.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2+\u017a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\3\2\3"+
		"\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00c5\n\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\5\26\u00db\n\26\3\26\3\26\3\27\6\27\u00e0"+
		"\n\27\r\27\16\27\u00e1\3\30\3\30\5\30\u00e6\n\30\3\31\3\31\3\31\3\31\5"+
		"\31\u00ec\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\5\32\u00f9\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\36\3\36\5\36\u0108\n\36\3\37\3\37\3 \3 \3 \5 \u010f\n \3 \3 \3 \5 "+
		"\u0114\n \5 \u0116\n \3!\3!\7!\u011a\n!\f!\16!\u011d\13!\3!\5!\u0120\n"+
		"!\3\"\3\"\5\"\u0124\n\"\3#\3#\3$\3$\5$\u012a\n$\3%\6%\u012d\n%\r%\16%"+
		"\u012e\3&\3&\3\'\3\'\3(\3(\7(\u0137\n(\f(\16(\u013a\13(\3)\3)\3)\3)\5"+
		")\u0140\n)\3*\3*\3*\3*\5*\u0146\n*\3+\6+\u0149\n+\r+\16+\u014a\3+\3+\3"+
		",\5,\u0150\n,\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3"+
		"\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\3\67\38\38"+
		"\38\39\39\3:\3:\3:\3;\3;\3;\3<\3<\3<\2\2=\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\2/\2\61\2\63\2\65\2\67\29\30;\2=\2?\2A\2C\2E\2G\2I\2K\2M\2O\31Q\2"+
		"S\2U\32W\33Y\34[\35]\36_\37a c!e\"g#i$k%m&o\'q(s)u*w+\3\2\17\4\2$$^^\n"+
		"\2$$))^^ddhhppttvv\3\2\62\65\4\2NNnn\3\2\63;\3\2\629\5\2\62;CHch\6\2&"+
		"&C\\aac|\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001\7\2&"+
		"&\62;C\\aac|\5\2\13\f\16\17\"\"\u017f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\29\3\2\2\2\2O\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2"+
		"\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2"+
		"g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3"+
		"\2\2\2\2u\3\2\2\2\2w\3\2\2\2\3y\3\2\2\2\5|\3\2\2\2\7~\3\2\2\2\t\u0080"+
		"\3\2\2\2\13\u0083\3\2\2\2\r\u0086\3\2\2\2\17\u0088\3\2\2\2\21\u008b\3"+
		"\2\2\2\23\u008f\3\2\2\2\25\u0092\3\2\2\2\27\u0096\3\2\2\2\31\u0099\3\2"+
		"\2\2\33\u00a0\3\2\2\2\35\u00a8\3\2\2\2\37\u00ae\3\2\2\2!\u00b4\3\2\2\2"+
		"#\u00c4\3\2\2\2%\u00c6\3\2\2\2\'\u00cb\3\2\2\2)\u00d3\3\2\2\2+\u00d8\3"+
		"\2\2\2-\u00df\3\2\2\2/\u00e5\3\2\2\2\61\u00eb\3\2\2\2\63\u00f8\3\2\2\2"+
		"\65\u00fa\3\2\2\2\67\u0101\3\2\2\29\u0103\3\2\2\2;\u0105\3\2\2\2=\u0109"+
		"\3\2\2\2?\u0115\3\2\2\2A\u0117\3\2\2\2C\u0123\3\2\2\2E\u0125\3\2\2\2G"+
		"\u0129\3\2\2\2I\u012c\3\2\2\2K\u0130\3\2\2\2M\u0132\3\2\2\2O\u0134\3\2"+
		"\2\2Q\u013f\3\2\2\2S\u0145\3\2\2\2U\u0148\3\2\2\2W\u014f\3\2\2\2Y\u0153"+
		"\3\2\2\2[\u0155\3\2\2\2]\u0157\3\2\2\2_\u0159\3\2\2\2a\u015c\3\2\2\2c"+
		"\u015f\3\2\2\2e\u0161\3\2\2\2g\u0163\3\2\2\2i\u0165\3\2\2\2k\u0167\3\2"+
		"\2\2m\u0169\3\2\2\2o\u016c\3\2\2\2q\u016f\3\2\2\2s\u0171\3\2\2\2u\u0174"+
		"\3\2\2\2w\u0177\3\2\2\2yz\7/\2\2z{\7@\2\2{\4\3\2\2\2|}\7*\2\2}\6\3\2\2"+
		"\2~\177\7+\2\2\177\b\3\2\2\2\u0080\u0081\7~\2\2\u0081\u0082\7~\2\2\u0082"+
		"\n\3\2\2\2\u0083\u0084\7(\2\2\u0084\u0085\7(\2\2\u0085\f\3\2\2\2\u0086"+
		"\u0087\7.\2\2\u0087\16\3\2\2\2\u0088\u0089\7k\2\2\u0089\u008a\7p\2\2\u008a"+
		"\20\3\2\2\2\u008b\u008c\7#\2\2\u008c\u008d\7k\2\2\u008d\u008e\7p\2\2\u008e"+
		"\22\3\2\2\2\u008f\u0090\7k\2\2\u0090\u0091\7u\2\2\u0091\24\3\2\2\2\u0092"+
		"\u0093\7#\2\2\u0093\u0094\7k\2\2\u0094\u0095\7u\2\2\u0095\26\3\2\2\2\u0096"+
		"\u0097\7c\2\2\u0097\u0098\7v\2\2\u0098\30\3\2\2\2\u0099\u009a\7V\2\2\u009a"+
		"\u009b\7j\2\2\u009b\u009c\7t\2\2\u009c\u009d\7q\2\2\u009d\u009e\7y\2\2"+
		"\u009e\u009f\7u\2\2\u009f\32\3\2\2\2\u00a0\u00a1\7T\2\2\u00a1\u00a2\7"+
		"g\2\2\u00a2\u00a3\7v\2\2\u00a3\u00a4\7w\2\2\u00a4\u00a5\7t\2\2\u00a5\u00a6"+
		"\7p\2\2\u00a6\u00a7\7u\2\2\u00a7\34\3\2\2\2\u00a8\u00a9\7E\2\2\u00a9\u00aa"+
		"\7c\2\2\u00aa\u00ab\7n\2\2\u00ab\u00ac\7n\2\2\u00ac\u00ad\7u\2\2\u00ad"+
		"\36\3\2\2\2\u00ae\u00af\7J\2\2\u00af\u00b0\7k\2\2\u00b0\u00b1\7p\2\2\u00b1"+
		"\u00b2\7v\2\2\u00b2\u00b3\7u\2\2\u00b3 \3\2\2\2\u00b4\u00b5\7v\2\2\u00b5"+
		"\u00b6\7{\2\2\u00b6\u00b7\7r\2\2\u00b7\u00b8\7g\2\2\u00b8\u00b9\7Q\2\2"+
		"\u00b9\u00ba\7h\2\2\u00ba\"\3\2\2\2\u00bb\u00bc\7v\2\2\u00bc\u00bd\7t"+
		"\2\2\u00bd\u00be\7w\2\2\u00be\u00c5\7g\2\2\u00bf\u00c0\7h\2\2\u00c0\u00c1"+
		"\7c\2\2\u00c1\u00c2\7n\2\2\u00c2\u00c3\7u\2\2\u00c3\u00c5\7g\2\2\u00c4"+
		"\u00bb\3\2\2\2\u00c4\u00bf\3\2\2\2\u00c5$\3\2\2\2\u00c6\u00c7\7p\2\2\u00c7"+
		"\u00c8\7w\2\2\u00c8\u00c9\7n\2\2\u00c9\u00ca\7n\2\2\u00ca&\3\2\2\2\u00cb"+
		"\u00cc\7w\2\2\u00cc\u00cd\7p\2\2\u00cd\u00ce\7m\2\2\u00ce\u00cf\7p\2\2"+
		"\u00cf\u00d0\7q\2\2\u00d0\u00d1\7y\2\2\u00d1\u00d2\7p\2\2\u00d2(\3\2\2"+
		"\2\u00d3\u00d4\7w\2\2\u00d4\u00d5\7p\2\2\u00d5\u00d6\7k\2\2\u00d6\u00d7"+
		"\7v\2\2\u00d7*\3\2\2\2\u00d8\u00da\7$\2\2\u00d9\u00db\5-\27\2\u00da\u00d9"+
		"\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\7$\2\2\u00dd"+
		",\3\2\2\2\u00de\u00e0\5/\30\2\u00df\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2"+
		"\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2.\3\2\2\2\u00e3\u00e6\n"+
		"\2\2\2\u00e4\u00e6\5\61\31\2\u00e5\u00e3\3\2\2\2\u00e5\u00e4\3\2\2\2\u00e6"+
		"\60\3\2\2\2\u00e7\u00e8\7^\2\2\u00e8\u00ec\t\3\2\2\u00e9\u00ec\5\63\32"+
		"\2\u00ea\u00ec\5\65\33\2\u00eb\u00e7\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb"+
		"\u00ea\3\2\2\2\u00ec\62\3\2\2\2\u00ed\u00ee\7^\2\2\u00ee\u00f9\5K&\2\u00ef"+
		"\u00f0\7^\2\2\u00f0\u00f1\5K&\2\u00f1\u00f2\5K&\2\u00f2\u00f9\3\2\2\2"+
		"\u00f3\u00f4\7^\2\2\u00f4\u00f5\5\67\34\2\u00f5\u00f6\5K&\2\u00f6\u00f7"+
		"\5K&\2\u00f7\u00f9\3\2\2\2\u00f8\u00ed\3\2\2\2\u00f8\u00ef\3\2\2\2\u00f8"+
		"\u00f3\3\2\2\2\u00f9\64\3\2\2\2\u00fa\u00fb\7^\2\2\u00fb\u00fc\7w\2\2"+
		"\u00fc\u00fd\5M\'\2\u00fd\u00fe\5M\'\2\u00fe\u00ff\5M\'\2\u00ff\u0100"+
		"\5M\'\2\u0100\66\3\2\2\2\u0101\u0102\t\4\2\2\u01028\3\2\2\2\u0103\u0104"+
		"\5;\36\2\u0104:\3\2\2\2\u0105\u0107\5? \2\u0106\u0108\5=\37\2\u0107\u0106"+
		"\3\2\2\2\u0107\u0108\3\2\2\2\u0108<\3\2\2\2\u0109\u010a\t\5\2\2\u010a"+
		">\3\2\2\2\u010b\u0116\7\62\2\2\u010c\u0113\5E#\2\u010d\u010f\5A!\2\u010e"+
		"\u010d\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0114\3\2\2\2\u0110\u0111\5I"+
		"%\2\u0111\u0112\5A!\2\u0112\u0114\3\2\2\2\u0113\u010e\3\2\2\2\u0113\u0110"+
		"\3\2\2\2\u0114\u0116\3\2\2\2\u0115\u010b\3\2\2\2\u0115\u010c\3\2\2\2\u0116"+
		"@\3\2\2\2\u0117\u011f\5C\"\2\u0118\u011a\5G$\2\u0119\u0118\3\2\2\2\u011a"+
		"\u011d\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011e\3\2"+
		"\2\2\u011d\u011b\3\2\2\2\u011e\u0120\5C\"\2\u011f\u011b\3\2\2\2\u011f"+
		"\u0120\3\2\2\2\u0120B\3\2\2\2\u0121\u0124\7\62\2\2\u0122\u0124\5E#\2\u0123"+
		"\u0121\3\2\2\2\u0123\u0122\3\2\2\2\u0124D\3\2\2\2\u0125\u0126\t\6\2\2"+
		"\u0126F\3\2\2\2\u0127\u012a\5C\"\2\u0128\u012a\7a\2\2\u0129\u0127\3\2"+
		"\2\2\u0129\u0128\3\2\2\2\u012aH\3\2\2\2\u012b\u012d\7a\2\2\u012c\u012b"+
		"\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f"+
		"J\3\2\2\2\u0130\u0131\t\7\2\2\u0131L\3\2\2\2\u0132\u0133\t\b\2\2\u0133"+
		"N\3\2\2\2\u0134\u0138\5Q)\2\u0135\u0137\5S*\2\u0136\u0135\3\2\2\2\u0137"+
		"\u013a\3\2\2\2\u0138\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139P\3\2\2\2"+
		"\u013a\u0138\3\2\2\2\u013b\u0140\t\t\2\2\u013c\u0140\n\n\2\2\u013d\u013e"+
		"\t\13\2\2\u013e\u0140\t\f\2\2\u013f\u013b\3\2\2\2\u013f\u013c\3\2\2\2"+
		"\u013f\u013d\3\2\2\2\u0140R\3\2\2\2\u0141\u0146\t\r\2\2\u0142\u0146\n"+
		"\n\2\2\u0143\u0144\t\13\2\2\u0144\u0146\t\f\2\2\u0145\u0141\3\2\2\2\u0145"+
		"\u0142\3\2\2\2\u0145\u0143\3\2\2\2\u0146T\3\2\2\2\u0147\u0149\t\16\2\2"+
		"\u0148\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u0148\3\2\2\2\u014a\u014b"+
		"\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\b+\2\2\u014dV\3\2\2\2\u014e\u0150"+
		"\7\17\2\2\u014f\u014e\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151\3\2\2\2"+
		"\u0151\u0152\7\f\2\2\u0152X\3\2\2\2\u0153\u0154\7=\2\2\u0154Z\3\2\2\2"+
		"\u0155\u0156\7>\2\2\u0156\\\3\2\2\2\u0157\u0158\7@\2\2\u0158^\3\2\2\2"+
		"\u0159\u015a\7>\2\2\u015a\u015b\7?\2\2\u015b`\3\2\2\2\u015c\u015d\7@\2"+
		"\2\u015d\u015e\7?\2\2\u015eb\3\2\2\2\u015f\u0160\7-\2\2\u0160d\3\2\2\2"+
		"\u0161\u0162\7/\2\2\u0162f\3\2\2\2\u0163\u0164\7,\2\2\u0164h\3\2\2\2\u0165"+
		"\u0166\7\61\2\2\u0166j\3\2\2\2\u0167\u0168\7\'\2\2\u0168l\3\2\2\2\u0169"+
		"\u016a\7-\2\2\u016a\u016b\7-\2\2\u016bn\3\2\2\2\u016c\u016d\7/\2\2\u016d"+
		"\u016e\7/\2\2\u016ep\3\2\2\2\u016f\u0170\7#\2\2\u0170r\3\2\2\2\u0171\u0172"+
		"\7#\2\2\u0172\u0173\7#\2\2\u0173t\3\2\2\2\u0174\u0175\7?\2\2\u0175\u0176"+
		"\7?\2\2\u0176v\3\2\2\2\u0177\u0178\7#\2\2\u0178\u0179\7?\2\2\u0179x\3"+
		"\2\2\2\27\2\u00c4\u00da\u00e1\u00e5\u00eb\u00f8\u0107\u010e\u0113\u0115"+
		"\u011b\u011f\u0123\u0129\u012e\u0138\u013f\u0145\u014a\u014f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}