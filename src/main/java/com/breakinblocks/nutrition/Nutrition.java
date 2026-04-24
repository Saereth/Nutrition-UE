package com.breakinblocks.nutrition;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import com.breakinblocks.nutrition.capabilities.CapabilityManager;
import com.breakinblocks.nutrition.command.ChatCommand;
import com.breakinblocks.nutrition.command.CommandEditNutrition;
import com.breakinblocks.nutrition.compat.CompatManager;
import com.breakinblocks.nutrition.events.EventEatFood;
import com.breakinblocks.nutrition.events.EventPlayerDeath;
import com.breakinblocks.nutrition.events.EventPlayerJoinWorld;
import com.breakinblocks.nutrition.events.EventRegistry;
import com.breakinblocks.nutrition.events.EventWorldTick;
import com.breakinblocks.nutrition.gui.ModGuiHandler;
import com.breakinblocks.nutrition.network.ModPacketHandler;
import com.breakinblocks.nutrition.potions.ModPotions;
import com.breakinblocks.nutrition.proxy.IProxy;
import com.breakinblocks.nutrition.utility.Config;
import com.breakinblocks.nutrition.utility.DataImporter;

@Mod(
     modid = Tags.MODID,
     version = Tags.VERSION,
     name = Tags.MODNAME,
     acceptedMinecraftVersions = "[1.12.2]")

public class Nutrition {

    // Create instance of mod
    @Mod.Instance
    public static Nutrition instance;

    // Create instance of proxy
    // This will vary depending on if the client or server is running
    @SidedProxy(
                clientSide = "com.breakinblocks.nutrition.proxy.ClientProxy",
                serverSide = "com.breakinblocks.nutrition.proxy.ServerProxy")
    public static IProxy proxy;

    // Events
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.registerConfigs(event.getModConfigurationDirectory()); // Load Config file
        ModPacketHandler.registerMessages(); // Register network messages
        CapabilityManager.register(); // Register capability

        ModPotions.createPotions(); // Register custom potions
        MinecraftForge.EVENT_BUS.register(new EventRegistry()); // Register custom potions
        MinecraftForge.EVENT_BUS.register(new EventPlayerJoinWorld()); // Attach capability to player
        MinecraftForge.EVENT_BUS.register(new EventPlayerDeath()); // Player death and warping
        MinecraftForge.EVENT_BUS.register(new EventEatFood()); // Register use item event
        MinecraftForge.EVENT_BUS.register(new EventWorldTick()); // Register update event for nutrition decay and potion
        // effects

        Nutrition.proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Nutrition.instance, new ModGuiHandler()); // Register GUI handler

        Nutrition.proxy.init(event);

        CompatManager.initCompat();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        DataImporter.reload(); // Load nutrients and effects

        Nutrition.proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new ChatCommand());
        event.registerServerCommand(new CommandEditNutrition());
    }
}
