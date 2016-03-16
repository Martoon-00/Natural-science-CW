package ru.ifmo.methods;

import ru.ifmo.data.Parameters;
import ru.ifmo.data.SimpleTSection;
import ru.ifmo.data.SimpleZSection;

import java.util.ArrayList;
import java.util.List;

public class EulerBackwardIterationsSolver extends BackwardSolver {

    private int iterationsNumber;

    public EulerBackwardIterationsSolver(int iterationsNumber) {
        this.iterationsNumber = iterationsNumber;
    }

    @Override
    public List<SimpleTSection> solve(int totalSteps, Parameters params) {
        SimpleTSection prev = initTX(params.zNum);

        List<SimpleTSection> res = new ArrayList<>();
        res.add(prev);

        double dz = params.dz;
        double dt = params.dt;
        final double D = Parameters.D;
        final double kappa = Parameters.kappa;
        final double Q = Parameters.Q;
        final double C = Parameters.C;
        List<SimpleZSection> prevL = prev.zs;

        prepareForProgonka(prevL.size(), D, kappa, dt, dz);

        List<Double> curX = new ArrayList<>(prevL.size());
        List<Double> curT = new ArrayList<>(prevL.size());
        for (SimpleZSection section : prevL) {
            curX.add(section.X);
            curT.add(section.T);
        }

        for (int k = 1; k < totalSteps; k++) {
            for (int j = 0; j < iterationsNumber; ++j) {
                List<Double> dX = new ArrayList<>(prevL.size());
                dX.add(0.0);
                for (int i = 1; i < prevL.size() - 1; ++i) {
                    dX.add(prevL.get(i).X / dt + w(curX.get(i), curT.get(i), params));
                }
                dX.add(1.0);

                List<Double> dT = new ArrayList<>(prevL.size());
                dT.add(Parameters.Tm);
                for (int i = 1; i < prevL.size() - 1; ++i) {
                    dT.add(prevL.get(i).T / dt - Q / C * w(curX.get(i), curT.get(i), params));
                }
                dT.add(Parameters.T0);

                curX = applyProgonka(aX, bX, cX, dX);
                curT = applyProgonka(aT, bT, cT, dT);
            }

            List<SimpleZSection> newTX = new ArrayList<>();
            for (int i = 0; i < curX.size(); ++i) {
                newTX.add(new SimpleZSection(curT.get(i), curX.get(i)));
            }
            res.add(new SimpleTSection(newTX));
            prevL = newTX;
        }

        return res;
    }
}
