package ru.ifmo.data;

/**
 * Equation parameters which may vary
 */
public class Parameters {
    /**
     * Constant parameters
     */
    public static final double K = 1.6e6;

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
