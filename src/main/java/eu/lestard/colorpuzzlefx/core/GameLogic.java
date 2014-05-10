package eu.lestard.colorpuzzlefx.core;

import eu.lestard.grid.Cell;
import eu.lestard.grid.GridModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        
        final List<Cell<Colors>> newSelectedCells = findNewSelectedCells(selectedCells, newSelectedColor);

        selectedCells = newSelectedCells;

        selectedCells.forEach(cell->cell.changeState(newSelectedColor));

    }

    List<Cell<Colors>> findNewSelectedCells(List<Cell<Colors>> alreadySelected, final Colors color){

        Set<Cell<Colors>> result = new HashSet<>();

        result.addAll(alreadySelected);

        long tmpCount = 0;

        while(true){
            Stream<Cell<Colors>> neighboursStream = findAllNeighboursWithColor(result.stream(), color);

            final List<Cell<Colors>> tmpList = neighboursStream.collect(Collectors.toList());
            result.addAll(tmpList);

            if(result.size() == tmpCount){
                break;
            }

            tmpCount = result.size();
        }

        return new ArrayList<>(result);

    }

    Stream<Cell<Colors>> findAllNeighboursWithColor(Stream<Cell<Colors>> stream, final Colors color){
        return findAllNeighbours(stream).filter(cell -> cell.stateProperty().get() == color);
    }


    Stream<Cell<Colors>> findAllNeighbours(Stream<Cell<Colors>> stream){
        return stream.map(cell -> gridModel.getNeighbours(cell)).flatMap(cells -> cells.stream()).distinct();
    }


}
