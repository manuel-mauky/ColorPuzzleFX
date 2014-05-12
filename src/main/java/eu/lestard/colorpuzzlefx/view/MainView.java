package eu.lestard.colorpuzzlefx.view;

import de.saxsys.jfx.mvvm.base.view.View;
import de.saxsys.jfx.mvvm.viewloader.ViewLoader;
import de.saxsys.jfx.mvvm.viewloader.ViewTuple;
import eu.lestard.colorpuzzlefx.core.Colors;
import eu.lestard.grid.GridModel;
import eu.lestard.grid.GridView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView extends View<MainViewModel> {

    @FXML
    private VBox buttonBar;

    @FXML
    private StackPane centerPane;

    private GridView<Colors> gridView;

    @FXML
    private Label movesLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        GridModel<Colors> gridModel = getViewModel().getGridModel();

        gridView = new GridView<>();
        gridView.setGridModel(gridModel);

        centerPane.getChildren().add(gridView);

        getViewModel().getColorMappings().forEach((state, profileColor) -> {
            Button button = new Button();
            button.setGraphic(new Rectangle(40, 40, profileColor));

            button.setOnAction(event -> getViewModel().selectColorAction(state));

            buttonBar.getChildren().add(button);

            gridView.addColorMapping(state, profileColor);
        });


        movesLabel.textProperty().bind(getViewModel().movesLabelText());

        initFinishedPopup();

        this.newGame();
    }

    private void initFinishedPopup() {
        ViewLoader viewLoader = new ViewLoader();
        final ViewTuple<FinishedViewModel> viewTuple = viewLoader.loadViewTuple(FinishedView.class);

        viewTuple.getCodeBehind().getViewModel().setOnNewGame((v)-> newGame());

        centerPane.getChildren().add(viewTuple.getView());

        viewTuple.getView().visibleProperty().bind(getViewModel().gameFinished());
    }

    @FXML
    public void newGame(){
        getViewModel().newGameAction();
    }

}
