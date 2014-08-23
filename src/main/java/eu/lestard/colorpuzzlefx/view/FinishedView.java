package eu.lestard.colorpuzzlefx.view;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;

public class FinishedView implements FxmlView<FinishedViewModel> {

    @InjectViewModel
    private FinishedViewModel viewModel;

    @FXML
    public void newGame(){
        viewModel.newGame();
    }


}
