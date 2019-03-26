package expression;

public class Or extends BinaryOperation {
    public Or(LTLExpression left, LTLExpression right) {
        super("||", left, right);
    }
}
