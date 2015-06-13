package eu.lestard.colorpuzzlefx.ai;

import eu.lestard.colorpuzzlefx.core.Colors;
import eu.lestard.colorpuzzlefx.core.GameLogic;

public interface Solver {

    void setGame(GameLogic game);

    Colors nextStep();
}
