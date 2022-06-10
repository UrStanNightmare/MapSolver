package ru.urstannigtmare.mapsolver.data;

import java.util.Objects;

public class LinkData {
    private int vertexNumber;
    private int movementCost;

    public LinkData(int vertexNumber, int movementCost) {
        this.vertexNumber = vertexNumber;
        this.movementCost = movementCost;
    }

    public int getVertexNumber() {
        return vertexNumber;
    }

    public int getMovementCost() {
        return movementCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        LinkData linkData = (LinkData) o;
        return vertexNumber == linkData.vertexNumber && movementCost == linkData.movementCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexNumber, movementCost);
    }

    @Override
    public String toString() {
        return "{" +
                "connectsWith: " + vertexNumber +
                ", costs=" + movementCost +
                '}';
    }
}
