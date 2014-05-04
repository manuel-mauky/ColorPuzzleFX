package eu.lestard.grid;

import eu.lestard.colorpuzzlefx.core.Configuration;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.HashMap;
import java.util.Map;

public class GridView<State extends Enum> extends StackPane {


    private ObjectProperty<GridModel<State>> gridModelProperty = new SimpleObjectProperty<>();
    private Pane rootPane = new Pane();

    private Map<State, Color> colorMapping = new HashMap<>();

    public GridView(){
        final NumberBinding fullSize = Bindings.min(this.widthProperty(), this.heightProperty());

        rootPane.maxWidthProperty().bind(fullSize);
        rootPane.maxHeightProperty().bind(fullSize);

        this.getChildren().add(rootPane);

        rootPane.setStyle("-fx-border-color:black");

        gridModelProperty.addListener((obs, oldValue, newGridModel) -> {
            init();
        });
    }

    private void init(){
        final GridModel<State> gridModel = getGridModel();

        int size = Configuration.size.get();

        NumberBinding pxPerCell = Bindings.min(rootPane.widthProperty(), rootPane.heightProperty()).divide(size);


        gridModel.getCells().forEach(cell -> {
            NumberBinding xStart = pxPerCell.multiply(cell.getColumn());
            NumberBinding yStart = pxPerCell.multiply(cell.getRow());

            Rectangle rectangle = new Rectangle();

            rectangle.setStrokeType(StrokeType.INSIDE);
            rectangle.setStroke(Color.LIGHTGREY);
            rectangle.setStrokeWidth(1);

            rectangle.xProperty().bind(xStart);
            rectangle.yProperty().bind(yStart);

            rectangle.widthProperty().bind(pxPerCell);
            rectangle.heightProperty().bind(pxPerCell);


            rectangle.setFill(colorMapping.get(cell.stateProperty().get()));

            rootPane.getChildren().add(rectangle);
        });


    }

    public GridView(GridModel<State> gridModel){
        setGridModel(gridModel);
    }

    public void setGridModel(GridModel<State> gridModel){
        this.gridModelProperty.setValue(gridModel);
    }

    public GridModel<State> getGridModel(){
        return gridModelProperty.get();
    }

    public ReadOnlyObjectProperty<GridModel<State>> gridModelProperty(){
        return gridModelProperty;
    }

    public void addColorMapping(State state, Color color){
        this.colorMapping.put(state, color);
    }


}
