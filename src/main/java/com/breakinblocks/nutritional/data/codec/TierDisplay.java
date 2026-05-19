package com.breakinblocks.nutritional.data.codec;

import com.breakinblocks.nutritional.util.HexColor;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.Optional;

public record TierDisplay(String name,
                          int color,
                          Optional<Holder<Item>> icon,
                          boolean hudVisible) {

    public static final Codec<TierDisplay> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(TierDisplay::name),
            HexColor.CODEC.optionalFieldOf("color", 0xFFFFFF).forGetter(TierDisplay::color),
            BuiltInRegistries.ITEM.holderByNameCodec().optionalFieldOf("icon").forGetter(TierDisplay::icon),
            Codec.BOOL.optionalFieldOf("hud_visible", true).forGetter(TierDisplay::hudVisible)
    ).apply(instance, TierDisplay::new));
}
