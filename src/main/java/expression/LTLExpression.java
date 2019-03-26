package expression;

import java.util.HashSet;
import java.util.Set;

public class LTLExpression {

    public static LTLExpression negate(LTLExpression e) {
        if (e == Constant.FALSE) {
            return Constant.TRUE;
        }
        if (e == Constant.TRUE) {
            return Constant.FALSE;
        }
        if (e instanceof Negation) {
            return ((Negation) e).expression;
        }
        return new Negation(e);
    }

    public static LTLExpression toNNF(LTLExpression e) {
        if (e instanceof Negation) {
            final LTLExpression sub = ((Negation) e).expression;
            if (sub == Constant.TRUE) {
                return Constant.FALSE;
            }
            if (sub == Constant.FALSE) {
                return Constant.TRUE;
            }
            if (sub instanceof Variable) {
                return e;
            }
            if (sub instanceof Negation) {
                return toNNF(((Negation) sub).expression);
            }
            if (sub instanceof And) {
                return new Or(toNNF(new Negation(((And) sub).left)), toNNF(new Negation(((And) sub).right)));
            }
            if (sub instanceof Or) {
                return new And(toNNF(new Negation(((Or) sub).left)), toNNF(new Negation(((Or) sub).right)));
            }
            if (sub instanceof Next) {
                return toNNF(new Next(new Negation(((Next) sub).expression)));
            }
            if (sub instanceof Future) {
                return toNNF(new Negation(new Until(Constant.TRUE, ((Future) sub).expression)));
            }
            if (sub instanceof Globally) {
                return toNNF(new Negation(new Release(Constant.FALSE, ((Globally) sub).expression)));
            }
            if (sub instanceof Until) {
                return new Release(toNNF(new Negation(((Until) sub).left)), toNNF(new Negation(((Until) sub).right)));
            }
            if (sub instanceof Release) {
                return new Until(toNNF(new Negation(((Release) sub).left)), toNNF(new Negation(((Release) sub).right)));
            }
        }

        if (e instanceof Variable) {
            return e;
        }
        if (e instanceof Constant) {
            return e;
        }
        if (e instanceof And) {
            return new And(toNNF(((And) e).left), toNNF(((And) e).right));
        }
        if (e instanceof Or) {
            return new Or(toNNF(((Or) e).left), toNNF(((Or) e).right));
        }
        if (e instanceof Next) {
            return new Next(toNNF(((Next) e).expression));
        }
        if (e instanceof Future) {
            return toNNF(new Until(Constant.TRUE, ((Future) e).expression));
        }
        if (e instanceof Globally) {
            return toNNF(new Release(Constant.FALSE, ((Globally) e).expression));
        }
        if (e instanceof Until) {
            return new Until(toNNF(((Until) e).left), toNNF(((Until) e).right));
        }
        if (e instanceof Release) {
            return new Release(toNNF(((Release) e).left), toNNF(((Release) e).right));
        }

        throw new IllegalStateException();
    }

    public static Set<LTLExpression> generateExpressions(LTLExpression e) {
        return new ExpressionsGenerator().generate(e).expressions;
    }

    private static class ExpressionsGenerator {
        final Set<LTLExpression> expressions = new HashSet<>();

        ExpressionsGenerator() {
            expressions.add(Constant.FALSE);
            expressions.add(Constant.TRUE);
        }

        ExpressionsGenerator generate(LTLExpression e) {
            expressions.add(e);
            expressions.add(toNNF(negate(e)));
            if (e instanceof UnaryOperation) {
                generate(((UnaryOperation) e).expression);
            } else if (e instanceof BinaryOperation) {
                generate(((BinaryOperation) e).left);
                generate(((BinaryOperation) e).right);
            }
            return this;
        }
    }
}
