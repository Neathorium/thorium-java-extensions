package com.neathorium.thorium.java.extensions.namespaces.utilities;

import com.neathorium.thorium.java.extensions.namespaces.predicates.BasicPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;

public interface SetExtensions {
    static <T> Set<T> ofWith(
        Function<Collection<T>, Set<T>> collectionConstructor,
        IntFunction<Set<T>> sizeConstructor,
        List<T> list
    ) {
        final var defaultSize = 0;
        Set<T> collection;
        if (NullablePredicates.areNull(collectionConstructor, sizeConstructor)) {
            return Set.copyOf(list);
        }
        if (NullablePredicates.isNull(list)) {
            collection = sizeConstructor.apply(defaultSize);
        } else {
            final var size = list.size();
            collection = BasicPredicates.isPositiveNonZero(size) ? collectionConstructor.apply(list) : sizeConstructor.apply(defaultSize);
        }

        return Collections.unmodifiableSet(collection);
    }


    static <T> Set<T> ofWithHashMap(List<T> list) {
        return SetExtensions.ofWith(HashSet::new, HashSet::new, list);
    }

    @SafeVarargs
    static <T> Set<T> ofWithHashMap(T... items) {
        final var list = ListUtilities.get(items);
        return SetExtensions.ofWithHashMap(list);
    }
}
