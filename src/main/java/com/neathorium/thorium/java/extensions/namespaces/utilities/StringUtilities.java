package com.neathorium.thorium.java.extensions.namespaces.utilities;

import com.neathorium.thorium.java.extensions.constants.StringUtilitiesConstants;
import com.neathorium.thorium.java.extensions.namespaces.predicates.BasicPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EqualsPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.GuardPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public interface StringUtilities {
    private static boolean parameterGuard(String object, String expected) {
        return NullablePredicates.isNotNull(object) && StringUtils.isNotEmpty(expected);
    }

    static boolean isInvisible(CharSequence sequence) {
        final var defaultValue = true;
        if (NullablePredicates.isNull(sequence)) {
            return defaultValue;
        }

        final var length = sequence.length();
        if (BasicPredicates.isZeroOrNonPositive(length)) {
            return defaultValue;
        }

        final var invisibleBlanks = StringUtilitiesConstants.INVISIBLE_BLANKS_SET;
        for (var index = 0; index < length; ++index) {
            final var currentChar = sequence.charAt(index);
            if (
                BooleanUtilities.isFalse(
                    Character.isWhitespace(currentChar) ||
                    BasicPredicates.isZeroOrNonPositive(String.valueOf(currentChar).strip().length()) ||
                    invisibleBlanks.contains(currentChar)
                )
            ) {
                return false;
            }
        }

        return defaultValue;
    }

    static boolean isVisible(CharSequence sequence) {
        return BooleanUtilities.isFalse(StringUtilities.isInvisible(sequence));
    }

    static boolean contains(String value, String expected) {
        return StringUtilities.parameterGuard(value, expected) && StringUtils.contains(value, expected);
    }

    static boolean startsWithCaseSensitive(String value, String expected) {
        return StringUtilities.parameterGuard(value, expected) && StringUtils.startsWith(value, expected);
    }

    static boolean startsWithCaseInsensitive(String value, String expected) {
        return StringUtilities.parameterGuard(value, expected) && StringUtils.startsWithIgnoreCase(value, expected);
    }

    static boolean endsWithCaseSensitive(String value, String expected) {
        return StringUtilities.parameterGuard(value, expected) && StringUtils.endsWith(value, expected);
    }

    static boolean endsWithCaseInsensitive(String value, String expected) {
        return StringUtilities.parameterGuard(value, expected) && StringUtils.endsWithIgnoreCase(value, expected);
    }

    static boolean startsNotWithCaseSensitive(String value, String expected) {
        return StringUtilities.parameterGuard(value, expected) && BooleanUtilities.isFalse(startsWithCaseSensitive(value, expected));
    }

    static boolean startsNotWithCaseInsensitive(String value, String expected) {
        return StringUtilities.parameterGuard(value, expected) && BooleanUtilities.isFalse(startsWithCaseInsensitive(value, expected));
    }

    static boolean endsNotWithCaseSensitive(String value, String expected) {
        return StringUtilities.parameterGuard(value, expected) && BooleanUtilities.isFalse(endsWithCaseSensitive(value, expected));
    }

    static boolean endsNotWithCaseInsensitive(String value, String expected) {
        return StringUtilities.parameterGuard(value, expected) && BooleanUtilities.isFalse(endsWithCaseInsensitive(value, expected));
    }

    static boolean uncontains(String value, String expected) {
        return StringUtilities.parameterGuard(value, expected) && BooleanUtilities.isFalse(StringUtils.contains(value, expected));
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

    static boolean areNotVisible(String... strings) {
        return GuardPredicates.areAll(StringUtilities::isInvisible, strings);
    }

    static boolean areAnyVisible(String... strings) {
        return GuardPredicates.areAny(StringUtilities::isVisible, strings);
    }

    static String castToString(Object o) {
        if (o instanceof String value) {
            return value;
        }

        final var returnValue = String.valueOf(o);
        return StringUtilities.isVisible(returnValue) && EqualsPredicates.isEqual(returnValue, "null") ? "" : returnValue;
    }

    static Predicate<String> contains(String expected) {
        return value -> StringUtilities.contains(value, expected);
    }

    static Predicate<String> uncontains(String expected) {
        return value -> StringUtilities.uncontains(value, expected);
    }

    static Predicate<String> containsValue(String value) {
        return expected -> StringUtilities.contains(value, expected);
    }

    static Predicate<String> uncontainsValue(String value) {
        return expected -> StringUtilities.uncontains(value, expected);
    }

    static Predicate<String> containsExpected(String expected) {
        return value -> StringUtilities.contains(value, expected);
    }

    static Predicate<String> uncontainsExpected(String expected) {
        return value -> StringUtilities.uncontains(value, expected);
    }

    static boolean valueContainsAny(String value, String[] expectedValues) {
        return GuardPredicates.areAny(StringUtilities.containsValue(value), expectedValues);
    }

    static boolean valueContainsNone(String value, String[] expectedValues) {
        return GuardPredicates.areAll(StringUtilities.uncontainsValue(value), expectedValues);
    }
}
