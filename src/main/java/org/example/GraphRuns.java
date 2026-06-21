package org.example;

import org.example.data_structures.Graph;
import org.example.data_structures.LinkedList;
import org.example.exceptions.QueueUnderflowException;
import org.example.functional_interfaces.MazeHeuristicFunction;
import org.example.maze_reader.Coordinates;
import org.example.maze_reader.MazeCube;
import org.example.maze_reader.MazeReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphRuns {

    //  Run DFS
    public static void main1(String[] args) {

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

    //  Solve Maze with DFS
    public static void main2(String[] args) throws IOException, QueueUnderflowException {
        Graph<MazeCube> mazeCubeGraph = MazeReader.readMazeBFS("/Users/aftab.shaikh/personal/data-strucutures/src/main/resources/maze/maze2.txt");

        System.out.println(mazeCubeGraph.dfs(new MazeCube("B", new Coordinates(1, 1))));
    }

    //  Solve Maze with GFS
    public static void main(String[] args) throws IOException, QueueUnderflowException {
        Graph<MazeCube> mazeCubeGraph = MazeReader.readMazeBFS("/Users/aftab.shaikh/personal/data-strucutures/src/main/resources/maze/maze2.txt");

        System.out.println(mazeCubeGraph.gfs(new MazeCube("B", new Coordinates(1, 1)), new MazeHeuristicFunction()));
//        traverse(mazeCubeGraph.getNode(), new ArrayList<>());
    }

    public static void traverse(Graph.Node<MazeCube> node, List<Graph.Node<MazeCube>> traversedNodes) {
        if (traversedNodes.stream().noneMatch((traversedNode) -> traversedNode.equals(node))) {
            for (Graph.Node<MazeCube> child: node.getChildren()) {
                System.out.printf("---------> %s\n", child.getData().getCoordinates());
            }

            traversedNodes.add(node);
            for (Graph.Node<MazeCube> child: node.getChildren()) {
                traverse(child, traversedNodes);
            }
        }
    }
}
