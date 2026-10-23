package com.trendy.bta.block;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

public class BookBoxBlock extends HorizontalDirectionalBlock {
    public BookBoxBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { builder.add(FACING); }
    @Override public BlockState getStateForPlacement(BlockPlaceContext context) { return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()); }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!level.isClientSide) {
            // Формула генерации: <x чанка>/<z чанка>/<поворот>/<дистанция>/<Y>
            int chunkX = pos.getX() >> 4;
            int chunkZ = pos.getZ() >> 4;
            int rot = state.getValue(FACING).get2DDataValue(); // 0=S, 1=W, 2=N, 3=E
            int dist = pos.getX() % 16; // Упрощенная дистанция
            String title = chunkX + "/" + chunkZ + "/" + rot + "/" + dist + "/" + pos.getY();

            ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
            book.set(net.minecraft.core.component.DataComponents.CUSTOM_NAME, Component.literal(title));
            
            ItemEntity entity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, book);
            level.addFreshEntity(entity);
        }
        return InteractionResult.SUCCESS;
    }
}
