package ru.urstannigtmare.mapsolver.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.urstannigtmare.mapsolver.dto.MovementData;
import ru.urstannigtmare.mapsolver.serializers.MovementDataDeserializer;

import java.io.IOException;
import java.nio.file.Paths;

public class ConfigFileReader {
    private final static Logger log = LoggerFactory.getLogger(ConfigFileReader.class.getName());
    private static final String CONFIG_FILE_NAME = "map.json";

    private MovementData movementData;

    /**
     * Класс для чтения данных о карте из .json файла.
     * @param race раса для чтения конкретных данных указанной расы.
     * @param tiles карта в виде текстовой строки для анализа на корректность.
     * @throws RuntimeException в случае различных ошибок чтения и проверок.
     */
    public ConfigFileReader(String race, String tiles) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        MovementDataDeserializer deserializer = new MovementDataDeserializer();
        deserializer.setRaceToFind(race);

        module.addDeserializer(MovementData.class, deserializer);
        mapper.registerModule(module);

        try {
            this.movementData = mapper.readValue(Paths.get(CONFIG_FILE_NAME).toFile(), MovementData.class);

            String filteredInputString = tiles.replaceAll(this.movementData.getRegExpOfTiles(), "");
            if (!filteredInputString.isBlank()){
                throw new IllegalArgumentException("Incorrect input tiles arguments: " + filteredInputString + "!!!");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MovementData getMovementData() {
        return this.movementData;
    }
}
