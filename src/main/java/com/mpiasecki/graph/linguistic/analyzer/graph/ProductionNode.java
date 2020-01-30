package com.mpiasecki.graph.linguistic.analyzer.graph;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class ProductionNode extends Node {

    private final List<Node> internalNodes;

    public ProductionNode(List<Node> internalNodes) {
        super();
        this.internalNodes = Collections.unmodifiableList(internalNodes);
    }

    public ProductionNode(List<Node> internalNodes, Boolean leadsToEnd) {
        super(leadsToEnd);
        this.internalNodes = Collections.unmodifiableList(internalNodes);
    }

    @Override
    Optional<Node> findValue(Character value, Stack<Node> path) {
        final Optional<Optional<Node>> result = internalNodes
                .stream()
                .map(productionNode -> productionNode.findValue(value, path))
                .filter(Optional::isPresent)
                .findFirst();

        if (result.isPresent()) {
            path.push(this);
            return result.get();
        }

        return Optional.empty();
    }
}
