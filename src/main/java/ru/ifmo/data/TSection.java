package ru.ifmo.data;

import java.util.ArrayList;
import java.util.List;

/**
 * System parameters at single slice of time
 */
public class TSection {
    /**
     * x sections
     */
    public final List<XSection> xs;

    /**
     * front position.
     * actually we don't need to display this, but it's required
     * for computation of front speed. So we remain it in debug purposes
     */
    public final double XF;

    /**
     * front speed
     */
    public final double VF;

    public TSection(List<XSection> xs, double XF, double VF) {
        this.xs = xs;
        this.XF = XF;
        this.VF = VF;
    }

    public List<TSection> extend(List<SimpleTSection> sections) {
        //TODO: calculate TSection's basing on SimpleTSection's

        return new ArrayList<>();
    }
}
