package com.neathorium.thorium.java.extensions.namespaces.utilities;

import com.neathorium.thorium.java.extensions.namespaces.VarargsFunctions;
import com.neathorium.thorium.java.extensions.namespaces.predicates.EmptiablePredicates;

import java.util.*;
import java.util.function.Function;

public interface ListUtilities {

    @SafeVarargs
    static <T> List<T> get(Function<Collection<? extends T>, List<T>> constructor, T... items) {
        final List<T> list = VarargsFunctions.handle(List::of, Arrays::asList, List::of, items);
        return constructor.apply(list);
    }

    @SafeVarargs
    static <T> List<T> getUnmodifiable(Function<Collection<? extends T>, List<T>> constructor, T... items) {
        return Collections.unmodifiableList(ListUtilities.get(constructor, items));
    }

    @SafeVarargs
    static <T> List<T> get(T... items) {
        return ListUtilities.get(ArrayList::new, items);
    }

    @SafeVarargs
    static <T> List<T> getUnmodifiable(T... items) {
        return Collections.unmodifiableList(ListUtilities.get(items));
    }

    static <T> boolean contains(List<T> list, Object item) {
        final List<Object> array = Arrays.asList(list, item);
        if (array.contains(null)) {
            return false;
        }

        return EmptiablePredicates.isNotNullAndNonEmpty(list) && list.contains(item);
    }

    static boolean contains(List<Object> list, Void item) {
        return EmptiablePredicates.isNotNullAndNonEmpty(list) && list.contains(item);
    }
}
