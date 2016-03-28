package ru.ifmo.methods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BackwardsUtils {

    protected double[] aX;
    protected double[] bX;
    protected double[] cX;

    protected double[] aT;
    protected double[] bT;
    protected double[] cT;

    public BackwardsUtils() {}

    protected void prepareForProgonka(int n, double D, double kappa, double dt, double dz) {
        aX = new double[n - 1];
        for (int i = 0; i < n - 2; ++i) {
            aX[i] = -D / Math.pow(dz, 2);
        }
        aX[n - 2] = 0.0;

        bX = new double[n];
        bX[0] = 1.0;
        for (int i = 1; i < n - 1; ++i) {
            bX[i] = 1.0 / dt + 2.0 * D / Math.pow(dz, 2);
        }
        bX[n - 1] = 1.0;

        cX = new double[n - 1];
        cX[0] = 0.0;
        for (int i = 1; i < n - 1; ++i) {
            cX[i] = -D / Math.pow(dz, 2);
        }

        aT = new double[n - 1];
        for (int i = 0; i < n - 2; ++i) {
            aT[i] = -kappa / Math.pow(dz, 2);
        }
        aT[n - 2] = 0.0;

        bT= new double[n];
        bT[0] = 1.0;
        for (int i = 1; i < n - 1; ++i) {
            bT[i] = 1.0 / dt + 2.0 * kappa / Math.pow(dz, 2);
        }
        bT[n - 1] = 1.0;

        cT = new double[n - 1];
        cT[0] = 0.0;
        for (int i = 1; i < n - 1; ++i) {
            cT[i] = -kappa / Math.pow(dz, 2);
        }
    }

    protected List<Double> applyProgonka(double[] a, double[] b, double[] c, double[] d) {
        for (int i = 0; i < d.length - 1; ++i) {
            d[i + 1] -= d[i] * (a[i] / b[i]);
            b[i + 1] -= c[i] * a[i] / b[i];
        }
        for (int i = d.length - 2; i >= 0; --i) {
            d[i] -= d[i + 1] * c[i] / b[i + 1];
        }

        List<Double> res = new ArrayList<>(d.length);
        for (int i = 0; i < d.length; ++i) {
            res.add(d[i] / b[i]);
        }

        return res;
    }
}
