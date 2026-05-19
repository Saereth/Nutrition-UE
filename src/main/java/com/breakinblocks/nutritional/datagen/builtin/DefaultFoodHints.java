package com.breakinblocks.nutritional.datagen.builtin;

import com.breakinblocks.nutritional.Nutritional;
import com.breakinblocks.nutritional.data.codec.ApplicationPhase;
import com.breakinblocks.nutritional.data.codec.FoodHintDef;
import com.breakinblocks.nutritional.data.codec.ItemMatch;
import com.breakinblocks.nutritional.data.registry.NutritionalRegistries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;

import java.util.Optional;

public final class DefaultFoodHints {

    public static final ResourceKey<FoodHintDef> MILK_BUCKET = key("milk_bucket");

    private DefaultFoodHints() {}

    public static void bootstrap(BootstrapContext<FoodHintDef> ctx) {
        ctx.register(MILK_BUCKET, new FoodHintDef(
                new ItemMatch(Optional.of(BuiltInRegistries.ITEM.wrapAsHolder(Items.MILK_BUCKET)), Optional.empty(), Optional.empty()),
                4.0f,
                true,
                ApplicationPhase.FINISH_USING
        ));
    }

    private static ResourceKey<FoodHintDef> key(String name) {
        return ResourceKey.create(NutritionalRegistries.FOOD_HINT, Nutritional.id(name));
    }
}
