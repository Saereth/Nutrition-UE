package com.breakinblocks.nutritional.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public record RangeSpec(Optional<Float> min, Optional<Float> max) {

    public static final Codec<RangeSpec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.optionalFieldOf("min").forGetter(RangeSpec::min),
            Codec.FLOAT.optionalFieldOf("max").forGetter(RangeSpec::max)
    ).apply(instance, RangeSpec::new));

    public boolean test(float value) {
        if (min.isPresent() && value < min.get()) return false;
        if (max.isPresent() && value > max.get()) return false;
        return true;
    }
}
