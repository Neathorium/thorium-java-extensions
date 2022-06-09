package com.neathorium.thorium.java.extensions.namespaces.predicates;

import com.neathorium.thorium.java.extensions.constants.CardinalityDefaults;
import com.neathorium.thorium.java.extensions.namespaces.CardinalitiesFunctions;
import com.neathorium.thorium.java.extensions.records.CardinalityData;

import java.util.function.Function;
import java.util.function.Predicate;

public interface GuardPredicates {
    @SafeVarargs
    private static <T, U> boolean areCore(U baseCondition, Function<U, Function<Boolean, Predicate<T>>> conditionHandler, CardinalityData data, T... objects) {
        if (
            NullablePredicates.isNull(baseCondition) ||
            NullablePredicates.isNull(conditionHandler) ||
            NullablePredicates.isNull(data) ||
            NullablePredicates.isNull(objects)
        ) {
            return false;
        }

        final var condition = conditionHandler.apply(baseCondition).apply(data.INVERT());
        final var guardValue = data.GUARD_VALUE();
        final var finalValue = data.FINAL_VALUE();
        if (AmountPredicates.isSingle(objects)) {
            final var onlyIndex = 0;
            return condition.test(objects[onlyIndex]) ? guardValue : finalValue;
        }

        for(var current : objects) {
            if (condition.test(current)) {
                return guardValue;
            }
        }

        return finalValue;
    }

    @SafeVarargs
    static <T> boolean are(Predicate<T> condition, CardinalityData conditionData, T... objects) {
        return areCore(condition, CardinalitiesFunctions::getPredicateForBoolean, conditionData, objects);
    }

    @SafeVarargs
    static <T> boolean are(Function<T, String> condition, CardinalityData conditionData, T... objects) {
        return areCore(condition, CardinalitiesFunctions::getPredicateForErrorString, conditionData, objects);
    }

    @SafeVarargs
    static <T> boolean areAll(Predicate<T> condition, T... objects) {
        return are(condition, CardinalityDefaults.ALL, objects);
    }

    @SafeVarargs
    static <T> boolean areAll(Function<T, String> condition, T... objects) {
        return are(condition, CardinalityDefaults.ALL, objects);
    }

    @SafeVarargs
    static <T> boolean areAny(Predicate<T> condition, T... objects) {
        return are(condition, CardinalityDefaults.ANY, objects);
    }

    @SafeVarargs
    static <T> boolean areNone(Predicate<T> condition, T... objects) {
        return are(condition, CardinalityDefaults.NONE, objects);
    }
}
