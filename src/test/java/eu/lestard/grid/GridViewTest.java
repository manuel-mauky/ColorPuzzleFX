package eu.lestard.grid;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GridViewTest {

    public static enum States {
        EMPTY,
        FILLED
    }

    private GridView<States> gridView;

    @Before
    public void setup(){
        gridView = new GridView<>();
    }

    @Test
    public void testWhenCellsAreAddedToModelThereAreRectanglesAddedInView(){

        final ObservableList<Node> rectangles = gridView.getRootPane().getChildren();
        assertThat(rectangles).isEmpty();

        final GridModel<States> gridModel = gridView.getGridModel();

        gridModel.cells().add(new Cell<>(0, 0));

        assertThat(rectangles).hasSize(1);

        final Rectangle rect1 = (Rectangle)rectangles.get(0);
        assertThat(rect1).isNotNull();


        gridModel.cells().add(new Cell<>(0, 1));
        assertThat(rectangles).hasSize(2);

        assertThat(rectangles).contains(rect1);
    }

    @Test
    public void testWhenCellsAreRemovedFromModelThereAreRectanglesRemovedInView(){
        final ObservableList<Node> rectangles = gridView.getRootPane().getChildren();

        final GridModel<States> gridModel = gridView.getGridModel();
        final Cell<States> cell_0_0 = new Cell<>(0, 0);
        gridModel.cells().add(cell_0_0);

        final Cell<States> cell_0_1 = new Cell<>(0, 0);
        gridModel.cells().add(cell_0_1);

        assertThat(rectangles).hasSize(2);


        gridModel.cells().remove(cell_0_0);


        assertThat(rectangles).hasSize(1);
    }


}
