package eu.lestard.colorpuzzlefx.view;

import de.saxsys.jfx.mvvm.base.view.View;
import eu.lestard.colorpuzzlefx.core.Colors;
import eu.lestard.grid.GridModel;
import eu.lestard.grid.GridView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView extends View<MainViewModel> {

    @FXML
    private VBox buttonBar;

    @FXML
    private BorderPane mainContainer;

    private GridView<Colors> gridView;

    @FXML
    private Label movesLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        GridModel<Colors> gridModel = getViewModel().getGridModel();

        gridView = new GridView<>();
        gridView.setGridModel(gridModel);

        mainContainer.setCenter(gridView);

        getViewModel().getColorMappings().forEach((state, profileColor) -> {
            Button button = new Button();
            button.setGraphic(new Rectangle(40, 40, profileColor));

            button.setOnAction(event-> getViewModel().selectColorAction(state));

            buttonBar.getChildren().add(button);

            gridView.addColorMapping(state, profileColor);
        });



        movesLabel.textProperty().bind(getViewModel().movesLabelText());

        this.newGame();
    }

    @FXML
    public void newGame(){
        getViewModel().newGameAction();
    }

}
