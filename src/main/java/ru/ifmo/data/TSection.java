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
     * front speed
     */
    public final double VF;

    public TSection(List<XSection> xs, double VF) {
        this.xs = xs;
        this.VF = VF;
    }


    public List<TSection> extend(List<SimpleTSection> sections) {
        //TODO: calculate TSection's basing on SimpleTSection's


        return new ArrayList<>();
    }
}
