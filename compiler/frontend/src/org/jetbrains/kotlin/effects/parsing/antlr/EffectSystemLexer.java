// Generated from /home/dsavvinov/Repos/kotlin-fork/kotlin/compiler/frontend/src/org/jetbrains/kotlin/effects/parsing/antlr/EffectSystem.g4 by ANTLR 4.7
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
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, BooleanLiteral=16, 
		NullLiteral=17, UnitLiteral=18, StringLiteral=19, IntegerLiteral=20, SimpleName=21, 
		WS=22, EOL=23, SEMI=24, LT=25, GT=26, LEQ=27, GEQ=28, PLUS=29, MINUS=30, 
		MUL=31, DIV=32, PERC=33, PLUSPLUS=34, MINUSMINUS=35, NOT=36, EXCLEXCL=37, 
		EQEQ=38, EXCLEQ=39;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "BooleanLiteral", 
		"NullLiteral", "UnitLiteral", "StringLiteral", "StringCharacters", "StringCharacter", 
		"EscapeSequence", "OctalEscape", "UnicodeEscape", "ZeroToThree", "IntegerLiteral", 
		"DecimalIntegerLiteral", "IntegerTypeSuffix", "DecimalNumeral", "Digits", 
		"Digit", "NonZeroDigit", "DigitOrUnderscore", "Underscores", "OctalDigit", 
		"HexDigit", "SimpleName", "JavaLetter", "JavaLetterOrDigit", "WS", "EOL", 
		"SEMI", "LT", "GT", "LEQ", "GEQ", "PLUS", "MINUS", "MUL", "DIV", "PERC", 
		"PLUSPLUS", "MINUSMINUS", "NOT", "EXCLEXCL", "EQEQ", "EXCLEQ"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'->'", "'('", "')'", "'||'", "'&&'", "'at'", "'in'", "'!in'", "'is'", 
		"'!is'", "','", "'Throws'", "'Returns'", "'Calls'", "'Hints'", null, "'null'", 
		"'unit'", null, null, null, null, null, "';'", "'<'", "'>'", "'<='", "'>='", 
		"'+'", "'-'", "'*'", "'/'", "'%'", "'++'", "'--'", "'!'", "'!!'", "'=='", 
		"'!='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "BooleanLiteral", "NullLiteral", "UnitLiteral", 
		"StringLiteral", "IntegerLiteral", "SimpleName", "WS", "EOL", "SEMI", 
		"LT", "GT", "LEQ", "GEQ", "PLUS", "MINUS", "MUL", "DIV", "PERC", "PLUSPLUS", 
		"MINUSMINUS", "NOT", "EXCLEXCL", "EQEQ", "EXCLEQ"
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
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2)\u0167\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\3\2\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\5\21\u00ba\n\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\5\24\u00c8\n\24\3\24\3\24\3\25\6\25\u00cd\n\25\r\25\16"+
		"\25\u00ce\3\26\3\26\5\26\u00d3\n\26\3\27\3\27\3\27\3\27\5\27\u00d9\n\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00e6\n\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\5\34"+
		"\u00f5\n\34\3\35\3\35\3\36\3\36\3\36\5\36\u00fc\n\36\3\36\3\36\3\36\5"+
		"\36\u0101\n\36\5\36\u0103\n\36\3\37\3\37\7\37\u0107\n\37\f\37\16\37\u010a"+
		"\13\37\3\37\5\37\u010d\n\37\3 \3 \5 \u0111\n \3!\3!\3\"\3\"\5\"\u0117"+
		"\n\"\3#\6#\u011a\n#\r#\16#\u011b\3$\3$\3%\3%\3&\3&\7&\u0124\n&\f&\16&"+
		"\u0127\13&\3\'\3\'\3\'\3\'\5\'\u012d\n\'\3(\3(\3(\3(\5(\u0133\n(\3)\6"+
		")\u0136\n)\r)\16)\u0137\3)\3)\3*\5*\u013d\n*\3*\3*\3+\3+\3,\3,\3-\3-\3"+
		".\3.\3.\3/\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65"+
		"\3\65\3\65\3\66\3\66\3\66\3\67\3\67\38\38\38\39\39\39\3:\3:\3:\2\2;\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\2+\2-\2/\2\61\2\63\2\65\26\67\29\2;\2=\2?\2A\2C"+
		"\2E\2G\2I\2K\27M\2O\2Q\30S\31U\32W\33Y\34[\35]\36_\37a c!e\"g#i$k%m&o"+
		"\'q(s)\3\2\17\4\2$$^^\n\2$$))^^ddhhppttvv\3\2\62\65\4\2NNnn\3\2\63;\3"+
		"\2\629\5\2\62;CHch\6\2&&C\\aac|\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01"+
		"\3\2\udc02\ue001\7\2&&\62;C\\aac|\5\2\13\f\16\17\"\"\2\u016c\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2\65\3\2\2\2\2K\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2"+
		"\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2"+
		"\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o"+
		"\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\3u\3\2\2\2\5x\3\2\2\2\7z\3\2\2\2\t|\3\2"+
		"\2\2\13\177\3\2\2\2\r\u0082\3\2\2\2\17\u0085\3\2\2\2\21\u0088\3\2\2\2"+
		"\23\u008c\3\2\2\2\25\u008f\3\2\2\2\27\u0093\3\2\2\2\31\u0095\3\2\2\2\33"+
		"\u009c\3\2\2\2\35\u00a4\3\2\2\2\37\u00aa\3\2\2\2!\u00b9\3\2\2\2#\u00bb"+
		"\3\2\2\2%\u00c0\3\2\2\2\'\u00c5\3\2\2\2)\u00cc\3\2\2\2+\u00d2\3\2\2\2"+
		"-\u00d8\3\2\2\2/\u00e5\3\2\2\2\61\u00e7\3\2\2\2\63\u00ee\3\2\2\2\65\u00f0"+
		"\3\2\2\2\67\u00f2\3\2\2\29\u00f6\3\2\2\2;\u0102\3\2\2\2=\u0104\3\2\2\2"+
		"?\u0110\3\2\2\2A\u0112\3\2\2\2C\u0116\3\2\2\2E\u0119\3\2\2\2G\u011d\3"+
		"\2\2\2I\u011f\3\2\2\2K\u0121\3\2\2\2M\u012c\3\2\2\2O\u0132\3\2\2\2Q\u0135"+
		"\3\2\2\2S\u013c\3\2\2\2U\u0140\3\2\2\2W\u0142\3\2\2\2Y\u0144\3\2\2\2["+
		"\u0146\3\2\2\2]\u0149\3\2\2\2_\u014c\3\2\2\2a\u014e\3\2\2\2c\u0150\3\2"+
		"\2\2e\u0152\3\2\2\2g\u0154\3\2\2\2i\u0156\3\2\2\2k\u0159\3\2\2\2m\u015c"+
		"\3\2\2\2o\u015e\3\2\2\2q\u0161\3\2\2\2s\u0164\3\2\2\2uv\7/\2\2vw\7@\2"+
		"\2w\4\3\2\2\2xy\7*\2\2y\6\3\2\2\2z{\7+\2\2{\b\3\2\2\2|}\7~\2\2}~\7~\2"+
		"\2~\n\3\2\2\2\177\u0080\7(\2\2\u0080\u0081\7(\2\2\u0081\f\3\2\2\2\u0082"+
		"\u0083\7c\2\2\u0083\u0084\7v\2\2\u0084\16\3\2\2\2\u0085\u0086\7k\2\2\u0086"+
		"\u0087\7p\2\2\u0087\20\3\2\2\2\u0088\u0089\7#\2\2\u0089\u008a\7k\2\2\u008a"+
		"\u008b\7p\2\2\u008b\22\3\2\2\2\u008c\u008d\7k\2\2\u008d\u008e\7u\2\2\u008e"+
		"\24\3\2\2\2\u008f\u0090\7#\2\2\u0090\u0091\7k\2\2\u0091\u0092\7u\2\2\u0092"+
		"\26\3\2\2\2\u0093\u0094\7.\2\2\u0094\30\3\2\2\2\u0095\u0096\7V\2\2\u0096"+
		"\u0097\7j\2\2\u0097\u0098\7t\2\2\u0098\u0099\7q\2\2\u0099\u009a\7y\2\2"+
		"\u009a\u009b\7u\2\2\u009b\32\3\2\2\2\u009c\u009d\7T\2\2\u009d\u009e\7"+
		"g\2\2\u009e\u009f\7v\2\2\u009f\u00a0\7w\2\2\u00a0\u00a1\7t\2\2\u00a1\u00a2"+
		"\7p\2\2\u00a2\u00a3\7u\2\2\u00a3\34\3\2\2\2\u00a4\u00a5\7E\2\2\u00a5\u00a6"+
		"\7c\2\2\u00a6\u00a7\7n\2\2\u00a7\u00a8\7n\2\2\u00a8\u00a9\7u\2\2\u00a9"+
		"\36\3\2\2\2\u00aa\u00ab\7J\2\2\u00ab\u00ac\7k\2\2\u00ac\u00ad\7p\2\2\u00ad"+
		"\u00ae\7v\2\2\u00ae\u00af\7u\2\2\u00af \3\2\2\2\u00b0\u00b1\7v\2\2\u00b1"+
		"\u00b2\7t\2\2\u00b2\u00b3\7w\2\2\u00b3\u00ba\7g\2\2\u00b4\u00b5\7h\2\2"+
		"\u00b5\u00b6\7c\2\2\u00b6\u00b7\7n\2\2\u00b7\u00b8\7u\2\2\u00b8\u00ba"+
		"\7g\2\2\u00b9\u00b0\3\2\2\2\u00b9\u00b4\3\2\2\2\u00ba\"\3\2\2\2\u00bb"+
		"\u00bc\7p\2\2\u00bc\u00bd\7w\2\2\u00bd\u00be\7n\2\2\u00be\u00bf\7n\2\2"+
		"\u00bf$\3\2\2\2\u00c0\u00c1\7w\2\2\u00c1\u00c2\7p\2\2\u00c2\u00c3\7k\2"+
		"\2\u00c3\u00c4\7v\2\2\u00c4&\3\2\2\2\u00c5\u00c7\7$\2\2\u00c6\u00c8\5"+
		")\25\2\u00c7\u00c6\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9"+
		"\u00ca\7$\2\2\u00ca(\3\2\2\2\u00cb\u00cd\5+\26\2\u00cc\u00cb\3\2\2\2\u00cd"+
		"\u00ce\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf*\3\2\2\2"+
		"\u00d0\u00d3\n\2\2\2\u00d1\u00d3\5-\27\2\u00d2\u00d0\3\2\2\2\u00d2\u00d1"+
		"\3\2\2\2\u00d3,\3\2\2\2\u00d4\u00d5\7^\2\2\u00d5\u00d9\t\3\2\2\u00d6\u00d9"+
		"\5/\30\2\u00d7\u00d9\5\61\31\2\u00d8\u00d4\3\2\2\2\u00d8\u00d6\3\2\2\2"+
		"\u00d8\u00d7\3\2\2\2\u00d9.\3\2\2\2\u00da\u00db\7^\2\2\u00db\u00e6\5G"+
		"$\2\u00dc\u00dd\7^\2\2\u00dd\u00de\5G$\2\u00de\u00df\5G$\2\u00df\u00e6"+
		"\3\2\2\2\u00e0\u00e1\7^\2\2\u00e1\u00e2\5\63\32\2\u00e2\u00e3\5G$\2\u00e3"+
		"\u00e4\5G$\2\u00e4\u00e6\3\2\2\2\u00e5\u00da\3\2\2\2\u00e5\u00dc\3\2\2"+
		"\2\u00e5\u00e0\3\2\2\2\u00e6\60\3\2\2\2\u00e7\u00e8\7^\2\2\u00e8\u00e9"+
		"\7w\2\2\u00e9\u00ea\5I%\2\u00ea\u00eb\5I%\2\u00eb\u00ec\5I%\2\u00ec\u00ed"+
		"\5I%\2\u00ed\62\3\2\2\2\u00ee\u00ef\t\4\2\2\u00ef\64\3\2\2\2\u00f0\u00f1"+
		"\5\67\34\2\u00f1\66\3\2\2\2\u00f2\u00f4\5;\36\2\u00f3\u00f5\59\35\2\u00f4"+
		"\u00f3\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f58\3\2\2\2\u00f6\u00f7\t\5\2\2"+
		"\u00f7:\3\2\2\2\u00f8\u0103\7\62\2\2\u00f9\u0100\5A!\2\u00fa\u00fc\5="+
		"\37\2\u00fb\u00fa\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u0101\3\2\2\2\u00fd"+
		"\u00fe\5E#\2\u00fe\u00ff\5=\37\2\u00ff\u0101\3\2\2\2\u0100\u00fb\3\2\2"+
		"\2\u0100\u00fd\3\2\2\2\u0101\u0103\3\2\2\2\u0102\u00f8\3\2\2\2\u0102\u00f9"+
		"\3\2\2\2\u0103<\3\2\2\2\u0104\u010c\5? \2\u0105\u0107\5C\"\2\u0106\u0105"+
		"\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109"+
		"\u010b\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u010d\5? \2\u010c\u0108\3\2\2"+
		"\2\u010c\u010d\3\2\2\2\u010d>\3\2\2\2\u010e\u0111\7\62\2\2\u010f\u0111"+
		"\5A!\2\u0110\u010e\3\2\2\2\u0110\u010f\3\2\2\2\u0111@\3\2\2\2\u0112\u0113"+
		"\t\6\2\2\u0113B\3\2\2\2\u0114\u0117\5? \2\u0115\u0117\7a\2\2\u0116\u0114"+
		"\3\2\2\2\u0116\u0115\3\2\2\2\u0117D\3\2\2\2\u0118\u011a\7a\2\2\u0119\u0118"+
		"\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c"+
		"F\3\2\2\2\u011d\u011e\t\7\2\2\u011eH\3\2\2\2\u011f\u0120\t\b\2\2\u0120"+
		"J\3\2\2\2\u0121\u0125\5M\'\2\u0122\u0124\5O(\2\u0123\u0122\3\2\2\2\u0124"+
		"\u0127\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126L\3\2\2\2"+
		"\u0127\u0125\3\2\2\2\u0128\u012d\t\t\2\2\u0129\u012d\n\n\2\2\u012a\u012b"+
		"\t\13\2\2\u012b\u012d\t\f\2\2\u012c\u0128\3\2\2\2\u012c\u0129\3\2\2\2"+
		"\u012c\u012a\3\2\2\2\u012dN\3\2\2\2\u012e\u0133\t\r\2\2\u012f\u0133\n"+
		"\n\2\2\u0130\u0131\t\13\2\2\u0131\u0133\t\f\2\2\u0132\u012e\3\2\2\2\u0132"+
		"\u012f\3\2\2\2\u0132\u0130\3\2\2\2\u0133P\3\2\2\2\u0134\u0136\t\16\2\2"+
		"\u0135\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0135\3\2\2\2\u0137\u0138"+
		"\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013a\b)\2\2\u013aR\3\2\2\2\u013b\u013d"+
		"\7\17\2\2\u013c\u013b\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\3\2\2\2"+
		"\u013e\u013f\7\f\2\2\u013fT\3\2\2\2\u0140\u0141\7=\2\2\u0141V\3\2\2\2"+
		"\u0142\u0143\7>\2\2\u0143X\3\2\2\2\u0144\u0145\7@\2\2\u0145Z\3\2\2\2\u0146"+
		"\u0147\7>\2\2\u0147\u0148\7?\2\2\u0148\\\3\2\2\2\u0149\u014a\7@\2\2\u014a"+
		"\u014b\7?\2\2\u014b^\3\2\2\2\u014c\u014d\7-\2\2\u014d`\3\2\2\2\u014e\u014f"+
		"\7/\2\2\u014fb\3\2\2\2\u0150\u0151\7,\2\2\u0151d\3\2\2\2\u0152\u0153\7"+
		"\61\2\2\u0153f\3\2\2\2\u0154\u0155\7\'\2\2\u0155h\3\2\2\2\u0156\u0157"+
		"\7-\2\2\u0157\u0158\7-\2\2\u0158j\3\2\2\2\u0159\u015a\7/\2\2\u015a\u015b"+
		"\7/\2\2\u015bl\3\2\2\2\u015c\u015d\7#\2\2\u015dn\3\2\2\2\u015e\u015f\7"+
		"#\2\2\u015f\u0160\7#\2\2\u0160p\3\2\2\2\u0161\u0162\7?\2\2\u0162\u0163"+
		"\7?\2\2\u0163r\3\2\2\2\u0164\u0165\7#\2\2\u0165\u0166\7?\2\2\u0166t\3"+
		"\2\2\2\27\2\u00b9\u00c7\u00ce\u00d2\u00d8\u00e5\u00f4\u00fb\u0100\u0102"+
		"\u0108\u010c\u0110\u0116\u011b\u0125\u012c\u0132\u0137\u013c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}