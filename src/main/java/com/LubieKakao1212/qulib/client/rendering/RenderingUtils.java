package com.LubieKakao1212.qulib.client.rendering;

import com.LubieKakao1212.qulib.math.AimUtil;
import com.LubieKakao1212.qulib.util.joml.Vector3dUtil;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.VertexFormat;
import org.joml.*;

public class RenderingUtils {

    public static void box(BufferBuilder bufferBuilder, Quaterniond orientation, Vector3d pos, Vector3d scale) {
        Vector3d up = Vector3dUtil.up().rotate(orientation);
        Vector3d down = up.negate(new Vector3d());
        Vector3d north = Vector3dUtil.north().rotate(orientation);
        Vector3d south = north.negate(new Vector3d());
        Vector3d east = Vector3dUtil.east().rotate(orientation);
        Vector3d west = east.negate(new Vector3d());

        Vector3d scale2 = scale.mul(2f, new Vector3d());
        Vector3d scaleOffsetUp = up.mul(scale2, new Vector3d());
        Vector3d scaleOffsetNorth = north.mul(scale2, new Vector3d());
        Vector3d scaleOffsetEast = east.mul(scale2, new Vector3d());

        Vector2d scaleXZ = new Vector2d(scale.x, scale.z);
        Vector2d scaleYZ = new Vector2d(scale.y, scale.z);
        Vector2d scaleYX = new Vector2d(scale.y, scale.x);

        //Top y+
        quad(bufferBuilder, up, east, scaleOffsetUp, scaleXZ);
        //Bottom y-
        quad(bufferBuilder, down, east, scaleOffsetUp.negate(), scaleXZ);

        //North z-
        quad(bufferBuilder, north, up, scaleOffsetNorth, scaleYX);
        //South z+
        quad(bufferBuilder, south, up, scaleOffsetNorth.negate(), scaleYX);

        //East x+
        quad(bufferBuilder, east, up, scaleOffsetEast, scaleYZ);
        //West x-
        quad(bufferBuilder, west, up, scaleOffsetEast.negate(), scaleYX);
    }

    public static void quad(BufferBuilder bufferBuilder, Vector3d normal, Vector3d leftTangent, Vector3d pos, Vector2d scale) {
        quad(bufferBuilder, new Quaterniond().lookAlong(normal, leftTangent), pos, scale);
    }

    public static void quad(BufferBuilder bufferBuilder, Quaterniond orientation, Vector3d pos, Vector2d scale) {
        double w = scale.x / 2f;
        double h = scale.y / 2f;

        Vector3d v1 = new Vector3d(w, h, 0.);
        Vector3d v2 = new Vector3d(w, -h, 0.);
        Vector3d v3 = new Vector3d(-w, -h, 0.);
        Vector3d v4 = new Vector3d(-w, h, 0.);

        v1.rotate(orientation);
        v2.rotate(orientation);
        v3.rotate(orientation);
        v4.rotate(orientation);

        v1.add(pos);
        v2.add(pos);
        v3.add(pos);
        v4.add(pos);

        quad(bufferBuilder, v1, v2, v3, v4);
    }

    /**
     * Appends four vertices to supplied bufferBuilder, you must call {@link BufferBuilder#begin(VertexFormat.Mode, VertexFormat)} with {@link VertexFormat.Mode#QUADS} beforehand.
     * Element order POSITION, UV, COLOR
     */
    public static void quad(BufferBuilder bufferBuilder, Vector3d v1, Vector3d v2, Vector3d v3, Vector3d v4) {
        vertex(bufferBuilder, v1);
        vertex(bufferBuilder, v2);
        vertex(bufferBuilder, v3);
        vertex(bufferBuilder, v4);
    }

    public static void vertex(BufferBuilder bufferBuilder, Vector3d pos) {
        bufferBuilder.vertex(pos.x, pos.y, pos.z);
        //bufferBuilder.color((float)color.x, (float)color.y, (float)color.z, (float)color.w);
    }


}
