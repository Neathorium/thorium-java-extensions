package com.neathorium.thorium.java.extensions.records;

import com.neathorium.thorium.java.extensions.interfaces.IExtendedSequencedCollection;

public record DividedCollectionData<T>(
    IExtendedSequencedCollection<T> ITEMS,
    T ITEM
) {}
