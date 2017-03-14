// Generated from /home/dsavvinov/Repos/kotlin-fork/kotlin/compiler/frontend/src/org/jetbrains/kotlin/effects/parsing/antlr/EffectSystem.g4 by ANTLR 4.6
package org.jetbrains.kotlin.effects.parsing.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EffectSystemParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EffectSystemVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#effectSchema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEffectSchema(EffectSystemParser.EffectSchemaContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClause(EffectSystemParser.ClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(EffectSystemParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#conjunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConjunction(EffectSystemParser.ConjunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#equalityComparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityComparison(EffectSystemParser.EqualityComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(EffectSystemParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#namedInfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedInfix(EffectSystemParser.NamedInfixContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(EffectSystemParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(EffectSystemParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#prefixUnaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixUnaryExpression(EffectSystemParser.PrefixUnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#postfixUnaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixUnaryExpression(EffectSystemParser.PostfixUnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#atomicExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicExpression(EffectSystemParser.AtomicExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#disjunctionOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisjunctionOperator(EffectSystemParser.DisjunctionOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#conjunctionOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConjunctionOperator(EffectSystemParser.ConjunctionOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#equalityOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityOperator(EffectSystemParser.EqualityOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(EffectSystemParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#additiveOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveOperator(EffectSystemParser.AdditiveOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#multiplicativeOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeOperator(EffectSystemParser.MultiplicativeOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#prefixUnaryOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixUnaryOperation(EffectSystemParser.PrefixUnaryOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#postfixUnaryOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixUnaryOperation(EffectSystemParser.PostfixUnaryOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#inOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInOperation(EffectSystemParser.InOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#isOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsOperation(EffectSystemParser.IsOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#effectsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEffectsList(EffectSystemParser.EffectsListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#effect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEffect(EffectSystemParser.EffectContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#throwsEffect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThrowsEffect(EffectSystemParser.ThrowsEffectContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#returnsEffect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnsEffect(EffectSystemParser.ReturnsEffectContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#callsEffect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallsEffect(EffectSystemParser.CallsEffectContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(EffectSystemParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EffectSystemParser#literalConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralConstant(EffectSystemParser.LiteralConstantContext ctx);
}