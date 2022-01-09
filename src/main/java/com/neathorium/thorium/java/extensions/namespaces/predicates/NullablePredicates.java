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
        return GuardPredicates.areNone(NullablePredicates::isNull, objects);
    }

    @SafeVarargs
    static <T> boolean areNull(T... objects) {
        return GuardPredicates.areAll(NullablePredicates::isNull, objects);
    }

    @SafeVarargs
    static <T> boolean areAnyNull(T... objects) {
        return GuardPredicates.areAny(NullablePredicates::isNull, objects);
    }
}
