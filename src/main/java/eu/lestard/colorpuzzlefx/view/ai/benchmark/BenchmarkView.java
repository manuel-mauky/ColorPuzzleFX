package eu.lestard.colorpuzzlefx.view.ai.benchmark;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class BenchmarkView implements FxmlView<BenchmarkViewModel> {

    @FXML
    public Spinner<Integer> sampleSpinner;
    @FXML
    public TableView<SolverTableItem> solverTable;
    @FXML
    public TableColumn<SolverTableItem,Boolean> activeColumn;
    @FXML
    public TableColumn<SolverTableItem, String> nameColumn;
    @FXML
    public TableColumn<SolverTableItem, Double> averageColumn;
    @FXML
    public TableColumn<SolverTableItem, Integer> medianColumn;
    @FXML
    public TableColumn<SolverTableItem, Integer> maxColumn;
    @FXML
    public TableColumn<SolverTableItem, Integer> minColumn;
    @FXML
    public TableColumn<SolverTableItem, Double> progressColumn;

    @InjectViewModel
    private BenchmarkViewModel viewModel;

    public void initialize() {
        solverTable.setItems(viewModel.solverTableItems());

        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));
        activeColumn.setCellFactory(CheckBoxTableCell.forTableColumn(activeColumn));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        averageColumn.setCellValueFactory(new PropertyValueFactory<>("average"));
        medianColumn.setCellValueFactory(new PropertyValueFactory<>("median"));
        maxColumn.setCellValueFactory(new PropertyValueFactory<>("max"));
        minColumn.setCellValueFactory(new PropertyValueFactory<>("min"));
        progressColumn.setCellValueFactory(new PropertyValueFactory<>("progress"));
        progressColumn.setCellFactory(ProgressBarTableCell.forTableColumn());

        sampleSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(viewModel.getMinSampleSize(), viewModel.getMaxSampleSize(), viewModel.getDefaultSampleSize(), viewModel.getStepSize()));
        viewModel.sampleSize().bind(sampleSpinner.valueProperty());
    }

    public void refresh() {
        viewModel.refresh();
    }
}
