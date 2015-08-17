package com.ideaheap;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StreamsBasedCalculator {

    AtomicInteger calculations = new AtomicInteger(0);

    public Map<String, String> getStringTransition(
        final Set<String> stringSet,
        final Map<String, Double> stringCosts) {

        ConcurrentMap<String, String> result = stringCosts.keySet().parallelStream().collect(
            Collectors.toConcurrentMap(
                (state) -> state,
                (state) -> stringTransitionCost(state, stringSet, stringCosts)
            )
        );
        System.out.println("Total calculations: " + calculations.get());
        return result;
    }

    private String stringTransitionCost(
        final String state,
        final Set<String> stringSet,
        final Map<String, Double> expectedUtilities) {
        return stringSet.stream().max(
            Comparator.comparing(
                act -> calculateExpectedUtility(state, act, expectedUtilities)
            )
        ).get();
    }

    private Double calculateExpectedUtility(
        String state,
        String act,
        Map<String, Double> expectedUtilities) {

        return expectedUtilities
            .entrySet()
            .stream()
            .mapToDouble(
                n -> getStateProbability(state, act, n.getKey()) * n.getValue()
            ).sum();
    }

    private Double getStateProbability(String state, String act, String key) {
        calculations.incrementAndGet();
        return 5.0;
    }

}