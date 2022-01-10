package com.neathorium.thorium.java.extensions.constants;

import com.neathorium.thorium.java.extensions.records.CardinalityData;

public abstract class CardinalityDefaults {
    public static final CardinalityData ANY = new CardinalityData(false, true, false);
    public static final CardinalityData ALL = new CardinalityData(true, false, true);
    public static final CardinalityData NONE = new CardinalityData(true, false, false);
}
