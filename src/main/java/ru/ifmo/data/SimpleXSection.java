package ru.ifmo.data;

/**
 * System parameters which are directly calculated by solver
 */
public class SimpleXSection {
    public final double T;
    public final double X;

    public SimpleXSection(double T, double X) {
        this.T = T;
        this.X = X;
    }
}
