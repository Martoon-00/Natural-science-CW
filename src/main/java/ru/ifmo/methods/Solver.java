package ru.ifmo.methods;

import ru.ifmo.data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Solver implements ExtendedSolver {
    @Override
    public List<TSection> extendedSolve(int totalSteps, Parameters params) {
        List<SimpleTSection> presolution = solve(totalSteps, params);

        Function<SimpleZSection, ZSection> mapZSection = section ->
                new ZSection(section.T, section.X, w(section.X, section.T, params));
        return presolution.stream()
                .map(tSection -> new TSection(
                        tSection.zs.stream()
                                .map(mapZSection)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public abstract List<SimpleTSection> solve(int totalSteps, Parameters params);

    protected SimpleTSection initTX(Parameters params) {
        List<SimpleZSection> res = new ArrayList<>();
        res.add(new SimpleZSection(params.Tm, 0.0));
        for (int i = 1; i < params.zNum; i++) {
            res.add(new SimpleZSection(params.T0, 1.0));
        }
        return new SimpleTSection(res);
    }

    protected double w(double x, double t, Parameters parameters) {
        return -parameters.K * Math.pow(x, parameters.alpha) * Math.exp(-parameters.E / (parameters.R * t));
    }
}
