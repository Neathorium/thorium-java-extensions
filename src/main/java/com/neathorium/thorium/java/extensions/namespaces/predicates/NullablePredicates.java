package com.neathorium.thorium.java.extensions.namespaces.predicates;

import java.util.Objects;

public interface NullablePredicates {
    static boolean isNull(Object object) {
        return Objects.isNull(object);
    }

    static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    @SafeVarargs
    static <T> boolean areNotNull(T... objects) {
        if (
            NullablePredicates.isNull(objects) ||
            BasicPredicates.isZeroOrNonPositive(objects.length)
        ) {
            return false;
        }

        return GuardPredicates.areNone(NullablePredicates::isNull, objects);
    }

    @SafeVarargs
    static <T> boolean areNull(T... objects) {
        if (
            NullablePredicates.isNull(objects) ||
            BasicPredicates.isZeroOrNonPositive(objects.length)
        ) {
            return true;
        }

        return GuardPredicates.areAll(NullablePredicates::isNull, objects);
    }

    @SafeVarargs
    static <T> boolean areAnyNull(T... objects) {
        return NullablePredicates.isNull(objects) || BasicPredicates.isZeroOrNonPositive(objects.length) || GuardPredicates.areAny(NullablePredicates::isNull, objects);
    }
}
