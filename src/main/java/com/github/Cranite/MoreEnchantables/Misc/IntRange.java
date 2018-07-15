package com.github.Cranite.MoreEnchantables.Misc;

public class IntRange {

    public final Integer minimum;
    public final Integer maximum;

    public IntRange(Integer minimum, Integer maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public Boolean betweenRange(Integer val) {
        return val > minimum && val < maximum;
    }

    public Boolean inRange(Integer val) {
        return val >= minimum && val <= maximum;
    }

}
