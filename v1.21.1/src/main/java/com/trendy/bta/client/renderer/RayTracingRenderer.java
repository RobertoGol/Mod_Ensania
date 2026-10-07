package com.trendy.bta.client.renderer;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.entity.custom.RayTracingEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RayTracingRenderer extends MobRenderer<RayTracingEntity, HumanoidModel<RayTracingEntity>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(TrendyBTA.MOD_ID, "textures/entity/ray_tracing.png");

    public RayTracingRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new HumanoidModel<>(ctx.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RayTracingEntity entity) {
        return TEXTURE;
    }
}
