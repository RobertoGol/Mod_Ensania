package com.trendy.bta.block;
import com.trendy.bta.block.entity.QuarryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
public class QuarryBlock extends Block implements EntityBlock {
    public QuarryBlock(Properties properties) { super(properties); }
    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new QuarryBlockEntity(pos, state); }
    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : (lvl, pos, st, be) -> { if (be instanceof QuarryBlockEntity quarry) quarry.tick(); };
    }
    @Override protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof QuarryBlockEntity q) {
                q.isActive = !q.isActive;
                player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Карьер: " + (q.isActive ? "АКТИВЕН" : "ОСТАНОВЛЕН")));
            }
        }
        return InteractionResult.SUCCESS;
    }
}
