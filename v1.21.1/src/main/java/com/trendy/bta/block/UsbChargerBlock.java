package com.trendy.bta.block;

import com.trendy.bta.block.entity.UsbChargerBlockEntity;
import com.trendy.bta.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class UsbChargerBlock extends Block implements EntityBlock {
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    public UsbChargerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVATED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { builder.add(ACTIVATED); }

    @Override public boolean isSignalSource(BlockState state) { return true; }
    @Override public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) { return state.getValue(ACTIVATED) ? 7 : 0; }

    @Nullable @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new UsbChargerBlockEntity(pos, state); }

    // ИСПРАВЛЕНИЕ: Выдаем предметы ровно в момент УСТАНОВКИ блока игроком
    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof UsbChargerBlockEntity usbBE) {
                if (!usbBE.hasGivenKit) {
                    dropItem(level, pos, new ItemStack(ModItems.REALITY_VISION.get()));
                    dropItem(level, pos, new ItemStack(ModItems.SMARTER_WATCH.get()));
                    dropItem(level, pos, new ItemStack(ModItems.ANKLE_MONITOR.get()));
                    usbBE.hasGivenKit = true;
                    usbBE.setChanged();
                }
            }
        }
    }

    // Открытие интерфейса по клику (без выдачи предметов)
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof UsbChargerBlockEntity usbBE) {
                level.setBlock(pos, state.setValue(ACTIVATED, true), 3);
                player.openMenu(usbBE, pos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    private void dropItem(Level level, BlockPos pos, ItemStack stack) {
        level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, stack));
    }
}
