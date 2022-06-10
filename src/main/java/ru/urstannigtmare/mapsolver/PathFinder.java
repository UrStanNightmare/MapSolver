package ru.urstannigtmare.mapsolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.urstannigtmare.mapsolver.data.GraphAdjacencyListHolder;
import ru.urstannigtmare.mapsolver.data.LinkData;
import ru.urstannigtmare.mapsolver.dto.PriorityVertex;

import java.util.*;

public class PathFinder {
    private final static Logger log = LoggerFactory.getLogger(PathFinder.class.getName());

    private final static int LEFT_TOP_CORNER_VERTEX_INDEX = 0;

    private final GraphAdjacencyListHolder holder;

    public PathFinder(GraphAdjacencyListHolder holder) {
        this.holder = holder;
    }

    /**
     * Реализация алгоритма A* для поиска кратчайшего пути от левого верхнего угла до правого нижнего, и нахождения стоимости этого пути.
     * @return минимальные затраты перемещения из правого верхнего угла в левый нижний.
     */
    public int findShortestWayFromLeftTopCornerToRightBottom() {
        Queue<PriorityVertex> checkQueue = new PriorityQueue<>();
        checkQueue.add(new PriorityVertex(LEFT_TOP_CORNER_VERTEX_INDEX, 0));

        HashMap<Integer, Integer> cameFrom = new HashMap<>();
        cameFrom.put(LEFT_TOP_CORNER_VERTEX_INDEX, -1);

        HashMap<Integer, Integer> costsSoFar = new HashMap<>();
        costsSoFar.put(LEFT_TOP_CORNER_VERTEX_INDEX, 0);

        int goal = holder.getBottomRightCornerVertexIndex();

        while (!checkQueue.isEmpty()) {
            int current = checkQueue.poll().getIndex();

            if (current == goal) {
                break;
            }

            for (LinkData link : this.holder.getVertexConnectionsList(current)) {
                int newCost = costsSoFar.get(current) + link.getMovementCost();
                if (!cameFrom.containsKey(link.getVertexNumber()) || newCost < costsSoFar.get(link.getVertexNumber())) {
                    costsSoFar.put(link.getVertexNumber(), newCost);
                    checkQueue.add(new PriorityVertex(link.getVertexNumber(), newCost +
                            holder.heuristic(holder.getBottomRightCornerVertexIndex(), link.getVertexNumber())));
                    cameFrom.put(link.getVertexNumber(), current);
                }
            }

        }

        logPath(cameFrom);

        return costsSoFar.get(holder.getBottomRightCornerVertexIndex());

    }

    /**
     * Вовзращает индекс вершины из которой был сделан переход в указанную.
     * @param element индекс вершины в которую был совершен переход.
     * @param cameFrom словарь путей из одной вершины в другую.
     * @return индекс вершины из которой был совершен переход.
     */
    private int getFromIndex(int element, HashMap<Integer, Integer> cameFrom) {
        return cameFrom.get(element);
    }

    private void logPath(HashMap<Integer, Integer> cameFrom) {
        String path = String.valueOf(holder.getBottomRightCornerVertexIndex());

        Integer currIndex = getFromIndex(holder.getBottomRightCornerVertexIndex(), cameFrom);

        while (currIndex != -1) {
            path = path + "<-" + currIndex;
            currIndex = getFromIndex(currIndex, cameFrom);
        }

        log.info(path);
    }

}
