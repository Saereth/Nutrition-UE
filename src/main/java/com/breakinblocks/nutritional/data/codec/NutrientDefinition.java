package com.breakinblocks.nutritional.data.codec;

import com.breakinblocks.nutritional.util.HexColor;
import com.breakinblocks.nutritional.util.NutritionalStreamCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public record NutrientDefinition(ItemStack icon,
                                 int color,
                                 float decay,
                                 boolean visible,
                                 float defaultValue,
                                 Optional<TagKey<Item>> items) {

    public static final Codec<NutrientDefinition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.SINGLE_ITEM_CODEC.fieldOf("icon").forGetter(NutrientDefinition::icon),
            HexColor.CODEC.fieldOf("color").forGetter(NutrientDefinition::color),
            Codec.FLOAT.optionalFieldOf("decay", 1.0f).forGetter(NutrientDefinition::decay),
            Codec.BOOL.optionalFieldOf("visible", true).forGetter(NutrientDefinition::visible),
            Codec.FLOAT.optionalFieldOf("default_value", 50.0f).forGetter(NutrientDefinition::defaultValue),
            TagKey.codec(Registries.ITEM).optionalFieldOf("items").forGetter(NutrientDefinition::items)
    ).apply(instance, NutrientDefinition::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, NutrientDefinition> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, NutrientDefinition::icon,
            ByteBufCodecs.INT, NutrientDefinition::color,
            ByteBufCodecs.FLOAT, NutrientDefinition::decay,
            ByteBufCodecs.BOOL, NutrientDefinition::visible,
            ByteBufCodecs.FLOAT, NutrientDefinition::defaultValue,
            ByteBufCodecs.optional(NutritionalStreamCodecs.tagKey(Registries.ITEM)), NutrientDefinition::items,
            NutrientDefinition::new
    );
}
