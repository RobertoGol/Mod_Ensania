package com.trendy.bta.block;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.block.entity.MeTerminalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

public class MeTerminalBlock extends Block {
    public MeTerminalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            MeTerminalBlockEntity be = (MeTerminalBlockEntity) level.getBlockEntity(pos);
            if (be != null) {
                player.openMenu(be);
            }
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPlayer) {
        super.onRemove(state, level, pos, newState, movedByPlayer);
        if (state.getBlock() != newState.getBlock()) {
            level.getBlockEntity(pos).ifPresent(blockEntity -> {
                blockEntity.setChanged();
            });
        }
    }
}