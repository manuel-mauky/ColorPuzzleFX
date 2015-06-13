package eu.lestard.colorpuzzlefx.core;

import eu.lestard.grid.Cell;
import eu.lestard.grid.GridModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Singleton
public class GameLogic {

    private IntegerProperty movesCounter = new SimpleIntegerProperty();

    List<Cell<Colors>> selectedCells = new ArrayList<>();

    private Colors currentColor;

    private GridModel<Colors> gridModel;

    private ColorProfile profile = new ColorProfile();

    private List<Runnable> onFinishedListener = new ArrayList<>();


    @Inject
    public GameLogic() {
        this(10);
    }

    public GameLogic(int size) {
        this.gridModel = new GridModel<>();

        gridModel.setNumberOfColumns(size);
        gridModel.setNumberOfRows(size);

        movesCounter.setValue(0);
    }


    void selectFirstCell(){

        selectedCells.clear();

        final Cell<Colors> cell = gridModel.getCell(0, 0);
        if(cell == null){
            throw new IllegalArgumentException("There is no cell at [0,0]! The Grid has to be initialized before the gridLogic can be created");
        }

        selectedCells.add(cell);


        selectColor(cell.stateProperty().get());
    }

    public void newGame(){

        Random rnd = new Random();

        Set<Colors> colorsSet = profile.getProfile().keySet();
        final Colors[] colorArray = colorsSet.toArray(new Colors[colorsSet.size()]);

        gridModel.getCells().forEach(cell ->{
            final Colors color = colorArray[rnd.nextInt(colorArray.length)];
            cell.changeState(color);
        });


        selectFirstCell();


        movesCounter.setValue(0);
    }

    public void selectColor(Colors newSelectedColor){
        if(currentColor == newSelectedColor){
            return;
        }

        this.currentColor = newSelectedColor;

        final List<Cell<Colors>> newSelectedCells = findNewSelectedCells(selectedCells, newSelectedColor);

        selectedCells = newSelectedCells;

        selectedCells.forEach(cell -> cell.changeState(newSelectedColor));

        movesCounter.setValue(movesCounter.get() + 1);

        if(isGameFinished()) {
            onFinishedListener.forEach(Runnable::run);
        }

    }

    public  boolean isGameFinished(){
        final long numberOfCellsWithCurrentColor = gridModel.getCells().stream().filter(cell -> cell.stateProperty().get() == currentColor).count();
        int numberOfAllCells = gridModel.getCells().size();
        return numberOfAllCells == numberOfCellsWithCurrentColor;
    }

    public void onFinished(Runnable action) {
        onFinishedListener.add(action);
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
        return stream.map(gridModel::getNeighbours).flatMap(Collection::stream).distinct();
    }

    public ReadOnlyIntegerProperty movesCounter(){
        return movesCounter;
    }

    public ColorProfile getProfile() {
        return profile;
    }

    public GridModel<Colors> getGridModel() {
        return gridModel;
    }


    public Colors getCurrentColor() {
        return currentColor;
    }

}
