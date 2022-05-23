package com.LubieKakao1212.qulib.util.iterator;

import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;

import java.util.Iterator;

public interface Vector3iIterator extends Iterator<Vector3i>, Iterable<Vector3i> {
    @NotNull
    @Override
    default Iterator<Vector3i> iterator() { return this; }
}
