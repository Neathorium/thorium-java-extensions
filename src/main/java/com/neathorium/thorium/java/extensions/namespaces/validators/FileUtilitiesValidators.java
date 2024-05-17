package com.neathorium.thorium.java.extensions.namespaces.validators;

import com.neathorium.thorium.java.extensions.namespaces.utilities.FileUtilities;
import com.neathorium.thorium.java.extensions.namespaces.utilities.StringUtilities;

import static org.apache.commons.lang3.StringUtils.isBlank;

public interface FileUtilitiesValidators {
    static String isExistingMessage(String path) {
        var errors = StringUtilities.isInvisible(path) ? "Path was blank.\n" : "";
        if (isBlank(errors) && FileUtilities.isNotExisting(path)) {
            errors += "File with path (\"" + path + "\") doesn't exist.\n";
        }

        return StringUtilities.isVisible(errors) ? "FileUtilitiesValidators.isExistingMessage: There were parameter issues:\n" + errors : "";
    }
}
