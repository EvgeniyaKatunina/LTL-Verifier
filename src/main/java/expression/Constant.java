package expression;

public class Constant extends LTLExpression {

    public static final Constant TRUE = new True();
    public static final Constant FALSE = new False();

    static class True extends Constant {
        public boolean getValue() {
            return true;
        }

        public String toString() {
            return "True";
        }
    }

    static class False extends Constant {
        public boolean getValue() {
            return false;
        }

        public String toString() {
            return "False";
        }
    }
}
