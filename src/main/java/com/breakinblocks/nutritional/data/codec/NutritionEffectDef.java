package com.breakinblocks.nutritional.data.codec;

import com.breakinblocks.nutritional.data.registry.NutritionalRegistries;
import com.breakinblocks.nutritional.util.NutritionalStreamCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

public record NutritionEffectDef(Holder<MobEffect> mobEffect,
                                 int amplifier,
                                 float min,
                                 float max,
                                 DetectionMode detect,
                                 int cumulativeStep,
                                 List<ResourceKey<NutrientDefinition>> nutrients,
                                 ParticleVisibility particles,
                                 int durationTicks,
                                 List<AttributeModifierEntry> attributeModifiers) {

    public static final Codec<NutritionEffectDef> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.MOB_EFFECT.holderByNameCodec().fieldOf("mob_effect").forGetter(NutritionEffectDef::mobEffect),
            Codec.INT.optionalFieldOf("amplifier", 0).forGetter(NutritionEffectDef::amplifier),
            Codec.FLOAT.fieldOf("min").forGetter(NutritionEffectDef::min),
            Codec.FLOAT.fieldOf("max").forGetter(NutritionEffectDef::max),
            DetectionMode.CODEC.optionalFieldOf("detect", DetectionMode.ANY).forGetter(NutritionEffectDef::detect),
            Codec.INT.optionalFieldOf("cumulative_step", 1).forGetter(NutritionEffectDef::cumulativeStep),
            ResourceKey.codec(NutritionalRegistries.NUTRIENT).listOf().fieldOf("nutrients").forGetter(NutritionEffectDef::nutrients),
            ParticleVisibility.CODEC.optionalFieldOf("particles", ParticleVisibility.TRANSPARENT).forGetter(NutritionEffectDef::particles),
            Codec.INT.optionalFieldOf("duration_ticks", 619).forGetter(NutritionEffectDef::durationTicks),
            AttributeModifierEntry.CODEC.listOf().optionalFieldOf("attribute_modifiers", List.of()).forGetter(NutritionEffectDef::attributeModifiers)
    ).apply(instance, NutritionEffectDef::new));

    public static final StreamCodec<ByteBuf, ResourceKey<NutrientDefinition>> NUTRIENT_KEY_STREAM_CODEC =
            NutritionalStreamCodecs.resourceKey(NutritionalRegistries.NUTRIENT);

    public static final StreamCodec<RegistryFriendlyByteBuf, NutritionEffectDef> STREAM_CODEC = StreamCodec.of(
            NutritionEffectDef::encode,
            NutritionEffectDef::decode
    );

    private static void encode(RegistryFriendlyByteBuf buf, NutritionEffectDef def) {
        ByteBufCodecs.holderRegistry(Registries.MOB_EFFECT).encode(buf, def.mobEffect);
        buf.writeVarInt(def.amplifier);
        buf.writeFloat(def.min);
        buf.writeFloat(def.max);
        DetectionMode.STREAM_CODEC.encode(buf, def.detect);
        buf.writeVarInt(def.cumulativeStep);
        NUTRIENT_KEY_STREAM_CODEC.apply(ByteBufCodecs.list()).encode(buf, def.nutrients);
        ParticleVisibility.STREAM_CODEC.encode(buf, def.particles);
        buf.writeVarInt(def.durationTicks);
        AttributeModifierEntry.STREAM_CODEC.apply(ByteBufCodecs.list()).encode(buf, def.attributeModifiers);
    }

    private static NutritionEffectDef decode(RegistryFriendlyByteBuf buf) {
        Holder<MobEffect> mobEffect = ByteBufCodecs.holderRegistry(Registries.MOB_EFFECT).decode(buf);
        int amplifier = buf.readVarInt();
        float min = buf.readFloat();
        float max = buf.readFloat();
        DetectionMode detect = DetectionMode.STREAM_CODEC.decode(buf);
        int cumulativeStep = buf.readVarInt();
        List<ResourceKey<NutrientDefinition>> nutrients = NUTRIENT_KEY_STREAM_CODEC.apply(ByteBufCodecs.list()).decode(buf);
        ParticleVisibility particles = ParticleVisibility.STREAM_CODEC.decode(buf);
        int durationTicks = buf.readVarInt();
        List<AttributeModifierEntry> modifiers = AttributeModifierEntry.STREAM_CODEC.apply(ByteBufCodecs.list()).decode(buf);
        return new NutritionEffectDef(mobEffect, amplifier, min, max, detect, cumulativeStep, nutrients, particles, durationTicks, modifiers);
    }
}
