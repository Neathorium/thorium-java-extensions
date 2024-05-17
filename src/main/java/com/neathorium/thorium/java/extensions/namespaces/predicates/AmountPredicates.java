package com.neathorium.thorium.java.extensions.namespaces.predicates;

import com.neathorium.thorium.java.extensions.namespaces.SizableFunctions;
import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;

import java.util.Collection;
import java.util.function.Supplier;

public interface AmountPredicates {
    static boolean hasMoreThan(Supplier<Integer> sizeFunction, int amount) {
        return (
            BasicPredicates.isNonNegative(amount) &&
            BasicPredicates.isBiggerThan(SizableFunctions.size(sizeFunction), amount)
        );
    }

    static boolean hasLessThan(Supplier<Integer> sizeFunction, int amount) {
        return (
            BasicPredicates.isNonNegative(amount) &&
            BasicPredicates.isSmallerThan(SizableFunctions.size(sizeFunction), amount)
        );
    }

    static boolean isZero(Supplier<Integer> sizeFunction) {
        return SizablePredicates.isSizeEqualTo(sizeFunction, 0);
    }

    static boolean isSingle(Supplier<Integer> sizeFunction) {
        return SizablePredicates.isSizeEqualTo(sizeFunction, 1);
    }

    static boolean isDouble(Supplier<Integer> sizeFunction) {
        return SizablePredicates.isSizeEqualTo(sizeFunction, 2);
    }

    static boolean isTriple(Supplier<Integer> sizeFunction) {
        return SizablePredicates.isSizeEqualTo(sizeFunction, 3);
    }

    static boolean isNonZero(Supplier<Integer> sizeFunction) {
        return BooleanUtilities.isFalse(isZero(sizeFunction));
    }

    static boolean isNotSingle(Supplier<Integer> sizeFunction) {
        return BooleanUtilities.isFalse(isSingle(sizeFunction));
    }

    static boolean isNotDouble(Supplier<Integer> sizeFunction) {
        return BooleanUtilities.isFalse(isDouble(sizeFunction));
    }

    static boolean isNotTriple(Supplier<Integer> sizeFunction) {
        return BooleanUtilities.isFalse(isTriple(sizeFunction));
    }

    static boolean isAtleastDouble(Supplier<Integer> sizeFunction) {
        return AmountPredicates.hasMoreThan(sizeFunction, 2);
    }

    static boolean isMany(Supplier<Integer> sizeFunction) {
        return AmountPredicates.hasMoreThan(sizeFunction, 1);
    }

    static boolean hasAtleast(Supplier<Integer> sizeFunction, int amount) {
        return AmountPredicates.hasMoreThan(sizeFunction, amount - 1);
    }

    static boolean hasIndex(Supplier<Integer> sizeFunction, int index) {
        return AmountPredicates.hasMoreThan(sizeFunction, index);
    }

    static boolean hasMoreThan(Object[] object, int amount) {
        return (
            BasicPredicates.isNonNegative(amount) &&
            BasicPredicates.isBiggerThan(SizableFunctions.size(object), amount)
        );
    }

    static boolean hasLessThan(Object[] object, int amount) {
        return (
            BasicPredicates.isNonNegative(amount) &&
            BasicPredicates.isSmallerThan(SizableFunctions.size(object), amount)
        );
    }

    static boolean isZero(Object[] object) {
        return NullablePredicates.isNotNull(object) && BasicPredicates.isSmallerThan(object.length, 1);
    }

    static boolean isSingle(Object[] object) {
        return NullablePredicates.isNotNull(object) && SizablePredicates.isSizeEqualTo(object.length, 1);
    }

    static boolean isDouble(Object[] object) {
        return NullablePredicates.isNotNull(object) && SizablePredicates.isSizeEqualTo(object.length, 2);
    }

    static boolean isTriple(Object[] object) {
        return NullablePredicates.isNotNull(object) && SizablePredicates.isSizeEqualTo(object.length, 3);
    }

    static boolean isNonZero(Object[] object) {
        return NullablePredicates.isNotNull(object) && BasicPredicates.isBiggerThan(object.length, 0);
    }

    static boolean isNotSingle(Object[] object) {
        return BooleanUtilities.isFalse(isSingle(object));
    }

    static boolean isNotDouble(Object[] object) {
        return BooleanUtilities.isFalse(isDouble(object));
    }

    static boolean isNotTriple(Object[] object) {
        return BooleanUtilities.isFalse(isTriple(object));
    }

    static boolean isAtleastDouble(Object[] object) {
        return AmountPredicates.hasMoreThan(object, 2);
    }

    static boolean isMany(Object[] object) {
        return AmountPredicates.hasMoreThan(object, 1);
    }

    static boolean hasAtleast(Object[] object, int amount) {
        return AmountPredicates.hasMoreThan(object, amount - 1);
    }

    static boolean hasIndex(Object[] object, int index) {
        return AmountPredicates.hasMoreThan(object, index);
    }

    /* Collections */

    static boolean hasMoreThan(Collection<?> collection, int amount) {
        return (
            BasicPredicates.isNonNegative(amount) &&
            BasicPredicates.isBiggerThan(SizableFunctions.size(collection), amount)
        );
    }

    static boolean hasLessThan(Collection<?> collection, int amount) {
        return (
            BasicPredicates.isNonNegative(amount) &&
            BasicPredicates.isSmallerThan(SizableFunctions.size(collection), amount)
        );
    }

    static boolean isZero(Collection<?> collection) {
        return NullablePredicates.isNotNull(collection) && BasicPredicates.isSmallerThan(collection.size(), 1);
    }

    static boolean isSingle(Collection<?> collection) {
        return NullablePredicates.isNotNull(collection) && SizablePredicates.isSizeEqualTo(collection.size(), 1);
    }

    static boolean isDouble(Collection<?> collection) {
        return NullablePredicates.isNotNull(collection) && SizablePredicates.isSizeEqualTo(collection.size(), 2);
    }

    static boolean isTriple(Collection<?> collection) {
        return NullablePredicates.isNotNull(collection) && SizablePredicates.isSizeEqualTo(collection.size(), 3);
    }

    static boolean isNonZero(Collection<?> collection) {
        return NullablePredicates.isNotNull(collection) && BasicPredicates.isBiggerThan(collection.size(), 0);
    }

    static boolean isNotSingle(Collection<?> collection) {
        return BooleanUtilities.isFalse(AmountPredicates.isSingle(collection));
    }

    static boolean isNotDouble(Collection<?> collection) {
        return BooleanUtilities.isFalse(AmountPredicates.isDouble(collection));
    }

    static boolean isNotTriple(Collection<?> collection) {
        return BooleanUtilities.isFalse(AmountPredicates.isTriple(collection));
    }

    static boolean isAtleastDouble(Collection<?> collection) {
        return AmountPredicates.hasMoreThan(collection, 2);
    }

    static boolean isMany(Collection<?> collection) {
        return AmountPredicates.hasMoreThan(collection, 1);
    }

    static boolean hasAtleast(Collection<?> collection, int amount) {
        return AmountPredicates.hasMoreThan(collection, amount - 1);
    }

    static boolean hasIndex(Collection<?> collection, int index) {
        return AmountPredicates.hasMoreThan(collection, index);
    }
}
