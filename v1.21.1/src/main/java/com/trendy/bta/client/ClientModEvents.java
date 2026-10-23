package com.trendy.bta.client;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.client.renderer.RayTracingRenderer;
import com.trendy.bta.client.renderer.SmartCompanionRenderer;
import com.trendy.bta.client.renderer.TinyAgnesRenderer;
import com.trendy.bta.client.screen.UsbChargerScreen;
import com.trendy.bta.registry.ModEntities;
import com.trendy.bta.registry.ModMenuTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = TrendyBTA.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Подключаем наши модели к сущностям
        event.registerEntityRenderer(ModEntities.SMART_COMPANION.get(), SmartCompanionRenderer::new);
        event.registerEntityRenderer(ModEntities.RAY_TRACING.get(), RayTracingRenderer::new);
        event.registerEntityRenderer(ModEntities.TINY_AGNES.get(), TinyAgnesRenderer::new);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        // Подключаем GUI интерфейса USB-терминала
        event.register(ModMenuTypes.USB_CHARGER_MENU.get(), UsbChargerScreen::new);
    }
}
