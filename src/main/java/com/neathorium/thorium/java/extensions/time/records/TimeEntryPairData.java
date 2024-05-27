package com.neathorium.thorium.java.extensions.time.records;

public record TimeEntryPairData(
    TimeEntryData INTERVAL,
    TimeEntryData DURATION
) {}
