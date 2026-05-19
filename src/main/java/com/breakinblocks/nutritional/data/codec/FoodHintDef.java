package com.breakinblocks.nutritional.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record FoodHintDef(ItemMatch match,
                          float healAmount,
                          boolean isValidFood,
                          ApplicationPhase applicationPhase) {

    public static final Codec<FoodHintDef> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemMatch.CODEC.fieldOf("match").forGetter(FoodHintDef::match),
            Codec.FLOAT.optionalFieldOf("heal_amount", 0.0f).forGetter(FoodHintDef::healAmount),
            Codec.BOOL.optionalFieldOf("is_valid_food", true).forGetter(FoodHintDef::isValidFood),
            ApplicationPhase.CODEC.optionalFieldOf("application_phase", ApplicationPhase.FINISH_USING).forGetter(FoodHintDef::applicationPhase)
    ).apply(instance, FoodHintDef::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, FoodHintDef> STREAM_CODEC = StreamCodec.composite(
            ItemMatch.STREAM_CODEC, FoodHintDef::match,
            ByteBufCodecs.FLOAT, FoodHintDef::healAmount,
            ByteBufCodecs.BOOL, FoodHintDef::isValidFood,
            ApplicationPhase.STREAM_CODEC, FoodHintDef::applicationPhase,
            FoodHintDef::new
    );
}
