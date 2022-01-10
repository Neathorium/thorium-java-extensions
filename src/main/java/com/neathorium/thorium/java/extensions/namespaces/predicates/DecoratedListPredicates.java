package com.neathorium.thorium.java.extensions.namespaces.predicates;

import com.neathorium.thorium.java.extensions.classes.DecoratedList;

import java.util.Objects;

public interface DecoratedListPredicates {
    static <T, U> boolean isOfTypeNonEmpty(DecoratedList<T> list, Class<U> clazz) {
        return (
            GuardPredicates.areAny(NullablePredicates::isNotNull, list, clazz) &&
            Objects.equals(clazz.getTypeName(), list.getType()) &&
            list.isNotNullAndNonEmpty()
        );
    }
}
