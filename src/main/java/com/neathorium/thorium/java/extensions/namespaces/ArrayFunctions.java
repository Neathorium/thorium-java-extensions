package com.neathorium.thorium.java.extensions.namespaces;

import com.neathorium.thorium.java.extensions.namespaces.predicates.AmountPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public interface ArrayFunctions {


    static <T> Object[] toSingleElementArray(T object, Predicate<T> guard) {
        return BooleanUtilities.isFalse(guard.test(object)) ? new Object[0] : Collections.singletonList(object).toArray(new Object[0]);
    }

    static <T> Object[] toSingleElementArray(T object) {
        return ArrayFunctions.toSingleElementArray(object, NullablePredicates::isNotNull);
    }
}
