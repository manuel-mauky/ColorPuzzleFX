package eu.lestard.colorpuzzlefx.view;

import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;
import eu.lestard.colorpuzzlefx.core.ColorProfile;
import eu.lestard.colorpuzzlefx.core.Colors;
import eu.lestard.colorpuzzlefx.core.Configuration;
import eu.lestard.colorpuzzlefx.core.GameLogic;
import eu.lestard.grid.GridModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

import java.util.Map;

public class MainViewModel implements ViewModel {

    private GridModel<Colors> gridModel;

    private GameLogic gameLogic;

    private ColorProfile profile = new ColorProfile();

    private StringProperty movesLabelText = new SimpleStringProperty();

    public MainViewModel(){
        gridModel = new GridModel<>();

        gameLogic = new GameLogic(gridModel);

        movesLabelText.bind(Bindings.concat("Moves:", gameLogic.movesCounter()));


        gridModel.numberOfColumns().bind(Configuration.size);
        gridModel.numberOfRows().bind(Configuration.size);

    }

    public void newGameAction(){
        gameLogic.newGame();
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

    public GridModel<Colors> getGridModel() {
        return gridModel;
    }


}
