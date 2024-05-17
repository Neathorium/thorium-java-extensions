package com.neathorium.thorium.java.extensions.namespaces.utilities;

import com.neathorium.thorium.java.extensions.namespaces.predicates.AmountPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.util.*;
import java.util.function.Function;

public interface ListUtilities {

    @SafeVarargs
    static <T> List<T> get(Function<Collection<? extends T>, List<T>> constructor, T... items) {
        List<T> list = null;
        if (NullablePredicates.isNull(items)) {
            list = List.of();
        } else {
            list = AmountPredicates.hasAtleast(items, 2) ? Arrays.asList(items) : List.of(items);
        }

        return constructor.apply(list);
    }

    @SafeVarargs
    static <T> List<T> getUnmodifiable(Function<Collection<? extends T>, List<T>> constructor, T... items) {
        return Collections.unmodifiableList(ListUtilities.get(constructor, items));
    }

    @SafeVarargs
    static <T> List<T> get(T... items) {
        return ListUtilities.get(ArrayList::new, items);
    }

    @SafeVarargs
    static <T> List<T> getUnmodifiable(T... items) {
        return Collections.unmodifiableList(ListUtilities.get(items));
    }
}
