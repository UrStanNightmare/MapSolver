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
        return this.vertexNumber;
    }

    public int getMovementCost() {
        return this.movementCost;
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
        return this.vertexNumber == linkData.vertexNumber && this.movementCost == linkData.movementCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.vertexNumber, this.movementCost);
    }

    @Override
    public String toString() {
        return "{" +
                "connectsWith: " + this.vertexNumber +
                ", costs=" + this.movementCost +
                '}';
    }
}
