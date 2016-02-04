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
     * speed of concentration change
     */
    public final double VX;

    public ZSection(double T, double X, double VX) {
        this.T = T;
        this.X = X;
        this.VX = VX;
    }
}
