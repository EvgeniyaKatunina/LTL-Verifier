// Generated from C:/Projects/LTLVerifier/src/main/antlr\LTLExpression.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LTLExpressionParser}.
 */
public interface LTLExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code next}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNext(LTLExpressionParser.NextContext ctx);
	/**
	 * Exit a parse tree produced by the {@code next}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNext(LTLExpressionParser.NextContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negation}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNegation(LTLExpressionParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negation}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNegation(LTLExpressionParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code conjunction}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(LTLExpressionParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code conjunction}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(LTLExpressionParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code disjunction}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDisjunction(LTLExpressionParser.DisjunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code disjunction}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDisjunction(LTLExpressionParser.DisjunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code future}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFuture(LTLExpressionParser.FutureContext ctx);
	/**
	 * Exit a parse tree produced by the {@code future}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFuture(LTLExpressionParser.FutureContext ctx);
	/**
	 * Enter a parse tree produced by the {@code globally}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGlobally(LTLExpressionParser.GloballyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code globally}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGlobally(LTLExpressionParser.GloballyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code release}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelease(LTLExpressionParser.ReleaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code release}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelease(LTLExpressionParser.ReleaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code implication}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterImplication(LTLExpressionParser.ImplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code implication}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitImplication(LTLExpressionParser.ImplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variable}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariable(LTLExpressionParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variable}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariable(LTLExpressionParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code until}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUntil(LTLExpressionParser.UntilContext ctx);
	/**
	 * Exit a parse tree produced by the {@code until}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUntil(LTLExpressionParser.UntilContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesis(LTLExpressionParser.ParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesis(LTLExpressionParser.ParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(LTLExpressionParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link LTLExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(LTLExpressionParser.BooleanLiteralContext ctx);
}