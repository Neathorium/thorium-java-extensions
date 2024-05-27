package com.neathorium.thorium.java.extensions.namespaces.predicates;

import com.neathorium.thorium.java.extensions.namespaces.VarargsFunctions;

import java.util.Objects;

public interface NullablePredicates {
    static boolean isNull(Object object) {
        return Objects.isNull(object);
    }

    static boolean isNotNull(Object object) {
        return Objects.equals(Boolean.FALSE, NullablePredicates.isNull(object));
    }

    @SafeVarargs
    static <T> boolean areNotNull(T... objects) {
        final var earlyValue = VarargsFunctions.handleSimple(false, true, objects);
        return earlyValue && GuardPredicates.areAll(NullablePredicates::isNotNull, objects);
    }

    @SafeVarargs
    static <T> boolean areNull(T... objects) {
        final var earlyValue = VarargsFunctions.handleSimple(true, false, objects);
        if (earlyValue) {
            return true;
        }
        return GuardPredicates.areAll(NullablePredicates::isNull, objects);
    }

    @SafeVarargs
    static <T> boolean areAnyNull(T... objects) {
        final var earlyValue = VarargsFunctions.handleSimple(true, false, objects);
        if (earlyValue) {
            return true;
        }

        return GuardPredicates.areAny(NullablePredicates::isNull, objects);
    }
}
