package com.neathorium.thorium.java.extensions.namespaces.predicates;

import com.neathorium.thorium.java.extensions.namespaces.SizableFunctions;
import com.neathorium.thorium.java.extensions.namespaces.utilities.BooleanUtilities;

import java.util.function.Supplier;

import static com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates.isNotNull;

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
        return hasMoreThan(sizeFunction, 2);
    }

    static boolean isMany(Supplier<Integer> sizeFunction) {
        return hasMoreThan(sizeFunction, 1);
    }

    static boolean hasAtleast(Supplier<Integer> sizeFunction, int amount) {
        return hasMoreThan(sizeFunction, amount - 1);
    }

    static boolean hasIndex(Supplier<Integer> sizeFunction, int index) {
        return hasMoreThan(sizeFunction, index);
    }

    static boolean hasMoreThan(Object[] object, int amount) {
        return (
                BasicPredicates.isNonNegative(amount) &&
                        BasicPredicates.isBiggerThan(SizableFunctions.size(object), amount)
        );
    }

    static boolean isZero(Object[] object) {
        return isNotNull(object) && BasicPredicates.isSmallerThan(object.length, 1);
    }

    static boolean isSingle(Object[] object) {
        return isNotNull(object) && SizablePredicates.isSizeEqualTo(object.length, 1);
    }

    static boolean isDouble(Object[] object) {
        return isNotNull(object) && SizablePredicates.isSizeEqualTo(object.length, 2);
    }

    static boolean isTriple(Object[] object) {
        return isNotNull(object) && SizablePredicates.isSizeEqualTo(object.length, 3);
    }

    static boolean isNonZero(Object[] object) {
        return isNotNull(object) && BasicPredicates.isBiggerThan(object.length, 0);
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
        return hasMoreThan(object, 2);
    }

    static boolean isMany(Object[] object) {
        return hasMoreThan(object, 1);
    }

    static boolean hasAtleast(Object[] object, int amount) {
        return hasMoreThan(object, amount - 1);
    }

    static boolean hasIndex(Object[] object, int index) {
        return hasMoreThan(object, index);
    }
}
