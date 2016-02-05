package ru.ifmo.server;

import ru.ifmo.data.TSection;

import java.util.List;
import java.util.stream.Collectors;

public class ServerHelper {
    public static String solutionToString(List<TSection> solution) {
        return solution.stream()
                .map(tSection -> tSection.zs.stream()
                                .map(x -> String.format("%f %f %f", x.T, x.X, x.VX))
                                .collect(Collectors.joining("\n"))
                        + "\n" + tSection.VF
                ).collect(Collectors.joining("\n\n"));
    }


}
