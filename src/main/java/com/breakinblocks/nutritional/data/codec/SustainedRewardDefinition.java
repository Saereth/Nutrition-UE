package com.breakinblocks.nutritional.data.codec;

import com.breakinblocks.nutritional.data.registry.NutritionalRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceKey;

import java.util.List;
import java.util.Optional;

public record SustainedRewardDefinition(int consecutiveDaysRequired,
                                        ResourceKey<DietTierDefinition> qualifyingTier,
                                        List<AttributeModifierEntry> attributeModifiers,
                                        boolean loseOnDeath,
                                        Optional<ResourceKey<DietTierDefinition>> loseOnTierBelow,
                                        RewardDisplay display) {

    public static final Codec<SustainedRewardDefinition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("consecutive_days_required").forGetter(SustainedRewardDefinition::consecutiveDaysRequired),
            ResourceKey.codec(NutritionalRegistries.DIET_TIER).fieldOf("qualifying_tier").forGetter(SustainedRewardDefinition::qualifyingTier),
            AttributeModifierEntry.CODEC.listOf().optionalFieldOf("attribute_modifiers", List.of()).forGetter(SustainedRewardDefinition::attributeModifiers),
            Codec.BOOL.optionalFieldOf("lose_on_death", true).forGetter(SustainedRewardDefinition::loseOnDeath),
            ResourceKey.codec(NutritionalRegistries.DIET_TIER).optionalFieldOf("lose_on_tier_below").forGetter(SustainedRewardDefinition::loseOnTierBelow),
            RewardDisplay.CODEC.fieldOf("display").forGetter(SustainedRewardDefinition::display)
    ).apply(instance, SustainedRewardDefinition::new));
}
