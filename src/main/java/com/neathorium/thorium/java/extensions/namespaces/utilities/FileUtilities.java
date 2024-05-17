package com.neathorium.thorium.java.extensions.namespaces.utilities;

import com.neathorium.thorium.exceptions.constants.ExceptionConstants;
import com.neathorium.thorium.exceptions.namespaces.ExceptionFunctions;
import com.neathorium.thorium.java.extensions.constants.FileUtilitiesConstants;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public interface FileUtilities {
    static boolean isExisting(String path) {
        var localPath = FileUtilitiesConstants.NULL_PATH;
        var exception = ExceptionConstants.EXCEPTION;
        try {
            localPath = Paths.get(path);
        } catch (InvalidPathException ex) {
            exception = new InvalidPathException(ex.getInput(), "isExisting: " + ex.getMessage());
        }

        return ExceptionFunctions.isNonException(exception) && Files.exists(localPath);
    }

    static boolean isNotExisting(String path) {
        return !FileUtilities.isExisting(path);
    }
}
