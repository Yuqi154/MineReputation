package org.hiedacamellia.minereputation.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;


public class MRClientConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue RENDER_OVER_VILLAGER = BUILDER
            .comment("Set to true to enable rendering over villager")
            .comment("设置为true以启用在村民上方的渲染")
            .define("render_over_village", true);

    public static final ModConfigSpec.BooleanValue SHOW_REPUTATION_VALUE = BUILDER
            .comment("Set to true to enable showing reputation value")
            .comment("设置为true以启用显示声望值")
            .define("show_reputation_value", false);

    public static final ModConfigSpec SPEC = BUILDER.build();

}
