package expression;

public class Negation extends UnaryOperation {
    public Negation(LTLExpression expression) {
        super("!", expression);
    }
}
