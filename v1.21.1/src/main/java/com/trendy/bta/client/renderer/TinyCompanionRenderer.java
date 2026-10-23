package com.trendy.bta.client.renderer;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.client.ClientModEvents;
import com.trendy.bta.client.model.TinyCompanionModel;
import com.trendy.bta.entity.custom.TinyCompanionEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TinyCompanionRenderer extends MobRenderer<TinyCompanionEntity, TinyCompanionModel> {
    // Указываем путь к текстуре, которую вытащил скрипт
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(TrendyBTA.MOD_ID, "textures/entity/tiny_companion.png");

    public TinyCompanionRenderer(EntityRendererProvider.Context context) {
        // Размер тени под роботом - 0.5f
        super(context, new TinyCompanionModel(context.bakeLayer(ClientModEvents.TINY_COMPANION_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(TinyCompanionEntity entity) {
        return TEXTURE;
    }
}