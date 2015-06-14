package eu.lestard.colorpuzzlefx.view.ai.benchmark;

import javafx.beans.property.*;

import eu.lestard.colorpuzzlefx.ai.Solver;

public class SolverTableItem {

    private final StringProperty name = new SimpleStringProperty();
    private final BooleanProperty active = new SimpleBooleanProperty(true);
    private final DoubleProperty average = new SimpleDoubleProperty();
    private final IntegerProperty median = new SimpleIntegerProperty();
    private final IntegerProperty max = new SimpleIntegerProperty();
    private final IntegerProperty min = new SimpleIntegerProperty();

    private final DoubleProperty progress = new SimpleDoubleProperty(0);

    private final Solver solver;

    public SolverTableItem(String name, Solver solver) {
        this.name.setValue(name);
        this.solver = solver;
    }

    public Solver getSolver() {
        return solver;
    }

    public StringProperty nameProperty() {
        return name;
    }


    public BooleanProperty activeProperty() {
        return active;
    }

    public DoubleProperty averageProperty() {
        return average;
    }

    public IntegerProperty medianProperty() {
        return median;
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    public IntegerProperty minProperty() {
        return min;
    }

    public DoubleProperty progressProperty() {
        return progress;
    }

}
