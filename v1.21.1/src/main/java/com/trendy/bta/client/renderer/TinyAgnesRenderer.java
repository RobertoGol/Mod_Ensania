package com.trendy.bta.client.renderer;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.entity.custom.TinyAgnesEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TinyAgnesRenderer extends MobRenderer<TinyAgnesEntity, HumanoidModel<TinyAgnesEntity>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(TrendyBTA.MOD_ID, "textures/entity/tiny_agnes.png");

    public TinyAgnesRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new HumanoidModel<>(ctx.bakeLayer(ModelLayers.PLAYER)), 0.25f); // Маленькая тень
    }

    @Override
    public ResourceLocation getTextureLocation(TinyAgnesEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(TinyAgnesEntity entity, PoseStack poseStack, float partialTickTime) {
        // Уменьшаем модель в 2.5 раза, чтобы она стала по-настоящему "Tiny"
        poseStack.scale(0.4F, 0.4F, 0.4F);
        super.scale(entity, poseStack, partialTickTime);
    }
}
