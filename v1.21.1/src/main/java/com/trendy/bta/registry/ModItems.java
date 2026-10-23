package com.trendy.bta.registry;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.item.ArrowBagItem;
import com.trendy.bta.item.BalloonItem;
import com.trendy.bta.item.CameraItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TrendyBTA.MOD_ID);

    // Существующие предметы из 1.RV-Pre1
    public static final DeferredItem<Item> REALITY_VISION = ITEMS.register("reality_vision", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SMARTER_WATCH = ITEMS.register("smarter_watch", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> ANKLE_MONITOR = ITEMS.register("ankle_monitor", () -> new Item(new Item.Properties().stacksTo(1)));
    
    // Рубин
    public static final DeferredItem<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties()));
    
    // Новые гаджеты
    public static final DeferredItem<Item> CAMERA = ITEMS.register("camera", () -> new CameraItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> BALLOON = ITEMS.register("balloon", () -> new BalloonItem(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> ARROW_BAG = ITEMS.register("arrow_bag", () -> new ArrowBagItem(new Item.Properties().stacksTo(1)));
    
    // Яйца призыва для наших новых сущностей
    public static final DeferredItem<Item> TINY_COMPANION_SPAWN_EGG = ITEMS.register("tiny_companion_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.SMART_COMPANION, 0xaaaaaa, 0x00ff00, new Item.Properties()));
    public static final DeferredItem<Item> RAY_TRACING_SPAWN_EGG = ITEMS.register("ray_tracing_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.RAY_TRACING, 0xffffff, 0x000000, new Item.Properties()));
    public static final DeferredItem<Item> TINY_AGNES_SPAWN_EGG = ITEMS.register("tiny_agnes_spawn_egg", 
            () -> new DeferredSpawnEggItem(ModEntities.TINY_AGNES, 0xffa500, 0xffffff, new Item.Properties()));
}
