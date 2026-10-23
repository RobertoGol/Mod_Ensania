package com.trendy.bta.entity.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import java.util.List;

public class RayTracingEntity extends PathfinderMob {
    private int chatCooldown = 0;

    public RayTracingEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            chatCooldown++;
            // Случайные сообщения в чат каждые ~30-60 секунд
            if (chatCooldown > 600 + this.random.nextInt(600)) {
                chatCooldown = 0;
                sendRandomMessageToNearby("Ray Tracing: Я обрабатываю отражения...");
            }
        }
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
        if (!this.level().isClientSide) {
            sendRandomMessageToNearby("Ray Tracing has left the game");
        }
    }

    private void sendRandomMessageToNearby(String message) {
        List<Player> players = this.level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(20.0D));
        for (Player player : players) {
            player.sendSystemMessage(Component.literal(message));
        }
    }
}
