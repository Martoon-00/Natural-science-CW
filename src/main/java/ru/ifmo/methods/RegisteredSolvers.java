package ru.ifmo.methods;

import java.util.Map;
import java.util.TreeMap;

public class RegisteredSolvers {
    /**
     * Map Solver name -> Solver instance
     */
    public static Map<String, ExtendedSolver> solvers = new TreeMap<String, ExtendedSolver>() {{
        put("Lol", new LolSolver());
        put("Euler Forward", new EulerForwardSolver());
        put("Euler Backward", new EulerBackwardSolver());
        put("Euler Backward Diagonal", new EulerBackwardDiagSolver());
        put("Euler Backward 5 Iterations", new EulerBackwardIterationsSolver(5));
    }};


}
