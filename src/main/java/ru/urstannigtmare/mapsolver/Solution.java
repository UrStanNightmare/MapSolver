package ru.urstannigtmare.mapsolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.urstannigtmare.mapsolver.data.GraphAdjacencyListHolder;
import ru.urstannigtmare.mapsolver.data.TileDataHolder;

/**
 * Класс для поиска минимальной стоимости пути.
 * Оставил всё создание вспомогательных сущностей в методе getResult(),
 * с учетом того, что предполагаю, что программа будет тестироваться именно по этому ментоду.
 * В инном случае сделал бы немного иначе, а в методе getResult() оставил бы создание только необходимых
 * для рассчета сущностей, используя уже заранее созданные с хранимыми данными карты и т.д.
 */
public class Solution {
    private static final Logger log = LoggerFactory.getLogger(Solution.class.getName());

    private Solution(){}

    /**
     * Нахождение минимальной стоимости перемещения из левого верхнего угла поля до правого нижнего угла.
     * Работает с картами размера n^2
     * @param tilesData строка тайлов поля карты. Длинна должна быть равна n^2 тайлам.
     * @param race раса которая проходит карту.
     * @return минимальная стоимость перемещения.
     */
    public static int getResult(String tilesData, String race) {

        int result = -1;

        if (tilesData == null || race == null){
            throw new IllegalArgumentException("Input data can't be null!");
        }

        try {
            TileDataHolder tileDataHolder = new TileDataHolder(tilesData, race);

            GraphAdjacencyListHolder graphAdjacencyListHolder = new GraphAdjacencyListHolder(tileDataHolder);

            PathFinder pathFinder = new PathFinder(graphAdjacencyListHolder);
            result = pathFinder.findShortestWayFromLeftTopCornerToRightBottom();


        } catch (RuntimeException exception) {
            log.error("{}", exception.getMessage());
            throw new RuntimeException(exception);
        }

        return result;
    }
}
