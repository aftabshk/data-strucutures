package org.example.data_structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Graph<T> {

    private final Node<T> node;

    public Graph(Node<T> node) {
        this.node = node;
    }

    public LinkedList<T> dfs(String goal) {
        return dfs(this.node, goal, new LinkedList<>(), new ArrayList<>());
    }

    private LinkedList<T> dfs(Node<T> currentNode, String goal, LinkedList<T> path, List<Node<T>> exploredNodes) {
        if (isGoal(currentNode, goal)) {
            path.appendFirst(currentNode.getData());
            return path;
        }
        exploredNodes.add(currentNode);

        List<Node<T>> children = currentNode.getChildren();

        for (Node<T> child : children) {
            if (!isExploredNode(exploredNodes, child)) {
                LinkedList<T> resultPath = dfs(child, goal, path, exploredNodes);

                if (!resultPath.isEmpty()) {
                    path.appendFirst(currentNode.getData());
                    return path;
                }
            }
        }

        return new LinkedList<>();
    }

    private boolean isGoal(Node<T> currentNode, String goal) {
        return currentNode.getData().equals(goal);
    }

    private boolean isExploredNode(List<Node<T>> exploredNodes, Node currentNode) {
        return exploredNodes.stream().anyMatch(n -> currentNode.getData().equals(n.getData()));
    }

    public static class Node<T> {

        private T data;
        private List<Node<T>> children;

        public Node(T data) {
            this.data = data;
            this.children = List.of();
        }

        public Node(T data, List<Node<T>> children) {
            this.data = data;
            this.children = children;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public List<Node<T>> getChildren() {
            return children;
        }

        public void setChildren(List<Node<T>> children) {
            this.children = children;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(data, node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }

    public static void main(String[] args) {

        /*** Normal Test1 */
//        Graph.Node terminalNodeD = new Graph.Node("D");
//        Graph.Node terminalNodeF = new Graph.Node("F");
//        Graph.Node terminalNodeE = new Graph.Node("E");
//        Graph.Node terminalNodeG = new Graph.Node("G");
//        Graph.Node nodeB = new Graph.Node("B", List.of(terminalNodeD));
//        Graph.Node nodeC = new Graph.Node("C", List.of(terminalNodeF, terminalNodeE, terminalNodeG));
//        Graph.Node nodeA = new Graph.Node("A", List.of(nodeB, nodeC));
//        Graph graph = new Graph(nodeA);
//
//        List<String> path = graph.dfs("G");
//
//        System.out.println(path);

        /* Cycle Test1 */
        Graph.Node<String> terminalNodeD = new Graph.Node<>("D");
        Graph.Node<String> terminalNodeF = new Graph.Node<>("F");
        Graph.Node<String> terminalNodeE = new Graph.Node<>("E");
        Graph.Node<String> terminalNodeG = new Graph.Node<>("G");
        Graph.Node<String> nodeB = new Graph.Node<>("B", List.of(terminalNodeD));
        Graph.Node<String> nodeC = new Graph.Node<>("C", List.of(terminalNodeF, terminalNodeE, terminalNodeG));
        Graph.Node<String> nodeA = new Graph.Node<>("A", new ArrayList<>());
        nodeA.getChildren().add(nodeA);
        nodeA.getChildren().add(nodeB);
        nodeA.getChildren().add(nodeC);
        Graph<String> graph = new Graph<>(nodeA);

        LinkedList<String> path = graph.dfs("G");

        System.out.println(path);

    }
}
