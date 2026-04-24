package com.breakinblocks.nutrition.events;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.breakinblocks.nutrition.Tags;
import com.breakinblocks.nutrition.capabilities.CapabilityManager;
import com.breakinblocks.nutrition.capabilities.SimpleImpl;
import com.breakinblocks.nutrition.network.Sync;
import com.breakinblocks.nutrition.proxy.ClientProxy;

public class EventPlayerJoinWorld {

    // Set up nutrition tracking on both client and server
    @SubscribeEvent
    public void AttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();

        // Only check against players
        if (!(entity instanceof EntityPlayer))
            return;

        // Start tracking nutrition
        if (!entity.getEntityWorld().isRemote) // Server
            event.addCapability(new ResourceLocation(Tags.MODID, "nutrition"), new CapabilityManager.Provider()); // Attach
                                                                                                                  // capability
                                                                                                                  // to
                                                                                                                  // player
        else if (entity instanceof EntityPlayerSP) // Client. Extra check to ensure it's EntityPlayerSP, not
                                                   // EntityOtherPlayerMP
            ClientProxy.localNutrition = new SimpleImpl(); // Initialize local dummy copy
    }

    // Sync on first join
    @SubscribeEvent
    public void EntityJoinWorldEvent(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        World world = event.getWorld();

        // Only check against players
        if (!(entity instanceof EntityPlayer))
            return;

        // Server only
        if (world.isRemote)
            return;

        // Update nutrition on first join, and on death
        Sync.serverRequest((EntityPlayer) entity);
    }
}
