package org.hiedacamellia.minereputation;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.hiedacamellia.minereputation.core.config.MRCommonConfig;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MineReputation.MODID)
public class MineReputation {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "minereputation";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public MineReputation(IEventBus modEventBus, ModContainer modContainer) {

        modContainer.registerConfig(ModConfig.Type.COMMON, MRCommonConfig.SPEC);
    }

    public static ResourceLocation rl(String path){
        return ResourceLocation.fromNamespaceAndPath(MODID,path);
    }
}
