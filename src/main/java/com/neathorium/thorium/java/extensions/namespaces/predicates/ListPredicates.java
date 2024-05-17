package com.neathorium.thorium.java.extensions.namespaces.predicates;

import java.util.List;

public interface ListPredicates {
    static boolean isEmpty(List<?> list) {
        return NullablePredicates.isNull(list) || list.isEmpty();
    }
}
