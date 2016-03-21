package ru.ifmo.server;

import ru.ifmo.data.Parameters;
import ru.ifmo.data.TSection;

import java.util.List;
import java.util.stream.Collectors;

public class ServerHelper {
    public static String solutionToString(List<TSection> solution) {
        return solution.stream()
                .map(tSection -> tSection.zs.stream()
                                .map(x -> String.format("%.3e %.3e %.3e", x.T, x.X, x.W))
                                .collect(Collectors.joining("\n"))
                        + "\n" + tSection.VF
                ).collect(Collectors.joining("\n\n"));
    }

    public static String defaultParamsString() {
        return Parameters.defaults.entrySet().stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining(","));
    }
}
