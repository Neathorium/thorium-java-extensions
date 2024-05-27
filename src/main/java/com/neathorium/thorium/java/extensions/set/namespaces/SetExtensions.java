package com.neathorium.thorium.java.extensions.set.namespaces;

import com.neathorium.thorium.java.extensions.namespaces.predicates.BasicPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.ListUtilities;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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


    static <T> Set<T> ofWithHashSet(List<T> list) {
        return SetExtensions.ofWith(HashSet::new, HashSet::new, list);
    }

    @SafeVarargs
    static <T> Set<T> ofWithHashSet(T... items) {
        final var list = ListUtilities.get(items);
        return SetExtensions.ofWithHashSet(list);
    }
}
