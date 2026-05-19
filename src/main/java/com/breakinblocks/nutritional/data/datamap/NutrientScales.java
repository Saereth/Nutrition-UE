package com.breakinblocks.nutritional.data.datamap;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public record NutrientScales(Map<ResourceLocation, Float> scales) {

    public static final Codec<NutrientScales> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.unboundedMap(ResourceLocation.CODEC, Codec.FLOAT).fieldOf("scales").forGetter(NutrientScales::scales)
    ).apply(instance, NutrientScales::new));

    public float scaleFor(ResourceLocation nutrientId) {
        return scales.getOrDefault(nutrientId, 1.0f);
    }
}
