package com.breakinblocks.nutritional.data.codec;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;

public enum DetectionMode implements StringRepresentable {
    ANY("any"),
    AVERAGE("average"),
    ALL("all"),
    CUMULATIVE("cumulative");

    public static final Codec<DetectionMode> CODEC = StringRepresentable.fromEnum(DetectionMode::values);
    public static final StreamCodec<ByteBuf, DetectionMode> STREAM_CODEC =
            ByteBufCodecs.idMapper(i -> values()[i], Enum::ordinal);

    private final String name;

    DetectionMode(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
