package com.breakinblocks.nutritional.data.codec;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;

public enum ParticleVisibility implements StringRepresentable {
    TRANSPARENT("transparent", false, false),
    TRANSLUCENT("translucent", true, true),
    OPAQUE("opaque", false, true);

    public static final Codec<ParticleVisibility> CODEC = StringRepresentable.fromEnum(ParticleVisibility::values);
    public static final StreamCodec<ByteBuf, ParticleVisibility> STREAM_CODEC =
            ByteBufCodecs.idMapper(i -> values()[i], Enum::ordinal);

    private final String name;
    private final boolean ambient;
    private final boolean showParticles;

    ParticleVisibility(String name, boolean ambient, boolean showParticles) {
        this.name = name;
        this.ambient = ambient;
        this.showParticles = showParticles;
    }

    public boolean ambient() { return ambient; }
    public boolean showParticles() { return showParticles; }

    @Override
    public String getSerializedName() {
        return name;
    }
}
