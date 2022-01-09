package com.neathorium.thorium.java.extensions.interfaces.functional.boilers;

import java.util.function.Function;

@FunctionalInterface
public interface IGetMessage {
    Function<Boolean, String> get();
}
