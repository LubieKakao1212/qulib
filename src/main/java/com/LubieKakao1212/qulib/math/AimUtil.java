package com.LubieKakao1212.qulib.math;

import com.LubieKakao1212.qulib.util.joml.Vector3dUtil;
import net.minecraft.core.Direction;
import org.joml.Quaterniond;
import org.joml.Vector3d;

import java.util.Random;

public class AimUtil {

    public static final Random random = new Random();

    public static Vector3d calculateForwardWithUniformSpread(Quaterniond aim, double maxSpread, Vector3d forwardIn) {
        return calculateForwardWithSpread(aim, random.nextDouble() * maxSpread / 2f, forwardIn);
    }

    public static Vector3d calculateForwardWithSpread(Quaterniond aim, double spread, Vector3d forwardIn) {
        return  calculateForwardWithSpread(aim, spread, random.nextDouble() * Math.PI * 2., forwardIn);
    }

    public static Vector3d calculateForwardWithSpread(Quaterniond aim, double spread, double roll, Vector3d forwardIn) {
        return calculateForwardWithSpread(spread, roll, aim.transform(forwardIn));
    }

    public static Vector3d calculateForwardWithSpread(double spread, double roll, Vector3d forward) {
        Vector3d side = perpendicular(forward);

        Quaterniond rotSide = new Quaterniond().fromAxisAngleRad(side, spread);

        Quaterniond rotRound = new Quaterniond().fromAxisAngleRad(forward, roll);
        return rotRound.mul(rotSide).transform(forward);
    }

    public static Vector3d perpendicular(Vector3d vector) {
        if(!MathUtil.axisEquals(vector, Vector3dUtil.SOUTH)) {
            return new Vector3d(0, -vector.z, vector.y);
        }
        return new Vector3d(-vector.y, vector.x, 0);
    }

    public static Quaterniond aimDeg(double pitch, double yaw) {
        return aimRad(pitch * MathUtil.degToRad, yaw * MathUtil.degToRad);
    }

    public static Quaterniond aimRad(double pitch, double yaw) {
        return aimRad(pitch, yaw, Direction.WEST, Direction.UP);
    }

    public static Quaterniond aimRad(double pitch, double yaw, Direction leftOrientation, Direction upOrientation) {
        return aimRad(pitch, yaw, Vector3dUtil.of(leftOrientation, new Vector3d()), Vector3dUtil.of(upOrientation, new Vector3d()));
    }

    public static Quaterniond aimRad(double pitch, double yaw, Vector3d left, Vector3d up) {
        Quaterniond q = new Quaterniond().fromAxisAngleRad(up, -yaw)
                .mul(new Quaterniond().fromAxisAngleRad(left, -pitch));
        return q;
    }

}
