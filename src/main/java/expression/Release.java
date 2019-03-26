package expression;

public class Release extends BinaryOperation {
    public Release(LTLExpression left, LTLExpression right) {
        super("R", left, right);
    }
}
