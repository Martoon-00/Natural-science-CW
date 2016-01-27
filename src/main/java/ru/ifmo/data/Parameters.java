package ru.ifmo.data;

/**
 * Equation parameters which may vary
 */
public class Parameters {
    /**
     * Computational parameters
     */
    public final double alpha;

    /**
     * Physical parameters
     */
    public final double dt;
    public final double dz;
    public final int xNum;  // number of x stapes, equals to 1 / dx

    // TODO: add as many as you can!
    // TODO: but only variable ones
}
