package eu.lestard.colorpuzzlefx.view;

import de.saxsys.mvvmfx.FluentViewLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SolverViewPopup {

    private static Stage stage;

    public static void open() {

        if(stage == null) {
            stage = new Stage();
            stage.setScene(new Scene(FluentViewLoader.fxmlView(SolverView.class).load().getView()));
        }

        stage.show();
        stage.toFront();
    }

}
