package eu.lestard.colorpuzzlefx.ai;

import eu.lestard.colorpuzzlefx.core.Colors;
import eu.lestard.colorpuzzlefx.core.GameLogic;
import eu.lestard.grid.Cell;
import eu.lestard.grid.GridModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Solver1 implements Solver {

    private GameLogic game;

    @Override
    public void setGame(GameLogic game) {
        this.game = game;
    }

    @Override
    public Colors nextStep() {

        final GridModel<Colors> gridModel = game.getGridModel();

        final Colors currentColor = game.getCurrentColor();

        final List<Colors> neighbourColors = game.getSelectedCells()
                .stream()
                .flatMap(cell -> gridModel.getNeighbours(cell).stream())
                .distinct()
                .filter(cell -> !cell.getState().equals(currentColor))
                .map(Cell::getState)
                .collect(Collectors.toList());

        Map<Colors, Integer> counters = new HashMap<>();

        neighbourColors.forEach(color -> {
            if (!counters.containsKey(color)) {
                counters.put(color, 0);
            }

            counters.put(color, counters.get(color) + 1);
        });

        final Optional<Colors> first = counters.entrySet()
                .stream()
                .sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();

        return first.orElse(Colors.Color1);
    }
}
