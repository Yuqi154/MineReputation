package org.hiedacamellia.minereputation.core.util;

import org.hiedacamellia.minereputation.MineReputation;

import java.util.*;

public class ReputationCache {

    protected static final Map<UUID,Integer> cache = new HashMap<>();

    public static Set<UUID> queried = new HashSet<>();

    public static void update(){
        HashSet<UUID> uuids = new HashSet<>(cache.keySet());
        uuids.forEach(uuid->{
            if(!queried.contains(uuid)){
                cache.remove(uuid);
            }
        });
        queried.clear();
    }

    public static void put(UUID uuid, int reputation){
        cache.put(uuid,reputation);
    }

    public static int get(UUID uuid){
        queried.add(uuid);
        return cache.getOrDefault(uuid,Integer.MIN_VALUE);
    }

    public static void remove(UUID uuid){
        cache.remove(uuid);
    }

    public static void clear(){
        cache.clear();
    }

}
