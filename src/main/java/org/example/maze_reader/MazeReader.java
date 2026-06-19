package org.example.maze_reader;

import org.example.data_structures.Graph;
import org.example.data_structures.Queue;
import org.example.exceptions.QueueUnderflowException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        MazeCube firstNode = new MazeCube(list.get(0).get(0), new Coordinates(0, 0));
        Queue<Graph.Node<MazeCube>> queueFrontier = new Queue<>();
        queueFrontier.add(new Graph.Node<>(firstNode));
        return getMazeBFS(new Graph<>(new Graph.Node<>(firstNode)),
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


        if (visitedNodes.stream().noneMatch(node::equals)) {
            if (Objects.equals(node.getData().getValue(), "A")) {
                graph.setNode(node);
            }

            visitedNodes.add(node);

            List<MazeCube> neighbours = node.getData().getNeighbours(upperBound);

            neighbours.forEach(neighbour -> {
                String value = list.get(neighbour.getCoordinates().getY()).get(neighbour.getCoordinates().getX());
                neighbour.setValue(value);
            });

            List<Graph.Node<MazeCube>> children = neighbours.stream()
                    .filter(neighbour -> !neighbour.getValue().equals("#"))
                    .map(Graph.Node::new)
                    .toList();

            node.setChildren(children);
            children.forEach(queue::add);

            return getMazeBFS(graph, list, queue, visitedNodes, upperBound);
        }

        return getMazeBFS(graph, list, queue, visitedNodes, upperBound);
    }
}
