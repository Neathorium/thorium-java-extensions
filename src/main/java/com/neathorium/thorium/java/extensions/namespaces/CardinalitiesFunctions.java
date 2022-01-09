package com.neathorium.thorium.java.extensions.namespaces;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;
import java.util.function.Predicate;

public interface CardinalitiesFunctions {
    private static <T> Predicate<T> noopBoolean(Predicate<T> object) {
        return object;
    }

    private static <T> Predicate<T> invertBoolean(Predicate<T> object) {
        return object.negate();
    }

    private static <T, U> U getFor(boolean condition, T base, Function<T, U> positive, Function<T, U> negative) {
        return (condition ? positive : negative).apply(base);
    }

    private static <T> Predicate<T> getPredicateForBoolean(boolean invert, Predicate<T> base) {
        return getFor(invert, base, CardinalitiesFunctions::invertBoolean, CardinalitiesFunctions::noopBoolean);
    }

    private static <T> Predicate<T> getPredicateForErrorString(boolean invert, Function<T, String> base) {
        Function<String, Boolean> operation;
        if (invert) {
            operation = StringUtils::isNotBlank;
        } else {
            operation = StringUtils::isBlank;
        }

        return base.andThen(operation)::apply;
    }

    static <T> Function<Boolean, Predicate<T>> getPredicateForBoolean(Predicate<T> base) {
        return invert -> getPredicateForBoolean(invert, base);
    }

    static <T> Function<Boolean, Predicate<T>> getPredicateForErrorString(Function<T, String> base) {
        return invert -> getPredicateForErrorString(invert, base);
    }
}
