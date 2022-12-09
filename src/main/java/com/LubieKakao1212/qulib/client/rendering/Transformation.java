package com.LubieKakao1212.qulib.client.rendering;

import org.joml.Matrix4x3d;
import org.joml.Quaterniond;
import org.joml.Vector3d;
import org.joml.Vector4d;

@FunctionalInterface
public interface Transformation {

    Vector3d transform(Vector3d vector);

    default Transformation rotate(Quaterniond rotation) {
        return (v) -> transform(v).rotate(rotation);
    }

    default Transformation scale(Vector3d scale) {
        return (v) -> transform(v).mul(scale);
    }

    default Transformation translate(Vector3d translation) {
        return (v) -> transform(v).add(translation);
    }

    static Transformation rotated(Quaterniond rotation) {
        return (v) -> v.rotate(rotation);
    }

    static Transformation scaled(Vector3d scale) {
        return (v) -> v.mul(scale);
    }

    static Transformation translated(Vector3d translation) {
        return (v) -> v.add(translation);
    }

    static Transformation transformed(Matrix4x3d transformation) {
        return (v) -> transformation.transformPosition(v);
    }
}
