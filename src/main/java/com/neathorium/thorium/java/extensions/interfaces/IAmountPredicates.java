package com.neathorium.thorium.java.extensions.interfaces;

public interface IAmountPredicates {
    boolean isSingle();
    boolean isMany();
    boolean hasMoreThan(int amount);
    boolean hasAtleast(int amount);
    boolean hasIndex(int index);
}
