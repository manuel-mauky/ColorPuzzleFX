package eu.lestard.colorpuzzlefx.view;

import de.saxsys.jfx.mvvm.base.view.View;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class FinishedView extends View<FinishedViewModel> {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void newGame(){
        getViewModel().newGame();
    }

}
