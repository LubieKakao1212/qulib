package com.LubieKakao1212.jomlmc.util;

import net.minecraft.core.Direction;
import org.joml.Vector3d;

public class Vector3dUtil {

    public static final Vector3d SOUTH = new org.joml.Vector3d(0., 0., 1.);
    public static final Vector3d NORTH = new org.joml.Vector3d(0., 0., -1.);
    public static final Vector3d EAST = new org.joml.Vector3d(1., 0., 0.);
    public static final Vector3d WEST = new org.joml.Vector3d(-1., 0., 0.);
    public static final Vector3d UP = new org.joml.Vector3d(0., 1., 0.);
    public static final Vector3d DOWN = new org.joml.Vector3d(0., -1., 0.);

    public static Vector3d south() {
        return new Vector3d(SOUTH);
    }

    public static Vector3d north() {
        return new Vector3d(NORTH);
    }

    public static Vector3d east() {
        return new Vector3d(EAST);
    }

    public static Vector3d west() {
        return new Vector3d(WEST);
    }

    public static Vector3d up() {
        return new Vector3d(UP);
    }

    public static Vector3d down() {
        return new Vector3d(DOWN);
    }

    public static Vector3d of(Direction dir, Vector3d dest) {
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
}
