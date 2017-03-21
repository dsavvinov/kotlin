// Generated from /home/dsavvinov/Repos/kotlin-fork/kotlin/compiler/frontend/src/org/jetbrains/kotlin/effects/parsing/antlr/EffectSystem.g4 by ANTLR 4.6
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
		T__9=10, T__10=11, T__11=12, T__12=13, BooleanLiteral=14, NullLiteral=15, 
		StringLiteral=16, IntegerLiteral=17, SimpleName=18, WS=19, EOL=20, SEMI=21, 
		LT=22, GT=23, LEQ=24, GEQ=25, PLUS=26, MINUS=27, MUL=28, DIV=29, PERC=30, 
		PLUSPLUS=31, MINUSMINUS=32, NOT=33, EXCLEXCL=34, EQEQ=35, EXCLEQ=36;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "BooleanLiteral", "NullLiteral", "StringLiteral", 
		"StringCharacters", "StringCharacter", "EscapeSequence", "OctalEscape", 
		"UnicodeEscape", "ZeroToThree", "IntegerLiteral", "DecimalIntegerLiteral", 
		"IntegerTypeSuffix", "DecimalNumeral", "Digits", "Digit", "NonZeroDigit", 
		"DigitOrUnderscore", "Underscores", "OctalDigit", "HexDigit", "SimpleName", 
		"JavaLetter", "JavaLetterOrDigit", "WS", "EOL", "SEMI", "LT", "GT", "LEQ", 
		"GEQ", "PLUS", "MINUS", "MUL", "DIV", "PERC", "PLUSPLUS", "MINUSMINUS", 
		"NOT", "EXCLEXCL", "EQEQ", "EXCLEQ"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'->'", "'('", "')'", "'||'", "'&&'", "'in'", "'!in'", "'is'", "'!is'", 
		"','", "'Throws'", "'Returns'", "'Calls'", null, "'null'", null, null, 
		null, null, null, "';'", "'<'", "'>'", "'<='", "'>='", "'+'", "'-'", "'*'", 
		"'/'", "'%'", "'++'", "'--'", "'!'", "'!!'", "'=='", "'!='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "BooleanLiteral", "NullLiteral", "StringLiteral", "IntegerLiteral", 
		"SimpleName", "WS", "EOL", "SEMI", "LT", "GT", "LEQ", "GEQ", "PLUS", "MINUS", 
		"MUL", "DIV", "PERC", "PLUSPLUS", "MINUSMINUS", "NOT", "EXCLEXCL", "EQEQ", 
		"EXCLEQ"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2&\u0153\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\5\17\u00ab\n\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\5\21\u00b4"+
		"\n\21\3\21\3\21\3\22\6\22\u00b9\n\22\r\22\16\22\u00ba\3\23\3\23\5\23\u00bf"+
		"\n\23\3\24\3\24\3\24\3\24\5\24\u00c5\n\24\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\5\25\u00d2\n\25\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\30\3\30\3\31\3\31\5\31\u00e1\n\31\3\32\3\32\3\33\3\33"+
		"\3\33\5\33\u00e8\n\33\3\33\3\33\3\33\5\33\u00ed\n\33\5\33\u00ef\n\33\3"+
		"\34\3\34\7\34\u00f3\n\34\f\34\16\34\u00f6\13\34\3\34\5\34\u00f9\n\34\3"+
		"\35\3\35\5\35\u00fd\n\35\3\36\3\36\3\37\3\37\5\37\u0103\n\37\3 \6 \u0106"+
		"\n \r \16 \u0107\3!\3!\3\"\3\"\3#\3#\7#\u0110\n#\f#\16#\u0113\13#\3$\3"+
		"$\3$\3$\5$\u0119\n$\3%\3%\3%\3%\5%\u011f\n%\3&\6&\u0122\n&\r&\16&\u0123"+
		"\3&\3&\3\'\5\'\u0129\n\'\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,\3"+
		"-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\63\3\64"+
		"\3\64\3\65\3\65\3\65\3\66\3\66\3\66\3\67\3\67\3\67\2\28\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\2%"+
		"\2\'\2)\2+\2-\2/\23\61\2\63\2\65\2\67\29\2;\2=\2?\2A\2C\2E\24G\2I\2K\25"+
		"M\26O\27Q\30S\31U\32W\33Y\34[\35]\36_\37a c!e\"g#i$k%m&\3\2\17\4\2$$^"+
		"^\n\2$$))^^ddhhppttvv\3\2\62\65\4\2NNnn\3\2\63;\3\2\629\5\2\62;CHch\6"+
		"\2&&C\\aac|\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001\7"+
		"\2&&\62;C\\aac|\5\2\13\f\16\17\"\"\u0158\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2/\3\2\2\2\2E\3\2\2\2\2K\3\2\2\2\2M\3"+
		"\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2"+
		"\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2"+
		"g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\3o\3\2\2\2\5r\3\2\2\2\7t\3"+
		"\2\2\2\tv\3\2\2\2\13y\3\2\2\2\r|\3\2\2\2\17\177\3\2\2\2\21\u0083\3\2\2"+
		"\2\23\u0086\3\2\2\2\25\u008a\3\2\2\2\27\u008c\3\2\2\2\31\u0093\3\2\2\2"+
		"\33\u009b\3\2\2\2\35\u00aa\3\2\2\2\37\u00ac\3\2\2\2!\u00b1\3\2\2\2#\u00b8"+
		"\3\2\2\2%\u00be\3\2\2\2\'\u00c4\3\2\2\2)\u00d1\3\2\2\2+\u00d3\3\2\2\2"+
		"-\u00da\3\2\2\2/\u00dc\3\2\2\2\61\u00de\3\2\2\2\63\u00e2\3\2\2\2\65\u00ee"+
		"\3\2\2\2\67\u00f0\3\2\2\29\u00fc\3\2\2\2;\u00fe\3\2\2\2=\u0102\3\2\2\2"+
		"?\u0105\3\2\2\2A\u0109\3\2\2\2C\u010b\3\2\2\2E\u010d\3\2\2\2G\u0118\3"+
		"\2\2\2I\u011e\3\2\2\2K\u0121\3\2\2\2M\u0128\3\2\2\2O\u012c\3\2\2\2Q\u012e"+
		"\3\2\2\2S\u0130\3\2\2\2U\u0132\3\2\2\2W\u0135\3\2\2\2Y\u0138\3\2\2\2["+
		"\u013a\3\2\2\2]\u013c\3\2\2\2_\u013e\3\2\2\2a\u0140\3\2\2\2c\u0142\3\2"+
		"\2\2e\u0145\3\2\2\2g\u0148\3\2\2\2i\u014a\3\2\2\2k\u014d\3\2\2\2m\u0150"+
		"\3\2\2\2op\7/\2\2pq\7@\2\2q\4\3\2\2\2rs\7*\2\2s\6\3\2\2\2tu\7+\2\2u\b"+
		"\3\2\2\2vw\7~\2\2wx\7~\2\2x\n\3\2\2\2yz\7(\2\2z{\7(\2\2{\f\3\2\2\2|}\7"+
		"k\2\2}~\7p\2\2~\16\3\2\2\2\177\u0080\7#\2\2\u0080\u0081\7k\2\2\u0081\u0082"+
		"\7p\2\2\u0082\20\3\2\2\2\u0083\u0084\7k\2\2\u0084\u0085\7u\2\2\u0085\22"+
		"\3\2\2\2\u0086\u0087\7#\2\2\u0087\u0088\7k\2\2\u0088\u0089\7u\2\2\u0089"+
		"\24\3\2\2\2\u008a\u008b\7.\2\2\u008b\26\3\2\2\2\u008c\u008d\7V\2\2\u008d"+
		"\u008e\7j\2\2\u008e\u008f\7t\2\2\u008f\u0090\7q\2\2\u0090\u0091\7y\2\2"+
		"\u0091\u0092\7u\2\2\u0092\30\3\2\2\2\u0093\u0094\7T\2\2\u0094\u0095\7"+
		"g\2\2\u0095\u0096\7v\2\2\u0096\u0097\7w\2\2\u0097\u0098\7t\2\2\u0098\u0099"+
		"\7p\2\2\u0099\u009a\7u\2\2\u009a\32\3\2\2\2\u009b\u009c\7E\2\2\u009c\u009d"+
		"\7c\2\2\u009d\u009e\7n\2\2\u009e\u009f\7n\2\2\u009f\u00a0\7u\2\2\u00a0"+
		"\34\3\2\2\2\u00a1\u00a2\7v\2\2\u00a2\u00a3\7t\2\2\u00a3\u00a4\7w\2\2\u00a4"+
		"\u00ab\7g\2\2\u00a5\u00a6\7h\2\2\u00a6\u00a7\7c\2\2\u00a7\u00a8\7n\2\2"+
		"\u00a8\u00a9\7u\2\2\u00a9\u00ab\7g\2\2\u00aa\u00a1\3\2\2\2\u00aa\u00a5"+
		"\3\2\2\2\u00ab\36\3\2\2\2\u00ac\u00ad\7p\2\2\u00ad\u00ae\7w\2\2\u00ae"+
		"\u00af\7n\2\2\u00af\u00b0\7n\2\2\u00b0 \3\2\2\2\u00b1\u00b3\7$\2\2\u00b2"+
		"\u00b4\5#\22\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2"+
		"\2\2\u00b5\u00b6\7$\2\2\u00b6\"\3\2\2\2\u00b7\u00b9\5%\23\2\u00b8\u00b7"+
		"\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb"+
		"$\3\2\2\2\u00bc\u00bf\n\2\2\2\u00bd\u00bf\5\'\24\2\u00be\u00bc\3\2\2\2"+
		"\u00be\u00bd\3\2\2\2\u00bf&\3\2\2\2\u00c0\u00c1\7^\2\2\u00c1\u00c5\t\3"+
		"\2\2\u00c2\u00c5\5)\25\2\u00c3\u00c5\5+\26\2\u00c4\u00c0\3\2\2\2\u00c4"+
		"\u00c2\3\2\2\2\u00c4\u00c3\3\2\2\2\u00c5(\3\2\2\2\u00c6\u00c7\7^\2\2\u00c7"+
		"\u00d2\5A!\2\u00c8\u00c9\7^\2\2\u00c9\u00ca\5A!\2\u00ca\u00cb\5A!\2\u00cb"+
		"\u00d2\3\2\2\2\u00cc\u00cd\7^\2\2\u00cd\u00ce\5-\27\2\u00ce\u00cf\5A!"+
		"\2\u00cf\u00d0\5A!\2\u00d0\u00d2\3\2\2\2\u00d1\u00c6\3\2\2\2\u00d1\u00c8"+
		"\3\2\2\2\u00d1\u00cc\3\2\2\2\u00d2*\3\2\2\2\u00d3\u00d4\7^\2\2\u00d4\u00d5"+
		"\7w\2\2\u00d5\u00d6\5C\"\2\u00d6\u00d7\5C\"\2\u00d7\u00d8\5C\"\2\u00d8"+
		"\u00d9\5C\"\2\u00d9,\3\2\2\2\u00da\u00db\t\4\2\2\u00db.\3\2\2\2\u00dc"+
		"\u00dd\5\61\31\2\u00dd\60\3\2\2\2\u00de\u00e0\5\65\33\2\u00df\u00e1\5"+
		"\63\32\2\u00e0\u00df\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\62\3\2\2\2\u00e2"+
		"\u00e3\t\5\2\2\u00e3\64\3\2\2\2\u00e4\u00ef\7\62\2\2\u00e5\u00ec\5;\36"+
		"\2\u00e6\u00e8\5\67\34\2\u00e7\u00e6\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8"+
		"\u00ed\3\2\2\2\u00e9\u00ea\5? \2\u00ea\u00eb\5\67\34\2\u00eb\u00ed\3\2"+
		"\2\2\u00ec\u00e7\3\2\2\2\u00ec\u00e9\3\2\2\2\u00ed\u00ef\3\2\2\2\u00ee"+
		"\u00e4\3\2\2\2\u00ee\u00e5\3\2\2\2\u00ef\66\3\2\2\2\u00f0\u00f8\59\35"+
		"\2\u00f1\u00f3\5=\37\2\u00f2\u00f1\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f2"+
		"\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f7\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7"+
		"\u00f9\59\35\2\u00f8\u00f4\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f98\3\2\2\2"+
		"\u00fa\u00fd\7\62\2\2\u00fb\u00fd\5;\36\2\u00fc\u00fa\3\2\2\2\u00fc\u00fb"+
		"\3\2\2\2\u00fd:\3\2\2\2\u00fe\u00ff\t\6\2\2\u00ff<\3\2\2\2\u0100\u0103"+
		"\59\35\2\u0101\u0103\7a\2\2\u0102\u0100\3\2\2\2\u0102\u0101\3\2\2\2\u0103"+
		">\3\2\2\2\u0104\u0106\7a\2\2\u0105\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107"+
		"\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108@\3\2\2\2\u0109\u010a\t\7\2\2"+
		"\u010aB\3\2\2\2\u010b\u010c\t\b\2\2\u010cD\3\2\2\2\u010d\u0111\5G$\2\u010e"+
		"\u0110\5I%\2\u010f\u010e\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f\3\2\2"+
		"\2\u0111\u0112\3\2\2\2\u0112F\3\2\2\2\u0113\u0111\3\2\2\2\u0114\u0119"+
		"\t\t\2\2\u0115\u0119\n\n\2\2\u0116\u0117\t\13\2\2\u0117\u0119\t\f\2\2"+
		"\u0118\u0114\3\2\2\2\u0118\u0115\3\2\2\2\u0118\u0116\3\2\2\2\u0119H\3"+
		"\2\2\2\u011a\u011f\t\r\2\2\u011b\u011f\n\n\2\2\u011c\u011d\t\13\2\2\u011d"+
		"\u011f\t\f\2\2\u011e\u011a\3\2\2\2\u011e\u011b\3\2\2\2\u011e\u011c\3\2"+
		"\2\2\u011fJ\3\2\2\2\u0120\u0122\t\16\2\2\u0121\u0120\3\2\2\2\u0122\u0123"+
		"\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"\u0126\b&\2\2\u0126L\3\2\2\2\u0127\u0129\7\17\2\2\u0128\u0127\3\2\2\2"+
		"\u0128\u0129\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012b\7\f\2\2\u012bN\3"+
		"\2\2\2\u012c\u012d\7=\2\2\u012dP\3\2\2\2\u012e\u012f\7>\2\2\u012fR\3\2"+
		"\2\2\u0130\u0131\7@\2\2\u0131T\3\2\2\2\u0132\u0133\7>\2\2\u0133\u0134"+
		"\7?\2\2\u0134V\3\2\2\2\u0135\u0136\7@\2\2\u0136\u0137\7?\2\2\u0137X\3"+
		"\2\2\2\u0138\u0139\7-\2\2\u0139Z\3\2\2\2\u013a\u013b\7/\2\2\u013b\\\3"+
		"\2\2\2\u013c\u013d\7,\2\2\u013d^\3\2\2\2\u013e\u013f\7\61\2\2\u013f`\3"+
		"\2\2\2\u0140\u0141\7\'\2\2\u0141b\3\2\2\2\u0142\u0143\7-\2\2\u0143\u0144"+
		"\7-\2\2\u0144d\3\2\2\2\u0145\u0146\7/\2\2\u0146\u0147\7/\2\2\u0147f\3"+
		"\2\2\2\u0148\u0149\7#\2\2\u0149h\3\2\2\2\u014a\u014b\7#\2\2\u014b\u014c"+
		"\7#\2\2\u014cj\3\2\2\2\u014d\u014e\7?\2\2\u014e\u014f\7?\2\2\u014fl\3"+
		"\2\2\2\u0150\u0151\7#\2\2\u0151\u0152\7?\2\2\u0152n\3\2\2\2\27\2\u00aa"+
		"\u00b3\u00ba\u00be\u00c4\u00d1\u00e0\u00e7\u00ec\u00ee\u00f4\u00f8\u00fc"+
		"\u0102\u0107\u0111\u0118\u011e\u0123\u0128\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}