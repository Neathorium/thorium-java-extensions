package com.neathorium.thorium.java.extensions.namespaces.enums;

import com.neathorium.thorium.java.extensions.interfaces.IEnumKey;
import com.neathorium.thorium.java.extensions.namespaces.predicates.GuardPredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.StringUtilities;
import org.apache.commons.lang3.StringUtils;

public interface EnumKeyFunctions {
    static <T extends IEnumKey> boolean isIn(String value, T key) {
        return GuardPredicates.areAny(StringUtilities.contains(value), key.getNames());
    }

    static <T extends IEnumKey> boolean isNotIn(String value, T key) {
        return GuardPredicates.areAll(StringUtilities.uncontains(value), key.getNames());
    }

    static <T extends IEnumKey> T getKey(String value, String valueName, T defaultValue, T[] values) {
        final var errorStatus = StringUtils.isBlank(value) || StringUtils.isBlank(valueName);
        if (errorStatus) {
            return defaultValue;
        }

        final var trimmedValue = value.trim();
        for (var val : values) {
            if (isNotIn(trimmedValue, val)) {
                continue;
            }

            return val;
        }

        return defaultValue;
    }
}
