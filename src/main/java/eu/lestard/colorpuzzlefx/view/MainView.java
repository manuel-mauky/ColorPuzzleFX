package eu.lestard.colorpuzzlefx.view;

import de.saxsys.jfx.mvvm.api.FxmlView;
import de.saxsys.jfx.mvvm.api.InjectViewModel;
import de.saxsys.jfx.mvvm.viewloader.ViewLoader;
import de.saxsys.jfx.mvvm.viewloader.ViewTuple;
import eu.lestard.colorpuzzlefx.core.Colors;
import eu.lestard.grid.GridModel;
import eu.lestard.grid.GridView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements FxmlView<MainViewModel>, Initializable {

    @FXML
    private VBox buttonBar;

    @FXML
    private StackPane centerPane;

    private GridView<Colors> gridView;

    @FXML
    private Label movesLabel;

    @InjectViewModel
    private MainViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        GridModel<Colors> gridModel = viewModel.getGridModel();

        gridView = new GridView<>();
        gridView.setGridModel(gridModel);

        gridView.strokeProperty().set(Color.TRANSPARENT);

        centerPane.getChildren().add(gridView);

        viewModel.getColorMappings().forEach((state, profileColor) -> {
            Button button = new Button();
            button.setGraphic(new Rectangle(40, 40, profileColor));

            button.setOnAction(event -> viewModel.selectColorAction(state));

            buttonBar.getChildren().add(button);

            gridView.addColorMapping(state, profileColor);
        });


        movesLabel.textProperty().bind(viewModel.movesLabelText());

        initFinishedPopup();

        this.newGame();
    }

    private void initFinishedPopup() {
        ViewLoader viewLoader = new ViewLoader();
        final ViewTuple<FinishedView, FinishedViewModel> viewTuple = viewLoader.loadViewTuple(FinishedView.class);

        viewTuple.getCodeBehind().getViewModel().setOnNewGame((v) -> newGame());

        centerPane.getChildren().add(viewTuple.getView());

        viewTuple.getView().visibleProperty().bind(viewModel.gameFinished());
    }

    @FXML
    public void newGame(){
        viewModel.newGameAction();
    }

}
