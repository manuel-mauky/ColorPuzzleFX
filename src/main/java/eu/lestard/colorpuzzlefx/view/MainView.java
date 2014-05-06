package eu.lestard.colorpuzzlefx.view;

import de.saxsys.jfx.mvvm.base.view.View;
import eu.lestard.colorpuzzlefx.core.ColorProfile;
import eu.lestard.colorpuzzlefx.core.Colors;
import eu.lestard.colorpuzzlefx.core.Configuration;
import eu.lestard.colorpuzzlefx.core.GameLogic;
import eu.lestard.grid.GridModel;
import eu.lestard.grid.GridView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainView extends View<MainViewModel> {

    @FXML
    private VBox buttonBar;

    @FXML
    private BorderPane mainContainer;

    private GridView<Colors> gridView;


    private GameLogic gameLogic;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        gridView = new GridView<>();

        mainContainer.setCenter(gridView);

        ColorProfile profile = new ColorProfile();

        profile.getProfile().forEach((state, profileColor) -> {
            Button button = new Button();
            button.setGraphic(new Rectangle(40, 40, profileColor));

            button.setOnAction(event-> gameLogic.selectColor(state));

            buttonBar.getChildren().add(button);

            gridView.addColorMapping(state, profileColor);
        });
        final GridModel<Colors> gridModel = gridView.getGridModel();

        gridModel.numberOfColumns().bind(Configuration.size);
        gridModel.numberOfRows().bind(Configuration.size);

        final Colors[] colorArray = profile.getProfile().keySet().toArray(new Colors[0]);

        Random rnd = new Random();

        gridModel.getCells().forEach(cell ->{
            final Colors color = colorArray[rnd.nextInt(colorArray.length)];
            cell.changeState(color);
        });


        gameLogic = new GameLogic(gridModel);

    }

}
