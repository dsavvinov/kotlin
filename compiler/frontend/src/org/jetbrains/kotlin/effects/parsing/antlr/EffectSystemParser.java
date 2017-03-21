// Generated from /home/dsavvinov/Repos/kotlin-fork/kotlin/compiler/frontend/src/org/jetbrains/kotlin/effects/parsing/antlr/EffectSystem.g4 by ANTLR 4.6
package org.jetbrains.kotlin.effects.parsing.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EffectSystemParser extends Parser {
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
	public static final int
		RULE_effectSchema = 0, RULE_clause = 1, RULE_expression = 2, RULE_conjunction = 3, 
		RULE_equalityComparison = 4, RULE_comparison = 5, RULE_namedInfix = 6, 
		RULE_additiveExpression = 7, RULE_multiplicativeExpression = 8, RULE_prefixUnaryExpression = 9, 
		RULE_postfixUnaryExpression = 10, RULE_atomicExpression = 11, RULE_disjunctionOperator = 12, 
		RULE_conjunctionOperator = 13, RULE_equalityOperator = 14, RULE_comparisonOperator = 15, 
		RULE_additiveOperator = 16, RULE_multiplicativeOperator = 17, RULE_prefixUnaryOperation = 18, 
		RULE_postfixUnaryOperation = 19, RULE_inOperation = 20, RULE_isOperation = 21, 
		RULE_effectsList = 22, RULE_effect = 23, RULE_throwsEffect = 24, RULE_returnsEffect = 25, 
		RULE_callsEffect = 26, RULE_type = 27, RULE_literalConstant = 28;
	public static final String[] ruleNames = {
		"effectSchema", "clause", "expression", "conjunction", "equalityComparison", 
		"comparison", "namedInfix", "additiveExpression", "multiplicativeExpression", 
		"prefixUnaryExpression", "postfixUnaryExpression", "atomicExpression", 
		"disjunctionOperator", "conjunctionOperator", "equalityOperator", "comparisonOperator", 
		"additiveOperator", "multiplicativeOperator", "prefixUnaryOperation", 
		"postfixUnaryOperation", "inOperation", "isOperation", "effectsList", 
		"effect", "throwsEffect", "returnsEffect", "callsEffect", "type", "literalConstant"
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

	@Override
	public String getGrammarFileName() { return "EffectSystem.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EffectSystemParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class EffectSchemaContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(EffectSystemParser.EOF, 0); }
		public List<ClauseContext> clause() {
			return getRuleContexts(ClauseContext.class);
		}
		public ClauseContext clause(int i) {
			return getRuleContext(ClauseContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(EffectSystemParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(EffectSystemParser.SEMI, i);
		}
		public EffectSchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_effectSchema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterEffectSchema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitEffectSchema(this);
		}
	}

	public final EffectSchemaContext effectSchema() throws RecognitionException {
		EffectSchemaContext _localctx = new EffectSchemaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_effectSchema);
		int _la;
		try {
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				match(EOF);
				}
				break;
			case T__1:
			case BooleanLiteral:
			case NullLiteral:
			case StringLiteral:
			case IntegerLiteral:
			case SimpleName:
			case PLUS:
			case MINUS:
			case PLUSPLUS:
			case MINUSMINUS:
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				clause();
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMI) {
					{
					{
					setState(60);
					match(SEMI);
					setState(61);
					clause();
					}
					}
					setState(66);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class ClauseContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public EffectsListContext effectsList() {
			return getRuleContext(EffectsListContext.class,0);
		}
		public ClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitClause(this);
		}
	}

	public final ClauseContext clause() throws RecognitionException {
		ClauseContext _localctx = new ClauseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			expression();
			setState(70);
			match(T__0);
			setState(71);
			effectsList();
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

	public static class ExpressionContext extends ParserRuleContext {
		public List<ConjunctionContext> conjunction() {
			return getRuleContexts(ConjunctionContext.class);
		}
		public ConjunctionContext conjunction(int i) {
			return getRuleContext(ConjunctionContext.class,i);
		}
		public List<DisjunctionOperatorContext> disjunctionOperator() {
			return getRuleContexts(DisjunctionOperatorContext.class);
		}
		public DisjunctionOperatorContext disjunctionOperator(int i) {
			return getRuleContext(DisjunctionOperatorContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			conjunction();
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(74);
				disjunctionOperator();
				setState(75);
				conjunction();
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ConjunctionContext extends ParserRuleContext {
		public List<EqualityComparisonContext> equalityComparison() {
			return getRuleContexts(EqualityComparisonContext.class);
		}
		public EqualityComparisonContext equalityComparison(int i) {
			return getRuleContext(EqualityComparisonContext.class,i);
		}
		public List<ConjunctionOperatorContext> conjunctionOperator() {
			return getRuleContexts(ConjunctionOperatorContext.class);
		}
		public ConjunctionOperatorContext conjunctionOperator(int i) {
			return getRuleContext(ConjunctionOperatorContext.class,i);
		}
		public ConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitConjunction(this);
		}
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_conjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			equalityComparison();
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(83);
				conjunctionOperator();
				setState(84);
				equalityComparison();
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class EqualityComparisonContext extends ParserRuleContext {
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public List<EqualityOperatorContext> equalityOperator() {
			return getRuleContexts(EqualityOperatorContext.class);
		}
		public EqualityOperatorContext equalityOperator(int i) {
			return getRuleContext(EqualityOperatorContext.class,i);
		}
		public EqualityComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityComparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterEqualityComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitEqualityComparison(this);
		}
	}

	public final EqualityComparisonContext equalityComparison() throws RecognitionException {
		EqualityComparisonContext _localctx = new EqualityComparisonContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_equalityComparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			comparison();
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EQEQ || _la==EXCLEQ) {
				{
				{
				setState(92);
				equalityOperator();
				setState(93);
				comparison();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ComparisonContext extends ParserRuleContext {
		public List<NamedInfixContext> namedInfix() {
			return getRuleContexts(NamedInfixContext.class);
		}
		public NamedInfixContext namedInfix(int i) {
			return getRuleContext(NamedInfixContext.class,i);
		}
		public List<ComparisonOperatorContext> comparisonOperator() {
			return getRuleContexts(ComparisonOperatorContext.class);
		}
		public ComparisonOperatorContext comparisonOperator(int i) {
			return getRuleContext(ComparisonOperatorContext.class,i);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			namedInfix();
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << GT) | (1L << LEQ) | (1L << GEQ))) != 0)) {
				{
				{
				setState(101);
				comparisonOperator();
				setState(102);
				namedInfix();
				}
				}
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class NamedInfixContext extends ParserRuleContext {
		public List<AdditiveExpressionContext> additiveExpression() {
			return getRuleContexts(AdditiveExpressionContext.class);
		}
		public AdditiveExpressionContext additiveExpression(int i) {
			return getRuleContext(AdditiveExpressionContext.class,i);
		}
		public List<InOperationContext> inOperation() {
			return getRuleContexts(InOperationContext.class);
		}
		public InOperationContext inOperation(int i) {
			return getRuleContext(InOperationContext.class,i);
		}
		public IsOperationContext isOperation() {
			return getRuleContext(IsOperationContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public NamedInfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedInfix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterNamedInfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitNamedInfix(this);
		}
	}

	public final NamedInfixContext namedInfix() throws RecognitionException {
		NamedInfixContext _localctx = new NamedInfixContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_namedInfix);
		int _la;
		try {
			setState(124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				additiveExpression();
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__5 || _la==T__6) {
					{
					{
					setState(110);
					inOperation();
					setState(111);
					additiveExpression();
					}
					}
					setState(117);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				additiveExpression();
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__7 || _la==T__8) {
					{
					setState(119);
					isOperation();
					setState(120);
					type();
					}
				}

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

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}
		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class,i);
		}
		public List<AdditiveOperatorContext> additiveOperator() {
			return getRuleContexts(AdditiveOperatorContext.class);
		}
		public AdditiveOperatorContext additiveOperator(int i) {
			return getRuleContext(AdditiveOperatorContext.class,i);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitAdditiveExpression(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_additiveExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			multiplicativeExpression();
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(127);
				additiveOperator();
				setState(128);
				multiplicativeExpression();
				}
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public List<PrefixUnaryExpressionContext> prefixUnaryExpression() {
			return getRuleContexts(PrefixUnaryExpressionContext.class);
		}
		public PrefixUnaryExpressionContext prefixUnaryExpression(int i) {
			return getRuleContext(PrefixUnaryExpressionContext.class,i);
		}
		public List<MultiplicativeOperatorContext> multiplicativeOperator() {
			return getRuleContexts(MultiplicativeOperatorContext.class);
		}
		public MultiplicativeOperatorContext multiplicativeOperator(int i) {
			return getRuleContext(MultiplicativeOperatorContext.class,i);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitMultiplicativeExpression(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_multiplicativeExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			prefixUnaryExpression();
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << PERC))) != 0)) {
				{
				{
				setState(136);
				multiplicativeOperator();
				setState(137);
				prefixUnaryExpression();
				}
				}
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class PrefixUnaryExpressionContext extends ParserRuleContext {
		public PostfixUnaryExpressionContext postfixUnaryExpression() {
			return getRuleContext(PostfixUnaryExpressionContext.class,0);
		}
		public List<PrefixUnaryOperationContext> prefixUnaryOperation() {
			return getRuleContexts(PrefixUnaryOperationContext.class);
		}
		public PrefixUnaryOperationContext prefixUnaryOperation(int i) {
			return getRuleContext(PrefixUnaryOperationContext.class,i);
		}
		public PrefixUnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixUnaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterPrefixUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitPrefixUnaryExpression(this);
		}
	}

	public final PrefixUnaryExpressionContext prefixUnaryExpression() throws RecognitionException {
		PrefixUnaryExpressionContext _localctx = new PrefixUnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_prefixUnaryExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << PLUSPLUS) | (1L << MINUSMINUS) | (1L << NOT))) != 0)) {
				{
				{
				setState(144);
				prefixUnaryOperation();
				}
				}
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(150);
			postfixUnaryExpression();
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

	public static class PostfixUnaryExpressionContext extends ParserRuleContext {
		public AtomicExpressionContext atomicExpression() {
			return getRuleContext(AtomicExpressionContext.class,0);
		}
		public List<PostfixUnaryOperationContext> postfixUnaryOperation() {
			return getRuleContexts(PostfixUnaryOperationContext.class);
		}
		public PostfixUnaryOperationContext postfixUnaryOperation(int i) {
			return getRuleContext(PostfixUnaryOperationContext.class,i);
		}
		public PostfixUnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixUnaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterPostfixUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitPostfixUnaryExpression(this);
		}
	}

	public final PostfixUnaryExpressionContext postfixUnaryExpression() throws RecognitionException {
		PostfixUnaryExpressionContext _localctx = new PostfixUnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_postfixUnaryExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			atomicExpression();
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUSPLUS) | (1L << MINUSMINUS) | (1L << EXCLEXCL))) != 0)) {
				{
				{
				setState(153);
				postfixUnaryOperation();
				}
				}
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class AtomicExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LiteralConstantContext literalConstant() {
			return getRuleContext(LiteralConstantContext.class,0);
		}
		public TerminalNode SimpleName() { return getToken(EffectSystemParser.SimpleName, 0); }
		public AtomicExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomicExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterAtomicExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitAtomicExpression(this);
		}
	}

	public final AtomicExpressionContext atomicExpression() throws RecognitionException {
		AtomicExpressionContext _localctx = new AtomicExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_atomicExpression);
		try {
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				match(T__1);
				setState(160);
				expression();
				setState(161);
				match(T__2);
				}
				break;
			case BooleanLiteral:
			case NullLiteral:
			case StringLiteral:
			case IntegerLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				literalConstant();
				}
				break;
			case SimpleName:
				enterOuterAlt(_localctx, 3);
				{
				setState(164);
				match(SimpleName);
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

	public static class DisjunctionOperatorContext extends ParserRuleContext {
		public DisjunctionOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjunctionOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterDisjunctionOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitDisjunctionOperator(this);
		}
	}

	public final DisjunctionOperatorContext disjunctionOperator() throws RecognitionException {
		DisjunctionOperatorContext _localctx = new DisjunctionOperatorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_disjunctionOperator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(T__3);
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

	public static class ConjunctionOperatorContext extends ParserRuleContext {
		public ConjunctionOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunctionOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterConjunctionOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitConjunctionOperator(this);
		}
	}

	public final ConjunctionOperatorContext conjunctionOperator() throws RecognitionException {
		ConjunctionOperatorContext _localctx = new ConjunctionOperatorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_conjunctionOperator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(T__4);
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

	public static class EqualityOperatorContext extends ParserRuleContext {
		public TerminalNode EQEQ() { return getToken(EffectSystemParser.EQEQ, 0); }
		public TerminalNode EXCLEQ() { return getToken(EffectSystemParser.EXCLEQ, 0); }
		public EqualityOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterEqualityOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitEqualityOperator(this);
		}
	}

	public final EqualityOperatorContext equalityOperator() throws RecognitionException {
		EqualityOperatorContext _localctx = new EqualityOperatorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_equalityOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			_la = _input.LA(1);
			if ( !(_la==EQEQ || _la==EXCLEQ) ) {
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

	public static class ComparisonOperatorContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(EffectSystemParser.LT, 0); }
		public TerminalNode GT() { return getToken(EffectSystemParser.GT, 0); }
		public TerminalNode LEQ() { return getToken(EffectSystemParser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(EffectSystemParser.GEQ, 0); }
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitComparisonOperator(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << GT) | (1L << LEQ) | (1L << GEQ))) != 0)) ) {
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

	public static class AdditiveOperatorContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(EffectSystemParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(EffectSystemParser.MINUS, 0); }
		public AdditiveOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterAdditiveOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitAdditiveOperator(this);
		}
	}

	public final AdditiveOperatorContext additiveOperator() throws RecognitionException {
		AdditiveOperatorContext _localctx = new AdditiveOperatorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_additiveOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
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

	public static class MultiplicativeOperatorContext extends ParserRuleContext {
		public TerminalNode MUL() { return getToken(EffectSystemParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(EffectSystemParser.DIV, 0); }
		public TerminalNode PERC() { return getToken(EffectSystemParser.PERC, 0); }
		public MultiplicativeOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterMultiplicativeOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitMultiplicativeOperator(this);
		}
	}

	public final MultiplicativeOperatorContext multiplicativeOperator() throws RecognitionException {
		MultiplicativeOperatorContext _localctx = new MultiplicativeOperatorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_multiplicativeOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << PERC))) != 0)) ) {
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

	public static class PrefixUnaryOperationContext extends ParserRuleContext {
		public TerminalNode MINUS() { return getToken(EffectSystemParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(EffectSystemParser.PLUS, 0); }
		public TerminalNode MINUSMINUS() { return getToken(EffectSystemParser.MINUSMINUS, 0); }
		public TerminalNode PLUSPLUS() { return getToken(EffectSystemParser.PLUSPLUS, 0); }
		public TerminalNode NOT() { return getToken(EffectSystemParser.NOT, 0); }
		public PrefixUnaryOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixUnaryOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterPrefixUnaryOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitPrefixUnaryOperation(this);
		}
	}

	public final PrefixUnaryOperationContext prefixUnaryOperation() throws RecognitionException {
		PrefixUnaryOperationContext _localctx = new PrefixUnaryOperationContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_prefixUnaryOperation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << PLUSPLUS) | (1L << MINUSMINUS) | (1L << NOT))) != 0)) ) {
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

	public static class PostfixUnaryOperationContext extends ParserRuleContext {
		public TerminalNode PLUSPLUS() { return getToken(EffectSystemParser.PLUSPLUS, 0); }
		public TerminalNode MINUSMINUS() { return getToken(EffectSystemParser.MINUSMINUS, 0); }
		public TerminalNode EXCLEXCL() { return getToken(EffectSystemParser.EXCLEXCL, 0); }
		public PostfixUnaryOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixUnaryOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterPostfixUnaryOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitPostfixUnaryOperation(this);
		}
	}

	public final PostfixUnaryOperationContext postfixUnaryOperation() throws RecognitionException {
		PostfixUnaryOperationContext _localctx = new PostfixUnaryOperationContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_postfixUnaryOperation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUSPLUS) | (1L << MINUSMINUS) | (1L << EXCLEXCL))) != 0)) ) {
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

	public static class InOperationContext extends ParserRuleContext {
		public InOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterInOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitInOperation(this);
		}
	}

	public final InOperationContext inOperation() throws RecognitionException {
		InOperationContext _localctx = new InOperationContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_inOperation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			_la = _input.LA(1);
			if ( !(_la==T__5 || _la==T__6) ) {
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

	public static class IsOperationContext extends ParserRuleContext {
		public IsOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterIsOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitIsOperation(this);
		}
	}

	public final IsOperationContext isOperation() throws RecognitionException {
		IsOperationContext _localctx = new IsOperationContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_isOperation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_la = _input.LA(1);
			if ( !(_la==T__7 || _la==T__8) ) {
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

	public static class EffectsListContext extends ParserRuleContext {
		public List<EffectContext> effect() {
			return getRuleContexts(EffectContext.class);
		}
		public EffectContext effect(int i) {
			return getRuleContext(EffectContext.class,i);
		}
		public EffectsListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_effectsList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterEffectsList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitEffectsList(this);
		}
	}

	public final EffectsListContext effectsList() throws RecognitionException {
		EffectsListContext _localctx = new EffectsListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_effectsList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			effect();
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(188);
				match(T__9);
				setState(189);
				effect();
				}
				}
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class EffectContext extends ParserRuleContext {
		public ThrowsEffectContext throwsEffect() {
			return getRuleContext(ThrowsEffectContext.class,0);
		}
		public ReturnsEffectContext returnsEffect() {
			return getRuleContext(ReturnsEffectContext.class,0);
		}
		public CallsEffectContext callsEffect() {
			return getRuleContext(CallsEffectContext.class,0);
		}
		public EffectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_effect; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterEffect(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitEffect(this);
		}
	}

	public final EffectContext effect() throws RecognitionException {
		EffectContext _localctx = new EffectContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_effect);
		try {
			setState(198);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				throwsEffect();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				returnsEffect();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 3);
				{
				setState(197);
				callsEffect();
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

	public static class ThrowsEffectContext extends ParserRuleContext {
		public TerminalNode SimpleName() { return getToken(EffectSystemParser.SimpleName, 0); }
		public ThrowsEffectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwsEffect; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterThrowsEffect(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitThrowsEffect(this);
		}
	}

	public final ThrowsEffectContext throwsEffect() throws RecognitionException {
		ThrowsEffectContext _localctx = new ThrowsEffectContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_throwsEffect);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(T__10);
			setState(201);
			match(SimpleName);
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

	public static class ReturnsEffectContext extends ParserRuleContext {
		public LiteralConstantContext literalConstant() {
			return getRuleContext(LiteralConstantContext.class,0);
		}
		public TerminalNode SimpleName() { return getToken(EffectSystemParser.SimpleName, 0); }
		public ReturnsEffectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnsEffect; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterReturnsEffect(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitReturnsEffect(this);
		}
	}

	public final ReturnsEffectContext returnsEffect() throws RecognitionException {
		ReturnsEffectContext _localctx = new ReturnsEffectContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_returnsEffect);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(T__11);
			setState(206);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BooleanLiteral:
			case NullLiteral:
			case StringLiteral:
			case IntegerLiteral:
				{
				setState(204);
				literalConstant();
				}
				break;
			case SimpleName:
				{
				setState(205);
				match(SimpleName);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class CallsEffectContext extends ParserRuleContext {
		public TerminalNode SimpleName() { return getToken(EffectSystemParser.SimpleName, 0); }
		public TerminalNode IntegerLiteral() { return getToken(EffectSystemParser.IntegerLiteral, 0); }
		public CallsEffectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callsEffect; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterCallsEffect(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitCallsEffect(this);
		}
	}

	public final CallsEffectContext callsEffect() throws RecognitionException {
		CallsEffectContext _localctx = new CallsEffectContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_callsEffect);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(T__12);
			setState(209);
			match(T__1);
			setState(210);
			match(SimpleName);
			setState(211);
			match(T__9);
			setState(212);
			match(IntegerLiteral);
			setState(213);
			match(T__2);
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode SimpleName() { return getToken(EffectSystemParser.SimpleName, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(SimpleName);
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

	public static class LiteralConstantContext extends ParserRuleContext {
		public TerminalNode BooleanLiteral() { return getToken(EffectSystemParser.BooleanLiteral, 0); }
		public TerminalNode IntegerLiteral() { return getToken(EffectSystemParser.IntegerLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(EffectSystemParser.StringLiteral, 0); }
		public TerminalNode NullLiteral() { return getToken(EffectSystemParser.NullLiteral, 0); }
		public LiteralConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterLiteralConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitLiteralConstant(this);
		}
	}

	public final LiteralConstantContext literalConstant() throws RecognitionException {
		LiteralConstantContext _localctx = new LiteralConstantContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_literalConstant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BooleanLiteral) | (1L << NullLiteral) | (1L << StringLiteral) | (1L << IntegerLiteral))) != 0)) ) {
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3&\u00de\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3\2\7\2"+
		"A\n\2\f\2\16\2D\13\2\5\2F\n\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4P\n\4"+
		"\f\4\16\4S\13\4\3\5\3\5\3\5\3\5\7\5Y\n\5\f\5\16\5\\\13\5\3\6\3\6\3\6\3"+
		"\6\7\6b\n\6\f\6\16\6e\13\6\3\7\3\7\3\7\3\7\7\7k\n\7\f\7\16\7n\13\7\3\b"+
		"\3\b\3\b\3\b\7\bt\n\b\f\b\16\bw\13\b\3\b\3\b\3\b\3\b\5\b}\n\b\5\b\177"+
		"\n\b\3\t\3\t\3\t\3\t\7\t\u0085\n\t\f\t\16\t\u0088\13\t\3\n\3\n\3\n\3\n"+
		"\7\n\u008e\n\n\f\n\16\n\u0091\13\n\3\13\7\13\u0094\n\13\f\13\16\13\u0097"+
		"\13\13\3\13\3\13\3\f\3\f\7\f\u009d\n\f\f\f\16\f\u00a0\13\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\5\r\u00a8\n\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30"+
		"\7\30\u00c1\n\30\f\30\16\30\u00c4\13\30\3\31\3\31\3\31\5\31\u00c9\n\31"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\5\33\u00d1\n\33\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\35\3\35\3\36\3\36\3\36\2\2\37\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\668:\2\13\3\2%&\3\2\30\33\3\2\34\35\3\2"+
		"\36 \4\2\34\35!#\4\2!\"$$\3\2\b\t\3\2\n\13\3\2\20\23\u00d3\2E\3\2\2\2"+
		"\4G\3\2\2\2\6K\3\2\2\2\bT\3\2\2\2\n]\3\2\2\2\ff\3\2\2\2\16~\3\2\2\2\20"+
		"\u0080\3\2\2\2\22\u0089\3\2\2\2\24\u0095\3\2\2\2\26\u009a\3\2\2\2\30\u00a7"+
		"\3\2\2\2\32\u00a9\3\2\2\2\34\u00ab\3\2\2\2\36\u00ad\3\2\2\2 \u00af\3\2"+
		"\2\2\"\u00b1\3\2\2\2$\u00b3\3\2\2\2&\u00b5\3\2\2\2(\u00b7\3\2\2\2*\u00b9"+
		"\3\2\2\2,\u00bb\3\2\2\2.\u00bd\3\2\2\2\60\u00c8\3\2\2\2\62\u00ca\3\2\2"+
		"\2\64\u00cd\3\2\2\2\66\u00d2\3\2\2\28\u00d9\3\2\2\2:\u00db\3\2\2\2<F\7"+
		"\2\2\3=B\5\4\3\2>?\7\27\2\2?A\5\4\3\2@>\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC"+
		"\3\2\2\2CF\3\2\2\2DB\3\2\2\2E<\3\2\2\2E=\3\2\2\2F\3\3\2\2\2GH\5\6\4\2"+
		"HI\7\3\2\2IJ\5.\30\2J\5\3\2\2\2KQ\5\b\5\2LM\5\32\16\2MN\5\b\5\2NP\3\2"+
		"\2\2OL\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2R\7\3\2\2\2SQ\3\2\2\2TZ\5"+
		"\n\6\2UV\5\34\17\2VW\5\n\6\2WY\3\2\2\2XU\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2"+
		"Z[\3\2\2\2[\t\3\2\2\2\\Z\3\2\2\2]c\5\f\7\2^_\5\36\20\2_`\5\f\7\2`b\3\2"+
		"\2\2a^\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\13\3\2\2\2ec\3\2\2\2fl\5"+
		"\16\b\2gh\5 \21\2hi\5\16\b\2ik\3\2\2\2jg\3\2\2\2kn\3\2\2\2lj\3\2\2\2l"+
		"m\3\2\2\2m\r\3\2\2\2nl\3\2\2\2ou\5\20\t\2pq\5*\26\2qr\5\20\t\2rt\3\2\2"+
		"\2sp\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2v\177\3\2\2\2wu\3\2\2\2x|\5"+
		"\20\t\2yz\5,\27\2z{\58\35\2{}\3\2\2\2|y\3\2\2\2|}\3\2\2\2}\177\3\2\2\2"+
		"~o\3\2\2\2~x\3\2\2\2\177\17\3\2\2\2\u0080\u0086\5\22\n\2\u0081\u0082\5"+
		"\"\22\2\u0082\u0083\5\22\n\2\u0083\u0085\3\2\2\2\u0084\u0081\3\2\2\2\u0085"+
		"\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\21\3\2\2"+
		"\2\u0088\u0086\3\2\2\2\u0089\u008f\5\24\13\2\u008a\u008b\5$\23\2\u008b"+
		"\u008c\5\24\13\2\u008c\u008e\3\2\2\2\u008d\u008a\3\2\2\2\u008e\u0091\3"+
		"\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\23\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0092\u0094\5&\24\2\u0093\u0092\3\2\2\2\u0094\u0097\3\2"+
		"\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097"+
		"\u0095\3\2\2\2\u0098\u0099\5\26\f\2\u0099\25\3\2\2\2\u009a\u009e\5\30"+
		"\r\2\u009b\u009d\5(\25\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e"+
		"\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\27\3\2\2\2\u00a0\u009e\3\2\2"+
		"\2\u00a1\u00a2\7\4\2\2\u00a2\u00a3\5\6\4\2\u00a3\u00a4\7\5\2\2\u00a4\u00a8"+
		"\3\2\2\2\u00a5\u00a8\5:\36\2\u00a6\u00a8\7\24\2\2\u00a7\u00a1\3\2\2\2"+
		"\u00a7\u00a5\3\2\2\2\u00a7\u00a6\3\2\2\2\u00a8\31\3\2\2\2\u00a9\u00aa"+
		"\7\6\2\2\u00aa\33\3\2\2\2\u00ab\u00ac\7\7\2\2\u00ac\35\3\2\2\2\u00ad\u00ae"+
		"\t\2\2\2\u00ae\37\3\2\2\2\u00af\u00b0\t\3\2\2\u00b0!\3\2\2\2\u00b1\u00b2"+
		"\t\4\2\2\u00b2#\3\2\2\2\u00b3\u00b4\t\5\2\2\u00b4%\3\2\2\2\u00b5\u00b6"+
		"\t\6\2\2\u00b6\'\3\2\2\2\u00b7\u00b8\t\7\2\2\u00b8)\3\2\2\2\u00b9\u00ba"+
		"\t\b\2\2\u00ba+\3\2\2\2\u00bb\u00bc\t\t\2\2\u00bc-\3\2\2\2\u00bd\u00c2"+
		"\5\60\31\2\u00be\u00bf\7\f\2\2\u00bf\u00c1\5\60\31\2\u00c0\u00be\3\2\2"+
		"\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3/"+
		"\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c9\5\62\32\2\u00c6\u00c9\5\64\33"+
		"\2\u00c7\u00c9\5\66\34\2\u00c8\u00c5\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8"+
		"\u00c7\3\2\2\2\u00c9\61\3\2\2\2\u00ca\u00cb\7\r\2\2\u00cb\u00cc\7\24\2"+
		"\2\u00cc\63\3\2\2\2\u00cd\u00d0\7\16\2\2\u00ce\u00d1\5:\36\2\u00cf\u00d1"+
		"\7\24\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00cf\3\2\2\2\u00d1\65\3\2\2\2\u00d2"+
		"\u00d3\7\17\2\2\u00d3\u00d4\7\4\2\2\u00d4\u00d5\7\24\2\2\u00d5\u00d6\7"+
		"\f\2\2\u00d6\u00d7\7\23\2\2\u00d7\u00d8\7\5\2\2\u00d8\67\3\2\2\2\u00d9"+
		"\u00da\7\24\2\2\u00da9\3\2\2\2\u00db\u00dc\t\n\2\2\u00dc;\3\2\2\2\23B"+
		"EQZclu|~\u0086\u008f\u0095\u009e\u00a7\u00c2\u00c8\u00d0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}