package org.example.maze_reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coordinates {
    private Integer x;
    private Integer y;

    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public List<Coordinates> getNeighbours(Coordinates upperBound) {
        List<Coordinates> result = new ArrayList<>();

        if ((this.x + 1) <= upperBound.getX()) {
            result.add(new Coordinates(this.x + 1, this.y));
        }
        if ((this.y + 1) <= upperBound.getY()) {
            result.add(new Coordinates(this.x, this.y + 1));
        }
        if ((this.x - 1) >= 0) {
            result.add(new Coordinates(this.x - 1, this.y));
        }
        if ((this.y - 1) >= 0) {
            result.add(new Coordinates(this.x, this.y - 1));
        }

        return result;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}