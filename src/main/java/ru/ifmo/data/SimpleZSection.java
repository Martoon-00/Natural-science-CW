package ru.ifmo.data;

/**
 * System parameters which are directly calculated by solver
 */
public class SimpleZSection {
    public final double T;
    public final double X;

    public SimpleZSection(double T, double X) {
        this.T = T;
        this.X = X;
    }
}
