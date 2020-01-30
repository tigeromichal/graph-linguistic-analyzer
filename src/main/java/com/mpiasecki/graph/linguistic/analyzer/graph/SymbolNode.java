package com.mpiasecki.graph.linguistic.analyzer.graph;

import java.util.Optional;
import java.util.Stack;

public class SymbolNode extends Node {

    private final Character value;

    public SymbolNode(Character value) {
        super();
        this.value = value;
    }

    public SymbolNode(Character value, Boolean leadsToEnd) {
        super(leadsToEnd);
        this.value = value;
    }

    @Override
    Optional<Node> findValue(Character value, Stack<Node> path) {
        return this.value.equals(value) ? Optional.of(this) : Optional.empty();
    }
}
