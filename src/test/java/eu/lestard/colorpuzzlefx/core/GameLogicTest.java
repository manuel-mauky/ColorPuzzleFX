package eu.lestard.colorpuzzlefx.core;

import eu.lestard.grid.Cell;
import eu.lestard.grid.GridModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("unchecked")
public class GameLogicTest {

    private GameLogic gameLogic;

    private GridModel<Colors> gridModel;

    @Before
    public void setup(){
        gameLogic = new GameLogic(5);
        gridModel = gameLogic.getGridModel();
    }


    @Test
    public void testGetAllNeighbours(){
        List<Cell<Colors>> list = new ArrayList<>();

        list.add(gridModel.getCell(0,0));

        final List<Cell<Colors>> allNeighbours = gameLogic.findAllNeighbours(list.stream()).collect(Collectors.toList());

        assertThat(allNeighbours).hasSize(2);
        assertThat(allNeighbours).contains(gridModel.getCell(0, 1));
        assertThat(allNeighbours).contains(gridModel.getCell(1, 0));

        final List<Cell<Colors>> neighboursOfNeighbours = gameLogic.findAllNeighbours(allNeighbours.stream()).collect(Collectors.toList());

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

        final List<Cell<Colors>> allNeighbours = gameLogic.findAllNeighbours(list.stream()).collect(Collectors.toList());
        assertThat(allNeighbours).hasSize(10);
        assertThat(allNeighbours).contains(gridModel.getCell(1, 0));
        assertThat(allNeighbours).contains(gridModel.getCell(0, 1));
        assertThat(allNeighbours).contains(gridModel.getCell(0, 2));
        assertThat(allNeighbours).contains(gridModel.getCell(1, 3));
        assertThat(allNeighbours).contains(gridModel.getCell(2, 3));
        assertThat(allNeighbours).contains(gridModel.getCell(3, 2));
        assertThat(allNeighbours).contains(gridModel.getCell(2, 1));

        assertThat(allNeighbours).contains(gridModel.getCell(1, 1));
        assertThat(allNeighbours).contains(gridModel.getCell(1, 2));
        assertThat(allNeighbours).contains(gridModel.getCell(2, 2));
    }

    /**
     * The grid:
     *
     *    1 1 2 2 4
     *    1 2 3 3 3
     *    4 2 2 4 4
     *    4 4 4 4 4
     *    2 2 2 4 4
     *
     *  At first "1" is selected. Now the user clicks "2".
     */
    @Test
    public void testFindNewSelectedCells(){
        gridModel.getCell(0,0).changeState(Colors.Color1);
        gridModel.getCell(0,1).changeState(Colors.Color1);
        gridModel.getCell(0,2).changeState(Colors.Color2);
        gridModel.getCell(0,3).changeState(Colors.Color2);
        gridModel.getCell(0,4).changeState(Colors.Color4);

        gridModel.getCell(1,0).changeState(Colors.Color1);
        gridModel.getCell(1,1).changeState(Colors.Color2);
        gridModel.getCell(1,2).changeState(Colors.Color3);
        gridModel.getCell(1,3).changeState(Colors.Color3);
        gridModel.getCell(1,4).changeState(Colors.Color3);

        gridModel.getCell(2,0).changeState(Colors.Color4);
        gridModel.getCell(2,1).changeState(Colors.Color2);
        gridModel.getCell(2,2).changeState(Colors.Color2);
        gridModel.getCell(2,3).changeState(Colors.Color4);
        gridModel.getCell(2,4).changeState(Colors.Color4);

        gridModel.getCell(3,0).changeState(Colors.Color4);
        gridModel.getCell(3,1).changeState(Colors.Color4);
        gridModel.getCell(3,2).changeState(Colors.Color4);
        gridModel.getCell(3,3).changeState(Colors.Color4);
        gridModel.getCell(3,4).changeState(Colors.Color4);

        gridModel.getCell(4,0).changeState(Colors.Color2);
        gridModel.getCell(4,1).changeState(Colors.Color2);
        gridModel.getCell(4,2).changeState(Colors.Color2);
        gridModel.getCell(4,3).changeState(Colors.Color4);
        gridModel.getCell(4,4).changeState(Colors.Color4);


        List<Cell<Colors>> first = new ArrayList<>();
        first.add(gridModel.getCell(0, 0));
        first.add(gridModel.getCell(0, 1));
        first.add(gridModel.getCell(1, 0));

        final List<Cell<Colors>> newSelectedCells = gameLogic.findNewSelectedCells(first, Colors.Color2);

        assertThat(newSelectedCells).hasSize(8);

        assertThat(newSelectedCells).contains(gridModel.getCell(0, 0));
        assertThat(newSelectedCells).contains(gridModel.getCell(0, 1));
        assertThat(newSelectedCells).contains(gridModel.getCell(1, 0));
        assertThat(newSelectedCells).contains(gridModel.getCell(0, 2));
        assertThat(newSelectedCells).contains(gridModel.getCell(0, 3));
        assertThat(newSelectedCells).contains(gridModel.getCell(1, 1));
        assertThat(newSelectedCells).contains(gridModel.getCell(2, 1));
        assertThat(newSelectedCells).contains(gridModel.getCell(2, 2));

    }

    /**
     * 1 1 2
     * 1 3 4
     * 4 4 1
     */
    @Test
    public void testSelectFirstCell(){
        gridModel.getCell(0,0).changeState(Colors.Color1);
        gridModel.getCell(0,1).changeState(Colors.Color1);
        gridModel.getCell(0,2).changeState(Colors.Color2);

        gridModel.getCell(1,0).changeState(Colors.Color1);
        gridModel.getCell(1,1).changeState(Colors.Color3);
        gridModel.getCell(1,2).changeState(Colors.Color4);

        gridModel.getCell(2,0).changeState(Colors.Color4);
        gridModel.getCell(2,1).changeState(Colors.Color4);
        gridModel.getCell(2,2).changeState(Colors.Color1);


        gameLogic.selectFirstCell();

        assertThat(gameLogic.selectedCells).hasSize(3)
            .contains(gridModel.getCell(0,0))
            .contains(gridModel.getCell(0,1))
            .contains(gridModel.getCell(1,0));
    }
}
