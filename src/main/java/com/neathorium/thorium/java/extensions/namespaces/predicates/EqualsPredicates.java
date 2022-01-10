package com.neathorium.thorium.java.extensions.namespaces.predicates;

import java.util.Objects;

public interface EqualsPredicates {
    static boolean isEqual(Object a, Object b) {
        return Objects.equals(a, b);
    }

    static boolean isNotEqual(Object a, Object b) {
        return !isEqual(a, b);
    }
}
