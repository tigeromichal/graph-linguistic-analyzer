package com.mpiasecki.graph.linguistic.analyzer;

import com.mpiasecki.graph.linguistic.analyzer.graph.Node;
import com.mpiasecki.graph.linguistic.analyzer.graph.ProductionNode;
import com.mpiasecki.graph.linguistic.analyzer.graph.SymbolNode;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        Node root = new SymbolNode(Character.MIN_VALUE);
        Node node2 = new SymbolNode(';', true);
        Node node3 = new SymbolNode('(');

        Node node5 = new SymbolNode(')', true);

        Node node6 = new SymbolNode('0', true);
        Node node7 = new SymbolNode('.');
        Node node8 = new SymbolNode('1', true);
        Node node9 = new SymbolNode('*');

        Node node1 = new ProductionNode(List.of(node3, node6));
        Node node4 = new ProductionNode(List.of(node3, node6));

        root.setNextNodes(node1);
        node1.setNextNodes(node2);
        node2.setNextNodes(node1);
        node3.setNextNodes(node4);
        node4.setNextNodes(node5);
        node5.setNextNodes(node9);
        node6.setNextNodes(node6, node7, node9);
        node7.setNextNodes(node8);
        node8.setNextNodes(node8, node9);
        node9.setNextNodes(node3, node6);

        boolean found = root.findNextNodeWithValue("0;0*((0.1111*0));");
        System.out.print(found ? "correct" : "incorrect");
    }
}
