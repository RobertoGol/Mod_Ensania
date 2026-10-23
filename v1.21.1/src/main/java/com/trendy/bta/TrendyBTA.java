package com.trendy.bta;

import com.trendy.bta.me.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegistryEvents;

@Mod(TrendyBTA.MOD_ID)
public class TrendyBTA {
    public static final String MOD_ID = "trendybta";
    
    // ME Network instance
    public static final MENetwork ME_NETWORK = new MENetwork(UUID.randomUUID());

    public TrendyBTA(IEventBus modEventBus) {
        // Регистрация блоков и предметов
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        // Блоковые сущности (Block Entities)
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        // Меню
        ModMenuTypes.MENUS.register(modEventBus);
        // Creative tabs
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        // Register some default ME crafting recipes
        registerDefaultMeRecipes();
    }
    
    private void registerDefaultMeRecipes() {
        // Example: crafting pattern - craft 1 me_diamond from 9 me_cells
        // ME_NETWORK.registerCraftingRecipe(new ItemStack(ModItems.ME_DISK.get()), 
        //         new ItemStack(ModItems.ME_CELL.get(), 9));
        
        // Example: crafting from Ensania rubies
        // ME_NETWORK.registerCraftingRecipe(new ItemStack(ModItems.RUBY.get(), 4),
        //         new ItemStack(ModItems.RUBY.get()));
        
        // These can be overridden by mod configurations
    }
}

