package com.neathorium.thorium.java.extensions.namespaces.utilities;

import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.util.Objects;

public interface BooleanUtilities {
    static <T> boolean isBoolean(T object) {
        return NullablePredicates.isNotNull(object) && Objects.equals(Boolean.class.getTypeName(), object.getClass().getTypeName());
    }

    static <T> boolean isNotBoolean(T object) {
        return !isBoolean(object);
    }

    private static <T> boolean isConditionCore(T object, Boolean value) {
        return isBoolean(object) && NullablePredicates.isNotNull(value) && EqualsPredicates.isEqual(value, object);
    }

    static <T> boolean isTrue(T object) {
        return isConditionCore(object, Boolean.TRUE);
    }

    static <T> boolean isFalse(T object) {
        return isConditionCore(object, Boolean.FALSE);
    }

    static boolean castToBoolean(Object o) {
        return o instanceof Boolean b ? b : NullablePredicates.isNotNull(o);
    }
}
