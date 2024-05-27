package com.neathorium.thorium.java.extensions.time.namespaces.factories;

import com.neathorium.thorium.java.extensions.interfaces.functional.TriFunction;
import com.neathorium.thorium.java.extensions.namespaces.predicates.BasicPredicates;
import com.neathorium.thorium.java.extensions.namespaces.predicates.NullablePredicates;
import com.neathorium.thorium.java.extensions.namespaces.utilities.StringUtilities;
import com.neathorium.thorium.java.extensions.time.constants.TimeEntryConstants;
import com.neathorium.thorium.java.extensions.time.records.TimeData;
import com.neathorium.thorium.java.extensions.time.records.TimeEntryData;
import com.neathorium.thorium.java.extensions.time.records.TimeEntryPairData;

import java.time.Clock;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

public interface TimeDataFactory {
    static TimeData getWith(
        TriFunction<TimeEntryPairData, Integer, Clock, TimeData> constructor,
        BiFunction<String, Throwable, IllegalArgumentException> exceptionConstructor,
        TimeEntryPairData timeData,
        int repetition,
        Clock clock
    ) {
        final var nameof = "TimeDataFactory.getWith";
        final var errors = (
            (NullablePredicates.isNotNull(constructor) ? "" : "Constructor was null.\n") +
            (NullablePredicates.isNotNull(exceptionConstructor) ? "" : "Exception Constructor was null.\n") +
            (NullablePredicates.isNotNull(timeData) ? "" : "Time Entry Pair Data was null.\n") +
            (BasicPredicates.isSmallerThan(repetition, -1) ? "" : "Repetition was less than -1.\n") +
            (NullablePredicates.isNotNull(clock) ? "" : "Clock was null.\n")
        );
        var localExceptionConstructor = exceptionConstructor;
        if (NullablePredicates.isNull(localExceptionConstructor)) {
            localExceptionConstructor = IllegalArgumentException::new;
        }

        if (StringUtilities.isVisible(errors)) {
            throw localExceptionConstructor.apply(nameof + ": " + errors, null);
        }
        return constructor.apply(timeData, repetition, clock);
    }
    static TimeData getWith(TimeEntryPairData timeData, int repetition, Clock clock) {
        return TimeDataFactory.getWith(TimeData::new, IllegalArgumentException::new, timeData, repetition, clock);
    }

    static TimeData getDefault() {
        return TimeDataFactory.getWith(TimeEntryConstants.DEFAULT_TIME_DATA, -1, TimeEntryConstants.CLOCK);
    }

    static TimeData getWith(long interval, long duration, int repetition, Clock clock) {
        final var intervalEntry = new TimeEntryData(interval, TimeUnit.MILLISECONDS);
        final var durationEntry = new TimeEntryData(duration, TimeUnit.MILLISECONDS);
        final var pair = TimeEntryPairDataFactory.getWith(intervalEntry, durationEntry);
        return TimeDataFactory.getWith(pair, repetition, clock);
    }

    static TimeData getWith(long interval, long duration, Clock clock) {
        return TimeDataFactory.getWith(interval, duration, 0, clock);
    }

    static TimeData getWithDefaultClock(long interval, long duration, int repetition) {
        return TimeDataFactory.getWith(interval, duration, repetition, TimeEntryConstants.CLOCK);
    }

    static TimeData getWithDefaultClock(int interval, int timeout, int repetition) {
        return TimeDataFactory.getWith(interval, timeout, repetition, TimeEntryConstants.CLOCK);
    }

    static TimeData getWithDefaultClock(long interval, long duration) {
        return TimeDataFactory.getWith(interval, duration, 0, TimeEntryConstants.CLOCK);
    }

    static TimeData getWithDefaultClock(int interval, int timeout) {
        return TimeDataFactory.getWith(interval, timeout, 0, TimeEntryConstants.CLOCK);
    }
}
