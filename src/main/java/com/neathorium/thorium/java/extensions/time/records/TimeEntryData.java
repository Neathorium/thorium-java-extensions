package com.neathorium.thorium.java.extensions.time.records;

import java.util.concurrent.TimeUnit;

public record TimeEntryData(
    long LENGTH,
    TimeUnit TIME_UNIT
) {}
