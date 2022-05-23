package com.LubieKakao1212.qulib.util.iterator;

import com.LubieKakao1212.qulib.util.joml.Vector3iUtil;
import net.minecraft.core.BlockPos;

public class VectorToBlockPosIterator implements BlockPosIterator {

    private Vector3iIterator iterator;

    public VectorToBlockPosIterator(Vector3iIterator iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public BlockPos next() {
        return Vector3iUtil.toBlockPos(iterator.next());
    }
}
