package ru.ifmo.data;

import java.util.List;

/**
 * System parameters at single slice of time
 */
public class TSection {
    /**
     * z slices
     */
    public final List<ZSection> zs;

    /**
     * front speed. This is am optional feature
     */
    public final double VF;

    public TSection(List<ZSection> zs) {
        this.zs = zs;

        // who will dare to count it?
        this.VF = 0;
    }
}
