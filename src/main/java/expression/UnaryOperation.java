package expression;

import java.util.Objects;

public class UnaryOperation extends LTLExpression {
    public final String name;
    public final LTLExpression expression;

    UnaryOperation(String name, LTLExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return name + " (" + expression.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnaryOperation)) {
            return false;
        }
        UnaryOperation unaryOperation = (UnaryOperation) o;
        return Objects.equals(name, unaryOperation.name) && Objects.equals(expression, unaryOperation.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expression);
    }
}
