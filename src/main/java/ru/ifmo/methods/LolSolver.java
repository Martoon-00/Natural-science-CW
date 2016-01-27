package ru.ifmo.methods;

import ru.ifmo.data.Parameters;
import ru.ifmo.data.SimpleTSection;
import ru.ifmo.data.SimpleXSection;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LolSolver implements Solver {
    @Override
    public List<SimpleTSection> solve(int totalSteps, Parameters params) {
        return Stream.iterate(0, a -> a + 1).limit(totalSteps)
                .map(t -> new SimpleTSection(
                        Stream.iterate(0, a -> a + 1).limit(params.xNum)
                                .map(x -> new SimpleXSection(
                                        1. / x / t,
                                        1. / (1 + Math.exp(x - 0.5 + 0.5 / t))
                                )).collect(Collectors.toList())
                )).collect(Collectors.toList());
    }
}
