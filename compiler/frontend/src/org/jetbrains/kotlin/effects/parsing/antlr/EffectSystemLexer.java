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
		UnitLiteral=16, StringLiteral=17, IntegerLiteral=18, SimpleName=19, WS=20, 
		EOL=21, SEMI=22, LT=23, GT=24, LEQ=25, GEQ=26, PLUS=27, MINUS=28, MUL=29, 
		DIV=30, PERC=31, PLUSPLUS=32, MINUSMINUS=33, NOT=34, EXCLEXCL=35, EQEQ=36, 
		EXCLEQ=37;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "BooleanLiteral", "NullLiteral", "UnitLiteral", 
		"StringLiteral", "StringCharacters", "StringCharacter", "EscapeSequence", 
		"OctalEscape", "UnicodeEscape", "ZeroToThree", "IntegerLiteral", "DecimalIntegerLiteral", 
		"IntegerTypeSuffix", "DecimalNumeral", "Digits", "Digit", "NonZeroDigit", 
		"DigitOrUnderscore", "Underscores", "OctalDigit", "HexDigit", "SimpleName", 
		"JavaLetter", "JavaLetterOrDigit", "WS", "EOL", "SEMI", "LT", "GT", "LEQ", 
		"GEQ", "PLUS", "MINUS", "MUL", "DIV", "PERC", "PLUSPLUS", "MINUSMINUS", 
		"NOT", "EXCLEXCL", "EQEQ", "EXCLEQ"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'->'", "'('", "')'", "'||'", "'&&'", "'in'", "'!in'", "'is'", "'!is'", 
		"','", "'Throws'", "'Returns'", "'Calls'", null, "'null'", "'unit'", null, 
		null, null, null, null, "';'", "'<'", "'>'", "'<='", "'>='", "'+'", "'-'", 
		"'*'", "'/'", "'%'", "'++'", "'--'", "'!'", "'!!'", "'=='", "'!='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "BooleanLiteral", "NullLiteral", "UnitLiteral", "StringLiteral", 
		"IntegerLiteral", "SimpleName", "WS", "EOL", "SEMI", "LT", "GT", "LEQ", 
		"GEQ", "PLUS", "MINUS", "MUL", "DIV", "PERC", "PLUSPLUS", "MINUSMINUS", 
		"NOT", "EXCLEXCL", "EQEQ", "EXCLEQ"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\'\u015a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\5\17\u00ad\n\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\5\22\u00bb\n\22\3\22\3\22\3\23\6\23\u00c0\n\23\r"+
		"\23\16\23\u00c1\3\24\3\24\5\24\u00c6\n\24\3\25\3\25\3\25\3\25\5\25\u00cc"+
		"\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00d9"+
		"\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32"+
		"\5\32\u00e8\n\32\3\33\3\33\3\34\3\34\3\34\5\34\u00ef\n\34\3\34\3\34\3"+
		"\34\5\34\u00f4\n\34\5\34\u00f6\n\34\3\35\3\35\7\35\u00fa\n\35\f\35\16"+
		"\35\u00fd\13\35\3\35\5\35\u0100\n\35\3\36\3\36\5\36\u0104\n\36\3\37\3"+
		"\37\3 \3 \5 \u010a\n \3!\6!\u010d\n!\r!\16!\u010e\3\"\3\"\3#\3#\3$\3$"+
		"\7$\u0117\n$\f$\16$\u011a\13$\3%\3%\3%\3%\5%\u0120\n%\3&\3&\3&\3&\5&\u0126"+
		"\n&\3\'\6\'\u0129\n\'\r\'\16\'\u012a\3\'\3\'\3(\5(\u0130\n(\3(\3(\3)\3"+
		")\3*\3*\3+\3+\3,\3,\3,\3-\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3"+
		"\62\3\63\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\66\3\66\3\66\3\67\3\67\3"+
		"\67\38\38\38\2\29\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\2\'\2)\2+\2-\2/\2\61\24\63\2\65\2\67\2"+
		"9\2;\2=\2?\2A\2C\2E\2G\25I\2K\2M\26O\27Q\30S\31U\32W\33Y\34[\35]\36_\37"+
		"a c!e\"g#i$k%m&o\'\3\2\17\4\2$$^^\n\2$$))^^ddhhppttvv\3\2\62\65\4\2NN"+
		"nn\3\2\63;\3\2\629\5\2\62;CHch\6\2&&C\\aac|\4\2\2\u0081\ud802\udc01\3"+
		"\2\ud802\udc01\3\2\udc02\ue001\7\2&&\62;C\\aac|\5\2\13\f\16\17\"\"\u015f"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2\61\3\2\2\2\2G\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2"+
		"\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2"+
		"a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3"+
		"\2\2\2\2o\3\2\2\2\3q\3\2\2\2\5t\3\2\2\2\7v\3\2\2\2\tx\3\2\2\2\13{\3\2"+
		"\2\2\r~\3\2\2\2\17\u0081\3\2\2\2\21\u0085\3\2\2\2\23\u0088\3\2\2\2\25"+
		"\u008c\3\2\2\2\27\u008e\3\2\2\2\31\u0095\3\2\2\2\33\u009d\3\2\2\2\35\u00ac"+
		"\3\2\2\2\37\u00ae\3\2\2\2!\u00b3\3\2\2\2#\u00b8\3\2\2\2%\u00bf\3\2\2\2"+
		"\'\u00c5\3\2\2\2)\u00cb\3\2\2\2+\u00d8\3\2\2\2-\u00da\3\2\2\2/\u00e1\3"+
		"\2\2\2\61\u00e3\3\2\2\2\63\u00e5\3\2\2\2\65\u00e9\3\2\2\2\67\u00f5\3\2"+
		"\2\29\u00f7\3\2\2\2;\u0103\3\2\2\2=\u0105\3\2\2\2?\u0109\3\2\2\2A\u010c"+
		"\3\2\2\2C\u0110\3\2\2\2E\u0112\3\2\2\2G\u0114\3\2\2\2I\u011f\3\2\2\2K"+
		"\u0125\3\2\2\2M\u0128\3\2\2\2O\u012f\3\2\2\2Q\u0133\3\2\2\2S\u0135\3\2"+
		"\2\2U\u0137\3\2\2\2W\u0139\3\2\2\2Y\u013c\3\2\2\2[\u013f\3\2\2\2]\u0141"+
		"\3\2\2\2_\u0143\3\2\2\2a\u0145\3\2\2\2c\u0147\3\2\2\2e\u0149\3\2\2\2g"+
		"\u014c\3\2\2\2i\u014f\3\2\2\2k\u0151\3\2\2\2m\u0154\3\2\2\2o\u0157\3\2"+
		"\2\2qr\7/\2\2rs\7@\2\2s\4\3\2\2\2tu\7*\2\2u\6\3\2\2\2vw\7+\2\2w\b\3\2"+
		"\2\2xy\7~\2\2yz\7~\2\2z\n\3\2\2\2{|\7(\2\2|}\7(\2\2}\f\3\2\2\2~\177\7"+
		"k\2\2\177\u0080\7p\2\2\u0080\16\3\2\2\2\u0081\u0082\7#\2\2\u0082\u0083"+
		"\7k\2\2\u0083\u0084\7p\2\2\u0084\20\3\2\2\2\u0085\u0086\7k\2\2\u0086\u0087"+
		"\7u\2\2\u0087\22\3\2\2\2\u0088\u0089\7#\2\2\u0089\u008a\7k\2\2\u008a\u008b"+
		"\7u\2\2\u008b\24\3\2\2\2\u008c\u008d\7.\2\2\u008d\26\3\2\2\2\u008e\u008f"+
		"\7V\2\2\u008f\u0090\7j\2\2\u0090\u0091\7t\2\2\u0091\u0092\7q\2\2\u0092"+
		"\u0093\7y\2\2\u0093\u0094\7u\2\2\u0094\30\3\2\2\2\u0095\u0096\7T\2\2\u0096"+
		"\u0097\7g\2\2\u0097\u0098\7v\2\2\u0098\u0099\7w\2\2\u0099\u009a\7t\2\2"+
		"\u009a\u009b\7p\2\2\u009b\u009c\7u\2\2\u009c\32\3\2\2\2\u009d\u009e\7"+
		"E\2\2\u009e\u009f\7c\2\2\u009f\u00a0\7n\2\2\u00a0\u00a1\7n\2\2\u00a1\u00a2"+
		"\7u\2\2\u00a2\34\3\2\2\2\u00a3\u00a4\7v\2\2\u00a4\u00a5\7t\2\2\u00a5\u00a6"+
		"\7w\2\2\u00a6\u00ad\7g\2\2\u00a7\u00a8\7h\2\2\u00a8\u00a9\7c\2\2\u00a9"+
		"\u00aa\7n\2\2\u00aa\u00ab\7u\2\2\u00ab\u00ad\7g\2\2\u00ac\u00a3\3\2\2"+
		"\2\u00ac\u00a7\3\2\2\2\u00ad\36\3\2\2\2\u00ae\u00af\7p\2\2\u00af\u00b0"+
		"\7w\2\2\u00b0\u00b1\7n\2\2\u00b1\u00b2\7n\2\2\u00b2 \3\2\2\2\u00b3\u00b4"+
		"\7w\2\2\u00b4\u00b5\7p\2\2\u00b5\u00b6\7k\2\2\u00b6\u00b7\7v\2\2\u00b7"+
		"\"\3\2\2\2\u00b8\u00ba\7$\2\2\u00b9\u00bb\5%\23\2\u00ba\u00b9\3\2\2\2"+
		"\u00ba\u00bb\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\7$\2\2\u00bd$\3\2"+
		"\2\2\u00be\u00c0\5\'\24\2\u00bf\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2&\3\2\2\2\u00c3\u00c6\n\2\2\2"+
		"\u00c4\u00c6\5)\25\2\u00c5\u00c3\3\2\2\2\u00c5\u00c4\3\2\2\2\u00c6(\3"+
		"\2\2\2\u00c7\u00c8\7^\2\2\u00c8\u00cc\t\3\2\2\u00c9\u00cc\5+\26\2\u00ca"+
		"\u00cc\5-\27\2\u00cb\u00c7\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00ca\3\2"+
		"\2\2\u00cc*\3\2\2\2\u00cd\u00ce\7^\2\2\u00ce\u00d9\5C\"\2\u00cf\u00d0"+
		"\7^\2\2\u00d0\u00d1\5C\"\2\u00d1\u00d2\5C\"\2\u00d2\u00d9\3\2\2\2\u00d3"+
		"\u00d4\7^\2\2\u00d4\u00d5\5/\30\2\u00d5\u00d6\5C\"\2\u00d6\u00d7\5C\""+
		"\2\u00d7\u00d9\3\2\2\2\u00d8\u00cd\3\2\2\2\u00d8\u00cf\3\2\2\2\u00d8\u00d3"+
		"\3\2\2\2\u00d9,\3\2\2\2\u00da\u00db\7^\2\2\u00db\u00dc\7w\2\2\u00dc\u00dd"+
		"\5E#\2\u00dd\u00de\5E#\2\u00de\u00df\5E#\2\u00df\u00e0\5E#\2\u00e0.\3"+
		"\2\2\2\u00e1\u00e2\t\4\2\2\u00e2\60\3\2\2\2\u00e3\u00e4\5\63\32\2\u00e4"+
		"\62\3\2\2\2\u00e5\u00e7\5\67\34\2\u00e6\u00e8\5\65\33\2\u00e7\u00e6\3"+
		"\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\64\3\2\2\2\u00e9\u00ea\t\5\2\2\u00ea"+
		"\66\3\2\2\2\u00eb\u00f6\7\62\2\2\u00ec\u00f3\5=\37\2\u00ed\u00ef\59\35"+
		"\2\u00ee\u00ed\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f4\3\2\2\2\u00f0\u00f1"+
		"\5A!\2\u00f1\u00f2\59\35\2\u00f2\u00f4\3\2\2\2\u00f3\u00ee\3\2\2\2\u00f3"+
		"\u00f0\3\2\2\2\u00f4\u00f6\3\2\2\2\u00f5\u00eb\3\2\2\2\u00f5\u00ec\3\2"+
		"\2\2\u00f68\3\2\2\2\u00f7\u00ff\5;\36\2\u00f8\u00fa\5? \2\u00f9\u00f8"+
		"\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc"+
		"\u00fe\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u0100\5;\36\2\u00ff\u00fb\3\2"+
		"\2\2\u00ff\u0100\3\2\2\2\u0100:\3\2\2\2\u0101\u0104\7\62\2\2\u0102\u0104"+
		"\5=\37\2\u0103\u0101\3\2\2\2\u0103\u0102\3\2\2\2\u0104<\3\2\2\2\u0105"+
		"\u0106\t\6\2\2\u0106>\3\2\2\2\u0107\u010a\5;\36\2\u0108\u010a\7a\2\2\u0109"+
		"\u0107\3\2\2\2\u0109\u0108\3\2\2\2\u010a@\3\2\2\2\u010b\u010d\7a\2\2\u010c"+
		"\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2"+
		"\2\2\u010fB\3\2\2\2\u0110\u0111\t\7\2\2\u0111D\3\2\2\2\u0112\u0113\t\b"+
		"\2\2\u0113F\3\2\2\2\u0114\u0118\5I%\2\u0115\u0117\5K&\2\u0116\u0115\3"+
		"\2\2\2\u0117\u011a\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119"+
		"H\3\2\2\2\u011a\u0118\3\2\2\2\u011b\u0120\t\t\2\2\u011c\u0120\n\n\2\2"+
		"\u011d\u011e\t\13\2\2\u011e\u0120\t\f\2\2\u011f\u011b\3\2\2\2\u011f\u011c"+
		"\3\2\2\2\u011f\u011d\3\2\2\2\u0120J\3\2\2\2\u0121\u0126\t\r\2\2\u0122"+
		"\u0126\n\n\2\2\u0123\u0124\t\13\2\2\u0124\u0126\t\f\2\2\u0125\u0121\3"+
		"\2\2\2\u0125\u0122\3\2\2\2\u0125\u0123\3\2\2\2\u0126L\3\2\2\2\u0127\u0129"+
		"\t\16\2\2\u0128\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u0128\3\2\2\2"+
		"\u012a\u012b\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012d\b\'\2\2\u012dN\3"+
		"\2\2\2\u012e\u0130\7\17\2\2\u012f\u012e\3\2\2\2\u012f\u0130\3\2\2\2\u0130"+
		"\u0131\3\2\2\2\u0131\u0132\7\f\2\2\u0132P\3\2\2\2\u0133\u0134\7=\2\2\u0134"+
		"R\3\2\2\2\u0135\u0136\7>\2\2\u0136T\3\2\2\2\u0137\u0138\7@\2\2\u0138V"+
		"\3\2\2\2\u0139\u013a\7>\2\2\u013a\u013b\7?\2\2\u013bX\3\2\2\2\u013c\u013d"+
		"\7@\2\2\u013d\u013e\7?\2\2\u013eZ\3\2\2\2\u013f\u0140\7-\2\2\u0140\\\3"+
		"\2\2\2\u0141\u0142\7/\2\2\u0142^\3\2\2\2\u0143\u0144\7,\2\2\u0144`\3\2"+
		"\2\2\u0145\u0146\7\61\2\2\u0146b\3\2\2\2\u0147\u0148\7\'\2\2\u0148d\3"+
		"\2\2\2\u0149\u014a\7-\2\2\u014a\u014b\7-\2\2\u014bf\3\2\2\2\u014c\u014d"+
		"\7/\2\2\u014d\u014e\7/\2\2\u014eh\3\2\2\2\u014f\u0150\7#\2\2\u0150j\3"+
		"\2\2\2\u0151\u0152\7#\2\2\u0152\u0153\7#\2\2\u0153l\3\2\2\2\u0154\u0155"+
		"\7?\2\2\u0155\u0156\7?\2\2\u0156n\3\2\2\2\u0157\u0158\7#\2\2\u0158\u0159"+
		"\7?\2\2\u0159p\3\2\2\2\27\2\u00ac\u00ba\u00c1\u00c5\u00cb\u00d8\u00e7"+
		"\u00ee\u00f3\u00f5\u00fb\u00ff\u0103\u0109\u010e\u0118\u011f\u0125\u012a"+
		"\u012f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}