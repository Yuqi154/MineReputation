package org.hiedacamellia.minereputation.client.gui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.hiedacamellia.immersiveui.client.graphic.gui.IUIGuiUtils;
import org.hiedacamellia.minereputation.MineReputation;
import org.hiedacamellia.minereputation.core.config.MRClientConfig;
import org.hiedacamellia.minereputation.core.util.ReputationChangeType;

public class ReputationRenderUtil {

    public static final ResourceLocation NORMAL_ICON = MineReputation.rl("textures/gui/normal.png");
    public static final ResourceLocation INCREASE_ICON = MineReputation.rl("textures/gui/increase.png");
    public static final ResourceLocation DECREASE_ICON = MineReputation.rl("textures/gui/decrease.png");

    public static void render(GuiGraphics guiGraphics, int value){
        render(guiGraphics,-8,-8,value);
        renderValue(guiGraphics,0,+20,value);
    }
    public static void render(GuiGraphics guiGraphics, ReputationChangeType type){
        render(guiGraphics,-8,-8,type);
    }
    public static void render(GuiGraphics guiGraphics,int x,int y, int value){
        render(guiGraphics,x,y,ReputationChangeType.calculate(value));
    }
    public static void render(GuiGraphics guiGraphics,int x,int y, ReputationChangeType type){
        switch (type){
            case DECREASE:
                guiGraphics.blit(DECREASE_ICON,x,y,0,0,0,16,16,16,16);
                break;
            case INCREASE:
                guiGraphics.blit(INCREASE_ICON,x,y,0,0,0,16,16,16,16);
                break;
            case NORMAL:
                guiGraphics.blit(NORMAL_ICON,x,y,0,0,0,16,16,16,16);
                break;
        }
    }
    public static void renderValue(GuiGraphics guiGraphics,int x,int y, int value){
        if(MRClientConfig.SHOW_REPUTATION_VALUE.isFalse())return;
        int color = switch (ReputationChangeType.calculate(value)) {
            case DECREASE -> 0xFFFF0000;
            case INCREASE -> 0xFF00FF00;
            case NORMAL -> 0xFFFFFFFF;
        };
        IUIGuiUtils.drawCenteredString(guiGraphics, Minecraft.getInstance().font, String.valueOf(value),x,y,color,false);
    }

    public static final Component NORMAL = Component.translatable("minereputation.reputation.normal");
    public static final Component INCREASE = Component.translatable("minereputation.reputation.increase");
    public static final Component DECREASE = Component.translatable("minereputation.reputation.decrease");

    public static Component getComponent(int value){
        return getComponent(ReputationChangeType.calculate(value));
    }

    public static Component getComponent(ReputationChangeType type){
        return switch (type) {
            case DECREASE -> DECREASE;
            case INCREASE -> INCREASE;
            case NORMAL -> NORMAL;
        };
    }
}
