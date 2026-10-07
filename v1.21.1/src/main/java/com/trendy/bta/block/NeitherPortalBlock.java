package com.trendy.bta.block;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class NeitherPortalBlock extends Block {
    public NeitherPortalBlock(Properties properties) { super(properties); }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && !entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            // TODO: Телепортация в случайное измерение по хэшу брошенной книги
        }
    }
}
