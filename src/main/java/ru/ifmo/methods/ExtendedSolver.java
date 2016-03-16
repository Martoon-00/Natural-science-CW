package ru.ifmo.methods;

import ru.ifmo.data.Parameters;
import ru.ifmo.data.TSection;

import java.util.List;

public interface ExtendedSolver {
    List<TSection> extendedSolve(int totalSteps, Parameters params);
}
