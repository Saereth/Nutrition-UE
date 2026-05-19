package com.breakinblocks.nutritional.data.datamap;

import com.breakinblocks.nutritional.Nutritional;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

public final class NutritionalDataMaps {

    public static final DataMapType<Item, NutrientScales> NUTRIENT_SCALES = DataMapType.builder(
            Nutritional.id("nutrient_scales"),
            Registries.ITEM,
            NutrientScales.CODEC
    ).synced(NutrientScales.CODEC, false).build();

    private NutritionalDataMaps() {}

    public static void register(IEventBus modBus) {
        modBus.addListener(NutritionalDataMaps::onRegister);
    }

    private static void onRegister(RegisterDataMapTypesEvent event) {
        event.register(NUTRIENT_SCALES);
    }
}
