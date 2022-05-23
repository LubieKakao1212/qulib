package com.LubieKakao1212.qulib.util.iterator;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public
interface BlockPosIterator extends Iterator<BlockPos>, Iterable<BlockPos> {
    @NotNull
    @Override
    default Iterator<BlockPos> iterator() { return this; }
}