package com.trendy.bta.registry;

import com.trendy.bta.TrendyBTA;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TrendyBTA.MOD_ID);

    public static final Supplier<CreativeModeTab> BTA_TAB = CREATIVE_MODE_TABS.register("bta_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.REALITY_VISION.get()))
                    .title(Component.translatable("creativetab.trendybta"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.USB_CHARGER.get());
                        output.accept(ModBlocks.BOOK_BOX.get());
                        output.accept(ModItems.REALITY_VISION.get());
                        output.accept(ModItems.SMARTER_WATCH.get());
                        output.accept(ModItems.ANKLE_MONITOR.get());
                        output.accept(ModItems.CAMERA.get());
                        output.accept(ModItems.QUIVER.get());
                        output.accept(ModItems.RUBY.get());
                        output.accept(ModItems.TINY_COMPANION_SPAWN_EGG.get());
                    }).build());
}
