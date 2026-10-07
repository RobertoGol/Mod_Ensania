package com.trendy.bta.block.entity;
import com.trendy.bta.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
public class QuarryBlockEntity extends BlockEntity {
    public boolean isActive = false;
    private int tickCounter = 0;
    private int currentX = -4, currentZ = -4, currentY;
    public final ItemStackHandler inventory = new ItemStackHandler(27);
    public QuarryBlockEntity(BlockPos pos, BlockState state) { super(ModBlockEntities.QUARRY_BE.get(), pos, state); this.currentY = pos.getY() - 1; }
    public void tick() {
        if (!isActive || level == null || level.isClientSide) return;
        if (++tickCounter >= 10) { tickCounter = 0; mineNextBlock(); }
    }
    private void mineNextBlock() {
        if (currentY < level.getMinBuildHeight()) { isActive = false; return; }
        BlockPos targetPos = this.getBlockPos().offset(currentX, currentY - this.getBlockPos().getY(), currentZ);
        BlockState state = level.getBlockState(targetPos);
        if (!state.isAir() && state.getBlock().defaultDestroyTime() >= 0) {
            Block.dropResources(state, level, targetPos, level.getBlockEntity(targetPos));
            level.setBlock(targetPos, Blocks.AIR.defaultBlockState(), 3);
        }
        if (++currentX > 4) { currentX = -4; if (++currentZ > 4) { currentZ = -4; currentY--; } }
    }
}
