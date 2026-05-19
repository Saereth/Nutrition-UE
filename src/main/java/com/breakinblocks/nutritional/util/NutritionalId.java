package com.breakinblocks.nutritional.util;

import com.breakinblocks.nutritional.Nutritional;
import net.minecraft.resources.ResourceLocation;

public final class NutritionalId {

    private NutritionalId() {}

    public static ResourceLocation of(String path) {
        return ResourceLocation.fromNamespaceAndPath(Nutritional.MOD_ID, path);
    }
}
