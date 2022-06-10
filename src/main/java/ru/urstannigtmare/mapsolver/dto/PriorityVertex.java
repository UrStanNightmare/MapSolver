package ru.urstannigtmare.mapsolver.dto;

import java.util.Objects;

public class PriorityVertex implements Comparable<PriorityVertex> {
    private int index;
    private int priority;

    public PriorityVertex(int index, int priority) {
        this.index = index;
        this.priority = priority;
    }

    public int getIndex() {
        return this.index;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public int compareTo(PriorityVertex o) {
        return Integer.compare(this.priority, o.getPriority());
    }

    @Override
    public String toString() {
        return "PriorityVertex{" +
                "index=" + index +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriorityVertex that = (PriorityVertex) o;
        return this.index == that.index && this.priority == that.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.index, this.priority);
    }
}
