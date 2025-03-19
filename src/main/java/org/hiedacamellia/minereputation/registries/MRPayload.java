package org.hiedacamellia.minereputation.registries;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.hiedacamellia.minereputation.core.network.VillageReputationS2CMessage;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class MRPayload {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1.0.0");

        registrar.playToClient(
                VillageReputationS2CMessage.TYPE,
                VillageReputationS2CMessage.STREAM_CODEC,
                VillageReputationS2CMessage::handleClient
        );

    }
}
