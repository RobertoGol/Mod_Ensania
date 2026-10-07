package com.trendy.bta.registry;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.block.entity.LogisticsPipeBlockEntity;
import com.trendy.bta.block.entity.QuarryBlockEntity;
import com.trendy.bta.block.entity.UltraFurnaceBlockEntity;
import com.trendy.bta.block.entity.UsbChargerBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TrendyBTA.MOD_ID);

    public static final Supplier<BlockEntityType<UsbChargerBlockEntity>> USB_CHARGER_BE = BLOCK_ENTITIES.register("usb_charger_be", () -> BlockEntityType.Builder.of(UsbChargerBlockEntity::new, ModBlocks.USB_CHARGER.get()).build(null));
    public static final Supplier<BlockEntityType<QuarryBlockEntity>> QUARRY_BE = BLOCK_ENTITIES.register("quarry_be", () -> BlockEntityType.Builder.of(QuarryBlockEntity::new, ModBlocks.QUARRY.get()).build(null));
    public static final Supplier<BlockEntityType<UltraFurnaceBlockEntity>> ULTRA_FURNACE_BE = BLOCK_ENTITIES.register("ultra_furnace_be", () -> BlockEntityType.Builder.of(UltraFurnaceBlockEntity::new, ModBlocks.ULTRA_FURNACE.get()).build(null));
    
    // РЕГИСТРАЦИЯ ТРУБЫ
    public static final Supplier<BlockEntityType<LogisticsPipeBlockEntity>> LOGISTICS_PIPE_BE = BLOCK_ENTITIES.register("logistics_pipe_be", () -> BlockEntityType.Builder.of(LogisticsPipeBlockEntity::new, ModBlocks.LOGISTICS_PIPE.get()).build(null));
}
