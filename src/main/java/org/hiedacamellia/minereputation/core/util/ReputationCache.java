package org.hiedacamellia.minereputation.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReputationCache {

    protected static final Map<UUID,Integer> cache = new HashMap<>();

    public static void put(UUID uuid, int reputation){
        cache.put(uuid,reputation);
    }

    public static int get(UUID uuid){
        return cache.getOrDefault(uuid,Integer.MIN_VALUE);
    }

    public static void remove(UUID uuid){
        cache.remove(uuid);
    }

    public static void clear(){
        cache.clear();
    }

}
