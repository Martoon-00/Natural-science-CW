package ru.ifmo.methods;

import ru.ifmo.data.Parameters;
import ru.ifmo.data.SimpleTSection;
import ru.ifmo.data.SimpleZSection;
import ru.ifmo.data.TSection;

import java.util.ArrayList;
import java.util.List;

public abstract class Solver implements ExtendedSolver {
    @Override
    public List<TSection> extendedSolve(int totalSteps, Parameters params) {
        return TSection.extend(solve(totalSteps, params));
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
