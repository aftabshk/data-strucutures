package org.example.functional_interfaces;

@FunctionalInterface
public interface HeuristicFunction<T extends Object> {

    Integer estimate(T goal, T node);
}
