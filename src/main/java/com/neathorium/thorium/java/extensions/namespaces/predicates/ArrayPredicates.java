package com.neathorium.thorium.java.extensions.namespaces.predicates;

public interface ArrayPredicates {
    static <T> boolean isNullOrEmptyArray(T[] array) {
        return (
            NullablePredicates.isNull(array) ||
            AmountPredicates.isZero(array) ||
            (AmountPredicates.isSingle(array) && NullablePredicates.isNull(array[0]))
        );
    }
}
