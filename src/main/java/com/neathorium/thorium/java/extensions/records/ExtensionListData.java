package com.neathorium.thorium.java.extensions.records;

import java.util.List;
import java.util.function.Function;

public record ExtensionListData<T>(Function<List<T>, T> GETTER, Function<List<T>, Integer> END_INDEX_FUNCTION, int START_INDEX) {}
