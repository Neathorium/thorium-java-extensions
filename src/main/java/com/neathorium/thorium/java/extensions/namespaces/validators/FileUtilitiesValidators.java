package com.neathorium.thorium.java.extensions.namespaces.validators;

import com.neathorium.thorium.java.extensions.namespaces.utilities.FileUtilities;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface FileUtilitiesValidators {
    static String isExistingMessage(String path) {
        var message = isBlank(path) ? "Path was blank.\n" : "";
        if (isBlank(message) && FileUtilities.isNotExisting(path)) {
            message += "File with path (\"" + path + "\") doesn't exist.\n";
        }

        return isNotBlank(message) ? "isExistingMessage: There were parameter issues:\n" + message : "";
    }
}
