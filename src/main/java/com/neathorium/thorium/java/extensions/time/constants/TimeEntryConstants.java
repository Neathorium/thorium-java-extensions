package com.neathorium.thorium.java.extensions.time.constants;

import com.neathorium.thorium.java.extensions.time.namespaces.factories.TimeEntryPairDataFactory;
import com.neathorium.thorium.java.extensions.time.records.TimeEntryData;
import com.neathorium.thorium.java.extensions.time.records.TimeEntryPairData;

import java.time.Clock;
import java.util.concurrent.TimeUnit;

public abstract class TimeEntryConstants {
    public static final Clock CLOCK = Clock.systemDefaultZone();
    public static final int DEFAULT_SLEEP_INTERVAL = 300;
    public static final int DEFAULT_SLEEP_DURATION = 3000;

    public static final TimeEntryData INTERVAL = new TimeEntryData(DEFAULT_SLEEP_INTERVAL, TimeUnit.MILLISECONDS);
    public static final TimeEntryData DURATION = new TimeEntryData(DEFAULT_SLEEP_DURATION, TimeUnit.MILLISECONDS);

    public static final TimeEntryPairData DEFAULT_TIME_DATA = TimeEntryPairDataFactory.getWith(INTERVAL, DURATION);
}
