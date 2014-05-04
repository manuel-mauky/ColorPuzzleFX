package eu.lestard.colorpuzzlefx.view;

import de.saxsys.jfx.mvvm.base.view.View;
import eu.lestard.colorpuzzlefx.core.ColorProfile;
import eu.lestard.colorpuzzlefx.core.Colors;
import eu.lestard.colorpuzzlefx.core.Configuration;
import eu.lestard.grid.GridModel;
import eu.lestard.grid.GridView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView extends View<MainViewModel> {

    @FXML
    private VBox buttonBar;

    @FXML
    private GridView<Colors> gridView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColorProfile profile = new ColorProfile();

        profile.getProfile().forEach((state, profileColor) -> {
            Button button = new Button();
            button.setGraphic(new Rectangle(40, 40, profileColor));
            buttonBar.getChildren().add(button);

            gridView.addColorMapping(state, profileColor);
        });

        int size = Configuration.size.get();
        GridModel<Colors> gridModel = new GridModel<>(size, size);
        gridModel.init();


        gridView.setGridModel(gridModel);

    }
}
