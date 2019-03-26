package expression;

public class And extends BinaryOperation {
    public And(LTLExpression left, LTLExpression right) {
        super("&&", left, right);
    }
}
