package eu.lestard.colorpuzzlefx.view.ai.benchmark;

import de.saxsys.mvvmfx.ViewModel;
import eu.lestard.colorpuzzlefx.ai.Solver;
import eu.lestard.colorpuzzlefx.ai.SolverManager;
import eu.lestard.colorpuzzlefx.core.Colors;
import eu.lestard.colorpuzzlefx.core.GameLogic;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BenchmarkViewModel implements ViewModel {

    private static int MAX_TRIES = 100;

    private ObservableList<SolverTableItem> solverTableItems = FXCollections.observableArrayList();

    private IntegerProperty sampleSize = new SimpleIntegerProperty(getDefaultSampleSize());


    public int getMaxSampleSize() {
        return 1000;
    }

    public int getMinSampleSize() {
        return 50;
    }

    public int getDefaultSampleSize() {
        return 200;
    }

    public int getStepSize() {
        return 50;
    }

    public IntegerProperty sampleSize() {
        return sampleSize;
    }

    public BenchmarkViewModel(SolverManager solverManager) {

        solverManager.getSolverNames()
                .forEach(name -> solverTableItems.add(new SolverTableItem(name, solverManager.getSolver(name).get())));
    }


    public ObservableList<SolverTableItem> solverTableItems() {
        return solverTableItems;
    }

    public void refresh() {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                solverTableItems.parallelStream()
                        .filter(item -> item.activeProperty().get())
                        .forEach(item -> {
                            final Solver solver = item.getSolver();

                            benchmark(solver, item);
                        });
                return null;
            }
        };

        new Thread(task).start();
    }


    private void benchmark(Solver solver, SolverTableItem item) {
        item.medianProperty().setValue(0);
        item.averageProperty().setValue(0);
        item.minProperty().setValue(0);
        item.maxProperty().setValue(0);
        item.progressProperty().setValue(0);


        final int sample = sampleSize.get();
        int min = MAX_TRIES;
        int max = 0;

        int sum = 0;

        List<Integer> results = new ArrayList<>();

        for (int i=1 ; i<=sample; i++) {
            final int result = solvePuzzle(solver);
            results.add(result);

            if(result < min) {
                min = result;
                Platform.runLater(() -> item.minProperty().setValue(result));
            }


            if(result > max) {
                max = result;
                Platform.runLater(() -> item.maxProperty().setValue(result));
            }

            sum += result;

            final double average = (double)sum / (double)i;

            Platform.runLater(() -> item.averageProperty().setValue(average));

            final double progress = (double)i / (double)sample;

            Platform.runLater(() -> item.progressProperty().setValue(progress));


            Collections.sort(results);
            final Integer median = results.get(results.size() / 2);

            Platform.runLater(() -> item.medianProperty().setValue(median));

        }
    }

    private int solvePuzzle(Solver solver) {
        GameLogic game = new GameLogic();
        game.newGame();

        solver.setGame(game);

        for(int i=0 ; i<MAX_TRIES ; i++) {

            if (game.isGameFinished()) {
                break;
            }

            final Colors nextStep = solver.nextStep();

            game.selectColor(nextStep);
        }

        return game.movesCounter().get();
    }
}
