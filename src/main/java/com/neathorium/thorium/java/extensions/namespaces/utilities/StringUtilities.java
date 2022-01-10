package com.neathorium.thorium.java.extensions.namespaces.utilities;

import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.GuardPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public interface StringUtilities {
    private static boolean parameterGuard(String object, String expected) {
        return NullablePredicates.isNotNull(object) && isNotEmpty(expected);
    }

    static boolean contains(String value, String expected) {
        return parameterGuard(value, expected) && StringUtils.contains(value, expected);
    }

    static boolean startsWithCaseSensitive(String value, String expected) {
        return parameterGuard(value, expected) && StringUtils.startsWith(value, expected);
    }

    static boolean startsWithCaseInsensitive(String value, String expected) {
        return parameterGuard(value, expected) && StringUtils.startsWithIgnoreCase(value, expected);
    }

    static boolean endsWithCaseSensitive(String value, String expected) {
        return parameterGuard(value, expected) && StringUtils.endsWith(value, expected);
    }

    static boolean endsWithCaseInsensitive(String value, String expected) {
        return parameterGuard(value, expected) && StringUtils.endsWithIgnoreCase(value, expected);
    }

    static boolean startsNotWithCaseSensitive(String value, String expected) {
        return parameterGuard(value, expected) && BooleanUtilities.isFalse(startsWithCaseSensitive(value, expected));
    }

    static boolean startsNotWithCaseInsensitive(String value, String expected) {
        return parameterGuard(value, expected) && BooleanUtilities.isFalse(startsWithCaseInsensitive(value, expected));
    }

    static boolean endsNotWithCaseSensitive(String value, String expected) {
        return parameterGuard(value, expected) && BooleanUtilities.isFalse(endsWithCaseSensitive(value, expected));
    }

    static boolean endsNotWithCaseInsensitive(String value, String expected) {
        return parameterGuard(value, expected) && BooleanUtilities.isFalse(endsWithCaseInsensitive(value, expected));
    }

    static boolean uncontains(String value, String expected) {
        return parameterGuard(value, expected) && BooleanUtilities.isFalse(StringUtils.contains(value, expected));
    }

    static boolean isStringMatchesPattern(String string, String regex) {
        return Pattern.matches(regex, string);
    }

    static boolean areNotBlank(String... strings) {
        return GuardPredicates.areAll(StringUtils::isNotBlank, strings);
    }

    static boolean areAnyBlank(String... strings) {
        return GuardPredicates.areAny(StringUtils::isBlank, strings);
    }

    static String castToString(Object o) {
        if (o instanceof String value) {
            return value;
        }

        final var returnValue = String.valueOf(o);
        return isNotBlank(returnValue) && EqualsPredicates.isEqual(returnValue, "null") ? "" : returnValue;
    }

    static Predicate<String> contains(String expected) {
        return (value) -> contains(value, expected);
    }

    static Predicate<String> uncontains(String expected) {
        return (value) -> uncontains(value, expected);
    }
}
