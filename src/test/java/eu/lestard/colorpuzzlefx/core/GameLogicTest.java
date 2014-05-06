package eu.lestard.colorpuzzlefx.core;

import eu.lestard.grid.Cell;
import eu.lestard.grid.GridModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("unchecked")
public class GameLogicTest {

    private GameLogic gameLogic;

    private GridModel<Colors> gridModel;

    @Before
    public void setup(){
        gridModel = new GridModel<>();

        gridModel.setNumberOfColumns(5);
        gridModel.setNumberOfRows(5);

        gameLogic = new GameLogic(gridModel);
    }

    @Test
    public void testGetAllNeighbours(){
        List<Cell<Colors>> list = new ArrayList<>();

        list.add(gridModel.getCell(0,0));

        final List<Cell<Colors>> allNeighbours = gameLogic.findAllNeighbours(list);

        assertThat(allNeighbours).hasSize(2);
        assertThat(allNeighbours).contains(gridModel.getCell(0, 1));
        assertThat(allNeighbours).contains(gridModel.getCell(1, 0));

        final List<Cell<Colors>> neighboursOfNeighbours = gameLogic.findAllNeighbours(allNeighbours);

        assertThat(neighboursOfNeighbours).hasSize(4);
        assertThat(neighboursOfNeighbours).contains(gridModel.getCell(0, 0));
        assertThat(neighboursOfNeighbours).contains(gridModel.getCell(0, 2));
        assertThat(neighboursOfNeighbours).contains(gridModel.getCell(1, 1));
        assertThat(neighboursOfNeighbours).contains(gridModel.getCell(2, 0));
    }

    @Test
    public void testGetAllNeighbours2(){
        List<Cell<Colors>> list = new ArrayList<>();

        list.add(gridModel.getCell(1, 1));
        list.add(gridModel.getCell(1, 2));
        list.add(gridModel.getCell(2, 2));

        final List<Cell<Colors>> allNeighbours = gameLogic.findAllNeighbours(list);
        assertThat(allNeighbours).hasSize(7);
        assertThat(allNeighbours).contains(gridModel.getCell(1, 0));
        assertThat(allNeighbours).contains(gridModel.getCell(0, 1));
        assertThat(allNeighbours).contains(gridModel.getCell(0, 2));
        assertThat(allNeighbours).contains(gridModel.getCell(1, 3));
        assertThat(allNeighbours).contains(gridModel.getCell(2, 3));
        assertThat(allNeighbours).contains(gridModel.getCell(3, 2));
        assertThat(allNeighbours).contains(gridModel.getCell(2, 1));
    }
}
