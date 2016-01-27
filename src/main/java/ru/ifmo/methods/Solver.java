package ru.ifmo.methods;

import ru.ifmo.data.Parameters;

public interface Solver {
    // TODO: I guess, this one should return something more meaningful
    void solve(int totalSteps, Parameters params);
}
