package ru.ifmo.data;

/**
 * System parameters at single point of space and time
 */
public class XSection {
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

    public XSection(double T, double X, double VX) {
        this.T = T;
        this.X = X;
        this.VX = VX;
    }
}
