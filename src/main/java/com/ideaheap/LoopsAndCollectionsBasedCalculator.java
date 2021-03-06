package com.ideaheap;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LoopsAndCollectionsBasedCalculator {

    AtomicInteger calculations = new AtomicInteger(0);

    public Map<String, String> getStringTransition(final Set<String> stringSet, final Map<String, Double> stringCosts) {

        ConcurrentMap<String, String> result = stringCosts.keySet().parallelStream().collect( //
                Collectors.toConcurrentMap( //
                        (state) -> state, //
                        (state) -> stringTransitionCost(state, stringSet, stringCosts)));
        System.out.println("Total calculations: " + calculations.get());
        return result;
    }

    private String stringTransitionCost(final String state, final Set<String> stringSet,
            final Map<String, Double> expectedUtilities) {

        String maxString = null;
        double maxStringValue = Double.NEGATIVE_INFINITY;

        for (String str : stringSet) {

            double stringValue = 0.0;
            for (Map.Entry<String, Double> entry : expectedUtilities.entrySet()) {
                stringValue += getStateProbability(state, str, entry.getKey()) * entry.getValue();
            }
            if (maxString == null || stringValue > maxStringValue) {
                maxStringValue = stringValue;
                maxString = str;
            }

        }
        return maxString;
    }

    private Double getStateProbability(String state, String act, String key) {
        calculations.incrementAndGet();
        return 5.0;
    }

}
