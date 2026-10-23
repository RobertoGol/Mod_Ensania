package com.trendy.bta.entity.ai;

import com.trendy.bta.entity.custom.TinyCompanionEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import java.util.EnumSet;

public class AgentMineOreGoal extends Goal {
    private final TinyCompanionEntity agent;
    private BlockPos targetBlock = null;
    private int breakTime = 0;

    public AgentMineOreGoal(TinyCompanionEntity agent) {
        this.agent = agent;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        BlockPos agentPos = agent.blockPosition();
        for (int x = -5; x <= 5; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -5; z <= 5; z++) {
                    BlockPos pos = agentPos.offset(x, y, z);
                    BlockState state = agent.level().getBlockState(pos);
                    if (state.is(BlockTags.MINEABLE_WITH_PICKAXE)) {
                        this.targetBlock = pos;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void tick() {
        if (targetBlock == null) return;
        if (agent.distanceToSqr(targetBlock.getX(), targetBlock.getY(), targetBlock.getZ()) > 4.0D) {
            agent.getNavigation().moveTo(targetBlock.getX(), targetBlock.getY(), targetBlock.getZ(), 1.0D);
        } else {
            agent.getNavigation().stop();
            agent.getLookControl().setLookAt(targetBlock.getX(), targetBlock.getY(), targetBlock.getZ());
            breakTime++;
            if (breakTime > 40) {
                agent.level().destroyBlock(targetBlock, true);
                targetBlock = null;
                breakTime = 0;
            }
        }
    }
}
