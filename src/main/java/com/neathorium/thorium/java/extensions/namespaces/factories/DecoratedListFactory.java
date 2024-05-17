package com.neathorium.thorium.java.extensions.namespaces.factories;

import com.neathorium.thorium.java.extensions.classes.DecoratedList;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface DecoratedListFactory {
    static <T> DecoratedList<T> getWith(List<T> list, Class<?> clazz) {
        return new DecoratedList<>(list, clazz.getTypeName());
    }

    static <T> DecoratedList<T> getWithObjectClass(List<T> list) {
        return getWith(list, Object.class);
    }

    static DecoratedList<String> getWithStringClass(List<String> list) {
        return getWith(list, String.class);
    }

    static DecoratedList<String> getWith(Collection<String> collection) {
        return getWith(collection, String.class);
    }

    static <T> DecoratedList<T> getWith(T[] list, Class<?> type) {
        return getWith(Arrays.asList(list), type);
    }

    static <T> DecoratedList<T> getWith(Collection<T> collection, Class<?> type) {
        return getWith(new ArrayList<>(collection), type);
    }

    static <T> DecoratedList<T> getWith(T element, Class<?> type) {
        final var list = new ArrayList<T>();
        list.add(element);
        return getWith(list, type);
    }

    static <T> DecoratedList<T> getImmutableWith(T element, Class<?> type) {
        return getWith(Collections.singletonList(element), type);
    }

    static <T> DecoratedList<T> getWith(Class<T> type) {
        return getWith(new ArrayList<>(), type);
    }

    static <T> DecoratedList<T> getWith(T[] list) {
        return getWithObjectClass(Arrays.asList(list));
    }

    static <T> DecoratedList<T> getWithDefaults() {
        return getWithObjectClass(new ArrayList<>());
    }

    static <T> DecoratedList<T> getWith(T element) {
        return getWithObjectClass(List.of(element));
    }

    static <T> DecoratedList<T> getWith(List<T> list) {
        return getWithObjectClass(list);
    }

    static <T> DecoratedList<T> getWith(List<T>... lists) {
        final var localList = new ArrayList<T>();
        for (var list : lists) {
            if (NullablePredicates.isNull(list)) {
                continue;
            }

            localList.addAll(list);
        }

        return DecoratedListFactory.getWith(localList);
    }


}
