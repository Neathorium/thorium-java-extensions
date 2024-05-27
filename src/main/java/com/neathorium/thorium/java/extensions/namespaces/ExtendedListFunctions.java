package com.neathorium.thorium.java.extensions.namespaces;

import com.neathorium.thorium.java.extensions.constants.IExtendedListConstants;
import com.neathorium.thorium.java.extensions.namespaces.predicates.*;
import com.neathorium.thorium.java.extensions.records.ConditionalSublistHandlerData;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public interface ExtendedListFunctions {
    private static <T> List<T> conditionalSublist(ConditionalSublistHandlerData<T> handlerData, List<T> list) {
        if (NullablePredicates.isNull(handlerData)) {
            return list;
        }

        if (NullablePredicates.isNull(list)) {
            return List.of();
        }

        final var status = handlerData.CONDITION().test(list::size);
        return status ? list.subList(handlerData.START_INDEX(), handlerData.END_INDEX_FUNCTION().apply(list)) : List.of(handlerData.GETTER().apply(list));
    }

    static <T> boolean addAllCondition(List<T> list, Collection<? extends T> c) {
        return NullablePredicates.isNotNull(list) && EmptiablePredicates.hasOnlyNonNullValues(c);
    }

    static <T> T first(List<T> list) {
        return list.get(IExtendedListConstants.FIRST_INDEX);
    }

    static <T> int lastIndex(List<T> list) {
        final var size = SizableFunctions.size(list::size);
        return BasicPredicates.isPositiveNonZero(size) ? size - 1 : IExtendedListConstants.NOT_IN_LIST_INDEX;
    }

    static <T> int secondLastIndex(List<T> list) {
        final var lastIndex = ExtendedListFunctions.lastIndex(list);
        return lastIndex - 1;
    }

    static <T> T last(List<T> list) {
        final var lastIndex = ExtendedListFunctions.lastIndex(list);
        return list.get(lastIndex);
    }

    static <T> List<T> tail(List<T> list) {
        final var handlerData = new ConditionalSublistHandlerData<T>(ExtendedListFunctions::last, ExtendedListFunctions::lastIndex, AmountPredicates::isNotDouble, IExtendedListConstants.SECOND_INDEX);
        return ExtendedListFunctions.conditionalSublist(handlerData, list);
    }

    static <T> List<T> initials(List<T> list) {
        final var handlerData = new ConditionalSublistHandlerData<T>(ExtendedListFunctions::first, ExtendedListFunctions::secondLastIndex, AmountPredicates::isAtleastDouble, IExtendedListConstants.FIRST_INDEX);
        return ExtendedListFunctions.conditionalSublist(handlerData, list);
    }


}
