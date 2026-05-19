package com.breakinblocks.nutritional.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record DimensionModifier(float yieldMultiplier, float decayMultiplier) {

    public static final DimensionModifier IDENTITY = new DimensionModifier(1.0f, 1.0f);

    public static final Codec<DimensionModifier> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.optionalFieldOf("yield_multiplier", 1.0f).forGetter(DimensionModifier::yieldMultiplier),
            Codec.FLOAT.optionalFieldOf("decay_multiplier", 1.0f).forGetter(DimensionModifier::decayMultiplier)
    ).apply(instance, DimensionModifier::new));
}
