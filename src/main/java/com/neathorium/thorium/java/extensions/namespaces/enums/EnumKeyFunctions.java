package com.neathorium.thorium.java.extensions.namespaces.enums;

import com.neathorium.thorium.java.extensions.interfaces.IEnumKey;
import com.neathorium.thorium.java.extensions.namespaces.utilities.StringUtilities;
import org.apache.commons.lang3.StringUtils;

public interface EnumKeyFunctions {
    static <T extends IEnumKey> boolean isIn(String value, T key) {
        return StringUtilities.valueContainsAny(value, key.getNames());
    }

    static <T extends IEnumKey> boolean isNotIn(String value, T key) {
        return StringUtilities.valueContainsNone(value, key.getNames());
    }

    static <T extends IEnumKey> T getKey(String value, String valueName, T defaultValue, T[] values) {
        final var errorStatus = StringUtilities.isInvisible(value) || StringUtilities.isInvisible(valueName);
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
