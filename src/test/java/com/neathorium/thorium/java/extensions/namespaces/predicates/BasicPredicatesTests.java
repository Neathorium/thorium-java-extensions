package com.neathorium.thorium.java.extensions.namespaces.predicates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class BasicPredicatesTests {
    public static Stream<Arguments> isBiggerThanProvider() {
        return Stream.of(
            Arguments.of("1 bigger than 0", 1, 0, true, "1 was not bigger than 0"),
            Arguments.of("-1 bigger than Integer.MIN_VALUE", -1, Integer.MIN_VALUE, true, "-1 was not bigger than Integer.MIN_VALUE"),
            Arguments.of("Integer.MAX_VALUE bigger than 0", Integer.MAX_VALUE, 0, true, "Integer.MAX_VALUE was not bigger than Integer.MIN_VALUE"),
            Arguments.of("Integer.MAX_VALUE bigger than Integer.MIN_VALUE", Integer.MAX_VALUE, Integer.MIN_VALUE, true, "Integer.MAX_VALUE was not bigger than Integer.MIN_VALUE"),
            Arguments.of("1 bigger than -1", 1, -1, true, "1 was not bigger than -1"),
            Arguments.of("0 not bigger than Integer.MAX_VALUE", 0, Integer.MAX_VALUE, false, "0 was bigger than Integer.MAX_VALUE"),
            Arguments.of("0 not bigger than 0", 0, 0, false, "0 was bigger than 0"),
            Arguments.of("1 not bigger than 1", 1, 1, false, "1 was bigger than 1"),
            Arguments.of("-1 not bigger than -1", -1, -1, false, "-1 was bigger than -1"),
            Arguments.of("-1 not bigger than 1", -1, 1, false, "-1 was bigger than 1")
        );
    }

    public static Stream<Arguments> isSmallerThanProvider() {
        return Stream.of(
            Arguments.of("0 smaller than 1", 0, 1, true, "0 was not smaller than 1"),
            Arguments.of("Integer.MIN_VALUE smaller than -1", Integer.MIN_VALUE, -1, true, "Integer.MIN_VALUE was not smaller than -1"),
            Arguments.of("0 smaller than Integer.MAX_VALUE", 0, Integer.MAX_VALUE, true, "0 was not smaller than Integer.MAX_VALUE"),
            Arguments.of("Integer.MIN_VALUE smaller than Integer.MAX_VALUE", Integer.MIN_VALUE, Integer.MAX_VALUE, true, "Integer.MIN_VALUE was not smaller than Integer.MAX_VALUE"),
            Arguments.of("-1 smaller than 1", -1, 1, true, "-1 was not smaller than 1"),
            Arguments.of("Integer.MAX_VALUE not smaller than 0", Integer.MAX_VALUE, 0, false, "Integer.MAX_VALUE was smaller than 0"),
            Arguments.of("0 not smaller than 0", 0, 0, false, "0 was smaller than 0"),
            Arguments.of("1 not smaller than 1", 1, 1, false, "1 was smaller than 1"),
            Arguments.of("-1 not smaller than -1", -1, -1, false,"-1 was smaller than -1"),
            Arguments.of("1 not smaller than -1", 1, -1, false, "1 was smaller than -1")
        );
    }

    public static Stream<Arguments> isBiggerThanLongProvider() {
        return Stream.of(
            Arguments.of("1L bigger than 0L", 1L, 0L, true, "1L was not bigger than 0L"),
            Arguments.of("-1L bigger than Long.MIN_VALUE", -1L, Long.MIN_VALUE, true, "-1L was not bigger than Long.MIN_VALUE"),
            Arguments.of("Long.MAX_VALUE bigger than 0L", Long.MAX_VALUE, 0L, true, "Long.MAX_VALUE was not bigger than Long.MIN_VALUE"),
            Arguments.of("Long.MAX_VALUE bigger than Long.MIN_VALUE", Long.MAX_VALUE, Long.MIN_VALUE, true, "Long.MAX_VALUE was not bigger than Long.MIN_VALUE"),
            Arguments.of("1L bigger than -1L", 1L, -1L, true, "1L was not bigger than -1L"),
            Arguments.of("0L not bigger than Long.MAX_VALUE", 0L, Long.MAX_VALUE, false, "0L was bigger than Long.MAX_VALUE"),
            Arguments.of("0L not bigger than 0L", 0L, 0L, false, "0L was bigger than 0L"),
            Arguments.of("1L not bigger than 1L", 1L, 1L, false, "1L was bigger than 1L"),
            Arguments.of("-1 not bigger than -1", -1L, -1L, false, "-1L was bigger than -1L"),
            Arguments.of("-1L not bigger than 1L", -1L, 1L, false, "-1L was bigger than 1L")
        );
    }

    public static Stream<Arguments> isSmallerThanLongProvider() {
        return Stream.of(
            Arguments.of("0L smaller than 1L", 0L, 1L, true, "0L was not smaller than 1L"),
            Arguments.of("Long.MIN_VALUE smaller than -1L", Long.MIN_VALUE, -1L, true, "Long.MIN_VALUE was not smaller than -1L"),
            Arguments.of("0L smaller than Long.MAX_VALUE", 0L, Long.MAX_VALUE, true, "0L was not smaller than Long.MAX_VALUE"),
            Arguments.of("Long.MIN_VALUE smaller than Long.MAX_VALUE", Long.MIN_VALUE, Long.MAX_VALUE, true, "Long.MIN_VALUE was not smaller than Long.MAX_VALUE"),
            Arguments.of("-1 smaller than 1", -1L, 1L, true, "-1 was not smaller than 1"),
            Arguments.of("Long.MAX_VALUE not smaller than 0L", Long.MAX_VALUE, 0L, false, "Long.MAX_VALUE was smaller than 0L"),
            Arguments.of("0L not smaller than 0L", 0L, 0L, false, "0L was smaller than 0L"),
            Arguments.of("1L not smaller than 1L", 1L, 1L, false, "1L was smaller than 1L"),
            Arguments.of("-1L not smaller than -1L", -1L, -1L, false,"-1L was smaller than -1L"),
            Arguments.of("1L not smaller than -1L", 1L, -1L, false, "1L was smaller than -1L")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("isBiggerThanProvider")
    @Tag("isBiggerThan")
    public void isBiggerThanTest(String name, int number, int limit, boolean expectedStatus, String errorMessage) {
        final BiPredicate<Integer, Integer> function = BasicPredicates::isBiggerThan;
        final var result = function.test(number, limit);
        Assertions.assertEquals(result, expectedStatus, errorMessage);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("isSmallerThanProvider")
    @Tag("isSmallerThan")
    public void isSmallerThanTest(String name, int number, int limit, boolean expectedStatus, String errorMessage) {
        final BiPredicate<Integer, Integer> function = BasicPredicates::isSmallerThan;
        final var result = function.test(number, limit);
        Assertions.assertEquals(result, expectedStatus, errorMessage);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("isBiggerThanLongProvider")
    @Tag("isBiggerThanLong")
    public void isBiggerThanLongTest(String name, long number, long limit, boolean expectedStatus, String errorMessage) {
        final BiPredicate<Long, Long> function = BasicPredicates::isBiggerThan;
        final var result = function.test(number, limit);
        Assertions.assertEquals(result, expectedStatus, errorMessage);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("isSmallerThanLongProvider")
    @Tag("isSmallerThanLong")
    public void isSmallerThanLongTest(String name, long number, long limit, boolean expectedStatus, String errorMessage) {
        final BiPredicate<Long, Long> function = BasicPredicates::isSmallerThan;
        final var result = function.test(number, limit);
        Assertions.assertEquals(result, expectedStatus, errorMessage);
    }
}
