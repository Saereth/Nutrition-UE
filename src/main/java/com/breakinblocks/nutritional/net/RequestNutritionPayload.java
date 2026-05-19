package com.breakinblocks.nutritional.net;

import com.breakinblocks.nutritional.Nutritional;
import com.breakinblocks.nutritional.data.attachment.NutritionalAttachments;
import com.breakinblocks.nutritional.data.attachment.PlayerNutritionData;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record RequestNutritionPayload() implements CustomPacketPayload {

    public static final Type<RequestNutritionPayload> TYPE = new Type<>(Nutritional.id("request_nutrition"));

    public static final StreamCodec<RegistryFriendlyByteBuf, RequestNutritionPayload> STREAM_CODEC =
            StreamCodec.unit(new RequestNutritionPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleOnServer(RequestNutritionPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                PlayerNutritionData data = player.getData(NutritionalAttachments.PLAYER_NUTRITION);
                PacketDistributor.sendToPlayer(player, new SyncNutritionPayload(data));
            }
        });
    }
}
