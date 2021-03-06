package com.ideaheap;

import com.ideaheap.StreamsBasedCalculator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

/**
 * Created by nwertzberger on 8/6/15.
 */
public class StreamsTests {

    private static Map<String, Double> stringMap = new HashMap<>();
    private static Set<String> stringSet = new HashSet<>();

    @BeforeClass
    public static void setUp() throws Exception {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            stringMap.put(UUID.randomUUID().toString(), random.nextDouble());
        }
        for (int i = 0; i < 100; i++) {
            stringSet.add(UUID.randomUUID().toString());
        }
        System.out.println("Setup complete");
    }

    @Test
    public void testStreams() throws Exception {
        StreamsBasedCalculator calculator = new StreamsBasedCalculator();
        Map<String, String> stringTransition = calculator.getStringTransition(
            stringSet,
            stringMap
        );
    }

    @Test
    public void testArrays() throws Exception {
        LoopsBasedCalculator calculator = new LoopsBasedCalculator();
        Map<String, String> stringTransition = calculator.getStringTransition(
            stringSet,
            stringMap
        );
    }

    @Test
    public void testCollectionLoops() throws Exception {
        LoopsAndCollectionsBasedCalculator calculator = new LoopsAndCollectionsBasedCalculator();
        Map<String, String> stringTransition = calculator.getStringTransition(
            stringSet,
            stringMap
        );
    }

}
