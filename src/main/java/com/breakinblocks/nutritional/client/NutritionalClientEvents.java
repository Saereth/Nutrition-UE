package com.breakinblocks.nutritional.client;

import com.breakinblocks.nutritional.Nutritional;
import com.breakinblocks.nutritional.common.InvertedNutrientIndex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;

@EventBusSubscriber(modid = Nutritional.MOD_ID, value = Dist.CLIENT)
public final class NutritionalClientEvents {

    private NutritionalClientEvents() {}

    @SubscribeEvent
    public static void onLoggingIn(ClientPlayerNetworkEvent.LoggingIn event) {
        ClientPacketListener connection = Minecraft.getInstance().getConnection();
        if (connection != null) {
            InvertedNutrientIndex.rebuild(connection.registryAccess());
        }
    }

    @SubscribeEvent
    public static void onLoggingOut(ClientPlayerNetworkEvent.LoggingOut event) {
        ClientNutritionCache.clear();
    }
}
