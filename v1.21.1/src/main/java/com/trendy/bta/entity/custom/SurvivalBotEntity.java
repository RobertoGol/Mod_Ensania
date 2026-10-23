package com.trendy.bta.entity.custom;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
// Бот-выживальщик (Сам играет, фермерит, убивает мобов)
public class SurvivalBotEntity extends PathfinderMob {
    public SurvivalBotEntity(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
}
