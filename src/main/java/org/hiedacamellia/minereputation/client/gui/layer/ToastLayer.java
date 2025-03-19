package org.hiedacamellia.minereputation.client.gui.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.network.chat.Component;
import org.hiedacamellia.minereputation.client.gui.widget.ReputationToastMessageWidget;
import org.hiedacamellia.minereputation.core.util.ReputationChangeType;

public class ToastLayer implements LayeredDraw.Layer {

    public static final ToastLayer INSTANCE = new ToastLayer();

    private ReputationToastMessageWidget widget;

    public void setToast(ReputationChangeType type){
        if(widget == null) return;
        widget.reset(type);
    }

    protected ToastLayer(){
        widget = new ReputationToastMessageWidget(0,0,200,20,100);
    }


    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        int sw = guiGraphics.guiWidth();
        if (widget != null) {
            PoseStack pose = guiGraphics.pose();
            pose.pushPose();
            pose.translate(sw-widget.getWidth(),0,0);

            widget.render(guiGraphics,0,0,deltaTracker.getRealtimeDeltaTicks());
            pose.popPose();
        }


    }

}