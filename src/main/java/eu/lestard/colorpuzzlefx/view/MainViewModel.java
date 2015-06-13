package eu.lestard.colorpuzzlefx.view;

import de.saxsys.mvvmfx.ViewModel;
import eu.lestard.colorpuzzlefx.core.ColorProfile;
import eu.lestard.colorpuzzlefx.core.Colors;
import eu.lestard.colorpuzzlefx.core.GameLogic;
import eu.lestard.grid.GridModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.scene.paint.Color;

import java.util.Map;

public class MainViewModel implements ViewModel {

    private GameLogic gameLogic;

    private ColorProfile profile = new ColorProfile();

    private StringProperty movesLabelText = new SimpleStringProperty();

    private BooleanProperty gameFinished = new SimpleBooleanProperty();

    public MainViewModel(GameLogic gameLogic){
        this.gameLogic = gameLogic;

        movesLabelText.bind(Bindings.concat("Moves:", gameLogic.movesCounter()));

        newGameAction();

        gameLogic.onFinished(() -> gameFinished.setValue(true));
    }

    public void newGameAction(){
        gameLogic.newGame();
        gameFinished.set(false);
    }

    public void selectColorAction(Colors color){
        gameLogic.selectColor(color);
    }

    public Map<Colors, Color> getColorMappings(){
        return profile.getProfile();
    }

    public ReadOnlyStringProperty movesLabelText(){
        return movesLabelText;
    }

    public ReadOnlyBooleanProperty gameFinished(){
        return gameFinished;
    }

    public GridModel<Colors> getGridModel() {
        return gameLogic.getGridModel();
    }

    public GameLogic getGameLogic(){
        return gameLogic;
    }

    public void openAI() {
        SolverViewPopup.open();
    }
}
