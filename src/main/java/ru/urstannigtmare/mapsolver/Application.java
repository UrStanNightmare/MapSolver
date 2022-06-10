package ru.urstannigtmare.mapsolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class.getName());

    public static void main(String[] args) {
        log.info("Application start.");

        String mapString =
                        "SWWS" +
                        "TTPP" +
                        "TPTT" +
                        "PWPP";
        String race = "Human";

        try {
            log.info("The shortest way cost is {}", Solution.getResult(mapString, race));
        } catch (RuntimeException exception) {
            log.error("{}", exception.getMessage(), exception);
        }

        log.info("Application end.");
    }
}
