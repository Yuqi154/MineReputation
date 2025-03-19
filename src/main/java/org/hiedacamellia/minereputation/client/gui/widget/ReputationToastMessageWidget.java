package org.hiedacamellia.minereputation.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.hiedacamellia.immersiveui.client.graphic.gui.IUIGuiUtils;
import org.hiedacamellia.minereputation.client.gui.util.ReputationUtil;
import org.hiedacamellia.minereputation.core.util.ReputationChangeType;

public class ReputationToastMessageWidget extends AbstractWidget {

    private final float time;
    private float count=0;
    private ReputationChangeType type;

    public ReputationToastMessageWidget(int x, int y, int width, int height, float time) {
        super(x, y, width, height, Component.empty());
        this.time = time;
    }

    public void reset(ReputationChangeType type){
        if(count>time)
            count = 0;
        this.setMessage(ReputationUtil.getComponent(type));
        this.setWidth(Minecraft.getInstance().font.width(getMessage())+16+4);
        this.type = type;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int i, int i1, float v) {
        RenderSystem.setShaderColor(1,1,1,1);
        if (count >time) return;
        if(getMessage().getString().isEmpty())return;
        count+=v;
        PoseStack pose = guiGraphics.pose();
        pose.pushPose();
        pose.translate(getX(),getY(),1000.0);
        //smoothstep alpha
        float alpha;
        if(count<time*0.5f){
            alpha = smoothStep(0,time*0.1f,count);
        }else {
            alpha = 1-smoothStep(time*0.9f,time,count);
        }
        pose.scale(alpha,alpha,0);
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,alpha);
        IUIGuiUtils.fillRoundRect(guiGraphics,0,0,width,height,0.02f,0x80000000);
        if(type!=null) {
            pose.pushPose();
            pose.translate(getWidth() - 8, (float) getHeight() /2,0);
            ReputationUtil.render(guiGraphics, type);
            pose.popPose();
        }
        IUIGuiUtils.drawCenteredString(guiGraphics, Minecraft.getInstance().font, getMessage(),(getWidth()-16)/2,getHeight()/2,0xFFFFFFFF,false);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        pose.popPose();
        RenderSystem.disableBlend();
    }

    private float smoothStep(float start,float end,float v){
        v = Math.max(0,Math.min(1,(v-start)/(end-start)));
        return v*v*(3-2*v);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }
}
