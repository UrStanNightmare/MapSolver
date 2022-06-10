package ru.urstannigtmare.mapsolver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SolutionTest {
    private final static String HUMAN_CLASS_NAME = "Human";

    private final static String EXAMPLE_MAP_FOR_HUMAN =
            "STWS" +
            "WTPP" +
            "TPTT" +
            "PWPP";

    private final static String SNAKE_MAP_MAP_FOR_HUMAN =
                    "SPPP" +
                    "SSSP" +
                    "PPPP" +
                    "PPPS";

    private final static String EXPECTED_MAP_FOR_HUMAN =
                "SPPPS"+
                "SSSPS"+
                "SSPPS"+
                "SSPSS"+
                "SSPPP";

    private final static String LONG_EXPECTED_MAP_FOR_HUMAN =
                    "SPPPS"+
                    "SSSPS"+
                    "SPPPS"+
                    "SPSSS"+
                    "SPPPP";

    @Test
    void testDefaultInputResultForHuman() {
        int weight = Solution.getResult(EXAMPLE_MAP_FOR_HUMAN, HUMAN_CLASS_NAME);

        assertEquals(10, weight);
    }

    @Test
    void testSnakeInputResultForHuman() {
        int weight = Solution.getResult(SNAKE_MAP_MAP_FOR_HUMAN, HUMAN_CLASS_NAME);

        assertEquals(10, weight);
    }

    @Test
    void testExpectedInputResultForHuman(){
        int weight = Solution.getResult(EXPECTED_MAP_FOR_HUMAN, HUMAN_CLASS_NAME);

        assertEquals(10, weight);
    }

    @Test
    void testLongExpectedInputResultForHuman(){
        int weight = Solution.getResult(LONG_EXPECTED_MAP_FOR_HUMAN, HUMAN_CLASS_NAME);

        assertEquals(12, weight);
    }

    @Test
    void testWrongMapSizeArgument(){
        assertThrows(RuntimeException.class, ()->{
            Solution.getResult("TTT", HUMAN_CLASS_NAME);
        });
    }

    @Test
    void testNullRaceArgument(){
        assertThrows(RuntimeException.class, ()->{
            Solution.getResult(EXAMPLE_MAP_FOR_HUMAN, null);
        });
    }

    @Test
    void testNullTilesDataArgument(){
        assertThrows(RuntimeException.class, ()->{
            Solution.getResult(null, HUMAN_CLASS_NAME);
        });
    }
}