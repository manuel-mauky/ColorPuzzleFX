package eu.lestard.colorpuzzlefx.ai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SolverManager {

    private Map<String, Solver> solverMap = new HashMap<>();


    public SolverManager() {
        solverMap.put("Bogo Solver", new BogoSolver());
        solverMap.put("Brute Force Solver", new BruteForceSolver());
        solverMap.put("Solver 1", new Solver1());
    }


    public List<String> getSolverNames() {
        return solverMap.keySet().stream().collect(Collectors.toList());
    }

    public Optional<Solver> getSolver(String name) {
        return Optional.ofNullable(solverMap.get(name));
    }

}
