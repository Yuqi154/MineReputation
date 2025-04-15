package org.hiedacamellia.minereputation.core.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.hiedacamellia.minereputation.core.config.MRCommonConfig;
import org.hiedacamellia.minereputation.core.network.VillageReputationS2CMessage;
import org.hiedacamellia.minereputation.core.util.ReputationCache;
import org.hiedacamellia.minereputation.core.util.VillagerSelector;

import java.util.List;
import java.util.UUID;

@EventBusSubscriber
public class MRServerEvent {

    @SubscribeEvent
    public static void onPlayerTick(final PlayerTickEvent.Post event){
        Player entity = event.getEntity();

        if(entity instanceof ServerPlayer serverPlayer){
            ServerLevel serverLevel = serverPlayer.serverLevel();

            if(serverLevel.getGameTime()%10!=0)return;

            Vec3 position = serverPlayer.position();

            int range = MRCommonConfig.TRACK_RADIUS.get();
            AABB aabb = new AABB(position.add(range, range, range), position.add(-range, -range, -range));
            List<Entity> entities = serverLevel.getEntities(serverPlayer, aabb, VillagerSelector.INSTANCE);
            entities.forEach(e->{
                Villager villager = (Villager) e;
                UUID uuid = villager.getUUID();
                int playerReputation = villager.getPlayerReputation(serverPlayer);
                ReputationCache.put(uuid, playerReputation);
                PacketDistributor.sendToPlayer(serverPlayer, new VillageReputationS2CMessage(uuid, playerReputation));
            });
            ReputationCache.update();
        }

    }
}
