package com.breakinblocks.nutritional.data.codec;

import com.breakinblocks.nutritional.util.HexColor;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record RewardDisplay(String name, int color) {

    public static final Codec<RewardDisplay> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(RewardDisplay::name),
            HexColor.CODEC.optionalFieldOf("color", 0xFFFFFF).forGetter(RewardDisplay::color)
    ).apply(instance, RewardDisplay::new));
}
