package com.breakinblocks.nutritional.advancement;

import com.breakinblocks.nutritional.Nutritional;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class NutritionalCriteria {

    public static final DeferredRegister<CriterionTrigger<?>> CRITERIA =
            DeferredRegister.create(Registries.TRIGGER_TYPE, Nutritional.MOD_ID);

    public static final DeferredHolder<CriterionTrigger<?>, TierReachedTrigger> TIER_REACHED =
            CRITERIA.register("tier_reached", TierReachedTrigger::new);

    public static final DeferredHolder<CriterionTrigger<?>, RewardEarnedTrigger> REWARD_EARNED =
            CRITERIA.register("reward_earned", RewardEarnedTrigger::new);

    private NutritionalCriteria() {}

    public static void register(IEventBus modBus) {
        CRITERIA.register(modBus);
    }
}
