package ru.urstannigtmare.mapsolver.data;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphAdjacencyListHolderTest {
    GraphAdjacencyListHolder holder = new GraphAdjacencyListHolder(new TileDataHolder("SSSS", "Human"));

    @Test
    void testGetVertexConnectionsList() {
        List<LinkData> vertexConnectionsList = holder.getVertexConnectionsList(0);

        assertEquals(2, vertexConnectionsList.size());
    }

    @Test
    void getBottomRightCornerVertexIndex() {
        int bottomRightCornerVertexIndex = holder.getBottomRightCornerVertexIndex();

        assertEquals(3, bottomRightCornerVertexIndex);
    }

    @Test
    void heuristic() {
        int heuristic = holder.heuristic(0, 3);

        assertEquals(2, heuristic);
    }
}