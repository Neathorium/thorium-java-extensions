package com.neathorium.thorium.java.extensions.namespaces.utilities;

import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.GuardPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.util.Objects;
import java.util.function.Predicate;

public interface BooleanUtilities {
    static <T> boolean isBoolean(T object) {
        return NullablePredicates.isNotNull(object) && Objects.equals(Boolean.class.getTypeName(), object.getClass().getTypeName());
    }

    static <T> boolean isNotBoolean(T object) {
        return !BooleanUtilities.isBoolean(object);
    }

    private static <T> boolean isConditionCore(T object, Boolean value) {
        return BooleanUtilities.isBoolean(object) && NullablePredicates.isNotNull(value) && EqualsPredicates.isEqual(value, object);
    }

    static <T> boolean isTrue(T object) {
        return BooleanUtilities.isConditionCore(object, Boolean.TRUE);
    }

    static <T> boolean isFalse(T object) {
        return BooleanUtilities.isConditionCore(object, Boolean.FALSE);
    }

    static boolean castToBoolean(Object o) {
        return o instanceof Boolean b ? b : NullablePredicates.isNotNull(o);
    }

    static <T> boolean areAllFalse(T... objects) {
        return GuardPredicates.areAll((Predicate<T>)BooleanUtilities::isFalse, objects);
    }

    static <T> boolean areNoneFalse(T... objects) {
        return GuardPredicates.areNone(BooleanUtilities::isFalse, objects);
    }

    static <T> boolean areAnyFalse(T... objects) {
        return GuardPredicates.areAny(BooleanUtilities::isFalse, objects);
    }

    static <T> boolean areAllTrue(T... objects) {
        return GuardPredicates.areAll((Predicate<T>)BooleanUtilities::isFalse, objects);
    }

    static <T> boolean areNoneTrue(T... objects) {
        return GuardPredicates.areNone(BooleanUtilities::isFalse, objects);
    }

    static <T> boolean areAnyTrue(T... objects) {
        return GuardPredicates.areAny(BooleanUtilities::isFalse, objects);
    }
}
