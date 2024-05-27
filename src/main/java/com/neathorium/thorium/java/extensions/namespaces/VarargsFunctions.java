package com.neathorium.thorium.java.extensions.namespaces;

import com.neathorium.thorium.java.extensions.namespaces.predicates.AmountPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.BasicPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface VarargsFunctions {
    @SafeVarargs
    static <ReturnType, ObjectType> ReturnType handleSimple(
        ReturnType defaultValue,
        ReturnType value,
        ObjectType... items
    ) {
        if (
            NullablePredicates.isNull(items) ||
            BasicPredicates.isZeroOrNonPositive(items.length)
        ) {
            return defaultValue;
        }

        return value;
    }
    @SafeVarargs
    static <ReturnType, ObjectType> ReturnType handle(
        Supplier<ReturnType> defaultGetter,
        Function<ObjectType[], ReturnType> manyGetter,
        Function<ObjectType[], ReturnType> singleGetter,
        ObjectType... items
    ) {
        if (
            NullablePredicates.isNull(items) ||
            BasicPredicates.isZeroOrNonPositive(items.length) ||
            (AmountPredicates.isSingle(items) && NullablePredicates.isNull(items[0]))
        ) {
            return defaultGetter.get();
        }

        final var status = AmountPredicates.hasAtleast(items, 2);
        final var getter = status ? manyGetter : singleGetter;
        return getter.apply(items);

    }
}
