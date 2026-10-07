package com.trendy.bta.registry;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TrendyBTA.MOD_ID);

    // Основные механизмы
    public static final DeferredBlock<Block> USB_CHARGER = registerBlock("usb_charger", () -> new UsbChargerBlock(BlockBehaviour.Properties.of().strength(1.5f).lightLevel(state -> state.getValue(UsbChargerBlock.ACTIVATED) ? 10 : 0)));
    public static final DeferredBlock<Block> QUARRY = registerBlock("quarry", () -> new QuarryBlock(BlockBehaviour.Properties.of().strength(3.5f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> ULTRA_FURNACE = registerBlock("ultra_furnace", () -> new UltraFurnaceBlock(BlockBehaviour.Properties.of().strength(3.5f).requiresCorrectToolForDrops().lightLevel(s -> 13)));
    
    // ЛОГИСТИКА
    public static final DeferredBlock<Block> LOGISTICS_PIPE = registerBlock("logistics_pipe", () -> new LogisticsPipeBlock(BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.METAL).noOcclusion()));

    // Коробка книг и портал
    public static final DeferredBlock<Block> BOOK_BOX = registerBlock("book_box", () -> new BookBoxBlock(BlockBehaviour.Properties.of().strength(1.5f).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> NEITHER_PORTAL = registerBlock("neither_portal", () -> new NeitherPortalBlock(BlockBehaviour.Properties.of().noCollission().strength(-1.0F).sound(SoundType.GLASS).lightLevel(s -> 11)));
    public static final DeferredBlock<Block> ACTIVE_COAL_BLOCK = registerBlock("active_coal_block", () -> new Block(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).requiresCorrectToolForDrops()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }
}
