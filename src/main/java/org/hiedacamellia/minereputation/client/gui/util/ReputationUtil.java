package org.hiedacamellia.minereputation.client.gui.util;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.hiedacamellia.minereputation.MineReputation;
import org.hiedacamellia.minereputation.core.util.ReputationChangeType;

public class ReputationUtil {

    public static final ResourceLocation NORMAL_ICON = MineReputation.rl("textures/gui/normal.png");
    public static final ResourceLocation INCREASE_ICON = MineReputation.rl("textures/gui/increase.png");
    public static final ResourceLocation DECREASE_ICON = MineReputation.rl("textures/gui/decrease.png");

    public static void render(GuiGraphics guiGraphics, int value){
        render(guiGraphics,-8,-8,value);
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
