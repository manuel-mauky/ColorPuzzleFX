package eu.lestard.colorpuzzlefx.view;

import de.saxsys.jfx.mvvm.api.FxmlView;
import de.saxsys.jfx.mvvm.api.InjectViewModel;
import javafx.fxml.FXML;

public class FinishedView implements FxmlView<FinishedViewModel> {

    @InjectViewModel
    private FinishedViewModel viewModel;

    @FXML
    public void newGame(){
        viewModel.newGame();
    }

    public FinishedViewModel getViewModel(){
        return viewModel;
    }

}
