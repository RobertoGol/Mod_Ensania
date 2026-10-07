package com.trendy.bta.entity.ai;

import com.trendy.bta.entity.custom.TinyCompanionEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import java.util.EnumSet;
import java.util.List;

public class AgentPickupItemGoal extends Goal {
    private final TinyCompanionEntity agent;
    private ItemEntity targetItem;

    public AgentPickupItemGoal(TinyCompanionEntity agent) {
        this.agent = agent;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        List<ItemEntity> items = agent.level().getEntitiesOfClass(ItemEntity.class, agent.getBoundingBox().inflate(10.0D));
        if (!items.isEmpty()) {
            this.targetItem = items.get(0);
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        if (targetItem != null && targetItem.isAlive()) {
            agent.getNavigation().moveTo(targetItem, 1.2D);
            if (agent.distanceToSqr(targetItem) < 2.0D) {
                targetItem.discard(); // В будущем положим в инвентарь
                agent.playSound(net.minecraft.sounds.SoundEvents.ITEM_PICKUP, 0.2F, 1.0F);
            }
        }
    }
}
