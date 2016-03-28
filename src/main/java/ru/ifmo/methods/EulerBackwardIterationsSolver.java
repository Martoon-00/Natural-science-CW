package ru.ifmo.methods;

import ru.ifmo.data.Parameters;
import ru.ifmo.data.SimpleTSection;
import ru.ifmo.data.SimpleZSection;

import java.util.ArrayList;
import java.util.List;

public class EulerBackwardIterationsSolver extends Solver {

    private int iterationsNumber;

    public EulerBackwardIterationsSolver(int iterationsNumber) {
        this.iterationsNumber = iterationsNumber;
    }

    @Override
    public List<SimpleTSection> solve(int totalSteps, Parameters params) {
        SimpleTSection prev = initTX(params);

        List<SimpleTSection> res = new ArrayList<>();
        res.add(prev);

        double dz = params.dz;
        double dt = params.dt;
        final double D = params.D;
        final double kappa = params.kappa;
        final double Q = params.Q;
        final double C = params.C;
        List<SimpleZSection> prevL = prev.zs;

        BackwardsUtils utils = new BackwardsUtils();

        int n = prevL.size();

        List<Double> curX = new ArrayList<>(prevL.size());
        List<Double> curT = new ArrayList<>(prevL.size());
        for (SimpleZSection section : prevL) {
            curX.add(section.X);
            curT.add(section.T);
        }

        for (int k = 1; k < totalSteps; k++) {
            assert prevL.size() > 1;

            for (int j = 0; j < iterationsNumber; ++j) {
                utils.prepareForProgonka(prevL.size(), D, kappa, dt, dz);

                double[] dX = new double[n];
                double curXValue = 0.0;
                dX[0] = curXValue;
                for (int i = 1; i < prevL.size() - 1; ++i) {
                    curXValue = prevL.get(i).X / dt + w(curX.get(i), curT.get(i), params);
                    dX[i] = curXValue;
                }
                dX[n - 1] = curXValue;

                double[] dT = new double[n];
                double curTValue = params.Tm;
                dT[0] = curTValue;
                for (int i = 1; i < prevL.size() - 1; ++i) {
                    curTValue = prevL.get(i).T / dt - Q / C * w(curX.get(i), curT.get(i), params);
                    dT[i] = curTValue;
                }
                dT[n - 1] = curTValue;

                curX = utils.applyProgonka(utils.aX, utils.bX, utils.cX, dX);
                curT = utils.applyProgonka(utils.aT, utils.bT, utils.cT, dT);
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
