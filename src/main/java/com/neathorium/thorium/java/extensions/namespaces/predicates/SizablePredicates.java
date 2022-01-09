package com.neathorium.thorium.java.extensions.namespaces.predicates;

import java.util.function.Supplier;

public interface SizablePredicates {
    static boolean isSizeEqualTo(int size, int expected) {
        return (
            BasicPredicates.isNonNegative(size) &&
            BasicPredicates.isNonNegative(expected) &&
            EqualsPredicates.isEqual(size, expected)
        );
    }

    static boolean isSizeEqualTo(Supplier<Integer> sizeFunction, int expected) {
        return NullablePredicates.isNotNull(sizeFunction) && isSizeEqualTo(sizeFunction.get(), expected);
    }
}
