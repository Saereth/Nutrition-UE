package com.breakinblocks.nutritional.common;

public final class PlayerModifierScopes {

    public static final PlayerModifierTracker EFFECTS = new PlayerModifierTracker(false);
    public static final PlayerModifierTracker TIER = new PlayerModifierTracker(false);
    public static final PlayerModifierTracker SUSTAINED = new PlayerModifierTracker(true);

    private PlayerModifierScopes() {}
}
