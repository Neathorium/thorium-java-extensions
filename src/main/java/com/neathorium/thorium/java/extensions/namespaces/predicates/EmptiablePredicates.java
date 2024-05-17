package com.neathorium.thorium.java.extensions.namespaces.predicates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public interface EmptiablePredicates {
    static <T> boolean isEmpty(T[] array) {
        return NullablePredicates.isNotNull(array) && AmountPredicates.isZero(array);
    }

    static <T> boolean isNullOrEmpty(T[] array) {
        return NullablePredicates.isNull(array) || AmountPredicates.isZero(array);
    }

    static <T> boolean isNotNullAndNonEmpty(T[] array) {
        return !(isNullOrEmpty(array));
    }

    static boolean isEmpty(Collection<?> collection) {
        return NullablePredicates.isNotNull(collection) && collection.isEmpty();
    }

    static boolean isNullOrEmpty(Collection<?> collection) {
        return NullablePredicates.isNull(collection) || collection.isEmpty();
    }

    static boolean isNotNullAndNonEmpty(Collection<?> collection) {
        return !(EmptiablePredicates.isNullOrEmpty(collection));
    }

    static boolean isEmpty(Map<?, ?> map) {
        return NullablePredicates.isNotNull(map) && map.isEmpty();
    }

    static boolean isNullOrEmpty(Map<?, ?> map) {
        return NullablePredicates.isNull(map) || map.isEmpty();
    }

    static boolean isNotNullAndNonEmpty(Map<?, ?> map) {
        return !(isNullOrEmpty(map));
    }

    static boolean hasOnlyNonNullValues(Collection<?> collection) {
        return (
            EmptiablePredicates.isNotNullAndNonEmpty(collection) &&
            SizablePredicates.isSizeEqualTo(collection::size, (int)collection.stream().filter(NullablePredicates::isNotNull).count())
        );
    }

    static boolean hasOnlyNonNullValues(Map<? ,?> map) {
        final var validMap = isNotNullAndNonEmpty(map);
        if (!validMap) {
            return false;
        }

        final var keys = new ArrayList<>(map.keySet());
        final var values = new ArrayList<>(map.values());
        return (
            SizablePredicates.isSizeEqualTo(map::size, (int)keys.stream().filter(NullablePredicates::isNotNull).count()) &&
            SizablePredicates.isSizeEqualTo(map::size, (int)values.stream().filter(NullablePredicates::isNotNull).count())
        );
    }
}
