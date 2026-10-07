package com.trendy.bta.client.screen;
import com.trendy.bta.TrendyBTA;
import com.trendy.bta.block.menu.UsbChargerMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
public class UsbChargerScreen extends AbstractContainerScreen<UsbChargerMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(TrendyBTA.MOD_ID, "textures/gui/usb_charger_gui.png");
    private static final ResourceLocation CREEPER_ACTIVE = ResourceLocation.fromNamespaceAndPath(TrendyBTA.MOD_ID, "textures/gui/creeper_active.png");
    public UsbChargerScreen(UsbChargerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 256; this.imageHeight = 220;
    }
    @Override protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        if (this.menu.blockEntity.isDimensionalLinkActive) {
            guiGraphics.blit(CREEPER_ACTIVE, x + 219, y + 16, 0, 0, 16, 16, 16, 16);
        }
    }
    @Override public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
