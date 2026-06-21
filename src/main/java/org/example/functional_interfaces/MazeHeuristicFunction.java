package org.example.functional_interfaces;

import org.example.maze_reader.MazeCube;

public class MazeHeuristicFunction implements HeuristicFunction<MazeCube> {

    @Override
    public Integer estimate(MazeCube goal, MazeCube node) {
        int goalSum = goal.getCoordinates().getX() + goal.getCoordinates().getY();
        int nodeSum = node.getCoordinates().getX() + node.getCoordinates().getY();
        return Math.abs(goalSum - nodeSum);
    }
}
