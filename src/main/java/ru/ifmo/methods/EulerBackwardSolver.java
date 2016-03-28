package ru.ifmo.methods;

import ru.ifmo.data.Parameters;
import ru.ifmo.data.SimpleTSection;
import ru.ifmo.data.SimpleZSection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EulerBackwardSolver extends Solver {

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
        double[] dX = new double[n];
        double[] dT = new double[n];

        for (int k = 1; k < totalSteps; ++k) {
            assert prevL.size() > 1;
            utils.prepareForProgonka(prevL.size(), D, kappa, dt, dz);

            double curX = 0.0;
            dX[0] = curX;
            for (int i = 1; i < n - 1; ++i) {
                curX = prevL.get(i).X / dt + w(prevL.get(i).X, prevL.get(i).T, params);
                dX[i] = curX;
            }
            dX[n - 1] = curX;

            double curT = params.Tm;
            dT[0] = curT;
            for (int i = 1; i < n - 1; ++i) {
                curT = prevL.get(i).T / dt - (Q / C) * w(prevL.get(i).X, prevL.get(i).T, params);
                dT[i] = curT;
            }
            dT[n - 1] = curT;

            List<Double> nextX = utils.applyProgonka(utils.aX, utils.bX, utils.cX, dX);
            List<Double> nextT = utils.applyProgonka(utils.aT, utils.bT, utils.cT, dT);

            List<SimpleZSection> newTX = new ArrayList<>();
            for (int i = 0; i < nextX.size(); ++i) {
                newTX.add(new SimpleZSection(nextT.get(i), nextX.get(i)));
            }
            res.add(new SimpleTSection(newTX));
            prevL = newTX;
        }

        return res;
    }

}