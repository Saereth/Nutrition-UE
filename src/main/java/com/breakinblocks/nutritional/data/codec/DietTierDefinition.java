package com.breakinblocks.nutritional.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record DietTierDefinition(int priority,
                                 TierCondition condition,
                                 List<AttributeModifierEntry> attributeModifiers,
                                 TierDisplay display,
                                 TierMessages messages) {

    public static final Codec<DietTierDefinition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.optionalFieldOf("priority", 0).forGetter(DietTierDefinition::priority),
            TierCondition.CODEC.fieldOf("condition").forGetter(DietTierDefinition::condition),
            AttributeModifierEntry.CODEC.listOf().optionalFieldOf("attribute_modifiers", List.of()).forGetter(DietTierDefinition::attributeModifiers),
            TierDisplay.CODEC.fieldOf("display").forGetter(DietTierDefinition::display),
            TierMessages.CODEC.optionalFieldOf("messages", TierMessages.EMPTY).forGetter(DietTierDefinition::messages)
    ).apply(instance, DietTierDefinition::new));
}
