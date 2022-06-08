package com.neathorium.thorium.java.extensions.namespaces.predicates;

import java.util.Objects;
import java.util.function.Predicate;

public interface BasicPredicates {
    static boolean isBiggerThan(long value, long limit) {
        return value > limit;
    }

    static boolean isNonNegative(int value) {
        return isBiggerThan(value, -1);
    }

    static boolean isNegative(int value) {
        return isBiggerThan(0, value);
    }

    static boolean isPositiveNonZero(int value) {
        return isBiggerThan(value, 0);
    }

    static boolean isSmallerThan(long value, long limit) {
        return value < limit;
    }

    static boolean isZero(int value) {
        return Objects.equals(value, 0);
    }

    static boolean isNonZero(int value) {
        return !isZero(value);
    }

    static boolean isZeroOrNonPositive(int value) {
        return isSmallerThan(value, 1);
    }

    static boolean areAnyNegative(Integer... values) {
        final Predicate<Integer> condition = BasicPredicates::isNegative;
        return GuardPredicates.areAny(condition, values);
    }

}
