package org.example.data_structures;

import org.example.functional_interfaces.HeuristicFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Graph<T> {

    private Node<T> node;

    public Graph(Node<T> node) {
        this.node = node;
    }

    public void setNode(Node<T> node) {
        this.node = node;
    }

    public Node<T> getNode() {
        return node;
    }

    public LinkedList<T> dfs(T goal) {
        return dfs(this.node, goal, new LinkedList<>(), new ArrayList<>());
    }

    private LinkedList<T> dfs(Node<T> currentNode, T goal, LinkedList<T> path, List<Node<T>> exploredNodes) {
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

    public LinkedList<T> gfs(T goal, HeuristicFunction<T> heuristicFunction) {
        List<Node<T>> cachedNodes = new ArrayList<>();
        cachedNodes.add(this.node);
        return gfs(this.node, goal, heuristicFunction, new LinkedList<>(), new ArrayList<>(), cachedNodes);
    }

    public LinkedList<T> gfs(Node<T> currentNode,
                             T goal,
                             HeuristicFunction<T> heuristicFunction,
                             LinkedList<T> path,
                             List<Node<T>> exploredNodes,
                             List<Node<T>> cachedNodes
    ) {
        if (isGoal(currentNode, goal)) {
            path.appendFirst(currentNode.getData());
            return path;
        }

        if (cachedNodes.isEmpty()) return null;

        List<Node<T>> notExploredNodes = currentNode.getChildren().stream().filter(n -> !isExploredNode(exploredNodes, n)).toList();
        cachedNodes.addAll(notExploredNodes);

        Integer distance = Integer.MAX_VALUE;
        int minDistanceIndex = -1;
        for (int i = cachedNodes.size() - 1; i >= 0; i--) {
            Node<T> tNode = cachedNodes.get(i);

            Integer currentDistance = heuristicFunction.estimate(goal, tNode.getData());

            if (currentDistance < distance) {
                minDistanceIndex = i;
                distance = currentDistance;
            }
        }

        Node<T> nextNode = cachedNodes.remove(minDistanceIndex);

        exploredNodes.add(nextNode);

        LinkedList<T> resultPath = gfs(nextNode, goal, heuristicFunction, path, exploredNodes, cachedNodes);

        if (!resultPath.isEmpty()) {
            resultPath.appendFirst(currentNode.getData());
            return resultPath;
        }

        return new LinkedList<>();
    }

    private boolean isGoal(Node<T> currentNode, T goal) {
        return currentNode.getData().equals(goal);
    }

    private boolean isExploredNode(List<Node<T>> exploredNodes, Node currentNode) {
        return exploredNodes.stream().anyMatch(n -> currentNode.getData().equals(n.getData()));
    }

    public static class Node<T extends Object> {

        private T data;
        private List<Node<T>> children;

        public Node(T data) {
            this.data = data;
            this.children = new ArrayList<>();
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

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }
}
