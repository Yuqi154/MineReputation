package org.hiedacamellia.minereputation.core.network;

import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.hiedacamellia.immersiveui.client.gui.component.w2s.World2ScreenWidget;
import org.hiedacamellia.minereputation.MineReputation;
import org.hiedacamellia.minereputation.client.gui.widget.w2s.ExistW2SWidget;
import org.hiedacamellia.minereputation.client.gui.widget.w2s.VillagerReputationW2SWidget;

import java.util.UUID;

public record VillageReputationS2CMessage(UUID uuid,int value)implements CustomPacketPayload {


    public static final Type<VillageReputationS2CMessage> TYPE = new Type<>(MineReputation.rl("village_reputation_s2c"));
    public static final StreamCodec<RegistryFriendlyByteBuf, VillageReputationS2CMessage> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, VillageReputationS2CMessage::uuid,
            ByteBufCodecs.INT, VillageReputationS2CMessage::value,
            VillageReputationS2CMessage::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleClient(final VillageReputationS2CMessage data, final IPayloadContext context){
        context.enqueueWork(() -> {
            World2ScreenWidget world2ScreenWidget = ExistW2SWidget.get(data.uuid());
            if(world2ScreenWidget instanceof VillagerReputationW2SWidget){
                ((VillagerReputationW2SWidget) world2ScreenWidget).setReputation(data.value());
            }
        });
    }

}
