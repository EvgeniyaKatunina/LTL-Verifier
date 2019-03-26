import expression.*;
import generated.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class LTLExpressionBuilder extends LTLExpressionBaseVisitor<LTLExpression> {

    private LTLExpressionBuilder() {
    }

    public static LTLExpression build(String string) {
        final LTLExpressionLexer lexer = new LTLExpressionLexer(new ANTLRInputStream(string));
        final LTLExpressionParser parser = new LTLExpressionParser(new CommonTokenStream(lexer));
        return new LTLExpressionBuilder().visit(parser.expression());
    }

    @Override
    public LTLExpression visitNext(LTLExpressionParser.NextContext ctx) {
        return new Next(visit(ctx.expression()));
    }

    @Override
    public LTLExpression visitNegation(LTLExpressionParser.NegationContext ctx) {
        return new Negation(visit(ctx.expression()));
    }

    @Override
    public LTLExpression visitConjunction(LTLExpressionParser.ConjunctionContext ctx) {
        return new And(visit(ctx.left), visit(ctx.right));
    }

    @Override
    public LTLExpression visitDisjunction(LTLExpressionParser.DisjunctionContext ctx) {
        return new Or(visit(ctx.left), visit(ctx.right));
    }

    @Override
    public LTLExpression visitFuture(LTLExpressionParser.FutureContext ctx) {
        return new Future(visit(ctx.expression()));
    }

    @Override
    public LTLExpression visitGlobally(LTLExpressionParser.GloballyContext ctx) {
        return new Globally(visit(ctx.expression()));
    }

    @Override
    public LTLExpression visitRelease(LTLExpressionParser.ReleaseContext ctx) {
        return new Release(visit(ctx.left), visit(ctx.right));
    }

    @Override
    public LTLExpression visitImplication(LTLExpressionParser.ImplicationContext ctx) {
        return new Or(new Negation(visit(ctx.left)), visit(ctx.right));
    }

    @Override
    public LTLExpression visitVariable(LTLExpressionParser.VariableContext ctx) {
        return new Variable(ctx.getText());
    }

    @Override
    public LTLExpression visitUntil(LTLExpressionParser.UntilContext ctx) {
        return new Until(visit(ctx.left), visit(ctx.right));
    }

    @Override
    public LTLExpression visitParenthesis(LTLExpressionParser.ParenthesisContext ctx) {
        return visit(ctx.getChild(1));
    }

    @Override
    public LTLExpression visitBooleanLiteral(LTLExpressionParser.BooleanLiteralContext ctx) {
        return Boolean.parseBoolean(ctx.getText()) ? Constant.TRUE : Constant.FALSE;
    }
}
