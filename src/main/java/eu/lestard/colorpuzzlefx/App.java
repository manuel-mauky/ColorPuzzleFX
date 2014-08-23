package eu.lestard.colorpuzzlefx;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import eu.lestard.colorpuzzlefx.view.MainView;
import eu.lestard.colorpuzzlefx.view.MainViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String...args){
        App.launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ColorPuzzleFX");


        final ViewTuple<MainView, MainViewModel> viewTuple = FluentViewLoader.fxmlView(MainView.class).load();

        stage.setScene(new Scene(viewTuple.getView()));
        stage.show();

    }
}
