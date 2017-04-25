// Generated from EffectSystem.g4 by ANTLR 4.6
package org.jetbrains.kotlin.effects.parsing.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EffectSystemParser}.
 */
public interface EffectSystemListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#effectSchema}.
	 * @param ctx the parse tree
	 */
	void enterEffectSchema(EffectSystemParser.EffectSchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#effectSchema}.
	 * @param ctx the parse tree
	 */
	void exitEffectSchema(EffectSystemParser.EffectSchemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#clause}.
	 * @param ctx the parse tree
	 */
	void enterClause(EffectSystemParser.ClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#clause}.
	 * @param ctx the parse tree
	 */
	void exitClause(EffectSystemParser.ClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(EffectSystemParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(EffectSystemParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(EffectSystemParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(EffectSystemParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#equalityComparison}.
	 * @param ctx the parse tree
	 */
	void enterEqualityComparison(EffectSystemParser.EqualityComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#equalityComparison}.
	 * @param ctx the parse tree
	 */
	void exitEqualityComparison(EffectSystemParser.EqualityComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(EffectSystemParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(EffectSystemParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#namedInfix}.
	 * @param ctx the parse tree
	 */
	void enterNamedInfix(EffectSystemParser.NamedInfixContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#namedInfix}.
	 * @param ctx the parse tree
	 */
	void exitNamedInfix(EffectSystemParser.NamedInfixContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(EffectSystemParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(EffectSystemParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(EffectSystemParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(EffectSystemParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#prefixUnaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixUnaryExpression(EffectSystemParser.PrefixUnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#prefixUnaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixUnaryExpression(EffectSystemParser.PrefixUnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#postfixUnaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixUnaryExpression(EffectSystemParser.PostfixUnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#postfixUnaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixUnaryExpression(EffectSystemParser.PostfixUnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#atomicExpression}.
	 * @param ctx the parse tree
	 */
	void enterAtomicExpression(EffectSystemParser.AtomicExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#atomicExpression}.
	 * @param ctx the parse tree
	 */
	void exitAtomicExpression(EffectSystemParser.AtomicExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#disjunctionOperator}.
	 * @param ctx the parse tree
	 */
	void enterDisjunctionOperator(EffectSystemParser.DisjunctionOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#disjunctionOperator}.
	 * @param ctx the parse tree
	 */
	void exitDisjunctionOperator(EffectSystemParser.DisjunctionOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#conjunctionOperator}.
	 * @param ctx the parse tree
	 */
	void enterConjunctionOperator(EffectSystemParser.ConjunctionOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#conjunctionOperator}.
	 * @param ctx the parse tree
	 */
	void exitConjunctionOperator(EffectSystemParser.ConjunctionOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#equalityOperator}.
	 * @param ctx the parse tree
	 */
	void enterEqualityOperator(EffectSystemParser.EqualityOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#equalityOperator}.
	 * @param ctx the parse tree
	 */
	void exitEqualityOperator(EffectSystemParser.EqualityOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(EffectSystemParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(EffectSystemParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#additiveOperator}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveOperator(EffectSystemParser.AdditiveOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#additiveOperator}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveOperator(EffectSystemParser.AdditiveOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#multiplicativeOperator}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeOperator(EffectSystemParser.MultiplicativeOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#multiplicativeOperator}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeOperator(EffectSystemParser.MultiplicativeOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#prefixUnaryOperation}.
	 * @param ctx the parse tree
	 */
	void enterPrefixUnaryOperation(EffectSystemParser.PrefixUnaryOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#prefixUnaryOperation}.
	 * @param ctx the parse tree
	 */
	void exitPrefixUnaryOperation(EffectSystemParser.PrefixUnaryOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#postfixUnaryOperation}.
	 * @param ctx the parse tree
	 */
	void enterPostfixUnaryOperation(EffectSystemParser.PostfixUnaryOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#postfixUnaryOperation}.
	 * @param ctx the parse tree
	 */
	void exitPostfixUnaryOperation(EffectSystemParser.PostfixUnaryOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#callSuffix}.
	 * @param ctx the parse tree
	 */
	void enterCallSuffix(EffectSystemParser.CallSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#callSuffix}.
	 * @param ctx the parse tree
	 */
	void exitCallSuffix(EffectSystemParser.CallSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#inOperation}.
	 * @param ctx the parse tree
	 */
	void enterInOperation(EffectSystemParser.InOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#inOperation}.
	 * @param ctx the parse tree
	 */
	void exitInOperation(EffectSystemParser.InOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#isOperation}.
	 * @param ctx the parse tree
	 */
	void enterIsOperation(EffectSystemParser.IsOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#isOperation}.
	 * @param ctx the parse tree
	 */
	void exitIsOperation(EffectSystemParser.IsOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#atOperation}.
	 * @param ctx the parse tree
	 */
	void enterAtOperation(EffectSystemParser.AtOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#atOperation}.
	 * @param ctx the parse tree
	 */
	void exitAtOperation(EffectSystemParser.AtOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#effectsList}.
	 * @param ctx the parse tree
	 */
	void enterEffectsList(EffectSystemParser.EffectsListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#effectsList}.
	 * @param ctx the parse tree
	 */
	void exitEffectsList(EffectSystemParser.EffectsListContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#effect}.
	 * @param ctx the parse tree
	 */
	void enterEffect(EffectSystemParser.EffectContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#effect}.
	 * @param ctx the parse tree
	 */
	void exitEffect(EffectSystemParser.EffectContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#throwsEffect}.
	 * @param ctx the parse tree
	 */
	void enterThrowsEffect(EffectSystemParser.ThrowsEffectContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#throwsEffect}.
	 * @param ctx the parse tree
	 */
	void exitThrowsEffect(EffectSystemParser.ThrowsEffectContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#returnsEffect}.
	 * @param ctx the parse tree
	 */
	void enterReturnsEffect(EffectSystemParser.ReturnsEffectContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#returnsEffect}.
	 * @param ctx the parse tree
	 */
	void exitReturnsEffect(EffectSystemParser.ReturnsEffectContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#callsEffect}.
	 * @param ctx the parse tree
	 */
	void enterCallsEffect(EffectSystemParser.CallsEffectContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#callsEffect}.
	 * @param ctx the parse tree
	 */
	void exitCallsEffect(EffectSystemParser.CallsEffectContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#callsRecord}.
	 * @param ctx the parse tree
	 */
	void enterCallsRecord(EffectSystemParser.CallsRecordContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#callsRecord}.
	 * @param ctx the parse tree
	 */
	void exitCallsRecord(EffectSystemParser.CallsRecordContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#hintsEffect}.
	 * @param ctx the parse tree
	 */
	void enterHintsEffect(EffectSystemParser.HintsEffectContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#hintsEffect}.
	 * @param ctx the parse tree
	 */
	void exitHintsEffect(EffectSystemParser.HintsEffectContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#literalConstant}.
	 * @param ctx the parse tree
	 */
	void enterLiteralConstant(EffectSystemParser.LiteralConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#literalConstant}.
	 * @param ctx the parse tree
	 */
	void exitLiteralConstant(EffectSystemParser.LiteralConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#typeExpression}.
	 * @param ctx the parse tree
	 */
	void enterTypeExpression(EffectSystemParser.TypeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#typeExpression}.
	 * @param ctx the parse tree
	 */
	void exitTypeExpression(EffectSystemParser.TypeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(EffectSystemParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(EffectSystemParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#typeOfOperator}.
	 * @param ctx the parse tree
	 */
	void enterTypeOfOperator(EffectSystemParser.TypeOfOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#typeOfOperator}.
	 * @param ctx the parse tree
	 */
	void exitTypeOfOperator(EffectSystemParser.TypeOfOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EffectSystemParser#typeParametersList}.
	 * @param ctx the parse tree
	 */
	void enterTypeParametersList(EffectSystemParser.TypeParametersListContext ctx);
	/**
	 * Exit a parse tree produced by {@link EffectSystemParser#typeParametersList}.
	 * @param ctx the parse tree
	 */
	void exitTypeParametersList(EffectSystemParser.TypeParametersListContext ctx);
}