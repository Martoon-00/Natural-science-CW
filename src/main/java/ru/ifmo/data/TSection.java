package ru.ifmo.data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public static List<TSection> extend(List<SimpleTSection> sections) {
        //TODO: in ZSections calculate VX(t, x) as V(t, x) - V(t - 1, x)

        // this is a stub, which evaluates no new values
        Function<List<SimpleZSection>, List<ZSection>> mapXSections = zSections -> zSections.stream()
                .map(section -> new ZSection(section.T, section.X, 0))
                .collect(Collectors.toList());
        return sections.stream()
                .map(simpleTSection ->
                        new TSection(mapXSections.apply(simpleTSection.zs)))
                .collect(Collectors.toList());
    }
}
