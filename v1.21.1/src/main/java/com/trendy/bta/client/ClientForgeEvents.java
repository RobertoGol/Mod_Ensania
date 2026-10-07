package com.trendy.bta.client;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

@EventBusSubscriber(modid = TrendyBTA.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientForgeEvents {
    
    public static boolean showMap = false;
    private static boolean wasWearingRV = false;

    // ИСПОЛЬЗУЕМ НАШ НЭЙМСПЕЙС "trendybta", так как скрипт положил файлы туда
    private static final ResourceLocation VR_OVERLAY = ResourceLocation.fromNamespaceAndPath(TrendyBTA.MOD_ID, "textures/misc/pumpkinblur.png");
    private static final ResourceLocation SHADER_EFFECT = ResourceLocation.fromNamespaceAndPath(TrendyBTA.MOD_ID, "shaders/post/spider.json");

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        // Отслеживаем нажатие 'J'
        while (ClientModEvents.TOGGLE_MAP_KEY.consumeClick()) {
            showMap = !showMap; // Включаем/отключаем карту
        }

        // Проверяем слот головы
        ItemStack helmet = mc.player.getItemBySlot(EquipmentSlot.HEAD);
        boolean isWearingRV = helmet.getItem() == ModItems.REALITY_VISION.get();

        // Логика включения/отключения шейдера экрана
        if (isWearingRV && !wasWearingRV) {
            if (mc.gameRenderer.currentEffect() == null) {
                mc.gameRenderer.loadEffect(SHADER_EFFECT);
            }
        } else if (!isWearingRV && wasWearingRV) {
            mc.gameRenderer.shutdownEffect();
            showMap = false; // Автоматически выключаем карту, если сняли очки
        }
        wasWearingRV = isWearingRV;
    }

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        ItemStack helmet = mc.player.getItemBySlot(EquipmentSlot.HEAD);
        if (helmet.getItem() == ModItems.REALITY_VISION.get()) {
            int width = event.getGuiGraphics().guiWidth();
            int height = event.getGuiGraphics().guiHeight();

            // 1. Рисуем темный трафарет-очки на весь экран из наших ассетов
            event.getGuiGraphics().blit(VR_OVERLAY, 0, 0, -90, 0.0F, 0.0F, width, height, width, height);

            // 2. Если нажали 'J', рисуем интерфейс карты
            if (showMap) {
                event.getGuiGraphics().drawCenteredString(mc.font, "--- СИСТЕМА ТОПОГРАФИИ АКТИВНА ---", width / 2, 20, 0x00FF00);
                event.getGuiGraphics().drawCenteredString(mc.font, "Координаты: " + mc.player.getBlockPos().toShortString(), width / 2, 35, 0x00FF00);
                event.getGuiGraphics().fill(width / 2 - 100, height / 2 - 100, width / 2 + 100, height / 2 + 100, 0x88000000);
            }
        }
    }
}