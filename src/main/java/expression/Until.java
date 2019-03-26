package expression;

public class Until extends BinaryOperation {
    public Until(LTLExpression left, LTLExpression right) {
        super("U", left, right);
    }
}
