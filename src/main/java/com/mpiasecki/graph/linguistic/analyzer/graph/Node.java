package com.mpiasecki.graph.linguistic.analyzer.graph;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

abstract public class Node {

    private final Boolean leadsToEnd;

    private List<Node> nextNodes;

    Node() {
        this.leadsToEnd = false;
    }

    Node(Boolean leadsToEnd) {
        this.leadsToEnd = leadsToEnd;
    }

    abstract Optional<Node> findValue(Character value, Stack<Node> path);

    public void setNextNodes(final Node... nextNodes) {
        this.nextNodes = Collections.unmodifiableList(List.of(nextNodes));
    }

    public boolean findNextNodeWithValue(String input) {
        return findNextNodeWithValue(input, 0, new Stack<>());
    }

    private boolean findNextNodeWithValue(String input, int index, Stack<Node> path) {
        if (index == input.length()) {
            return leadsToEnd;
        }

        final Optional<Optional<Node>> result = nextNodes
                .stream()
                .map(nextNode -> nextNode.findValue(input.charAt(index), path))
                .filter(Optional::isPresent)
                .findFirst();

        if (result.isPresent()) {
            return result.get().get().findNextNodeWithValue(input, index + 1, path);
        }

        if (leadsToEnd && !path.empty()) {
            final Node nodeFromStack = path.pop();
            if (nodeFromStack.findNextNodeWithValue(input, index, path)) {
                return true;
            } else {
                path.push(nodeFromStack);
                return false;
            }
        }

        return false;
    }
}
