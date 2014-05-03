package eu.lestard.grid;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

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
        System.out.println("Initializing the GridView");
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
