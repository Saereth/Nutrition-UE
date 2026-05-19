package com.breakinblocks.nutritional.event;

import net.minecraft.core.RegistryAccess;
import net.neoforged.bus.api.Event;

public class NutritionalDataReloadedEvent extends Event {

    private final RegistryAccess registryAccess;

    public NutritionalDataReloadedEvent(RegistryAccess registryAccess) {
        this.registryAccess = registryAccess;
    }

    public RegistryAccess getRegistryAccess() {
        return registryAccess;
    }
}
