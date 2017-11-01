package com.learn;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MontyHallProblemTest {
    int numberOfIterations = 1_000_000;
    int numberOfItems = 8;
    int numberOfItemsToRemove = 1;

    @Test
    public void testMontyHallGetAlwaysFirstChoice() throws Exception {
        execute(numberOfIterations, numberOfItems, numberOfItemsToRemove, Strategy.GET_ALWAYS_FIRST_CHOICE);
    }

    @Test
    public void testMontyHallAlwaysDifferentFromTheFirstChoice() throws Exception {
        execute(numberOfIterations, numberOfItems, numberOfItemsToRemove, Strategy.GET_ALWAYS_DIFFERENT_FROM_FIRST_CHOICE);
    }

    public static void execute(int numberOfIterations, int numberOfItems, int numberOfItemsToRemove, Strategy strategy) throws Exception {
        List<Boolean> result = new ArrayList<>();

        for (int j = 0; j < numberOfIterations; j++) {
            MontyHallProblem b = new MontyHallProblem(numberOfItems, numberOfItemsToRemove, strategy);
            b.pickUpAnIndex();
            result.add(b.checkResult());
        }
        BigDecimal value = new BigDecimal((result.stream().filter(it -> it == true).count()));
        value = value.divide(new BigDecimal(result.size())).multiply(new BigDecimal(100));

        System.out.println(String.format("Percentage = %4.3f %%", value));
    }

}
