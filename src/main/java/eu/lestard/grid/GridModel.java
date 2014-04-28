package eu.lestard.grid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GridModel<State extends Enum> {

    private final ObservableList<Cell<State>> cells = FXCollections.observableArrayList();
    private final int numberOfColumns;
    private final int numberOfRows;
    private State defaultState;


    public GridModel(int numberOfColumns, int numberOfRows) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
    }

    public void init() {

        for (int column = 0; column < numberOfColumns; column++) {
            for (int row = 0; row < numberOfRows; row++) {
                Cell<State> cell = new Cell<>(column, row);
                if (defaultState != null) {
                    cell.changeState(defaultState);
                }
                cells.add(cell);
            }
        }

    }

    public ObservableList<Cell<State>> cells() {
        return cells;
    }

    public Cell<State> getCell(final int column, final int row) {
        return cells.stream()
            .filter(cell ->
                (cell.getColumn() == column && cell.getRow() == row))
            .findFirst()
            .orElse(null);
    }

    public void setDefaultState(State defaultState) {
        this.defaultState = defaultState;
    }
}
