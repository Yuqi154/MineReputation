package org.hiedacamellia.minereputation.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;


public class MRCommonConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue TRACK_RADIUS = BUILDER
            .comment("The radius of the area to track reputation changes")
            .comment("设置要跟踪声望变化的区域半径")
            .defineInRange("track_radius", 10, 1, 100);

    public static final ModConfigSpec SPEC = BUILDER.build();

}
