package ru.ifmo.methods;

import java.util.ArrayList;
import java.util.List;

public abstract class BackwardSolver extends Solver {

    protected List<Double> aX;
    protected List<Double> bX;
    protected List<Double> cX;

    protected List<Double> aT;
    protected List<Double> bT;
    protected List<Double> cT;

    protected void prepareForProgonka(int n, double D, double kappa, double dt, double dz) {
        aX = new ArrayList<>(n - 1);
        for (int i = 0; i < n - 2; ++i) {
            aX.add(-D / (dz * dz));
        }
        aX.add(0.0);

        bX = new ArrayList<>(n);
        bX.add(1.0);
        for (int i = 1; i < n - 1; ++i) {
            bX.add(1 / dt + 2 * D / (dz * dz));
        }
        bX.add(1.0);

        cX = new ArrayList<>(n - 1);
        cX.add(0.0);
        for (int i = 1; i < n - 1; ++i) {
            cX.add(-D / (dz * dz));
        }

        aT = new ArrayList<>(n - 1);
        for (int i = 0; i < n - 2; ++i) {
            aT.add(-kappa / (dz * dz));
        }
        aT.add(0.0);

        bT = new ArrayList<>(n);
        bT.add(1.0);
        for (int i = 1; i < n - 1; ++i) {
            bT.add(1 / dt + 2 * kappa / (dz * dz));
        }
        bT.add(1.0);

        cT = new ArrayList<>(n - 1);
        cT.add(0.0);
        for (int i = 1; i < n - 1; ++i) {
            cT.add(-kappa / (dz * dz));
        }
    }

    protected static List<Double> applyProgonka(List<Double> a, List<Double> b, List<Double> c, List<Double> d) {
        for (int i = 0; i < d.size() - 1; ++i) {
            d.set(i + 1, d.get(i + 1) - d.get(i) * a.get(i) / b.get(i));
            b.set(i + 1, b.get(i + 1) - c.get(i) * a.get(i) / b.get(i));
        }
        for (int i = d.size() - 2; i >= 0; --i) {
            d.set(i, d.get(i) - d.get(i + 1) * c.get(i) / b.get(i + 1));
        }

        List<Double> res = new ArrayList<>(d.size());
        for (int i = 0; i < d.size(); ++i) {
            res.add(d.get(i) / b.get(i));
        }

        return res;
    }
}
