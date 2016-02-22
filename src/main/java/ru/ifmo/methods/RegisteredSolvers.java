package ru.ifmo.methods;

import java.util.Map;
import java.util.TreeMap;

public class RegisteredSolvers {
    /**
     * Map Solver name -> Solver instance
     */
    public static Map<String, Solver> solvers = new TreeMap<String, Solver>() {{
        put("Lol", new LolSolver());
        put("Euler Forward", new EulerForwardSolver());
        put("Euler Backward", new EulerBackwardSolver());
        put("Euler Backward Diagonal", new EulerBackwardDiagSolver());
        put("Euler Backward Iterations", new EulerBackwardIterationsSolver());
    }};


}
