package buchi;

import expression.LTLExpression;

import java.util.HashSet;
import java.util.Set;

public class Node {
    public final String name;
    public final Set<Node> incoming;
    public final Set<LTLExpression> now;
    public final Set<LTLExpression> next;

    public Node(String name) {
        this.name = name;
        this.incoming = new HashSet<>();
        this.now = new HashSet<>();
        this.next = new HashSet<>();
    }

    public Node(Node other) {
        this.name = other.name;
        this.incoming = other.incoming;
        this.now = other.now;
        this.next = other.next;
    }

    @Override
    public String toString() {
        return "State{name=" + name + "}";
    }
}
