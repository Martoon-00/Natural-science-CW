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

    protected SimpleTSection initTX(int zNum) {
        List<SimpleZSection> res = new ArrayList<>();
        res.add(new SimpleZSection(Parameters.Tm, 0.0));
        for (int i = 1; i < zNum; i++) {
            res.add(new SimpleZSection(Parameters.T0, 1.0));
        }
        return new SimpleTSection(res);
    }

    protected double w(double x, double t, Parameters parameters) {
        return -Parameters.K * Math.pow(x, parameters.alpha) * Math.exp(-Parameters.E / (Parameters.R * t));
    }
}
