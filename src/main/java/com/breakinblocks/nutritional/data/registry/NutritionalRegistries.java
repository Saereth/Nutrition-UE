package com.breakinblocks.nutritional.data.registry;

import com.breakinblocks.nutritional.Nutritional;
import com.breakinblocks.nutritional.data.codec.DietTierDefinition;
import com.breakinblocks.nutritional.data.codec.DimensionModifier;
import com.breakinblocks.nutritional.data.codec.FoodHintDef;
import com.breakinblocks.nutritional.data.codec.NutrientDefinition;
import com.breakinblocks.nutritional.data.codec.NutritionEffectDef;
import com.breakinblocks.nutritional.data.codec.SustainedRewardDefinition;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

public final class NutritionalRegistries {

    public static final ResourceKey<Registry<NutrientDefinition>> NUTRIENT =
            ResourceKey.createRegistryKey(Nutritional.id("nutrient"));

    public static final ResourceKey<Registry<NutritionEffectDef>> EFFECT =
            ResourceKey.createRegistryKey(Nutritional.id("effect"));

    public static final ResourceKey<Registry<FoodHintDef>> FOOD_HINT =
            ResourceKey.createRegistryKey(Nutritional.id("food_hint"));

    public static final ResourceKey<Registry<DietTierDefinition>> DIET_TIER =
            ResourceKey.createRegistryKey(Nutritional.id("diet_tier"));

    public static final ResourceKey<Registry<SustainedRewardDefinition>> SUSTAINED_REWARD =
            ResourceKey.createRegistryKey(Nutritional.id("sustained_reward"));

    public static final ResourceKey<Registry<DimensionModifier>> DIMENSION_MODIFIER =
            ResourceKey.createRegistryKey(Nutritional.id("dimension_modifier"));

    private NutritionalRegistries() {}

    public static void register(IEventBus modBus) {
        modBus.addListener(NutritionalRegistries::onNewRegistry);
    }

    private static void onNewRegistry(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(NUTRIENT, NutrientDefinition.CODEC, NutrientDefinition.CODEC);
        event.dataPackRegistry(EFFECT, NutritionEffectDef.CODEC, NutritionEffectDef.CODEC);
        event.dataPackRegistry(FOOD_HINT, FoodHintDef.CODEC, FoodHintDef.CODEC);
        event.dataPackRegistry(DIET_TIER, DietTierDefinition.CODEC, DietTierDefinition.CODEC);
        event.dataPackRegistry(SUSTAINED_REWARD, SustainedRewardDefinition.CODEC, SustainedRewardDefinition.CODEC);
        event.dataPackRegistry(DIMENSION_MODIFIER, DimensionModifier.CODEC, DimensionModifier.CODEC);
    }
}
