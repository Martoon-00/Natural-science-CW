package ru.ifmo.data;

/**
 * Equation parameters which may vary
 */
public class Parameters {
    /**
     * Constant parameters
     */
    public static final double K = 1.6e6;
    public static final double E = 8e4;
    public static final double R = 8.314;
    public static final double Q = 7e5;
    public static final double rho = 830;
    public static final double T0 = 293;
    public static final double C = 1980;
    public static final double lambda = 0.13;

    //// TODO:  D = 8e-12 
    public static final double D = 7.9104296e-8;

    /**
     * Varying parameters
     */
    public final double alpha;
    public final double dt;
    public final double dz;

    // Number of z steps, approximately equals to 1 / dz.
    // We wish to count or get this here, because there are different ways to perform a rounding of the fraction
    public final int zNum;

    // TODO: add as many as you can!


    public Parameters(double alpha, double dt, double dz, int zNum) {
        this.alpha = alpha;
        this.dt = dt;
        this.dz = dz;
        this.zNum = zNum;
    }
}
