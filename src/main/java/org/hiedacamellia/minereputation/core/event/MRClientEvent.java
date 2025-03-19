package org.hiedacamellia.minereputation.core.event;


import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.hiedacamellia.minereputation.MineReputation;
import org.hiedacamellia.minereputation.client.gui.widget.w2s.ExistW2SWidget;
import org.hiedacamellia.minereputation.client.gui.widget.w2s.VillagerReputationW2SWidget;
import org.hiedacamellia.minereputation.core.util.VillagerSelector;

import java.util.List;
import java.util.UUID;

@EventBusSubscriber(modid = MineReputation.MODID, bus = EventBusSubscriber.Bus.GAME,value = Dist.CLIENT)
public class MRClientEvent {

    @SubscribeEvent
    public static void onLoggingOut(final ClientPlayerNetworkEvent.LoggingOut event) {
        ExistW2SWidget.removeALL();
    }

    @SubscribeEvent
    public static void onClientTick(final ClientTickEvent.Post event){


        LocalPlayer player = Minecraft.getInstance().player;
        ClientLevel level = Minecraft.getInstance().level;

        if(player==null)return;
        if(level==null)return;
        if(level.getGameTime()%10!=0)return;


        Vec3 position = player.position();
        //取附近10范围
        AABB aabb = new AABB(position.add(10, 10, 10), position.add(-10, -10, -10));
        List<Entity> entities = level.getEntities(player, aabb, VillagerSelector.INSTANCE);
        entities.forEach(e->{
            Villager villager = (Villager) e;
            UUID uuid = villager.getUUID();
            if(ExistW2SWidget.get(uuid)==null){
                VillagerReputationW2SWidget widget = new VillagerReputationW2SWidget(villager);
                ExistW2SWidget.add(uuid,widget);
            }
        });

    }
}
