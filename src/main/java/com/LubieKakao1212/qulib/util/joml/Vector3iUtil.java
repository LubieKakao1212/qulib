package com.LubieKakao1212.qulib.util.joml;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import org.joml.Vector3d;
import org.joml.Vector3i;

public class Vector3iUtil {

    public static final Vector3i ZERO = new org.joml.Vector3i(0, 0,  0);
    public static final Vector3i SOUTH = new org.joml.Vector3i(0, 0, 1);
    public static final Vector3i NORTH = new org.joml.Vector3i(0, 0, -1);
    public static final Vector3i EAST = new org.joml.Vector3i(1, 0, 0);
    public static final Vector3i WEST = new org.joml.Vector3i(-1, 0, 0);
    public static final Vector3i UP = new org.joml.Vector3i(0, 1, 0);
    public static final Vector3i DOWN = new org.joml.Vector3i(0, -1, 0);

    public static Vector3i zero() {
        return new Vector3i(ZERO);
    }

    public static Vector3i south() {
        return new Vector3i(SOUTH);
    }

    public static Vector3i north() {
        return new Vector3i(NORTH);
    }

    public static Vector3i east() {
        return new Vector3i(EAST);
    }

    public static Vector3i west() {
        return new Vector3i(WEST);
    }

    public static Vector3i up() {
        return new Vector3i(UP);
    }

    public static Vector3i down() {
        return new Vector3i(DOWN);
    }

    public static Vector3i of(Direction dir, Vector3i dest) {
        switch (dir) {
            case SOUTH -> dest.set(SOUTH);
            case NORTH -> dest.set(NORTH);

            case EAST -> dest.set(EAST);
            case WEST -> dest.set(WEST);

            case UP -> dest.set(UP);
            case DOWN -> dest.set(DOWN);
        }

        return dest;
    }

    public static Vector3i of(BlockPos pos) {
        return new Vector3i(pos.getX(), pos.getY(), pos.getZ());
    }

    public static BlockPos toBlockPos(Vector3i pos) {
        return new BlockPos(pos.x(), pos.y(), pos.z());
    }

    public static AABB toAABB(Vector3i min, Vector3i max) {
        return new AABB(min.x, min.y, min.z, max.x, max.y, max.z);
    }

    public static AABB toAABBSize(Vector3i pos, Vector3i size) {
        return new AABB(pos.x, pos.y, pos.z, pos.x + size.x, pos.y + size.y, pos.z + size.z);
    }

}
