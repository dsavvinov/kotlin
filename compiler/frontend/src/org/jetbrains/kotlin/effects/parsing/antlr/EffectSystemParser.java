// Generated from EffectSystem.g4 by ANTLR 4.6
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, BooleanLiteral=17, 
		NullLiteral=18, UnknownLiteral=19, UnitLiteral=20, StringLiteral=21, IntegerLiteral=22, 
		SimpleName=23, WS=24, EOL=25, SEMI=26, LT=27, GT=28, LEQ=29, GEQ=30, PLUS=31, 
		MINUS=32, MUL=33, DIV=34, PERC=35, PLUSPLUS=36, MINUSMINUS=37, NOT=38, 
		EXCLEXCL=39, EQEQ=40, EXCLEQ=41;
	public static final int
		RULE_effectSchema = 0, RULE_clause = 1, RULE_expression = 2, RULE_conjunction = 3, 
		RULE_equalityComparison = 4, RULE_comparison = 5, RULE_namedInfix = 6, 
		RULE_additiveExpression = 7, RULE_multiplicativeExpression = 8, RULE_prefixUnaryExpression = 9, 
		RULE_postfixUnaryExpression = 10, RULE_atomicExpression = 11, RULE_disjunctionOperator = 12, 
		RULE_conjunctionOperator = 13, RULE_equalityOperator = 14, RULE_comparisonOperator = 15, 
		RULE_additiveOperator = 16, RULE_multiplicativeOperator = 17, RULE_prefixUnaryOperation = 18, 
		RULE_postfixUnaryOperation = 19, RULE_callSuffix = 20, RULE_inOperation = 21, 
		RULE_isOperation = 22, RULE_atOperation = 23, RULE_effectsList = 24, RULE_effect = 25, 
		RULE_throwsEffect = 26, RULE_returnsEffect = 27, RULE_callsEffect = 28, 
		RULE_callsRecord = 29, RULE_hintsEffect = 30, RULE_literalConstant = 31, 
		RULE_typeExpression = 32, RULE_type = 33, RULE_typeOfOperator = 34, RULE_typeParametersList = 35;
	public static final String[] ruleNames = {
		"effectSchema", "clause", "expression", "conjunction", "equalityComparison", 
		"comparison", "namedInfix", "additiveExpression", "multiplicativeExpression", 
		"prefixUnaryExpression", "postfixUnaryExpression", "atomicExpression", 
		"disjunctionOperator", "conjunctionOperator", "equalityOperator", "comparisonOperator", 
		"additiveOperator", "multiplicativeOperator", "prefixUnaryOperation", 
		"postfixUnaryOperation", "callSuffix", "inOperation", "isOperation", "atOperation", 
		"effectsList", "effect", "throwsEffect", "returnsEffect", "callsEffect", 
		"callsRecord", "hintsEffect", "literalConstant", "typeExpression", "type", 
		"typeOfOperator", "typeParametersList"
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitEffectSchema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EffectSchemaContext effectSchema() throws RecognitionException {
		EffectSchemaContext _localctx = new EffectSchemaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_effectSchema);
		int _la;
		try {
			setState(81);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				match(EOF);
				}
				break;
			case T__1:
			case BooleanLiteral:
			case NullLiteral:
			case UnitLiteral:
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
				setState(73);
				clause();
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMI) {
					{
					{
					setState(74);
					match(SEMI);
					setState(75);
					clause();
					}
					}
					setState(80);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClauseContext clause() throws RecognitionException {
		ClauseContext _localctx = new ClauseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			expression();
			setState(84);
			match(T__0);
			setState(85);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			conjunction();
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(88);
				disjunctionOperator();
				setState(89);
				conjunction();
				}
				}
				setState(95);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitConjunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_conjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			equalityComparison();
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(97);
				conjunctionOperator();
				setState(98);
				equalityComparison();
				}
				}
				setState(104);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitEqualityComparison(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityComparisonContext equalityComparison() throws RecognitionException {
		EqualityComparisonContext _localctx = new EqualityComparisonContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_equalityComparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			comparison();
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EQEQ || _la==EXCLEQ) {
				{
				{
				setState(106);
				equalityOperator();
				setState(107);
				comparison();
				}
				}
				setState(113);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			namedInfix();
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << GT) | (1L << LEQ) | (1L << GEQ))) != 0)) {
				{
				{
				setState(115);
				comparisonOperator();
				setState(116);
				namedInfix();
				}
				}
				setState(122);
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
		public AtOperationContext atOperation() {
			return getRuleContext(AtOperationContext.class,0);
		}
		public EffectContext effect() {
			return getRuleContext(EffectContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitNamedInfix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamedInfixContext namedInfix() throws RecognitionException {
		NamedInfixContext _localctx = new NamedInfixContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_namedInfix);
		int _la;
		try {
			setState(142);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				additiveExpression();
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__6 || _la==T__7) {
					{
					{
					setState(124);
					inOperation();
					setState(125);
					additiveExpression();
					}
					}
					setState(131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				additiveExpression();
				setState(133);
				atOperation();
				setState(134);
				effect();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(136);
				additiveExpression();
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8 || _la==T__9) {
					{
					setState(137);
					isOperation();
					setState(138);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_additiveExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			multiplicativeExpression();
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(145);
				additiveOperator();
				setState(146);
				multiplicativeExpression();
				}
				}
				setState(152);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_multiplicativeExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			prefixUnaryExpression();
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << PERC))) != 0)) {
				{
				{
				setState(154);
				multiplicativeOperator();
				setState(155);
				prefixUnaryExpression();
				}
				}
				setState(161);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitPrefixUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixUnaryExpressionContext prefixUnaryExpression() throws RecognitionException {
		PrefixUnaryExpressionContext _localctx = new PrefixUnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_prefixUnaryExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << PLUSPLUS) | (1L << MINUSMINUS) | (1L << NOT))) != 0)) {
				{
				{
				setState(162);
				prefixUnaryOperation();
				}
				}
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(168);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitPostfixUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixUnaryExpressionContext postfixUnaryExpression() throws RecognitionException {
		PostfixUnaryExpressionContext _localctx = new PostfixUnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_postfixUnaryExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			atomicExpression();
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << PLUSPLUS) | (1L << MINUSMINUS) | (1L << EXCLEXCL))) != 0)) {
				{
				{
				setState(171);
				postfixUnaryOperation();
				}
				}
				setState(176);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitAtomicExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomicExpressionContext atomicExpression() throws RecognitionException {
		AtomicExpressionContext _localctx = new AtomicExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_atomicExpression);
		try {
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(177);
				match(T__1);
				setState(178);
				expression();
				setState(179);
				match(T__2);
				}
				break;
			case BooleanLiteral:
			case NullLiteral:
			case UnitLiteral:
			case StringLiteral:
			case IntegerLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(181);
				literalConstant();
				}
				break;
			case SimpleName:
				enterOuterAlt(_localctx, 3);
				{
				setState(182);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitDisjunctionOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DisjunctionOperatorContext disjunctionOperator() throws RecognitionException {
		DisjunctionOperatorContext _localctx = new DisjunctionOperatorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_disjunctionOperator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitConjunctionOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConjunctionOperatorContext conjunctionOperator() throws RecognitionException {
		ConjunctionOperatorContext _localctx = new ConjunctionOperatorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_conjunctionOperator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitEqualityOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityOperatorContext equalityOperator() throws RecognitionException {
		EqualityOperatorContext _localctx = new EqualityOperatorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_equalityOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitAdditiveOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveOperatorContext additiveOperator() throws RecognitionException {
		AdditiveOperatorContext _localctx = new AdditiveOperatorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_additiveOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitMultiplicativeOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeOperatorContext multiplicativeOperator() throws RecognitionException {
		MultiplicativeOperatorContext _localctx = new MultiplicativeOperatorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_multiplicativeOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitPrefixUnaryOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixUnaryOperationContext prefixUnaryOperation() throws RecognitionException {
		PrefixUnaryOperationContext _localctx = new PrefixUnaryOperationContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_prefixUnaryOperation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
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
		public CallSuffixContext callSuffix() {
			return getRuleContext(CallSuffixContext.class,0);
		}
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitPostfixUnaryOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixUnaryOperationContext postfixUnaryOperation() throws RecognitionException {
		PostfixUnaryOperationContext _localctx = new PostfixUnaryOperationContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_postfixUnaryOperation);
		try {
			setState(203);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUSPLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				match(PLUSPLUS);
				}
				break;
			case MINUSMINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(200);
				match(MINUSMINUS);
				}
				break;
			case EXCLEXCL:
				enterOuterAlt(_localctx, 3);
				{
				setState(201);
				match(EXCLEXCL);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 4);
				{
				setState(202);
				callSuffix();
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

	public static class CallSuffixContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CallSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterCallSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitCallSuffix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitCallSuffix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallSuffixContext callSuffix() throws RecognitionException {
		CallSuffixContext _localctx = new CallSuffixContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_callSuffix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(T__1);
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << BooleanLiteral) | (1L << NullLiteral) | (1L << UnitLiteral) | (1L << StringLiteral) | (1L << IntegerLiteral) | (1L << SimpleName) | (1L << PLUS) | (1L << MINUS) | (1L << PLUSPLUS) | (1L << MINUSMINUS) | (1L << NOT))) != 0)) {
				{
				setState(206);
				expression();
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__5) {
					{
					{
					setState(207);
					match(T__5);
					setState(208);
					expression();
					}
					}
					setState(213);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(216);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitInOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InOperationContext inOperation() throws RecognitionException {
		InOperationContext _localctx = new InOperationContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_inOperation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			_la = _input.LA(1);
			if ( !(_la==T__6 || _la==T__7) ) {
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitIsOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IsOperationContext isOperation() throws RecognitionException {
		IsOperationContext _localctx = new IsOperationContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_isOperation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			_la = _input.LA(1);
			if ( !(_la==T__8 || _la==T__9) ) {
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

	public static class AtOperationContext extends ParserRuleContext {
		public AtOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterAtOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitAtOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitAtOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtOperationContext atOperation() throws RecognitionException {
		AtOperationContext _localctx = new AtOperationContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_atOperation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(T__10);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitEffectsList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EffectsListContext effectsList() throws RecognitionException {
		EffectsListContext _localctx = new EffectsListContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_effectsList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			effect();
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(225);
				match(T__5);
				setState(226);
				effect();
				}
				}
				setState(231);
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
		public HintsEffectContext hintsEffect() {
			return getRuleContext(HintsEffectContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitEffect(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EffectContext effect() throws RecognitionException {
		EffectContext _localctx = new EffectContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_effect);
		try {
			setState(236);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				enterOuterAlt(_localctx, 1);
				{
				setState(232);
				throwsEffect();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(233);
				returnsEffect();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 3);
				{
				setState(234);
				callsEffect();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 4);
				{
				setState(235);
				hintsEffect();
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
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitThrowsEffect(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThrowsEffectContext throwsEffect() throws RecognitionException {
		ThrowsEffectContext _localctx = new ThrowsEffectContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_throwsEffect);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(T__11);
			setState(239);
			type();
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode UnknownLiteral() { return getToken(EffectSystemParser.UnknownLiteral, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitReturnsEffect(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnsEffectContext returnsEffect() throws RecognitionException {
		ReturnsEffectContext _localctx = new ReturnsEffectContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_returnsEffect);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			match(T__12);
			setState(242);
			match(T__1);
			setState(245);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case BooleanLiteral:
			case NullLiteral:
			case UnitLiteral:
			case StringLiteral:
			case IntegerLiteral:
			case SimpleName:
			case PLUS:
			case MINUS:
			case PLUSPLUS:
			case MINUSMINUS:
			case NOT:
				{
				setState(243);
				expression();
				}
				break;
			case UnknownLiteral:
				{
				setState(244);
				match(UnknownLiteral);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(247);
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

	public static class CallsEffectContext extends ParserRuleContext {
		public List<CallsRecordContext> callsRecord() {
			return getRuleContexts(CallsRecordContext.class);
		}
		public CallsRecordContext callsRecord(int i) {
			return getRuleContext(CallsRecordContext.class,i);
		}
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitCallsEffect(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallsEffectContext callsEffect() throws RecognitionException {
		CallsEffectContext _localctx = new CallsEffectContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_callsEffect);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(T__13);
			setState(250);
			match(T__1);
			setState(251);
			callsRecord();
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI) {
				{
				{
				setState(252);
				match(SEMI);
				setState(253);
				callsRecord();
				}
				}
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(259);
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

	public static class CallsRecordContext extends ParserRuleContext {
		public TerminalNode SimpleName() { return getToken(EffectSystemParser.SimpleName, 0); }
		public TerminalNode IntegerLiteral() { return getToken(EffectSystemParser.IntegerLiteral, 0); }
		public CallsRecordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callsRecord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterCallsRecord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitCallsRecord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitCallsRecord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallsRecordContext callsRecord() throws RecognitionException {
		CallsRecordContext _localctx = new CallsRecordContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_callsRecord);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(SimpleName);
			setState(262);
			match(IntegerLiteral);
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

	public static class HintsEffectContext extends ParserRuleContext {
		public TerminalNode SimpleName() { return getToken(EffectSystemParser.SimpleName, 0); }
		public TypeExpressionContext typeExpression() {
			return getRuleContext(TypeExpressionContext.class,0);
		}
		public HintsEffectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hintsEffect; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterHintsEffect(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitHintsEffect(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitHintsEffect(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HintsEffectContext hintsEffect() throws RecognitionException {
		HintsEffectContext _localctx = new HintsEffectContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_hintsEffect);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			match(T__14);
			setState(265);
			match(T__1);
			setState(266);
			match(SimpleName);
			setState(267);
			match(T__5);
			setState(268);
			typeExpression();
			setState(269);
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

	public static class LiteralConstantContext extends ParserRuleContext {
		public TerminalNode BooleanLiteral() { return getToken(EffectSystemParser.BooleanLiteral, 0); }
		public TerminalNode IntegerLiteral() { return getToken(EffectSystemParser.IntegerLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(EffectSystemParser.StringLiteral, 0); }
		public TerminalNode NullLiteral() { return getToken(EffectSystemParser.NullLiteral, 0); }
		public TerminalNode UnitLiteral() { return getToken(EffectSystemParser.UnitLiteral, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitLiteralConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralConstantContext literalConstant() throws RecognitionException {
		LiteralConstantContext _localctx = new LiteralConstantContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_literalConstant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BooleanLiteral) | (1L << NullLiteral) | (1L << UnitLiteral) | (1L << StringLiteral) | (1L << IntegerLiteral))) != 0)) ) {
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

	public static class TypeExpressionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeOfOperatorContext typeOfOperator() {
			return getRuleContext(TypeOfOperatorContext.class,0);
		}
		public TypeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterTypeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitTypeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitTypeExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeExpressionContext typeExpression() throws RecognitionException {
		TypeExpressionContext _localctx = new TypeExpressionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_typeExpression);
		try {
			setState(275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(273);
				type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(274);
				typeOfOperator();
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode SimpleName() { return getToken(EffectSystemParser.SimpleName, 0); }
		public TypeParametersListContext typeParametersList() {
			return getRuleContext(TypeParametersListContext.class,0);
		}
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(SimpleName);
			setState(279);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(278);
				typeParametersList();
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

	public static class TypeOfOperatorContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SimpleName() { return getToken(EffectSystemParser.SimpleName, 0); }
		public TypeOfOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeOfOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterTypeOfOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitTypeOfOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitTypeOfOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeOfOperatorContext typeOfOperator() throws RecognitionException {
		TypeOfOperatorContext _localctx = new TypeOfOperatorContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_typeOfOperator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			expression();
			setState(282);
			match(T__15);
			setState(283);
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

	public static class TypeParametersListContext extends ParserRuleContext {
		public List<TypeExpressionContext> typeExpression() {
			return getRuleContexts(TypeExpressionContext.class);
		}
		public TypeExpressionContext typeExpression(int i) {
			return getRuleContext(TypeExpressionContext.class,i);
		}
		public TypeParametersListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParametersList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).enterTypeParametersList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EffectSystemListener ) ((EffectSystemListener)listener).exitTypeParametersList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EffectSystemVisitor ) return ((EffectSystemVisitor<? extends T>)visitor).visitTypeParametersList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParametersListContext typeParametersList() throws RecognitionException {
		TypeParametersListContext _localctx = new TypeParametersListContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_typeParametersList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(LT);
			setState(286);
			typeExpression();
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(287);
				match(T__5);
				setState(288);
				typeExpression();
				}
				}
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(294);
			match(GT);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3+\u012b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\7\2O\n\2\f\2\16\2R\13\2"+
		"\5\2T\n\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4^\n\4\f\4\16\4a\13\4\3\5"+
		"\3\5\3\5\3\5\7\5g\n\5\f\5\16\5j\13\5\3\6\3\6\3\6\3\6\7\6p\n\6\f\6\16\6"+
		"s\13\6\3\7\3\7\3\7\3\7\7\7y\n\7\f\7\16\7|\13\7\3\b\3\b\3\b\3\b\7\b\u0082"+
		"\n\b\f\b\16\b\u0085\13\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u008f\n\b"+
		"\5\b\u0091\n\b\3\t\3\t\3\t\3\t\7\t\u0097\n\t\f\t\16\t\u009a\13\t\3\n\3"+
		"\n\3\n\3\n\7\n\u00a0\n\n\f\n\16\n\u00a3\13\n\3\13\7\13\u00a6\n\13\f\13"+
		"\16\13\u00a9\13\13\3\13\3\13\3\f\3\f\7\f\u00af\n\f\f\f\16\f\u00b2\13\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00ba\n\r\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\5\25\u00ce"+
		"\n\25\3\26\3\26\3\26\3\26\7\26\u00d4\n\26\f\26\16\26\u00d7\13\26\5\26"+
		"\u00d9\n\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\7\32"+
		"\u00e6\n\32\f\32\16\32\u00e9\13\32\3\33\3\33\3\33\3\33\5\33\u00ef\n\33"+
		"\3\34\3\34\3\34\3\35\3\35\3\35\3\35\5\35\u00f8\n\35\3\35\3\35\3\36\3\36"+
		"\3\36\3\36\3\36\7\36\u0101\n\36\f\36\16\36\u0104\13\36\3\36\3\36\3\37"+
		"\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3!\3!\3\"\3\"\5\"\u0116\n\"\3#\3#\5#\u011a"+
		"\n#\3$\3$\3$\3$\3%\3%\3%\3%\7%\u0124\n%\f%\16%\u0127\13%\3%\3%\3%\2\2"+
		"&\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDF"+
		"H\2\n\3\2*+\3\2\35 \3\2!\"\3\2#%\4\2!\"&(\3\2\t\n\3\2\13\f\4\2\23\24\26"+
		"\30\u0124\2S\3\2\2\2\4U\3\2\2\2\6Y\3\2\2\2\bb\3\2\2\2\nk\3\2\2\2\ft\3"+
		"\2\2\2\16\u0090\3\2\2\2\20\u0092\3\2\2\2\22\u009b\3\2\2\2\24\u00a7\3\2"+
		"\2\2\26\u00ac\3\2\2\2\30\u00b9\3\2\2\2\32\u00bb\3\2\2\2\34\u00bd\3\2\2"+
		"\2\36\u00bf\3\2\2\2 \u00c1\3\2\2\2\"\u00c3\3\2\2\2$\u00c5\3\2\2\2&\u00c7"+
		"\3\2\2\2(\u00cd\3\2\2\2*\u00cf\3\2\2\2,\u00dc\3\2\2\2.\u00de\3\2\2\2\60"+
		"\u00e0\3\2\2\2\62\u00e2\3\2\2\2\64\u00ee\3\2\2\2\66\u00f0\3\2\2\28\u00f3"+
		"\3\2\2\2:\u00fb\3\2\2\2<\u0107\3\2\2\2>\u010a\3\2\2\2@\u0111\3\2\2\2B"+
		"\u0115\3\2\2\2D\u0117\3\2\2\2F\u011b\3\2\2\2H\u011f\3\2\2\2JT\7\2\2\3"+
		"KP\5\4\3\2LM\7\34\2\2MO\5\4\3\2NL\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2"+
		"\2QT\3\2\2\2RP\3\2\2\2SJ\3\2\2\2SK\3\2\2\2T\3\3\2\2\2UV\5\6\4\2VW\7\3"+
		"\2\2WX\5\62\32\2X\5\3\2\2\2Y_\5\b\5\2Z[\5\32\16\2[\\\5\b\5\2\\^\3\2\2"+
		"\2]Z\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`\7\3\2\2\2a_\3\2\2\2bh\5\n"+
		"\6\2cd\5\34\17\2de\5\n\6\2eg\3\2\2\2fc\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3"+
		"\2\2\2i\t\3\2\2\2jh\3\2\2\2kq\5\f\7\2lm\5\36\20\2mn\5\f\7\2np\3\2\2\2"+
		"ol\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2r\13\3\2\2\2sq\3\2\2\2tz\5\16"+
		"\b\2uv\5 \21\2vw\5\16\b\2wy\3\2\2\2xu\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3"+
		"\2\2\2{\r\3\2\2\2|z\3\2\2\2}\u0083\5\20\t\2~\177\5,\27\2\177\u0080\5\20"+
		"\t\2\u0080\u0082\3\2\2\2\u0081~\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081"+
		"\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0091\3\2\2\2\u0085\u0083\3\2\2\2\u0086"+
		"\u0087\5\20\t\2\u0087\u0088\5\60\31\2\u0088\u0089\5\64\33\2\u0089\u0091"+
		"\3\2\2\2\u008a\u008e\5\20\t\2\u008b\u008c\5.\30\2\u008c\u008d\5D#\2\u008d"+
		"\u008f\3\2\2\2\u008e\u008b\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0091\3\2"+
		"\2\2\u0090}\3\2\2\2\u0090\u0086\3\2\2\2\u0090\u008a\3\2\2\2\u0091\17\3"+
		"\2\2\2\u0092\u0098\5\22\n\2\u0093\u0094\5\"\22\2\u0094\u0095\5\22\n\2"+
		"\u0095\u0097\3\2\2\2\u0096\u0093\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096"+
		"\3\2\2\2\u0098\u0099\3\2\2\2\u0099\21\3\2\2\2\u009a\u0098\3\2\2\2\u009b"+
		"\u00a1\5\24\13\2\u009c\u009d\5$\23\2\u009d\u009e\5\24\13\2\u009e\u00a0"+
		"\3\2\2\2\u009f\u009c\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2\23\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a6\5&\24"+
		"\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8"+
		"\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa\u00ab\5\26\f\2"+
		"\u00ab\25\3\2\2\2\u00ac\u00b0\5\30\r\2\u00ad\u00af\5(\25\2\u00ae\u00ad"+
		"\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\27\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\7\4\2\2\u00b4\u00b5\5\6\4"+
		"\2\u00b5\u00b6\7\5\2\2\u00b6\u00ba\3\2\2\2\u00b7\u00ba\5@!\2\u00b8\u00ba"+
		"\7\31\2\2\u00b9\u00b3\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00b8\3\2\2\2"+
		"\u00ba\31\3\2\2\2\u00bb\u00bc\7\6\2\2\u00bc\33\3\2\2\2\u00bd\u00be\7\7"+
		"\2\2\u00be\35\3\2\2\2\u00bf\u00c0\t\2\2\2\u00c0\37\3\2\2\2\u00c1\u00c2"+
		"\t\3\2\2\u00c2!\3\2\2\2\u00c3\u00c4\t\4\2\2\u00c4#\3\2\2\2\u00c5\u00c6"+
		"\t\5\2\2\u00c6%\3\2\2\2\u00c7\u00c8\t\6\2\2\u00c8\'\3\2\2\2\u00c9\u00ce"+
		"\7&\2\2\u00ca\u00ce\7\'\2\2\u00cb\u00ce\7)\2\2\u00cc\u00ce\5*\26\2\u00cd"+
		"\u00c9\3\2\2\2\u00cd\u00ca\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00cc\3\2"+
		"\2\2\u00ce)\3\2\2\2\u00cf\u00d8\7\4\2\2\u00d0\u00d5\5\6\4\2\u00d1\u00d2"+
		"\7\b\2\2\u00d2\u00d4\5\6\4\2\u00d3\u00d1\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2"+
		"\2\2\u00d8\u00d0\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"\u00db\7\5\2\2\u00db+\3\2\2\2\u00dc\u00dd\t\7\2\2\u00dd-\3\2\2\2\u00de"+
		"\u00df\t\b\2\2\u00df/\3\2\2\2\u00e0\u00e1\7\r\2\2\u00e1\61\3\2\2\2\u00e2"+
		"\u00e7\5\64\33\2\u00e3\u00e4\7\b\2\2\u00e4\u00e6\5\64\33\2\u00e5\u00e3"+
		"\3\2\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8"+
		"\63\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\u00ef\5\66\34\2\u00eb\u00ef\58\35"+
		"\2\u00ec\u00ef\5:\36\2\u00ed\u00ef\5> \2\u00ee\u00ea\3\2\2\2\u00ee\u00eb"+
		"\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ed\3\2\2\2\u00ef\65\3\2\2\2\u00f0"+
		"\u00f1\7\16\2\2\u00f1\u00f2\5D#\2\u00f2\67\3\2\2\2\u00f3\u00f4\7\17\2"+
		"\2\u00f4\u00f7\7\4\2\2\u00f5\u00f8\5\6\4\2\u00f6\u00f8\7\25\2\2\u00f7"+
		"\u00f5\3\2\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa\7\5"+
		"\2\2\u00fa9\3\2\2\2\u00fb\u00fc\7\20\2\2\u00fc\u00fd\7\4\2\2\u00fd\u0102"+
		"\5<\37\2\u00fe\u00ff\7\34\2\2\u00ff\u0101\5<\37\2\u0100\u00fe\3\2\2\2"+
		"\u0101\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0105"+
		"\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u0106\7\5\2\2\u0106;\3\2\2\2\u0107"+
		"\u0108\7\31\2\2\u0108\u0109\7\30\2\2\u0109=\3\2\2\2\u010a\u010b\7\21\2"+
		"\2\u010b\u010c\7\4\2\2\u010c\u010d\7\31\2\2\u010d\u010e\7\b\2\2\u010e"+
		"\u010f\5B\"\2\u010f\u0110\7\5\2\2\u0110?\3\2\2\2\u0111\u0112\t\t\2\2\u0112"+
		"A\3\2\2\2\u0113\u0116\5D#\2\u0114\u0116\5F$\2\u0115\u0113\3\2\2\2\u0115"+
		"\u0114\3\2\2\2\u0116C\3\2\2\2\u0117\u0119\7\31\2\2\u0118\u011a\5H%\2\u0119"+
		"\u0118\3\2\2\2\u0119\u011a\3\2\2\2\u011aE\3\2\2\2\u011b\u011c\5\6\4\2"+
		"\u011c\u011d\7\22\2\2\u011d\u011e\7\31\2\2\u011eG\3\2\2\2\u011f\u0120"+
		"\7\35\2\2\u0120\u0125\5B\"\2\u0121\u0122\7\b\2\2\u0122\u0124\5B\"\2\u0123"+
		"\u0121\3\2\2\2\u0124\u0127\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2"+
		"\2\2\u0126\u0128\3\2\2\2\u0127\u0125\3\2\2\2\u0128\u0129\7\36\2\2\u0129"+
		"I\3\2\2\2\32PS_hqz\u0083\u008e\u0090\u0098\u00a1\u00a7\u00b0\u00b9\u00cd"+
		"\u00d5\u00d8\u00e7\u00ee\u00f7\u0102\u0115\u0119\u0125";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}