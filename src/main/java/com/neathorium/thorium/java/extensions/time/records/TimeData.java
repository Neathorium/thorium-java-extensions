package com.neathorium.thorium.java.extensions.time.records;

import java.time.Clock;

public record TimeData(
    TimeEntryPairData ENTRY_PAIR_DATA,
    int REPETITION,
    Clock CLOCK
) {}
