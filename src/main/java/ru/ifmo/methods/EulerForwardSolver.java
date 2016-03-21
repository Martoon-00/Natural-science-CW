package ru.ifmo.methods;


import ru.ifmo.data.Parameters;
import ru.ifmo.data.SimpleTSection;
import ru.ifmo.data.SimpleZSection;

import java.util.ArrayList;
import java.util.List;

public class EulerForwardSolver extends Solver {

    @Override
    public List<SimpleTSection> solve(int totalSteps, Parameters params) {
        SimpleTSection prev = initTX(params);

        List<SimpleTSection> res = new ArrayList<>();
        res.add(prev);

        double dz = params.dz;
        double dt = params.dt;
        final double D = params.D;
        final double kappa = params.kappa;
        List<SimpleZSection> prevL = prev.zs;

        for (int k = 1; k < totalSteps; k++) {
            List<SimpleZSection> newTX = new ArrayList<>();
            newTX.add(new SimpleZSection(params.Tm, 0));
            for (int i = 1; i < params.zNum - 1; i++) {
                double newT = dt * (kappa * (prevL.get(i - 1).T - 2 * prevL.get(i).T + prevL.get(i + 1).T)
                        / Math.pow(dz, 2)
                        + w(prevL.get(i).X, prevL.get(i).T, params)) + prevL.get(i).T;
                double newX = dt * (D * (prevL.get(i - 1).X - 2 * prevL.get(i).X + prevL.get(i + 1).X)
                        / Math.pow(dz, 2)
                        + w(prevL.get(i).X, prevL.get(i).T, params)) + prevL.get(i).X;
                newTX.add(new SimpleZSection(newT, newX));
            }
            newTX.add(new SimpleZSection(params.T0, 1));
            res.add(new SimpleTSection(newTX));
            prevL = newTX;
        }
        return res;
    }
}
