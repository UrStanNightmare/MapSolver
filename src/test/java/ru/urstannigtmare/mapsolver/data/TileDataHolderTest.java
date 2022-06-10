package ru.urstannigtmare.mapsolver.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileDataHolderTest {
    private final TileDataHolder humanHolder = new TileDataHolder("SSSS", "Human");
    private final TileDataHolder swamperHolder = new TileDataHolder("SSSS", "Swamper");

    @Test
    void getTileTransferValueByIndexForHuman() {
        int price = humanHolder.getTileTransferValueByIndex(0);

        assertEquals(5, price);
    }

    @Test
    void testGetDimension() {
        int dimension = humanHolder.getDimension();

        assertEquals(2, dimension);
    }

    @Test
    void getTileTransferValueByIndexForSwamper() {
        int price = swamperHolder.getTileTransferValueByIndex(0);

        assertEquals(2, price);
    }
}