package com.breakinblocks.nutritional.data.codec;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum CombineMode implements StringRepresentable {
    ALL("all"),
    ANY("any");

    public static final Codec<CombineMode> CODEC = StringRepresentable.fromEnum(CombineMode::values);

    private final String name;

    CombineMode(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
