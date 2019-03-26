package expression;

import java.util.Objects;

public class BinaryOperation extends LTLExpression {
    public final String name;
    public final LTLExpression left;
    public final LTLExpression right;

    BinaryOperation(String name, LTLExpression left, LTLExpression right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left + ") " + name + " (" + right + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BinaryOperation)) {
            return false;
        }
        BinaryOperation binaryOperation = (BinaryOperation) o;
        return Objects.equals(name, binaryOperation.name) && Objects.equals(left, binaryOperation.left) && Objects.equals(right, binaryOperation.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, left, right);
    }
}
