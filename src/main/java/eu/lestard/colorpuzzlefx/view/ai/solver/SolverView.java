package eu.lestard.colorpuzzlefx.view.ai.solver;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;

public class SolverView implements FxmlView<SolverViewModel>{

    @FXML
    public Label waitTimeLabel;
    @FXML
    public Slider waitTimeSlider;
    @FXML
    public Rectangle colorBlock;
    @FXML
    public ToggleButton autoButton;
    @FXML
    public Button nextButton;
    @FXML
    public ChoiceBox<String> solverChoiceBox;

    @InjectViewModel
    private SolverViewModel viewModel;

    public void initialize() {
        solverChoiceBox.setItems(viewModel.solverNames());
        solverChoiceBox.getSelectionModel().select(0);
        viewModel.selectedSolverName().bind(solverChoiceBox.getSelectionModel().selectedItemProperty());


        waitTimeLabel.textProperty().bind(viewModel.speedLabel());
        colorBlock.fillProperty().bind(viewModel.nextColor());

        viewModel.waitTime().bind(waitTimeSlider.valueProperty());

        waitTimeSlider.setBlockIncrement(viewModel.getWaitTimeIncrement());
        waitTimeSlider.setMin(viewModel.getMinWaitTime());
        waitTimeSlider.setMax(viewModel.getMaxWaitTime());

        autoButton.selectedProperty().bindBidirectional(viewModel.autoButtonPressed());

        nextButton.disableProperty().bind(viewModel.autoButtonPressed());
        waitTimeSlider.disableProperty().bind(viewModel.autoButtonPressed());
    }

    public void auto() {
        viewModel.auto();
    }

    public void next() {
        viewModel.next();
    }

    public void openBenchmark() {
        viewModel.openBenchmark();
    }
}
