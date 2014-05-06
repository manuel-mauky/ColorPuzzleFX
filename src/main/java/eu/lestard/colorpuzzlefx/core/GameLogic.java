package eu.lestard.colorpuzzlefx.core;

import eu.lestard.grid.Cell;
import eu.lestard.grid.GridModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameLogic {


    private List<Cell<Colors>> selectedCells = new ArrayList<>();

    private Colors currentColor;

    private GridModel<Colors> gridModel;

    public GameLogic(GridModel<Colors> gridModel){
        this.gridModel = gridModel;

        final Cell<Colors> cell = gridModel.getCell(0, 0);
        if(cell == null){
            throw new IllegalArgumentException("There is no cell at [0,0]! The Grid has to be initialized before the gridLogic can be created");
        }

        selectedCells.add(cell);

        currentColor = cell.stateProperty().get();

    }

    public void selectColor(Colors newSelectedColor){
        this.currentColor = newSelectedColor;

        // todo: find all neighbours with same color;

    }

    List<Cell<Colors>> findAllNeighbours(List<Cell<Colors>> list){
        return list.stream().map(cell -> gridModel.getNeighbours(cell)).flatMap(cells -> cells.stream()).distinct().filter(cell -> !list.contains(cell)).collect(Collectors.toList());
    }

}
