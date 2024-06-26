package com.neathorium.thorium.java.extensions.namespaces.predicates;

import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class NullablePredicatesTests {
    public static Stream<Arguments> isNullProvider() {
        return Stream.of(
            Arguments.of("Null is null", null, true, "Null wasn't null"),
            Arguments.of("Basic object is not null", new Object(), false, "Basic object was null"),
            Arguments.of("Primitive integer 1 is not null", 1, false, "Primitive integer 1 was null"),
            Arguments.of("Empty String is not null", "", false, "Empty String was null"),
            Arguments.of("Single whitespace String is not null", " ", false, "Single whitespace String was null"),
            Arguments.of("Single (\"a\") String is not null", "a", false, "Single (\"a\") String was null")
        );
    }

    public static Stream<Arguments> isNotNullProvider() {
        return Stream.of(
            Arguments.of("Null is null", null, false, "Null wasn't null"),
            Arguments.of("Basic object is not null", new Object(), true, "Basic object was null"),
            Arguments.of("Primitive integer 1 is not null", 1, true, "Primitive integer 1 was null"),
            Arguments.of("Empty String is not null", "", true, "Empty String was null"),
            Arguments.of("Single whitespace String is not null", " ", true, "Single whitespace String was null"),
            Arguments.of("Single (\"a\") String is not null", "a", true, "Single (\"a\") String was null")
        );
    }

    public static Stream<Arguments> areNullProvider() {
        return Stream.of(
            Arguments.of("Null (single) is true.", null, true, "Null (single) isn't true."),
            Arguments.of("Singleton collection of null are null.", Collections.singleton(null).toArray(), true, "Singleton collection of null aren't null."),
            Arguments.of("Null and null are null.", Arrays.asList(null, null).toArray(), true, "Nulls (double) aren't null."),
            Arguments.of("Null, null and null are null.", Arrays.asList(null, null, null).toArray(), true, "A new Object() was null as well."),
            Arguments.of("Singleton collection of new Object() aren't null.", Collections.singleton(new Object()).toArray(), false, "Singleton collection of null aren't null."),
            Arguments.of("Null and new Object() aren't null.", Arrays.asList(null, new Object()).toArray(), false, "Nulls (double) aren't null."),
            Arguments.of("Null, null and new Object() aren't null.", Arrays.asList(null, null, new Object()).toArray(), false, "A new Object() was null as well."),
            Arguments.of("new Object(), null and new Object() aren't null.", Arrays.asList(new Object(), null, new Object()).toArray(), false, "A new Object() was null as well."),
            Arguments.of("new Object(), new Object() and new Object() aren't null.", Arrays.asList(new Object(), new Object(), new Object()).toArray(), false, "A new Object() was null as well.")
        );
    }

    public static Stream<Arguments> areAnyNullProvider() {
        return Stream.of(
            Arguments.of("Null (single) is true.", null, true, "Null (single) isn't true."),
            Arguments.of("Singleton collection of null are null.", Collections.singleton(null).toArray(), true, "Singleton collection of null aren't null."),
            Arguments.of("Null and null are null.", Arrays.asList(null, null).toArray(), true, "Nulls (double) aren't null."),
            Arguments.of("Null, null and null are null.", Arrays.asList(null, null, null).toArray(), true, "A new Object() was null as well."),
            Arguments.of("Null and new Object() - null is null.", Arrays.asList(null, new Object()).toArray(), true, "Null and new Object() - null wasn't null."),
            Arguments.of("new Object(), null and new Object() - null is null.", Arrays.asList(new Object(), null, new Object()).toArray(), true, "A new Object() was null as well."),
            Arguments.of("Null, null and new Object() - two nulls are null.", Arrays.asList(null, null, new Object()).toArray(), true, "No nulls found."),
            Arguments.of("Singleton collection of new Object() aren't null.", Collections.singleton(new Object()).toArray(), false, "Singleton collection of null aren't null."),
            Arguments.of("new Object(), new Object() and new Object() aren't null.", Arrays.asList(new Object(), new Object(), new Object()).toArray(), false, "A new Object() was null as well.")
        );
    }

    public static Stream<Arguments> areNotNullProvider() {
        return Stream.of(
            Arguments.of("Null are false.", null, false, "Null (single) are true."),
            Arguments.of("Singleton collection of null are false.", Collections.singleton(null).toArray(), false, "Singleton collection of null aren't null."),
            Arguments.of("Null and null are false.", Arrays.asList(null, null).toArray(), false, "Nulls (double) aren't null."),
            Arguments.of("Null, null and null are false.", Arrays.asList(null, null, null).toArray(), false, "Nulls (triple) aren't null."),
            Arguments.of("Singleton collection of new Object() are true.", Collections.singleton(new Object()).toArray(), true, "Singleton collection of null aren't null."),
            Arguments.of("Null and new Object() are false.", Arrays.asList(null, new Object()).toArray(), false, "Null and new Object() aren't null."),
            Arguments.of("Null, null and new Object() are false.", Arrays.asList(null, null, new Object()).toArray(), false, "Null, null and new Object() weren't null."),
            Arguments.of("new Object(), null and new Object() are false.", Arrays.asList(new Object(), null, new Object()).toArray(), false, "New Object(), null and new Object() weren't null"),
            Arguments.of("new Object(), new Object() and new Object() are true.", Arrays.asList(new Object(), new Object(), new Object()).toArray(), true, "A new Object() was null as well.")
        );
    }

    public static Stream<Arguments> equivalencyProvider() {
        return Stream.of(
            Arguments.of("Null are false.", null, false, true, "Null (single) are true."),
            Arguments.of("Singleton collection of null are false.", Collections.singleton(null).toArray(), false, true, "Singleton collection of null aren't null."),
            Arguments.of("Null and null are false.", Arrays.asList(null, null).toArray(), false, true, "Nulls (double) aren't null."),
            Arguments.of("Null, null and null are false.", Arrays.asList(null, null, null).toArray(), false, true, "Nulls (triple) aren't null."),
            Arguments.of("Singleton collection of new Object() are true.", Collections.singleton(new Object()).toArray(), true, false, "Singleton collection of null aren't null."),
            Arguments.of("Null and new Object() are false.", Arrays.asList(null, new Object()).toArray(), false, true, "Null and new Object() aren't null."),
            Arguments.of("Null, null and new Object() are false.", Arrays.asList(null, null, new Object()).toArray(), false, true, "Null, null and new Object() weren't null."),
            Arguments.of("new Object(), null and new Object() are false.", Arrays.asList(new Object(), null, new Object()).toArray(), false, true, "New Object(), null and new Object() weren't null"),
            Arguments.of("new Object(), new Object() and new Object() are true.", Arrays.asList(new Object(), new Object(), new Object()).toArray(), true, false, "A new Object() was null as well.")
        );
    }

    public static Stream<Arguments> noParameterProvider() {
        final Supplier<Boolean> areNullSupplier = NullablePredicates::areNull;
        final Supplier<Boolean> areAnyNullSupplier = NullablePredicates::areAnyNull;
        final Supplier<Boolean> areNotNullSupplier = NullablePredicates::areNotNull;

        return Stream.of(
            Arguments.of("NullablePredicates.areNull() is false", areNullSupplier, true, "areNull() no parameter was false."),
            Arguments.of("NullablePredicates.areAnyNull() is false", areAnyNullSupplier, true, "areAnyNull() no parameter was false."),
            Arguments.of("NullablePredicates.areNotNull() is false", areNotNullSupplier, false, "areNotNull() no parameter was true.")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("isNullProvider")
    @Tag("NullablePredicates")
    @Tag("isNull")
    void isNullTest(String name, Object object, boolean expectedStatus, String errorMessage) {
        final var result = NullablePredicates.isNull(object);
        Assertions.assertEquals(expectedStatus, result, errorMessage);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("isNotNullProvider")
    @Tag("NullablePredicates")
    @Tag("isNotNull")
    void isNotNullTest(String name, Object object, boolean expectedStatus, String errorMessage) {
        final var result = NullablePredicates.isNotNull(object);
        Assertions.assertEquals(expectedStatus, result, errorMessage);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("areNullProvider")
    @Tag("NullablePredicates")
    @Tag("areNull")
    void areNullTest(String name, Object[] objects, boolean expectedStatus, String errorMessage) {
        final var result = NullablePredicates.areNull(objects);
        Assertions.assertEquals(expectedStatus, result, errorMessage);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("areAnyNullProvider")
    @Tag("NullablePredicates")
    @Tag("areAnyNull")
    void areAnyNullTest(String name, Object[] objects, boolean expectedStatus, String errorMessage) {
        final var result = NullablePredicates.areAnyNull(objects);
        Assertions.assertEquals(expectedStatus, result, errorMessage);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("areNotNullProvider")
    @Tag("NullablePredicates")
    @Tag("areNotNull")
    void areNotNullTest(String name, Object[] objects, boolean expectedStatus, String errorMessage) {
        final var result = NullablePredicates.areNotNull(objects);
        Assertions.assertEquals(expectedStatus, result, errorMessage);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("noParameterProvider")
    @Tag("NullablePredicates")
    @Tag("areNull")
    @Tag("areNullNoParameter")
    void noParameterTest(String name, Supplier<Boolean> function, boolean expectedStatus, String message) {
        Assertions.assertEquals(expectedStatus, function.get(), message);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("equivalencyProvider")
    @Tag("NullablePredicates")
    @Tag("Equivalency")
    void equivalencyTest(String name, Object[] objects, boolean expectedNotNullStatus, boolean expectedNullStatus, String errorMessage) {
        final var notNullResult = NullablePredicates.areNotNull(objects);
        final var nullResult = NullablePredicates.areAnyNull(objects);
        final var status = (
            Objects.equals(notNullResult, expectedNotNullStatus) &&
            Objects.equals(nullResult, expectedNullStatus)
        );
        Assertions.assertTrue(status, errorMessage);
    }
}
