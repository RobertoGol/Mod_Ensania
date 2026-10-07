package com.trendy.bta;

import com.trendy.bta.registry.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TrendyBTA.MOD_ID)
public class TrendyBTA {
    public static final String MOD_ID = "trendybta";

    public TrendyBTA(IEventBus modEventBus) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModMenuTypes.MENUS.register(modEventBus); // Включили меню!
    }
}
