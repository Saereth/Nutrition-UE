package com.breakinblocks.nutritional.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public record TierMessages(Optional<String> enter, Optional<String> exit) {

    public static final TierMessages EMPTY = new TierMessages(Optional.empty(), Optional.empty());

    public static final Codec<TierMessages> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.optionalFieldOf("enter").forGetter(TierMessages::enter),
            Codec.STRING.optionalFieldOf("exit").forGetter(TierMessages::exit)
    ).apply(instance, TierMessages::new));
}
