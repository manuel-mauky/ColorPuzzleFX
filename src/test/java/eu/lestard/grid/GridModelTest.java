package eu.lestard.grid;

import org.junit.Before;
import org.junit.Test;

import static eu.lestard.assertj.javafx.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class GridModelTest {

    public static enum States {
        EMPTY,
        FILLED
    }

    private GridModel<States> model;

    @Before
    public void setup(){
        model = new GridModel<>(3, 5);
        model.setDefaultState(States.EMPTY);
    }

    @Test
    public void testInit(){
        assertThat(model.cells()).isEmpty();

        model.init();

        assertThat(model.cells()).hasSize( 3 * 5);

        model.cells().forEach(cell ->{
            assertThat(cell.stateProperty()).hasValue(States.EMPTY);
        });

    }

    @Test
    public void testGetCell(){
        model.init();

        final Cell cell_0_0 = model.getCell(0, 0);

        assertThat(cell_0_0).isNotNull();
        assertThat(cell_0_0.getColumn()).isEqualTo(0);
        assertThat(cell_0_0.getRow()).isEqualTo(0);


        final Cell cell_3_5 = model.getCell(2, 4);
        assertThat(cell_3_5).isNotNull();
        assertThat(cell_3_5.getColumn()).isEqualTo(2);
        assertThat(cell_3_5.getRow()).isEqualTo(4);

        final Cell cellOutOfRange = model.getCell(3, 5);

        assertThat(cellOutOfRange).isNull();
    }
}
