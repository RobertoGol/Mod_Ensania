package com.trendy.bta.block.entity;

import com.trendy.bta.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class LogisticsPipeBlockEntity extends BlockEntity {
    // Внутренний буфер трубы (1 слот для транзита)
    public final ItemStackHandler buffer = new ItemStackHandler(1);
    private int tickCounter = 0;

    public LogisticsPipeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LOGISTICS_PIPE_BE.get(), pos, state);
    }

    public void tick() {
        if (level == null || level.isClientSide) return;
        
        tickCounter++;
        if (tickCounter >= 10) { // Проверяем соседей каждые полсекунды
            tickCounter = 0;
            transferItems();
        }
    }

    private void transferItems() {
        // Логика: если труба пустая, пытаемся забрать предмет из соседнего инвентаря
        // Если труба полная, пытаемся передать предмет дальше
        
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = this.getBlockPos().relative(dir);
            BlockEntity neighborBE = level.getBlockEntity(neighborPos);
            
            if (neighborBE != null) {
                // Получаем доступ к инвентарю соседа (Карьер, USB-блок или другая труба)
                IItemHandler neighborInventory = level.getCapability(Capabilities.ItemHandler.BLOCK, neighborPos, dir.getOpposite());
                
                if (neighborInventory != null) {
                    // TODO: Здесь будет полная логика извлечения и вставки предметов через IItemHandler.
                    // Труба будет тянуть предметы из QuarryBlockEntity и толкать их в UsbChargerBlockEntity.
                }
            }
        }
    }
}
