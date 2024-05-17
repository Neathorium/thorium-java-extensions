package com.neathorium.thorium.java.extensions.namespaces.predicates;

import com.neathorium.thorium.java.extensions.constants.CardinalityDefaults;
import com.neathorium.thorium.java.extensions.namespaces.CardinalitiesFunctions;
import com.neathorium.thorium.java.extensions.records.CardinalityData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface GuardPredicates {

    private static <T, U> boolean areCore(List<T> list, U baseCondition, Function<U, Function<Boolean, Predicate<T>>> conditionHandler, CardinalityData data) {
        if (
            NullablePredicates.isNull(baseCondition) ||
            NullablePredicates.isNull(conditionHandler) ||
            NullablePredicates.isNull(data) ||
            ListPredicates.isEmpty(list)
        ) {
            return false;
        }

        final var condition = conditionHandler.apply(baseCondition).apply(data.INVERT());
        final var guardValue = data.GUARD_VALUE();
        final var finalValue = data.FINAL_VALUE();
        if (AmountPredicates.isSingle(list)) {
            return condition.test(list.getFirst()) ? guardValue : finalValue;
        }

        for(var current : list) {
            if (condition.test(current)) {
                return guardValue;
            }
        }

        return finalValue;
    }
    @SafeVarargs
    private static <T, U> boolean areCore(U baseCondition, Function<U, Function<Boolean, Predicate<T>>> conditionHandler, CardinalityData data, T... objects) {
        final var list = new ArrayList<T>();
        Collections.addAll(list, objects);
        return GuardPredicates.areCore(list, baseCondition, conditionHandler, data);
    }

    @SafeVarargs
    static <T> boolean are(Predicate<T> condition, CardinalityData conditionData, T... objects) {
        return GuardPredicates.areCore(condition, CardinalitiesFunctions::getPredicateForBoolean, conditionData, objects);
    }

    @SafeVarargs
    static <T> boolean are(Function<T, String> condition, CardinalityData conditionData, T... objects) {
        return GuardPredicates.areCore(condition, CardinalitiesFunctions::getPredicateForErrorString, conditionData, objects);
    }

    @SafeVarargs
    static <T> boolean areAll(Predicate<T> condition, T... objects) {
        return GuardPredicates.are(condition, CardinalityDefaults.ALL, objects);
    }

    @SafeVarargs
    static <T> boolean areAll(Function<T, String> condition, T... objects) {
        return GuardPredicates.are(condition, CardinalityDefaults.ALL, objects);
    }

    @SafeVarargs
    static <T> boolean areAny(Predicate<T> condition, T... objects) {
        return GuardPredicates.are(condition, CardinalityDefaults.ANY, objects);
    }

    @SafeVarargs
    static <T> boolean areNone(Predicate<T> condition, T... objects) {
        return GuardPredicates.are(condition, CardinalityDefaults.NONE, objects);
    }
}
