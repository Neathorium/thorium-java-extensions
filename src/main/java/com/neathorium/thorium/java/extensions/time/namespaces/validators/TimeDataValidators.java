package com.neathorium.thorium.java.extensions.time.namespaces.validators;

import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.StringUtilities;
import com.neathorium.thorium.java.extensions.time.records.TimeData;
import com.neathorium.thorium.java.extensions.time.records.TimeEntryData;
import com.neathorium.thorium.java.extensions.time.records.TimeEntryPairData;

import java.util.concurrent.TimeUnit;

public interface TimeDataValidators {
    static String isValid(long interval, TimeUnit timeUnit) {
        final var nameof = "TimeDataValidators.isValid";
        final var errors = (
            ((interval > -1) ? "" : "Interval(\"" + interval + "\") was smaller than zero.\n") +
            ((NullablePredicates.isNotNull(timeUnit)) ? "" : "Time Unit parameter was null.\n")
        );

        return StringUtilities.isVisible(errors) ? (nameof + ": " + errors) : "";
    }
    static String isValid(TimeEntryData entryData) {
        final var nameof = "TimeDataValidators.isValid";
        var errors = NullablePredicates.isNotNull(entryData) ? "" : "Entry Data parameter was null.\n";

        return StringUtilities.isVisible(errors) ? (nameof + ": " + errors) : "";
    }

    static String isValid(TimeEntryData interval, TimeEntryData duration) {
        final var nameof = "TimeDataValidators.isValid";
        var errors = (
            TimeDataValidators.isValid(interval) +
            TimeDataValidators.isValid(duration)
        );
        return StringUtilities.isVisible(errors) ? (nameof + ": " + errors) : "";
    }

    static String isValid(TimeEntryPairData entryPairData) {
        final var nameof = "TimeDataValidators.isValid";
        var errors = NullablePredicates.isNotNull(entryPairData) ? "" : "Entry Pair Data parameter was null.\n";
        if (StringUtilities.isInvisible(errors)) {
            errors += TimeDataValidators.isValid(entryPairData.INTERVAL(), entryPairData.DURATION());
        }

        return StringUtilities.isVisible(errors) ? (nameof + ": " + errors) : "";
    }

    static String isValid(TimeData timeData) {
        final var nameof = "TimeDataValidators.isValid";
        var errors = NullablePredicates.isNotNull(timeData) ? "" : "TimeData parameter was null.\n";
        if (StringUtilities.isInvisible(errors)) {
            errors += (
                (NullablePredicates.isNotNull(timeData.CLOCK()) ? "" : "TimeData clock was null.\n") +
                (NullablePredicates.isNotNull(timeData.REPETITION()) ? "" : "TimeData Repetition was null.\n") +
                TimeDataValidators.isValid(timeData.ENTRY_PAIR_DATA())
            );
        }

        return StringUtilities.isVisible(errors) ? (nameof + ": " + errors) : "";
    }
}
