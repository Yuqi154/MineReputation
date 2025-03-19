package org.hiedacamellia.minereputation.client.gui.widget.w2s;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import org.hiedacamellia.immersiveui.client.gui.component.w2s.World2ScreenWidget;
import org.hiedacamellia.minereputation.client.gui.util.ReputationUtil;
import org.joml.Vector3f;

public class VillagerReputationW2SWidget extends World2ScreenWidget {

    private final Villager villager;
    protected static final Player player = Minecraft.getInstance().player;
    protected static final Font font = Minecraft.getInstance().font;
    protected int reputation;

    public void setReputation(int reputation){
        this.reputation = reputation;
    }

    public VillagerReputationW2SWidget(Villager villager) {
        super(villager.getUUID());
        this.villager = villager;
    }

    @Override
    public void calculateRenderScale(float distanceSqr) {
        this.scale = 2/(float)Math.sqrt(distanceSqr);
        if (distanceSqr > 100 && !shouldRemove) {
            ExistW2SWidget.remove(uuid);
        }
    }

    @Override
    public boolean click(int button) {
        return false;
    }

    @Override
    public boolean scroll(double mouseX, double mouseY, double scrollX, double scrollY) {
        return false;
    }

    @Override
    public void getWorldPos(Vector3f out) {
        if(villager == null) return;
        out.set(villager.getEyePosition().add(0,0.5,0).toVector3f());
    }

    @Override
    public void render(GuiGraphics guiGraphics, boolean highlight, float value, DeltaTracker deltaTracker) {
        if(player == null) return;
        if(!villager.isAlive()){
            ExistW2SWidget.remove(getId());
        }

        RenderSystem.setShaderColor(1,1,1,alpha);

        float x1 = x - (float) 200 / 2;
        float y1 = y - (float) 150 / 2;
        float x2 =  200 + x1;
        float y2 =  150 + y1;

        float centerX = (x1 + x2) / 2;
        float centerY = (y1 + y2) / 2;

        PoseStack pose = guiGraphics.pose();
        pose.pushPose();
        RenderSystem.enableBlend();
        pose.translate(centerX, centerY, 0);
        ReputationUtil.render(guiGraphics,reputation);


        RenderSystem.disableBlend();
        pose.popPose();
        RenderSystem.setShaderColor(1,1,1,1);
    }
}
