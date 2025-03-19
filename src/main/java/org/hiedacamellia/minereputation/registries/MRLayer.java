package org.hiedacamellia.minereputation.registries;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import org.hiedacamellia.immersiveui.client.gui.layer.World2ScreenWidgetLayer;
import org.hiedacamellia.minereputation.MineReputation;
import org.hiedacamellia.minereputation.client.gui.layer.ToastLayer;


@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class MRLayer {
    @SubscribeEvent
    public static void registerOverlay(RegisterGuiLayersEvent event) {
        event.registerBelow(VanillaGuiLayers.DEBUG_OVERLAY,
                MineReputation.rl("toast"),
                ToastLayer.INSTANCE);
    }
}