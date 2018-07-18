package com.github.cranite.MoreEnchantables.Misc;

import java.util.concurrent.ThreadLocalRandom;

public class Random {

    public static Integer randomIntRange(Integer min, Integer max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static Float randomFloat() {
        return ThreadLocalRandom.current().nextFloat();
    }

    public static Boolean executeProbability(Integer value, Integer maximum) {
        // If value is above maximum, it's guaranteed
        // As 75/50 is 150% chance of occurrence
        if (value >= maximum) {
            return true;
        }
        // Generate a random number between, say, 0-50
        // If that number is between 0 and the value
        // then return an occurrence, otherwise false
        Integer randomIndex = randomIntRange(0, maximum);
        return randomIndex <= value;
    }

}

