package eu.lestard.colorpuzzlefx.view.ai.solver;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SolverViewPopup {

    private static Stage stage;

    public static void open() {

        if(stage == null) {
            stage = new Stage();
            final ViewTuple<SolverView, SolverViewModel> viewTuple = FluentViewLoader.fxmlView(SolverView.class).load();

            final SolverViewModel solverViewModel = viewTuple.getViewModel();
            stage.setOnCloseRequest(event -> solverViewModel.pause());

            stage.setScene(new Scene(viewTuple.getView()));
        }

        stage.show();
        stage.toFront();
    }

    public static void close() {
        if(stage != null) {
            stage.close();
        }
    }

}
