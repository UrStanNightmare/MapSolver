package ru.urstannigtmare.mapsolver.dto;

import java.util.List;
import java.util.Map;

public class MovementData {
    private Map<String, Integer> movementData;
    private List<String> availableTiles;

    public MovementData(Map<String, Integer> movementData, List<String> availableTiles) {
        this.movementData = movementData;
        this.availableTiles = availableTiles;
    }

    public Map<String, Integer> getMovementData() {
        return this.movementData;
    }

    public List<String> getAvailableTiles() {
        return this.availableTiles;
    }

    public String getAvailableTilesString() {
        String tiles = "";
        for (String tile : this.availableTiles) {
            tiles = tiles + tile + ",";
        }

        return tiles.substring(0, tiles.length() - 1);
    }

    public String getRegExpOfTiles() {
        return "[" + this.getAvailableTilesString() + "]";
    }

    public Integer getMovementCostBySymlink(String symlink){
        return this.movementData.get(symlink);
    }
}
