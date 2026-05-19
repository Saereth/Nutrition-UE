package com.breakinblocks.nutritional.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public record AttributeModifierEntry(Holder<Attribute> attribute,
                                     double amount,
                                     AttributeModifier.Operation operation) {

    public static final Codec<AttributeModifierEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.ATTRIBUTE.holderByNameCodec().fieldOf("attribute").forGetter(AttributeModifierEntry::attribute),
            Codec.DOUBLE.fieldOf("amount").forGetter(AttributeModifierEntry::amount),
            AttributeModifier.Operation.CODEC.fieldOf("operation").forGetter(AttributeModifierEntry::operation)
    ).apply(instance, AttributeModifierEntry::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, AttributeModifierEntry> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.holderRegistry(Registries.ATTRIBUTE), AttributeModifierEntry::attribute,
            ByteBufCodecs.DOUBLE, AttributeModifierEntry::amount,
            AttributeModifier.Operation.STREAM_CODEC, AttributeModifierEntry::operation,
            AttributeModifierEntry::new
    );
}
