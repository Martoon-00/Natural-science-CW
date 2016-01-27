package ru.ifmo.methods;

import ru.ifmo.data.Parameters;
import ru.ifmo.data.SimpleTSection;

import java.util.List;

public interface Solver {
    List<SimpleTSection> solve(int totalSteps, Parameters params);
}
