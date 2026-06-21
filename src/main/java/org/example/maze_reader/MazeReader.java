package org.example.maze_reader;

import org.example.data_structures.Graph;
import org.example.data_structures.Queue;
import org.example.exceptions.QueueUnderflowException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class MazeReader {

    public static Graph<MazeCube> readMazeBFS(String fileName) throws IOException, QueueUnderflowException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        byte[] bytes = fileInputStream.readAllBytes();

        List<List<String>> maze = Arrays.stream(new String(bytes).split("\n"))
                .map(line -> Arrays.stream(line.split("")).toList())
                .toList()
                .reversed();

        return getMazeBFS(maze);
    }

    public static Graph<MazeCube> getMazeBFS(List<List<String>> list) throws QueueUnderflowException {
        MazeCube mazeCube = new MazeCube(list.get(0).get(0), new Coordinates(0, 0));
        Queue<Graph.Node<MazeCube>> queueFrontier = new Queue<>();
        Graph.Node<MazeCube> firstNode = new Graph.Node<>(mazeCube);
        queueFrontier.add(firstNode);
        return getMazeBFS(new Graph<>(firstNode),
                list,
                queueFrontier,
                new ArrayList<>(),
                new Coordinates(list.size() - 1, list.get(0).size() - 1));
    }

    private static Graph<MazeCube> getMazeBFS(Graph<MazeCube> graph,
                                              List<List<String>> list,
                                              Queue<Graph.Node<MazeCube>> queue,
                                              List<Graph.Node<MazeCube>> visitedNodes,
                                              Coordinates upperBound) throws QueueUnderflowException {
        if (queue.isEmpty()) return graph;

        Graph.Node<MazeCube> node = queue.remove();
        System.out.printf("-------------------> Visiting Node %s <---------------------\n", node);
        System.out.printf("-------------------> It's children b4 visiting %s <---------------------\n", node.getChildren());
        visitedNodes.add(node);

        List<MazeCube> neighbours = node.getData().getNeighbours(upperBound);
        neighbours.forEach(neighbour -> {
            String value = list.get(neighbour.getCoordinates().getY()).get(neighbour.getCoordinates().getX());
            neighbour.setValue(value);
        });
        List<Graph.Node<MazeCube>> children = neighbours
                .stream()
                .filter(neighbour -> !neighbour.getValue().equals("#"))
                .map(Graph.Node::new)
                .toList();

        System.out.printf("It's found children are %s\n", children);
        System.out.printf("Visited nodes are %s\n", visitedNodes);
        for (Graph.Node<MazeCube> child : children) {
            visitedNodes
                    .stream()
                    .filter(visitedNode -> visitedNode.equals(child))
                    .findFirst()
                    .ifPresentOrElse((visitedChild) -> {
                        System.out.printf("Visited child %s Not adding to queue\n", visitedChild);
                        node.getChildren().add(visitedChild);
                    }, () -> {
                        System.out.printf("Not a Visited child %s Adding to queue\n", child);
                        queue.add(child);
                        node.getChildren().add(child);
                    });
        }
        System.out.printf("Queue %s\n", queue);
        System.out.printf("-------------------> Visited Node %s <---------------------\n", node);
        System.out.printf("-------------------> It's children %s <---------------------\n", node.getChildren());

        return getMazeBFS(graph, list, queue, visitedNodes, upperBound);
    }
}
