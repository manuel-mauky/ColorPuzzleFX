package eu.lestard.colorpuzzlefx.view.ai.benchmark;

import de.saxsys.mvvmfx.FluentViewLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BenchmarkViewPopup {
    private static Stage stage;

    public static void open() {

        if(stage == null) {
            stage = new Stage();
            stage.setScene(new Scene(FluentViewLoader.fxmlView(BenchmarkView.class).load().getView()));
        }

        stage.show();
        stage.toFront();
    }

}
