package com.trendy.bta.block.entity;
import com.trendy.bta.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
public class UltraFurnaceBlockEntity extends BlockEntity {
    public final ItemStackHandler inventory = new ItemStackHandler(3) { @Override protected void onContentsChanged(int slot) { setChanged(); } };
    public UltraFurnaceBlockEntity(BlockPos pos, BlockState state) { super(ModBlockEntities.ULTRA_FURNACE_BE.get(), pos, state); }
    public void tick() { /* Логика сверхбыстрой плавки */ }
}
