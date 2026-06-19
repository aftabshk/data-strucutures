package org.example.maze_reader;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MazeCube {

    private String value;
    private Coordinates coordinates;

    public MazeCube(String value, Coordinates coordinates) {
        this.value = value;
        this.coordinates = coordinates;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<MazeCube> getNeighbours(Coordinates upperBound) {
        List<Coordinates> neighbours = this.coordinates.getNeighbours(upperBound);
        return neighbours.stream().map(coord -> new MazeCube("", coord)).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeCube mazeCube = (MazeCube) o;
        return Objects.equals(coordinates, mazeCube.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }

    @Override
    public String toString() {
        return "MazeCube{" +
                "value='" + value + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}