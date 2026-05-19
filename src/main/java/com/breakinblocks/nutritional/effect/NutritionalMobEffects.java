package com.breakinblocks.nutritional.effect;

import com.breakinblocks.nutritional.Nutritional;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class NutritionalMobEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, Nutritional.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> NOURISHED = MOB_EFFECTS.register(
            "nourished",
            () -> new SimpleMobEffect(MobEffectCategory.BENEFICIAL, 0x4caf50)
                    .addAttributeModifier(Attributes.MAX_HEALTH,
                            Nutritional.id("effect/nourished/max_health"), 1.0,
                            AttributeModifier.Operation.ADD_VALUE)
    );

    public static final DeferredHolder<MobEffect, MobEffect> MALNOURISHED = MOB_EFFECTS.register(
            "malnourished",
            () -> new SimpleMobEffect(MobEffectCategory.HARMFUL, 0x7b1f1f)
                    .addAttributeModifier(Attributes.MAX_HEALTH,
                            Nutritional.id("effect/malnourished/max_health"), -1.0,
                            AttributeModifier.Operation.ADD_VALUE)
    );

    public static final DeferredHolder<MobEffect, MobEffect> TOUGHNESS = MOB_EFFECTS.register(
            "toughness",
            () -> new SimpleMobEffect(MobEffectCategory.NEUTRAL, 0x8d6e63)
                    .addAttributeModifier(Attributes.MAX_HEALTH,
                            Nutritional.id("effect/toughness/max_health"), 4.0,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.ARMOR_TOUGHNESS,
                            Nutritional.id("effect/toughness/armor_toughness"), 2.0,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.ATTACK_SPEED,
                            Nutritional.id("effect/toughness/attack_speed"), 0.1,
                            AttributeModifier.Operation.ADD_VALUE)
    );

    private NutritionalMobEffects() {}

    public static void register(IEventBus modBus) {
        MOB_EFFECTS.register(modBus);
    }

    private static final class SimpleMobEffect extends MobEffect {
        SimpleMobEffect(MobEffectCategory category, int color) {
            super(category, color);
        }
    }
}
