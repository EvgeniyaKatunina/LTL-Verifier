package expression;

public class Future extends UnaryOperation {
    public Future(LTLExpression expression) {
        super("F", expression);
    }
}
