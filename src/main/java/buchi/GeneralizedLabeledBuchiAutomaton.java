package buchi;

import expression.*;

import java.util.*;
import java.util.stream.Collectors;

import static util.Util.*;

public class GeneralizedLabeledBuchiAutomaton {
    public final Set<String> atomPropositions;
    public final List<Node> states;
    public final Map<Node, Symbol> labels;
    public final Map<Node, List<Node>> delta;
    public final List<Node> start;
    public final List<Set<Node>> finish;

    public GeneralizedLabeledBuchiAutomaton(Set<String> atomPropositions, List<Node> states, Map<Node, Symbol> labels, Map<Node, List<Node>> delta, List<Node> start, List<Set<Node>> finish) {
        this.atomPropositions = atomPropositions;
        this.states = states;
        this.labels = labels;
        this.delta = delta;
        this.start = start;
        this.finish = finish;
    }

    private static int expandCounter = 0;

    public static GeneralizedLabeledBuchiAutomaton fromLTLExpression(LTLExpression LTLExpression, Set<String> atomPropositions) {
        LTLExpression inNNF = LTLExpression.toNNF(LTLExpression);
        Node init = new Node("init");
        ArrayList<Node> nodes = new ArrayList<>();
        expandCounter = 0;
        expand(setOf(inNNF), Collections.emptySet(), Collections.emptySet(), setOf(init), new NodeNameGenerator(), nodes);
        return fromNodes(inNNF, nodes, init, atomPropositions);
    }

    private static GeneralizedLabeledBuchiAutomaton fromNodes(LTLExpression LTLExpression, List<Node> nodes, Node init, Set<String> atomPropositions) {
        final Set<Variable> atomVars = atomPropositions.stream().map(Variable::new).collect(Collectors.toSet());
        final HashMap<Node, Symbol> labels = new HashMap<>();
        for (Node node : nodes) {
            final Set<LTLExpression> minSet = new HashSet<>(node.now);
            minSet.retainAll(atomVars);
            final Set<LTLExpression> maxSet = atomVars.stream().filter(v -> !node.now.contains(new Negation(v))).collect(Collectors.toSet());
            labels.put(node, new Symbol(minSet, maxSet));
        }

        final HashMap<Node, List<Node>> delta = new HashMap<>();
        for (Node node : nodes) {
            for (Node from : node.incoming) {
                if (from == init) continue;
                delta.merge(from, Collections.singletonList(node), (a, b) -> {
                    final ArrayList<Node> res = new ArrayList<>(a);
                    res.addAll(b);
                    return res;
                });
            }
        }

        final List<Node> start = nodes.stream().filter(n -> n.incoming.contains(init)).collect(Collectors.toList());
        final List<Set<Node>> finish = LTLExpression.generateExpressions(LTLExpression).stream()
                .filter(f -> f instanceof Until)
                .map(f -> nodes.stream()
                        .filter(n -> n.now.contains(((Until) f).right) || !n.now.contains(f))
                        .collect(Collectors.toSet())
                ).collect(Collectors.toList());
        return new GeneralizedLabeledBuchiAutomaton(atomPropositions, nodes, labels, delta, start, finish);
    }

    private static void expand(Set<LTLExpression> curr, Set<LTLExpression> old, Set<LTLExpression> next, Set<Node> incoming,
                               NodeNameGenerator nameGenerator, ArrayList<Node> nodes) {
        expandCounter++;
        if (curr.isEmpty()) {
            Optional<Node> r = nodes.stream().filter(n -> contentEquals(n.next, next) && contentEquals(n.now, old)).findFirst();
            if (r.isPresent()) {
                r.get().incoming.addAll(incoming);
            } else {
                Node q = new Node(nameGenerator.next());
                nodes.add(q);
                q.incoming.addAll(incoming);
                q.now.addAll(old);
                q.next.addAll(next);
                expand(q.next, Collections.emptySet(), Collections.emptySet(), setOf(q), nameGenerator, nodes);
            }
        } else {
            final LTLExpression f = curr.iterator().next();
            final Set<LTLExpression> nCurr = new HashSet<>(curr);
            nCurr.remove(f);
            final Set<LTLExpression> nOld = new HashSet<>(old);
            nOld.add(f);
            if (isBase(f)) {
                if (f == Constant.FALSE || nOld.contains(LTLExpression.negate(f))) return;
                expand(nCurr, nOld, next, incoming, nameGenerator, nodes);
            } else if (f instanceof And) {
                final Set<LTLExpression> additional = setOf(((And) f).left, ((And) f).right);
                additional.removeAll(nOld);
                nCurr.addAll(additional);
                expand(nCurr, nOld, next, incoming, nameGenerator, nodes);
            } else if (f instanceof Next) {
                final Set<LTLExpression> newNext = new HashSet<>(next);
                newNext.add(((Next) f).expression);
                expand(nCurr, nOld, newNext, incoming, nameGenerator, nodes);
            } else if (f instanceof Or || f instanceof Until || f instanceof Release) {
                final Set<LTLExpression> curr1Set = curr1(f);
                curr1Set.removeAll(nOld);
                curr1Set.addAll(nCurr);
                final Set<LTLExpression> newNext = new HashSet<>(next);
                newNext.addAll(next1(f));
                expand(curr1Set, nOld, newNext, incoming, nameGenerator, nodes);
                final Set<LTLExpression> curr2Set = curr2(f);
                curr2Set.removeAll(nOld);
                curr2Set.addAll(nCurr);
                expand(curr2Set, nOld, next, incoming, nameGenerator, nodes);
            } else {
                throw new IllegalArgumentException("Negation in Negative Normal Form");
            }
        }
    }

    private static Set<LTLExpression> curr1(LTLExpression f) {
        if (f instanceof Until) return setOf(((Until) f).left);
        if (f instanceof Release || f instanceof Or) return setOf(((BinaryOperation) f).right);
        throw new IllegalArgumentException("undefined for " + f);
    }

    private static Set<LTLExpression> curr2(LTLExpression f) {
        if (f instanceof Until) return setOf(((Until) f).right);
        if (f instanceof Release) return setOf(((Release) f).left, ((Release) f).right);
        if (f instanceof Or) return setOf(((Or) f).left);
        throw new IllegalArgumentException("undefined for " + f);
    }

    private static Set<LTLExpression> next1(LTLExpression f) {
        if (f instanceof Until || f instanceof Release) return setOf(f);
        if (f instanceof Or) return Collections.emptySet();
        throw new IllegalArgumentException("undefined for " + f);
    }

    private static boolean isBase(LTLExpression f) {
        return f instanceof Constant || f instanceof Variable
                || (f instanceof Negation && ((Negation) f).expression instanceof Variable);
    }

    private static class NodeNameGenerator {
        private int id = 0;

        String next() {
            return "node #" + (++id);
        }
    }
}
