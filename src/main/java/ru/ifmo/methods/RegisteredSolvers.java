package ru.ifmo.methods;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class RegisteredSolvers {
    /**
     * Map Solver name -> Solver instance
     */
    public static Map<String, Supplier<ExtendedSolver>> solvers = new TreeMap<String, Supplier<ExtendedSolver>>() {{
        put("Lol", LolSolver::new);
        put("Euler Forward", EulerForwardSolver::new);
        put("Euler Backward", EulerBackwardSolver::new);
        put("Euler Backward Diagonal", EulerBackwardDiagSolver::new);
        put("Euler Backward 5 Iterations", () -> new EulerBackwardIterationsSolver(5));
    }};


}
