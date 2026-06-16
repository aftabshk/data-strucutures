package org.example.data_structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Graph {

    private Node node;

    public Graph(Node node) {
        this.node = node;
    }

    public LinkedList<String> dfs(String goal) {
        return dfs(this.node, goal, new LinkedList<>(), new ArrayList<>());
    }

    private LinkedList<String> dfs(Node currentNode, String goal, LinkedList<String> path, List<Node> exploredNodes) {
        if (isGoal(currentNode, goal)) {
            path.appendFirst(currentNode.getData());
            return path;
        }
        exploredNodes.add(currentNode);

        List<Node> children = currentNode.getChildren();

        for (Node child : children) {
            if (!isExploredNode(exploredNodes, child)) {
                LinkedList<String> resultPath = dfs(child, goal, path, exploredNodes);

                if (!resultPath.isEmpty()) {
                    path.appendFirst(currentNode.getData());
                    return path;
                }
            }
        }

        return new LinkedList<>();
    }

    private static boolean isGoal(Node currentNode, String goal) {
        return currentNode.getData().equals(goal);
    }

    private static boolean isExploredNode(List<Node> exploredNodes, Node currentNode) {
        return exploredNodes.stream().anyMatch(n -> currentNode.getData().equals(n.getData()));
    }

    public static class Node {

        private String data;
        private List<Node> children;

        public Node(String data) {
            this.data = data;
            this.children = List.of();
        }

        public Node(String data, List<Node> children) {
            this.data = data;
            this.children = children;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
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
        Graph.Node terminalNodeD = new Graph.Node("D");
        Graph.Node terminalNodeF = new Graph.Node("F");
        Graph.Node terminalNodeE = new Graph.Node("E");
        Graph.Node terminalNodeG = new Graph.Node("G");
        Graph.Node nodeB = new Graph.Node("B", List.of(terminalNodeD));
        Graph.Node nodeC = new Graph.Node("C", List.of(terminalNodeF, terminalNodeE, terminalNodeG));
        Graph.Node nodeA = new Graph.Node("A", new ArrayList<>());
        nodeA.getChildren().add(nodeA);
        nodeA.getChildren().add(nodeB);
        nodeA.getChildren().add(nodeC);
        Graph graph = new Graph(nodeA);

        LinkedList<String> path = graph.dfs("G");

        System.out.println(path);

    }
}
