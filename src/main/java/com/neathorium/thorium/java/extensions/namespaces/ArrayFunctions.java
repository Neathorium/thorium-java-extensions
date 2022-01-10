package com.neathorium.thorium.java.extensions.namespaces;

import com.neathorium.thorium.java.extensions.constants.ArrayConstants;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;

import java.util.Collections;
import java.util.function.Predicate;

public interface ArrayFunctions {
    static <T> Object[] toSingleElementArray(T object, Predicate<T> guard) {
        return BooleanUtilities.isFalse(guard.test(object)) ? ArrayConstants.EMPTY_OBJECT_ARRAY : Collections.singletonList(object).toArray(new Object[0]);
    }

    static <T> Object[] toSingleElementArray(T object) {
        return toSingleElementArray(object, NullablePredicates::isNotNull);
    }
}
