package com.breakinblocks.nutrition.proxy;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.breakinblocks.nutrition.capabilities.INutrientManager;
import com.breakinblocks.nutrition.events.EventNutritionButton;
import com.breakinblocks.nutrition.events.EventNutritionKey;
import com.breakinblocks.nutrition.events.EventTooltip;
import com.breakinblocks.nutrition.utility.Config;

public class ClientProxy implements IProxy {

    public static INutrientManager localNutrition; // Holds local copy of data/methods for client-side prediction
    public static KeyBinding keyNutritionGui;

    @Override
    public void preInit(FMLPreInitializationEvent event) {}

    @Override
    public void init(FMLInitializationEvent event) {
        if (Config.enableGui) { // If GUI is enabled
            ClientRegistry.registerKeyBinding(keyNutritionGui = new KeyBinding("key.nutrition", 49, "Nutrition")); // Register
                                                                                                                   // Nutrition
                                                                                                                   // keybind,
                                                                                                                   // default
                                                                                                                   // to
                                                                                                                   // "N"
            MinecraftForge.EVENT_BUS.register(new EventNutritionKey()); // Register key input event to respond to
                                                                        // keybind
            if (Config.enableGuiButton)
                MinecraftForge.EVENT_BUS.register(new EventNutritionButton()); // Register GUI button event
        }

        if (Config.enableTooltips)
            MinecraftForge.EVENT_BUS.register(new EventTooltip()); // Register tooltip event
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {}
}
