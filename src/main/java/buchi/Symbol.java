package buchi;

import expression.LTLExpression;
import util.Util;

import java.util.Set;

public class Symbol {
    public final Set<LTLExpression> min;
    public final Set<LTLExpression> max;

    public Symbol(Set<LTLExpression> min, Set<LTLExpression> max) {
        this.min = min;
        this.max = max;
    }

    public Symbol(Set<LTLExpression> minmax) {
        this(minmax, minmax);
    }

    public Symbol(LTLExpression f) {
        this(Util.setOf(f));
    }

    public boolean subsetOf(Symbol other) {
        return min.containsAll(other.min) && other.max.containsAll(max);
    }

    @Override
    public String toString() {
        if (min == max) return "Symbol: [" + min + "]";
        return "Symbol: [" + min + "," + max + "]";
    }
}
