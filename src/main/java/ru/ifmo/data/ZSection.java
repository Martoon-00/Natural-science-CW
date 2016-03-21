package ru.ifmo.data;

/**
 * System parameters at single point of space and time
 */
public class ZSection {
    /**
     * temperature
     */
    public final double T;

    /**
     * concentration
     */
    public final double X;

    /**
     * speed of react
     */
    public final double W;

    public ZSection(double T, double X, double W) {
        this.T = T;
        this.X = X;
        this.W = W;
    }
}
