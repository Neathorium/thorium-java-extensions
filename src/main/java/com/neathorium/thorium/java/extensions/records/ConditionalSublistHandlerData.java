package com.neathorium.thorium.java.extensions.records;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public record ConditionalSublistHandlerData<T>(
    Function<List<T>, T> GETTER,
    Function<List<T>, Integer> END_INDEX_FUNCTION,
    Predicate<Supplier<Integer>> CONDITION,
    int START_INDEX
) {}
