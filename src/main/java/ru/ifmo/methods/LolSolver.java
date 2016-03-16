package ru.ifmo.methods;

import ru.ifmo.data.Parameters;
import ru.ifmo.data.SimpleTSection;
import ru.ifmo.data.SimpleZSection;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LolSolver extends Solver {

    /**
     * X describes a moving wave and T describes a line
     */
    @Override
    public List<SimpleTSection> solve(int totalSteps, Parameters params) {
        System.out.printf("Lol solver: %d iterations requested\n", totalSteps);
        return Stream.iterate(0, a -> a + 1).limit(totalSteps)
                .map(t -> new SimpleTSection(
                        Stream.iterate(0, a -> a + 1).limit(params.zNum)
                                .map(z -> z * params.dz)
                                .map(z -> new SimpleZSection(
                                        z * t,
                                        1.5 / (1 + Math.exp(Math.pow((z - 0.5 * (1 - Math.exp((double)-t / 100) * 4)), 2)))
                                )).collect(Collectors.toList())
                )).collect(Collectors.toList());
    }
}
