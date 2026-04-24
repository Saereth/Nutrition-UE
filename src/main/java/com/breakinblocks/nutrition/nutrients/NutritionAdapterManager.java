package com.breakinblocks.nutrition.nutrients;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

import com.breakinblocks.nutrition.api.INutritionFood;
import com.breakinblocks.nutrition.api.INutritionFoodAdapter;
import com.breakinblocks.nutrition.api.NutritionUtil;

public class NutritionAdapterManager {

    private static final List<INutritionFoodAdapter> ADAPTERS = new ArrayList<>();

    /**
     * Use {@link NutritionUtil#register(INutritionFoodAdapter)}.
     */
    @Deprecated
    public static void register(INutritionFoodAdapter adapter) {
        ADAPTERS.add(adapter);
    }

    @Nullable
    public static INutritionFood apply(ItemStack itemStack) {
        for (INutritionFoodAdapter adapter : ADAPTERS) {
            if (adapter.canApply(itemStack))
                return adapter.apply(itemStack);
        }
        return null;
    }
}
