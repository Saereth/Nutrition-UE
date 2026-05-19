package com.breakinblocks.nutritional.data.codec;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;

public enum ApplicationPhase implements StringRepresentable {
    FINISH_USING("finish_using"),
    ON_RIGHT_CLICK("on_right_click");

    public static final Codec<ApplicationPhase> CODEC = StringRepresentable.fromEnum(ApplicationPhase::values);
    public static final StreamCodec<ByteBuf, ApplicationPhase> STREAM_CODEC =
            ByteBufCodecs.idMapper(i -> values()[i], Enum::ordinal);

    private final String name;

    ApplicationPhase(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
