package com.neathorium.thorium.java.extensions.namespaces;

import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;

import java.util.Collection;
import java.util.function.Supplier;

public interface SizableFunctions {
    static int size(Supplier<Integer> sizeFunction) {
        return NullablePredicates.isNotNull(sizeFunction) ? sizeFunction.get() : 0;
    }

    static int size(Object[] object) {
        return NullablePredicates.isNotNull(object) ? object.length : 0;
    }


    static int size(Collection<?> collection) {
        return NullablePredicates.isNotNull(collection) ? collection.size() : 0;
    }
}
