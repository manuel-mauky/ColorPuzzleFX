package eu.lestard.colorpuzzlefx.view;

import de.saxsys.mvvmfx.ViewModel;
import eu.lestard.colorpuzzlefx.ai.Solver;
import eu.lestard.colorpuzzlefx.core.ColorProfile;
import eu.lestard.colorpuzzlefx.core.Colors;
import eu.lestard.colorpuzzlefx.core.GameLogic;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class SolverViewModel implements ViewModel{

    private StringProperty speedLabel = new SimpleStringProperty();
    private ObjectProperty<Paint> nextColor = new SimpleObjectProperty<>();

    private BooleanProperty autoButtonPressed = new SimpleBooleanProperty();
    private DoubleProperty waitTime = new SimpleDoubleProperty(1);

    private Solver solver;
    private GameLogic gameLogic;
    private Colors nextStep;
    private ColorProfile colorProfile = new ColorProfile();

    private Timeline timeline;

    public SolverViewModel(Solver solver, GameLogic gameLogic) {
        this.solver = solver;
        this.gameLogic = gameLogic;


        speedLabel.bind(Bindings.format("Wait time: %1$.1f s", waitTime));


        solver.setGame(gameLogic);

        next();

        waitTime.addListener((observable, oldValue, newValue) -> {
           this.timeline = createTimeline();
        });


        this.timeline = createTimeline();
    }

    private Timeline createTimeline() {
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(waitTime.get()), event -> {
            this.next();
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        return timeline;
    }

    public void auto() {
        if(autoButtonPressed.get()) {
            timeline.play();
        } else {
            timeline.pause();
        }
    }

    public void next() {
        if(!gameLogic.isGameFinished()) {


            if(nextStep != null) {
                gameLogic.selectColor(nextStep);
            }

            nextStep = solver.nextStep();
            nextColor.set(colorProfile.getColor(nextStep));
        }
    }

    public DoubleProperty waitTime() {
        return waitTime;
    }


    public double getMinWaitTime() {
        return 0.1;
    }

    public double getMaxWaitTime() {
        return 5;
    }

    public double getWaitTimeIncrement() {
        return 0.3;
    }

    public BooleanProperty autoButtonPressed() {
        return autoButtonPressed;
    }

    public ObservableValue<Paint> nextColor() {
        return nextColor;
    }


    public ObservableStringValue speedLabel() {
        return speedLabel;
    }

}
