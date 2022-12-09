package com.LubieKakao1212.qulib.client.rendering;

import com.LubieKakao1212.qulib.math.AimUtil;
import com.LubieKakao1212.qulib.util.joml.Vector3dUtil;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import io.netty.channel.nio.NioEventLoopGroup;
import org.joml.*;

public class RenderingUtils {

    public static void box(VertexConsumer bufferBuilder, Quaterniond orientation, Vector3d pos, Vector3d scale) {
        Vector3d up = Vector3dUtil.up().rotate(orientation);
        Vector3d down = up.negate(new Vector3d());
        Vector3d north = Vector3dUtil.north().rotate(orientation);
        Vector3d south = north.negate(new Vector3d());
        Vector3d east = Vector3dUtil.east().rotate(orientation);
        Vector3d west = east.negate(new Vector3d());

        Vector3d scale2 = scale.mul(0.5f, new Vector3d());
        Vector3d scaleOffsetUp = up.mul(scale2, new Vector3d());
        Vector3d scaleOffsetNorth = north.mul(scale2, new Vector3d());
        Vector3d scaleOffsetEast = east.mul(scale2, new Vector3d());

        Vector2d scaleXZ = new Vector2d(scale.x, scale.z);
        Vector2d scaleYZ = new Vector2d(scale.y, scale.z);
        Vector2d scaleYX = new Vector2d(scale.y, scale.x);

        //Top y+
        quad(bufferBuilder, up, east, pos.add(scaleOffsetUp, new Vector3d()), scaleXZ);
        //Bottom y-
        quad(bufferBuilder, down, east, pos.add(scaleOffsetUp.negate(), new Vector3d()), scaleXZ);

        //North z-
        quad(bufferBuilder, north, up, pos.add(scaleOffsetNorth, new Vector3d()), scaleYX);
        //South z+
        quad(bufferBuilder, south, up, pos.add(scaleOffsetNorth.negate(), new Vector3d()), scaleYX);

        //East x+
        quad(bufferBuilder, east, up, pos.add(scaleOffsetEast, new Vector3d()), scaleYZ);
        //West x-
        quad(bufferBuilder, west, up,  pos.add(scaleOffsetEast.negate(), new Vector3d()), scaleYX);
    }

    public static void quad(VertexConsumer bufferBuilder, Vector3d normal, Vector3d leftTangent, Vector3d pos, Vector2d scale) {
        quad(bufferBuilder, new Quaterniond().lookAlong(normal, leftTangent), pos, scale);
    }

    public static void quad(VertexConsumer bufferBuilder, Quaterniond orientation, Vector3d pos, Vector2d scale) {
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
    public static void quad(VertexConsumer bufferBuilder, Vector3d v1, Vector3d v2, Vector3d v3, Vector3d v4) {
        vertex(bufferBuilder, v1);
        vertex(bufferBuilder, v2);
        vertex(bufferBuilder, v3);
        vertex(bufferBuilder, v4);
    }

    public static void vertex(VertexConsumer bufferBuilder, Vector3d pos) {
        bufferBuilder.vertex(pos.x, pos.y, pos.z);
        //bufferBuilder.color((float)color.x, (float)color.y, (float)color.z, (float)color.w);
       /* bufferBuilder.uv(0f,0f);
        bufferBuilder.uv2(0);
        bufferBuilder.normal(0f,0f,0f);*/
        bufferBuilder.endVertex();
    }

    RenderingUtils.Mesh CUBE = new RenderingUtils.Mesh(
            new Vector3d[] {
                    new Vector3d(0.5 , 0.5 , 0.5 ),
                    new Vector3d(-0.5, 0.5 , 0.5 ),
                    new Vector3d(0.5 , -0.5, 0.5 ),
                    new Vector3d(-0.5, -0.5, 0.5 ),
                    new Vector3d(0.5 , 0.5 , -0.5),
                    new Vector3d(-0.5, 0.5 , -0.5),
                    new Vector3d(0.5 , -0.5, -0.5),
                    new Vector3d(-0.5, -0.5, -0.5),
            },
            new int[] {
                    4, 5, 1, 0,  //Up Y+
                    2, 3, 7, 6,  //Down Y-
                    0, 1, 3, 2,  //South Z+
                    6, 7, 5, 4,  //North Z-
                    0, 2, 6, 4,  //East X+
                    5, 7, 3, 1   //West X-
            }
    );

    public static class Mesh {
        private Vector3d[] vertices;
        private int[] indices;

        private int quadCount;

        public Mesh(Vector3d[] vertices, int[] indices) {
            this.vertices = vertices;
            this.indices = indices;

            quadCount = indices.length / 4;
            if(quadCount * 4 != indices.length) {
                //throw new Exception("Invalid quad index count");
            }
            for(int i = 0; i < indices.length; i++) {
                if(indices[i] >= vertices.length || indices[i] < 0) {
                    //throw new Exception("Quad index out of bounds");
                }
            }
        }

        public void render(VertexConsumer vertexConsumer, Transformation transformation) {
            Vector3d[] vertices = new Vector3d[this.vertices.length];
            for(int i = 0; i < vertices.length; i++) {
                Vector3d v = vertices[i] = new Vector3d(this.vertices[i]);
                transformation.transform(v);
            }

            for(int i = 0; i < quadCount; i++) {
                quad(vertexConsumer,
                        vertices[indices[4 * i    ]],
                        vertices[indices[4 * i + 1]],
                        vertices[indices[4 * i + 2]],
                        vertices[indices[4 * i + 3]]
                );
            }
        }
    }

}
