package com.breakinblocks.nutritional.datagen.builtin;

import com.breakinblocks.nutritional.Nutritional;
import com.breakinblocks.nutritional.data.codec.AttributeModifierEntry;
import com.breakinblocks.nutritional.data.codec.RewardDisplay;
import com.breakinblocks.nutritional.data.codec.SustainedRewardDefinition;
import com.breakinblocks.nutritional.data.registry.NutritionalRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.List;
import java.util.Optional;

public final class DefaultRewards {

    public static final ResourceKey<SustainedRewardDefinition> HEALTHY_LIFESTYLE = key("healthy_lifestyle");

    private DefaultRewards() {}

    public static void bootstrap(BootstrapContext<SustainedRewardDefinition> ctx) {
        ctx.register(HEALTHY_LIFESTYLE, new SustainedRewardDefinition(
                7,
                DefaultTiers.NOURISHED,
                List.of(
                        new AttributeModifierEntry(Attributes.MAX_HEALTH, 4.0, AttributeModifier.Operation.ADD_VALUE)
                ),
                true,
                Optional.of(DefaultTiers.SURVIVING),
                new RewardDisplay("reward.nutritional.healthy_lifestyle", 0xFBC02D)
        ));
    }

    private static ResourceKey<SustainedRewardDefinition> key(String name) {
        return ResourceKey.create(NutritionalRegistries.SUSTAINED_REWARD, Nutritional.id(name));
    }
}
