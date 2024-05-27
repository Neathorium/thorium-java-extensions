package com.neathorium.thorium.java.extensions.time.namespaces.factories;

import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.StringUtilities;
import com.neathorium.thorium.java.extensions.time.records.TimeEntryData;
import com.neathorium.thorium.java.extensions.time.records.TimeEntryPairData;

import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

public interface TimeEntryPairDataFactory {
    private static TimeEntryPairData getWith(
        BiFunction<TimeEntryData, TimeEntryData, TimeEntryPairData> constructor,
        BiFunction<String, Throwable, IllegalArgumentException> exceptionConstructor,
        TimeEntryData intervalData,
        TimeEntryData durationData
    ) {
        final var nameof = "TimeEntryPairDataFactory.getWith";
        final var errors = (
            (NullablePredicates.isNotNull(constructor) ? "" :  "WaitTimeEntryDataPair Constructor parameter was null.\n") +
            (NullablePredicates.isNotNull(intervalData) ? "" : "Interval Data parameter was null.\n") +
            (NullablePredicates.isNotNull(durationData)?  "" : "Duration Data parameter was null.\n")
        );
        var localExceptionConstructor = exceptionConstructor;
        if (NullablePredicates.isNull(localExceptionConstructor)) {
            localExceptionConstructor = IllegalArgumentException::new;
        }
        if (StringUtilities.isVisible(errors)) {
            throw localExceptionConstructor.apply(nameof + ": " + errors, null);
        }

        return constructor.apply(intervalData, durationData);
    }

    static TimeEntryPairData getWith(TimeEntryData intervalData, TimeEntryData durationData) {
        return TimeEntryPairDataFactory.getWith(TimeEntryPairData::new, IllegalArgumentException::new, intervalData, durationData);
    }

    static TimeEntryPairData getWithMilliseconds(long interval, long duration) {
        final var intervalData = new TimeEntryData(interval, TimeUnit.MILLISECONDS);
        final var durationData = new TimeEntryData(duration, TimeUnit.MILLISECONDS);
        return TimeEntryPairDataFactory.getWith(intervalData, durationData);
    }

    static TimeEntryPairData getDurationInMilliseconds(long duration) {
        return TimeEntryPairDataFactory.getWithMilliseconds(0, duration);
    }
}
