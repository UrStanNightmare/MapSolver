package ru.urstannigtmare.mapsolver.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Класс для хранения списка смежности и вспомогательной информации о графе.
 * Вершины нумеруются начиная с 0, заканчивая n-1. Таким образом для графа 2х2 структура будет:
 * <table border="1">
 *   <tr>
 *     <td> 0, </td> <td> 1 </td>
 *   </tr>
 *   <tr>
 *     <td> 2, </td> <td> 3</td>
 *   </tr>
 * </table>
 *и т.д. <br/>
 *Можно создать граф любой размерности NxN
 */
public class GraphAdjacencyListHolder {
    private static final Logger log = LoggerFactory.getLogger(GraphAdjacencyListHolder.class.getName());

    private static final int MAX_LINKS = 4;

    private List<List<LinkData>> vertexLinksList;
    private int dimension;

    public GraphAdjacencyListHolder(TileDataHolder tileDataHolder) {
        this.dimension = tileDataHolder.getDimension();

        int vertexCount = this.dimension * this.dimension;

        this.initializeVertexLinksList(vertexCount);

        this.fillVertexListWithConnections(vertexCount, dimension, tileDataHolder);

        this.logAdjacencyListData(vertexCount);
    }

    private void logAdjacencyListData(int vertexCount){
        log.info("Graph adjacency list data:{}", System.lineSeparator());
        for (int i = 0; i < vertexCount; i++) {
            log.info("Vertex{}:{} {}{}", i, System.lineSeparator(), vertexLinksList.get(i), System.lineSeparator());
        }
    }


    private void initializeVertexLinksList(int vertexCount) {
        this.vertexLinksList = new ArrayList<>(vertexCount);

        IntStream.iterate(0, i -> i + 1)
                .limit(vertexCount)
                .forEach(value -> {
                    vertexLinksList.add(new ArrayList<>(MAX_LINKS));
                });
    }

    /**
     * Заполняет список смежности соединениями с соседями с указанием стоимости перехода.
     * Обход идёт начиная с 0 вершины, заканчивая перед n-1 вершиной,
     * так как из последней вершины связи с предыдущими нам уже не нужны.
     * @param vertexCount количество вершин.
     * @param dimension размерность поля.
     * @param tileDataHolder вспомогательный объект для хранения данных о карте.
     */
    private void fillVertexListWithConnections(int vertexCount, int dimension, TileDataHolder tileDataHolder) {
        for (int i = 0; i < vertexCount - 1; i++) {

            if (i - 1 > -1 && isVertexesOnTheSameRow(i, i - 1, dimension)) {

                connectAdjacentVertex(i, -1, tileDataHolder);
            }

            if (i + 1 < vertexCount && isVertexesOnTheSameRow(i, i + 1, dimension)) {

                connectAdjacentVertex(i, 1, tileDataHolder);
            }

            if (i + dimension < vertexCount) {

                connectAdjacentVertex(i, dimension, tileDataHolder);
            }

            if (i - dimension > -1) {

                connectAdjacentVertex(i, -dimension, tileDataHolder);
            }
        }
    }

    private void connectAdjacentVertex(int currentIndex, int indexDifference, TileDataHolder holder) {
        int movementCost = holder.getTileTransferValueByIndex(currentIndex + indexDifference);
        this.addLinkDataToVertex(currentIndex, new LinkData(currentIndex + indexDifference, movementCost));
    }

    private boolean isVertexesOnTheSameRow(int vertexA, int vertexB, int dimension) {
        int vertexARow = getVertexRowByIndexAndDimension(vertexA, dimension);
        int vertexBRow = getVertexRowByIndexAndDimension(vertexB, dimension);

        return vertexARow == vertexBRow;
    }

    private int getVertexRowByIndexAndDimension(int index, int dimension) {
        return (int) index / dimension;
    }

    private void addLinkDataToVertex(int vertexIndex, LinkData data) {
        this.vertexLinksList.get(vertexIndex).add(data);
    }


    /**
     * Возвращает копию списка соседних вершин для указанной вершины.
     * Копия возвращается для предотвращения изменений данных извне.
     * @param vertexIndex индекс вершины к которой присоединены соседи.
     * @return копию списка соседних вершин, связанных с указанной.
     */
    public List<LinkData> getVertexConnectionsList(int vertexIndex) {
        return new ArrayList<>(this.vertexLinksList.get(vertexIndex));
    }

    public int getBottomRightCornerVertexIndex() {
        return this.vertexLinksList.size() - 1;
    }

    public int heuristic(int vertexAIndex, int vertexBIndex) {
        int aX = getVertexRowByIndexAndDimension(vertexAIndex, this.dimension);
        int aY = vertexAIndex - aX * dimension;

        int bX = getVertexRowByIndexAndDimension(vertexBIndex, this.dimension);
        int bY = vertexBIndex - bX * dimension;

        return Math.abs(aX - bX) + Math.abs(aY - bY);
    }

}
