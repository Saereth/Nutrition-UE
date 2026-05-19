package com.breakinblocks.nutritional.datagen.builtin;

import com.breakinblocks.nutritional.Nutritional;
import com.breakinblocks.nutritional.attribute.NutritionalAttributes;
import com.breakinblocks.nutritional.data.codec.AttributeModifierEntry;
import com.breakinblocks.nutritional.data.codec.CombineMode;
import com.breakinblocks.nutritional.data.codec.DietTierDefinition;
import com.breakinblocks.nutritional.data.codec.RangeSpec;
import com.breakinblocks.nutritional.data.codec.TierCondition;
import com.breakinblocks.nutritional.data.codec.TierDisplay;
import com.breakinblocks.nutritional.data.codec.TierMessages;
import com.breakinblocks.nutritional.data.registry.NutritionalRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class DefaultTiers {

    public static final ResourceKey<DietTierDefinition> STARVING     = key("starving");
    public static final ResourceKey<DietTierDefinition> MALNOURISHED = key("malnourished");
    public static final ResourceKey<DietTierDefinition> SURVIVING    = key("surviving");
    public static final ResourceKey<DietTierDefinition> NOURISHED    = key("nourished");
    public static final ResourceKey<DietTierDefinition> GOURMAND     = key("gourmand");

    private DefaultTiers() {}

    public static void bootstrap(BootstrapContext<DietTierDefinition> ctx) {
        ctx.register(GOURMAND, new DietTierDefinition(
                100,
                new TierCondition(CombineMode.ALL,
                        Optional.of(new RangeSpec(Optional.of(85.0f), Optional.empty())),
                        Optional.of(new RangeSpec(Optional.of(70.0f), Optional.empty())),
                        Optional.empty(),
                        Map.of()),
                List.of(
                        new AttributeModifierEntry(Attributes.MAX_HEALTH, 4.0, AttributeModifier.Operation.ADD_VALUE),
                        new AttributeModifierEntry(NutritionalAttributes.NUTRIENT_ABSORPTION, 0.1, AttributeModifier.Operation.ADD_VALUE),
                        new AttributeModifierEntry(NutritionalAttributes.NUTRIENT_DECAY_RATE, -0.2, AttributeModifier.Operation.ADD_VALUE)
                ),
                display("tier.nutritional.gourmand", 0xFBC02D, Items.GOLDEN_APPLE),
                new TierMessages(Optional.of("tier.nutritional.gourmand.enter"), Optional.of("tier.nutritional.gourmand.exit"))
        ));

        ctx.register(NOURISHED, new DietTierDefinition(
                75,
                new TierCondition(CombineMode.ALL,
                        Optional.of(new RangeSpec(Optional.of(60.0f), Optional.empty())),
                        Optional.of(new RangeSpec(Optional.of(40.0f), Optional.empty())),
                        Optional.empty(),
                        Map.of()),
                List.of(
                        new AttributeModifierEntry(Attributes.MAX_HEALTH, 2.0, AttributeModifier.Operation.ADD_VALUE),
                        new AttributeModifierEntry(NutritionalAttributes.NUTRIENT_DECAY_RATE, -0.1, AttributeModifier.Operation.ADD_VALUE)
                ),
                display("tier.nutritional.nourished", 0x4CAF50, Items.BREAD),
                new TierMessages(Optional.of("tier.nutritional.nourished.enter"), Optional.of("tier.nutritional.nourished.exit"))
        ));

        ctx.register(SURVIVING, new DietTierDefinition(
                50,
                new TierCondition(CombineMode.ALL,
                        Optional.of(new RangeSpec(Optional.of(30.0f), Optional.empty())),
                        Optional.empty(),
                        Optional.empty(),
                        Map.of()),
                List.of(),
                display("tier.nutritional.surviving", 0xBDBDBD, Items.WHEAT),
                TierMessages.EMPTY
        ));

        ctx.register(MALNOURISHED, new DietTierDefinition(
                25,
                new TierCondition(CombineMode.ANY,
                        Optional.of(new RangeSpec(Optional.empty(), Optional.of(30.0f))),
                        Optional.of(new RangeSpec(Optional.empty(), Optional.of(15.0f))),
                        Optional.empty(),
                        Map.of()),
                List.of(
                        new AttributeModifierEntry(Attributes.MAX_HEALTH, -2.0, AttributeModifier.Operation.ADD_VALUE),
                        new AttributeModifierEntry(NutritionalAttributes.NUTRIENT_ABSORPTION, -0.1, AttributeModifier.Operation.ADD_VALUE)
                ),
                display("tier.nutritional.malnourished", 0xE57373, Items.ROTTEN_FLESH),
                new TierMessages(Optional.of("tier.nutritional.malnourished.enter"), Optional.of("tier.nutritional.malnourished.exit"))
        ));

        ctx.register(STARVING, new DietTierDefinition(
                10,
                new TierCondition(CombineMode.ALL,
                        Optional.of(new RangeSpec(Optional.empty(), Optional.of(15.0f))),
                        Optional.empty(),
                        Optional.empty(),
                        Map.of()),
                List.of(
                        new AttributeModifierEntry(Attributes.MAX_HEALTH, -4.0, AttributeModifier.Operation.ADD_VALUE),
                        new AttributeModifierEntry(Attributes.MOVEMENT_SPEED, -0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                        new AttributeModifierEntry(NutritionalAttributes.NUTRIENT_ABSORPTION, -0.25, AttributeModifier.Operation.ADD_VALUE)
                ),
                display("tier.nutritional.starving", 0x7B1F1F, Items.BONE),
                new TierMessages(Optional.of("tier.nutritional.starving.enter"), Optional.of("tier.nutritional.starving.exit"))
        ));
    }

    private static TierDisplay display(String name, int color, Item icon) {
        Holder<Item> iconHolder = BuiltInRegistries.ITEM.wrapAsHolder(icon);
        return new TierDisplay(name, color, Optional.of(iconHolder), true);
    }

    private static ResourceKey<DietTierDefinition> key(String name) {
        return ResourceKey.create(NutritionalRegistries.DIET_TIER, Nutritional.id(name));
    }
}
