package com.neathorium.thorium.java.extensions.namespaces.predicates;

import com.neathorium.thorium.java.extensions.records.CardinalityData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class GuardPredicatesTests {
    public static Stream<Arguments> provider() {
        final var singleArray = List.of(new Object()).toArray(new Object[0]);
        return Stream.of(
            Arguments.of("Nulls are false.", null, null, null, false, "Nulls are true."),
            Arguments.of("Nulls, new Object()[] are false.", null, null, singleArray, false, "Nulls, new Object()[] are true."),
            Arguments.of("Null, some CardinalityData, new Object()[] are false.", null, new CardinalityData(false, false, false), singleArray, false, "Null, some CardinalityData, new Object()[] are true.")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provider")
    @Tag("GuardPredicates")
    @Tag("are")
    void areNotNullTest(String name, Predicate<Object> condition, CardinalityData conditionData, Object[] objects, boolean expectedStatus, String errorMessage) {
        final var result = GuardPredicates.are(condition, conditionData, objects);
        Assertions.assertEquals(expectedStatus, result, errorMessage);
    }
}
