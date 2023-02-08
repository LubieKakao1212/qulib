package com.LubieKakao1212.qulib.util.joml;

import com.mojang.math.Quaternion;
import org.joml.Quaterniond;

public class QuaterniondUtil {

    public static double angle(Quaterniond a, Quaterniond b) {
        double angle1 = a.difference(b, new Quaterniond()).angle();

        return angle1;
    }

    public static double smallerAngle(Quaterniond a, Quaterniond b) {
        return (Math.PI) - Math.abs(angle(a, b) - Math.PI);
    }

    public static Quaterniond step(Quaterniond from, Quaterniond to, double maxAngle, Quaterniond dst) {
        double angle = smallerAngle(from, to);

        double step = maxAngle / angle;
        step = Math.min(Math.max(step, 0.), 1.);

        return from.slerp(to, step, dst).normalize();
    }

    public static Quaterniond step(Quaterniond from, Quaterniond to, double maxAngle) {
        return step(from, to, maxAngle, from);
    }

    public static Quaternion toMojang(Quaterniond quat) {
        return new Quaternion((float)quat.x, (float)quat.y, (float)quat.z, (float)quat.w);
    }


}
