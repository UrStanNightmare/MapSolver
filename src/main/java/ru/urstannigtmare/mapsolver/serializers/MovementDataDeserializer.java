package ru.urstannigtmare.mapsolver.serializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ru.urstannigtmare.mapsolver.dto.MovementData;

import java.io.IOException;
import java.util.*;

public class MovementDataDeserializer extends JsonDeserializer<MovementData> {

    private final static String AVAILABLE_TILES_KEY = "availableTiles";
    private final static String RACE_DATA_KEY = "raceData";

    private String raceToFind;


    @Override
    public MovementData deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        if (this.raceToFind == null) {
            throw new IllegalArgumentException("Race not specified! Can't read world save data!");
        }
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);

        List<String> availableTiles = new LinkedList<>();

        ArrayNode tilesNode = (ArrayNode) node.get(AVAILABLE_TILES_KEY);

        if (tilesNode == null) {
            throw new JsonParseException(p, "Available tiles node not specified!");
        }

        for (JsonNode n : tilesNode) {
            availableTiles.add(n.textValue());
        }

        JsonNode racesNode = node.get(RACE_DATA_KEY);
        if (racesNode == null) {
            throw new JsonParseException(p, "Races movement data not specified!");
        }

        ArrayNode movementNode = (ArrayNode) racesNode.get(this.raceToFind);
        if (movementNode == null){
            throw new JsonParseException(p, this.raceToFind + " not specified in map save data!");
        }


        List<Integer> movementCosts = new LinkedList<>();
        for (JsonNode n : movementNode) {
            movementCosts.add(n.intValue());
        }

        if (availableTiles.size() != movementCosts.size()) {
            throw new IllegalArgumentException("Tiles size does not match movement costs size!");
        }

        Map<String, Integer> movementMap = new HashMap<>(availableTiles.size());

        for (int i = 0; i < availableTiles.size(); i++) {
            movementMap.put(availableTiles.get(i), movementCosts.get(i));
        }

        return new MovementData(movementMap, new ArrayList<>(availableTiles));
    }

    public void setRaceToFind(String raceToFind) {
        this.raceToFind = raceToFind;
    }
}
