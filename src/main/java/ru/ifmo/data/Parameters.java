package ru.ifmo.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Equation parameters which may vary
 */
public class Parameters {
    public static final Map<String, String> defaults = new HashMap<String, String>() {{
        put("R", "8.134");
        put("Q", "7e5");
        put("rho", "830");
        put("T0", "293");
        put("C", "1980");
        put("Tm", "T0 + Q / C");
        put("lambda", "0.13");
        put("kappa", "lambda / (rho * C)");
        put("K", "1.6e6");
        put("E", "8e4");
        put("D", "7.9104296e-8");
        put("alpha", "1");
        put("l", "1");
    }};

    public final double R;
    public final double Q;
    public final double rho;
    public final double T0;
    public final double C;
    public final double Tm;
    public final double lambda;
    public final double kappa;

    public final double alpha;
    public final double dt;
    public final double dz;
    private final double l;  // out of use. Consider zNum instead

    // Number of z steps, approximately equals to 1 / dz.
    // We wish to count or get this here, because there are different ways to perform a rounding of the fraction
    public final int zNum;

    public final double K;
    public final double E;
    //// TODO:  D = 8e-12
    public final double D;

    public Parameters(double r, double q, double rho, double t0, double c, double tm, double lambda, double kappa, double alpha, double dt, double dz, double l, int zNum, double k, double e, double d) {
        R = r;
        Q = q;
        this.rho = rho;
        T0 = t0;
        C = c;
        Tm = tm;
        this.lambda = lambda;
        this.kappa = kappa;
        this.alpha = alpha;
        this.dt = dt;
        this.dz = dz;
        this.l = l;
        this.zNum = zNum;
        K = k;
        E = e;
        D = d;
    }
}
