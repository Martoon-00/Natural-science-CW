package ru.ifmo.data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * System parameters at single slice of time
 */
public class TSection {
    /**
     * x sections
     */
    public final List<ZSection> xs;

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

    public TSection(List<ZSection> xs, double XF, double VF) {
        this.xs = xs;
        this.XF = XF;
        this.VF = VF;
    }

    public static List<TSection> extend(List<SimpleTSection> sections) {
        //TODO: calculate TSection's basing on SimpleTSection's

        // this is a stub, which evaluates no new values
        Function<SimpleZSection, ZSection> mapXSection = section ->
                new ZSection(section.T, section.X, 0);
        return sections.stream()
                .map(tSection -> new TSection(
                        tSection.xs.stream()
                                .map(mapXSection)
                                .collect(Collectors.toList()),
                        0,
                        0
                ))
                .collect(Collectors.toList());
    }
}
