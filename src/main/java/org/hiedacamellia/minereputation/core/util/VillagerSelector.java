package org.hiedacamellia.minereputation.core.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;

import java.util.function.Predicate;

public class VillagerSelector implements Predicate<Entity> {

    public static final VillagerSelector INSTANCE = new VillagerSelector();

    protected VillagerSelector(){

    }

    @Override
    public boolean test(Entity entity) {
        return entity instanceof Villager;
    }
}
