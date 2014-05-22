package eu.lestard.colorpuzzlefx.view;


import de.saxsys.jfx.mvvm.api.ViewModel;

import java.util.function.Consumer;

public class FinishedViewModel implements ViewModel {

    private Consumer<Void> onNewGame;

    public void newGame(){
        if(onNewGame != null){
            onNewGame.accept(null);
        }
    }

    public void setOnNewGame(Consumer<Void> onNewGame){
        this.onNewGame = onNewGame;
    }

}
