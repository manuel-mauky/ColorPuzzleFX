package eu.lestard.colorpuzzlefx.ai;

import eu.lestard.colorpuzzlefx.core.Colors;
import eu.lestard.colorpuzzlefx.core.GameLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BogoSolver implements Solver {
    private GameLogic game;
    private List<Colors> allColors;

    @Override
    public void setGame(GameLogic game) {
        this.game = game;
        allColors = new ArrayList<>(game.getProfile().getProfile().keySet());
    }

    @Override
    public Colors nextStep() {
        final Colors currentColor = game.getCurrentColor();

        Collections.shuffle(allColors);

        return allColors.stream()
                .filter(color -> !color.equals(currentColor))
                .findAny().orElse(currentColor);
    }
}
