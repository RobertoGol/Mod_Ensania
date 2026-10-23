package com.trendy.bta.registry;

import com.trendy.bta.TrendyBTA;
import com.trendy.bta.entity.custom.*;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = TrendyBTA.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        // 1. Агент (Education Edition)
        event.put(ModEntities.AGENT_EE.get(), PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D).build());
                
        // 2. Бот-выживальщик
        event.put(ModEntities.SURVIVAL_BOT.get(), PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D).build());
                
        // 3. Умный компаньон (Берем из его класса)
        event.put(ModEntities.SMART_COMPANION.get(), SmartCompanionEntity.createAttributes().build());
        
        // 4. Шуточный бот Ray Tracing
        event.put(ModEntities.RAY_TRACING.get(), RayTracingEntity.createAttributes().build());
        
        // 5. Малышка Агнес (Tinies)
        event.put(ModEntities.TINY_AGNES.get(), TinyAgnesEntity.createAttributes().build());
    }
}
