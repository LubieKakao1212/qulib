package com.LubieKakao1212.jomlmc.nbt;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import org.joml.Quaterniond;
import org.joml.Vector3d;

public class JomlNBT {

    public static Quaterniond readQuaternion(ListTag nbt) {
        return new Quaterniond(nbt.getDouble(0), nbt.getDouble(1), nbt.getDouble(2), nbt.getDouble(3));
    }

    public static Quaterniond readQuaternion(CompoundTag nbt, String key) {
        return readQuaternion(nbt.getList(key, Tag.TAG_DOUBLE));
    }

    public static ListTag writeQuaternion(Quaterniond value) {
        ListTag nbt = new ListTag();
        nbt.add(DoubleTag.valueOf(value.x()));
        nbt.add(DoubleTag.valueOf(value.y()));
        nbt.add(DoubleTag.valueOf(value.z()));
        nbt.add(DoubleTag.valueOf(value.w()));
        return nbt;
    }

    public static Vector3d readVector3(ListTag nbt) {
        return new Vector3d(nbt.getDouble(0), nbt.getDouble(1), nbt.getDouble(2));
    }

    public static Vector3d readVector3(CompoundTag nbt, String key) {
        return readVector3(nbt.getList(key, Tag.TAG_DOUBLE));
    }

    public static ListTag writeVector3(Vector3d value) {
        ListTag nbt = new ListTag();
        nbt.add(DoubleTag.valueOf(value.x()));
        nbt.add(DoubleTag.valueOf(value.y()));
        nbt.add(DoubleTag.valueOf(value.z()));
        return nbt;
    }

}
