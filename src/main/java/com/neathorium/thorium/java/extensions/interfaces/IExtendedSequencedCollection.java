package com.neathorium.thorium.java.extensions.interfaces;

import com.neathorium.thorium.java.extensions.records.DividedCollectionData;

public interface IExtendedSequencedCollection<T> {
    T first();
    int lastIndex();
    T last();
    T getFirst();
    T getLast();

    T addFirst();
    T addLast();

    IExtendedSequencedCollection<T> tail();
    IExtendedSequencedCollection<T> initials();

    default DividedCollectionData<T> removeFirst() {
        return new DividedCollectionData<>(this.tail(), this.getFirst());
    }

    default DividedCollectionData<T> removeLast() {
        return new DividedCollectionData<>(this.initials(), this.getLast());
    }

    default IExtendedSequencedCollection<T> identity() {
        return this;
    }
}
