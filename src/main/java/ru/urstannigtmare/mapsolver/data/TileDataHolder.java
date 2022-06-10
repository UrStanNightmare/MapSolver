package ru.urstannigtmare.mapsolver.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.urstannigtmare.mapsolver.dto.MovementData;
import ru.urstannigtmare.mapsolver.utils.ConfigFileReader;

/**
 * Класс для хранения данных о карте, таких как стоимость перемещения, размерность и т.д.
 */
public class TileDataHolder {
    private final static Logger log = LoggerFactory.getLogger(TileDataHolder.class.getName());

    private final MovementData movementData;
    private final String tilesString;

    private int dimension;

    public TileDataHolder(String mapString, String race) {
        this.checkInputData(mapString, race);

        ConfigFileReader fileReader = new ConfigFileReader(race, mapString);
        this.movementData = fileReader.getMovementData();

        this.tilesString = mapString;

        log.info("Map size is {}x{}", dimension, dimension);


    }

    private void checkInputData(String tilesData, String raceString) throws IllegalArgumentException {
        if (raceString == null || raceString.isBlank()) {
            throw new IllegalArgumentException("Wrong race input data!");
        }

        if (tilesData == null) {
            throw new IllegalArgumentException("Wrong tile data! Can't be null!!!");
        }

        int totalLength = tilesData.length();
        if (totalLength == 0) {
            throw new IllegalArgumentException("Tile data can't be empty!");
        }

        double dimensionRoot = Math.sqrt(totalLength);


        if (dimensionRoot - Math.floor(dimensionRoot) != 0d) {
            throw new IllegalArgumentException("Wrong tiles count! Should be n^2 values!!!");
        }

        this.dimension = (int) dimensionRoot;
    }

    /**
     * Возвращает симлинк конкретного тайла по индексу в строке карты.
     * @param index индекс тайла в строке карты.
     * @return симлинк тайла.
     */
    private String getTileSymLinkByIndex(int index) {
        return tilesString.substring(index, index + 1);
    }

    /**
     * Возвращает стоимость перемещения в указанный по индексу тайл.
     * @param index индекс тайла в который будет происходить перемещение.
     * @return стоимость перемещения в указанный тайл.
     */
    public int getTileTransferValueByIndex(int index) {
        String symlink = this.getTileSymLinkByIndex(index);

        return this.movementData.getMovementCostBySymlink(symlink);
    }

    public int getDimension() {
        return this.dimension;
    }
}
