package ru.ifmo.plotti.co.send;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlotValueBuilder {
    public static int MAX_PLOTS_NUM = 9;

    private final OptionalDouble[] values = Stream.generate(OptionalDouble::empty).limit(MAX_PLOTS_NUM).
            toArray(OptionalDouble[]::new);

    public PlotValueBuilder add(PlotColor color, double val) {
        values[color.no] = OptionalDouble.of(val);
        return this;
    }

    String build() {
        Function<OptionalDouble, String> singleValue = op -> {
            if (op.isPresent())
                return String.valueOf(op.getAsDouble());
            return "";
        };
        return Arrays.stream(values)
                .map(singleValue)
                .collect(Collectors.joining(","));
    }
}
