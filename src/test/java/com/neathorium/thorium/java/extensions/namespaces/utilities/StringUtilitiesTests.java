package com.neathorium.thorium.java.extensions.namespaces.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class StringUtilitiesTests {
    public static Stream<Arguments> containsProvider() {
        return Stream.of(
            Arguments.of("Null doesn't contain null in String terms", null, null, false, "Null contains null"),
            Arguments.of("Null doesn't contain empty string(\"\") in String terms", null, "", false, "Null contains empty string(\"\")"),
            Arguments.of("Empty string(\"\") doesn't contain null in String terms", "", null, false, "Empty string(\"\") contains null"),
            Arguments.of("Empty string(\"\") doesn't contain empty string(\"\") in String terms", "", "", false, "Empty string(\"\") contains empty string(\"\")"),
            Arguments.of("Empty string(\"\") doesn't contain single space string(\" \") in String terms", "", " ", false, "Empty string(\"\") contains single space string(\" \")"),
            Arguments.of("Single space string(\" \") contains single space string(\" \") in String terms", " ", " ", true, "Single space string(\"\") doesn't contain single space string(\" \")"),
            Arguments.of("Double space string(\"  \") contains single space string(\" \") in String terms", " ", " ", true, "Double space string(\"\") doesn't contain single space string(\" \")"),
            Arguments.of("String(\"Johnny applesauce\") contains single a string(\"a\") in String terms", " ", " ", true, "String(\"Johnny applesauce\") doesn't contain single a string(\"a\")")
        );
    }

    public static Stream<Arguments> startsWithCaseSensitiveProvider() {
        return Stream.of(
            Arguments.of("Single space string(\" \") starts with single space string(\" \") in String terms", " ", " ", true, "Single space string(\"\") doesn't contain single space string(\" \")"),
            Arguments.of("Double space string(\"  \") starts with single space string(\" \") in String terms", " ", " ", true, "Double space string(\"\") doesn't contain single space string(\" \")"),
            Arguments.of("_1234 string starts with _", "_1234", "_", true, "_1234 string doesn't start with _"),
            Arguments.of("_1234 string starts with _1", "_1234", "_1", true, "_1234 string doesn't start with _1"),
            Arguments.of("_1234 string starts with _12", "_1234", "_12", true, "_1234 string doesn't start with _12"),
            Arguments.of("_1234 string starts with _123", "_1234", "_123", true, "_1234 string doesn't start with _123"),
            Arguments.of("_1234 string starts with _1234", "_1234", "_1234", true, "_1234 string doesn't start with _1234"),
            Arguments.of("_Aa34 string starts with _", "_Aa34", "_", true, "_Aa34 string doesn't start with _"),
            Arguments.of("_Aa34 string starts with _A", "_Aa34", "_A", true, "_Aa34 string doesn't start with _A"),
            Arguments.of("_Aa34 string starts with _Aa", "_Aa34", "_Aa", true, "_Aa34 string doesn't start with _Aa"),
            Arguments.of("_Aa34 string starts with _Aa3", "_Aa34", "_Aa3", true, "_Aa34 string doesn't start with _Aa3"),
            Arguments.of("_Aa34 string starts with _Aa34", "_Aa34", "_Aa34", true, "_Aa34 string doesn't start with _Aa34"),
            Arguments.of("_AA34 starts with _", "_AA34", "_", true, "_AA34 string doesn't start with _"),
            Arguments.of("_AA34 starts with _A", "_AA34", "_A", true, "_AA34 string doesn't start with _A"),

            Arguments.of("Null doesn't start with null", null, null, false, "Null starts with null."),
            Arguments.of("Null doesn't start with empty string", null, "", false, "Null starts with empty string."),
            Arguments.of("Empty string doesn't start with null", "", null, false, "Empty string starts with null"),
            Arguments.of("Empty string doesn't start with empty string", "", "", false, "Empty string starts with empty string"),
            Arguments.of("Null doesn't start with a single space string(\" \")", null, " ", false, "Null starts with a single space string(\" \")."),
            Arguments.of("Null doesn't start with string(\" a\")", null, " a", false, "Null starts with string(\" a\")."),
            Arguments.of("Null doesn't start with String terms", null, null, false, "Null contains null"),
            Arguments.of("Null doesn't start with String(\"\") in String terms", null, "", false, "Null contains empty string(\"\")"),
            Arguments.of("Empty string(\"\") doesn't start with in String terms", "", null, false, "Empty string(\"\") contains null"),
            Arguments.of("Empty string(\"\") doesn't start with empty string(\"\") in String terms", "", "", false, "Empty string(\"\") contains empty string(\"\")"),
            Arguments.of("Empty string(\"\") doesn't start with single space string(\" \") in String terms", "", " ", false, "Empty string(\"\") contains single space string(\" \")"),
            Arguments.of("String(\"Johnny applesauce\") doesn't start with a single a string(\"a\") in String terms", "Johnny applesauce", "a", false, "String(\"Johnny applesauce\") starts with a single a string(\"a\")"),
            Arguments.of("_1234 string doesn't start with _12345", "_1234", "_12345", false, "_1234 string starts with _12345"),
            Arguments.of("_Aa34 string doesn't start with _Aa345", "_Aa34", "_Aa345", false, "_Aa34 string starts with _Aa345"),
            Arguments.of("_AA34 doesn't start with _Aa", "_AA34", "_Aa", false, "_AA34 string doesn't start with _Aa"),
            Arguments.of("_AA34 string doesn't start with _Aa3", "_AA34", "_Aa3", false, "_AA34 string starts with _Aa3"),
            Arguments.of("_AA34 string doesn't start with _Aa34", "_AA34", "_Aa34", false, "_AA34 string starts with _Aa34"),
            Arguments.of("_AA34 string doesn't start with _Aa345", "_AA34", "_12345", false, "_Aa34 string starts with _12345")
        );
    }

    public static Stream<Arguments> startsNotWithCaseSensitiveProvider() {
        return Stream.of(
            Arguments.of("Single space string(\" \") starts with single space string(\" \") in String terms", " ", " ", false, "Single space string(\"\") doesn't contain single space string(\" \")"),
            Arguments.of("Double space string(\"  \") starts with single space string(\" \") in String terms", " ", " ", false, "Double space string(\"\") doesn't contain single space string(\" \")"),
            Arguments.of("_1234 string starts with _", "_1234", "_", false, "_1234 string doesn't start with _"),
            Arguments.of("_1234 string starts with _1", "_1234", "_1", false, "_1234 string doesn't start with _1"),
            Arguments.of("_1234 string starts with _12", "_1234", "_12", false, "_1234 string doesn't start with _12"),
            Arguments.of("_1234 string starts with _123", "_1234", "_123", false, "_1234 string doesn't start with _123"),
            Arguments.of("_1234 string starts with _1234", "_1234", "_1234", false, "_1234 string doesn't start with _1234"),
            Arguments.of("_Aa34 string starts with _", "_Aa34", "_", false, "_Aa34 string doesn't start with _"),
            Arguments.of("_Aa34 string starts with _A", "_Aa34", "_A", false, "_Aa34 string doesn't start with _A"),
            Arguments.of("_Aa34 string starts with _Aa", "_Aa34", "_Aa", false, "_Aa34 string doesn't start with _Aa"),
            Arguments.of("_Aa34 string starts with _Aa3", "_Aa34", "_Aa3", false, "_Aa34 string doesn't start with _Aa3"),
            Arguments.of("_Aa34 string starts with _Aa34", "_Aa34", "_Aa34", false, "_Aa34 string doesn't start with _Aa34"),
            Arguments.of("_AA34 starts with _", "_AA34", "_", false, "_AA34 string doesn't start with _"),
            Arguments.of("_AA34 starts with _A", "_AA34", "_A", false, "_AA34 string doesn't start with _A"),

            Arguments.of("Null is false", null, null, false, "Null isn't false."),
            Arguments.of("Null is false, doesn't start with empty string", null, "", false, "Null isn't false, starts with empty string."),
            Arguments.of("Empty string and null is false", "", null, false, "Empty string and null isn't false"),
            Arguments.of("Empty string and empty string is false", "", "", false, "Empty string and empty string isn't false"),
            Arguments.of("Null and single space string(\" \") is false", null, " ", false, "Null and single space string(\" \") isn't false."),
            Arguments.of("Null is false doesn't start with string(\" a\")", null, " a", false, "Null starts with string(\" a\")."),
            Arguments.of("Null and String terms is false", null, null, false, "Null and String terms isn't false."),
            Arguments.of("Null and String(\"\") is false", null, "", false, "Null and String(\"\") isn't false."),
            Arguments.of("Empty string(\"\") and String terms is false", "", null, false, "Empty string(\"\") and String terms isn't false."),
            Arguments.of("Empty string(\"\") and Empty string(\"\") is false", "", "", false, "Empty string(\"\") and Empty string(\"\") isn't false."),
            Arguments.of("Empty string(\"\") doesn't start with single space string(\" \") in String terms", "", " ", true, "Empty string(\"\") contains single space string(\" \")"),
            Arguments.of("String(\"Johnny applesauce\") doesn't start with a single a string(\"a\") in String terms", "Johnny applesauce", "a", true, "String(\"Johnny applesauce\") starts with a single a string(\"a\")"),
            Arguments.of("_1234 string doesn't start with _12345", "_1234", "_12345", true, "_1234 string starts with _12345"),
            Arguments.of("_Aa34 string doesn't start with _Aa345", "_Aa34", "_Aa345", true, "_Aa34 string starts with _Aa345"),
            Arguments.of("_AA34 doesn't start with _Aa", "_AA34", "_Aa", true, "_AA34 string doesn't start with _Aa"),
            Arguments.of("_AA34 string doesn't start with _Aa3", "_AA34", "_Aa3", true, "_AA34 string starts with _Aa3"),
            Arguments.of("_AA34 string doesn't start with _Aa34", "_AA34", "_Aa34", true, "_AA34 string starts with _Aa34"),
            Arguments.of("_AA34 string doesn't start with _Aa345", "_AA34", "_12345", true, "_Aa34 string starts with _12345")
        );
    }

    public static Stream<Arguments> uncontainsProvider() {
        return Stream.of(
            Arguments.of("Null doesn't contain null in String terms", null, null, false, "Null contains null"),
            Arguments.of("Null doesn't contain empty string(\"\") in String terms", null, "", false, "Null contains empty string(\"\")"),
            Arguments.of("Empty string(\"\") doesn't contain null in String terms", "", null, false, "Empty string(\"\") contains null"),
            Arguments.of("Empty string(\"\") doesn't contain empty string(\"\") in String terms", "", "", false, "Empty string(\"\") contains empty string(\"\")"),
            Arguments.of("Single space string(\" \") contains single space string(\" \") in String terms", " ", " ", false, "Single space string(\"\") doesn't contain single space string(\" \")"),
            Arguments.of("Double space string(\"  \") contains single space string(\" \") in String terms", " ", " ", false, "Double space string(\"\") doesn't contain single space string(\" \")"),
            Arguments.of("String(\"Johnny applesauce\") contains single a string(\"a\") in String terms", " ", "  ", true, "String(\"Johnny applesauce\") doesn't contain single a string(\"a\")"),
            Arguments.of("Empty string(\"\") doesn't contain single space string(\" \") in String terms", "", " ", true, "Empty string(\"\") contains single space string(\" \")"),
            Arguments.of("Empty string(\"\") doesn't contain double space string(\"  \") in String terms", "", "  ", true, "Empty string(\"\") contains double space string(\"  \")")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("containsProvider")
    @Tag("StringUtilities")
    @Tag("contains")
    public void containsTest(String name, String object, String expected, boolean expectedStatus, String errorMessage) {
        final var result = StringUtilities.contains(object, expected);
        Assertions.assertEquals(expectedStatus, result, errorMessage);
    }

    @ParameterizedTest(name = "Predicate: {0}")
    @MethodSource("containsProvider")
    @Tag("StringUtilities")
    @Tag("contains-Predicate")
    public void containsPredicateTest(String name, String object, String expected, boolean expectedStatus, String errorMessage) {
        final var result = StringUtilities.contains(expected).test(object);
        Assertions.assertEquals(expectedStatus, result, errorMessage);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("uncontainsProvider")
    @Tag("StringUtilities")
    @Tag("uncontains")
    public void uncontainsTest(String name, String object, String expected, boolean expectedStatus, String errorMessage) {
        final var result = StringUtilities.uncontains(object, expected);
        Assertions.assertEquals(expectedStatus, result, errorMessage);
    }

    @ParameterizedTest(name = "Predicate: {0}")
    @MethodSource("uncontainsProvider")
    @Tag("StringUtilities")
    @Tag("uncontains-Predicate")
    public void uncontainsPredicateTest(String name, String object, String expected, boolean expectedStatus, String errorMessage) {
        final var result = StringUtilities.uncontains(expected).test(object);
        Assertions.assertEquals(expectedStatus, result, errorMessage);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("startsWithCaseSensitiveProvider")
    @Tag("StringUtilities")
    @Tag("startsWithCaseSensitive-Predicate")
    public void startsWithCaseSensitiveTest(String name, String object, String expected, boolean expectedStatus, String errorMessage) {
        final var result = StringUtilities.startsWithCaseSensitive(object, expected);
        Assertions.assertEquals(expectedStatus, result, errorMessage);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("startsNotWithCaseSensitiveProvider")
    @Tag("StringUtilities")
    @Tag("startsNotWithCaseSensitive-Predicate")
    public void startsNotWithCaseSensitiveTest(String name, String object, String expected, boolean expectedStatus, String errorMessage) {
        final var result = StringUtilities.startsNotWithCaseSensitive(object, expected);
        Assertions.assertEquals(expectedStatus, result, errorMessage);
    }
}
