package com.LubieKakao1212.qulib.util.joml;

import com.mojang.math.Matrix4f;
import org.joml.Matrix4d;
import org.joml.Matrix4x3d;

import java.nio.FloatBuffer;

public class MatrixUtil {

    public static Matrix4d of(Matrix4f matrix) {
        float[] arr = new float[16];

        FloatBuffer buffer = FloatBuffer.wrap(arr);
        matrix.store(buffer);

        return new Matrix4d(
            arr[0], arr[1], arr[2], arr[3],
            arr[4], arr[5], arr[6], arr[7],
            arr[8], arr[9], arr[10], arr[11],
            arr[12], arr[13], arr[14], arr[15]
        );
    }

}
