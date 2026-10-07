package com.trendy.bta.registry;
import com.trendy.bta.TrendyBTA;
import com.trendy.bta.entity.custom.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, TrendyBTA.MOD_ID);
    
    // Ванильные рендеры (Человечки)
    public static final Supplier<EntityType<TinyAgnesEntity>> TINY_AGNES = ENTITIES.register("tiny_agnes", () -> EntityType.Builder.of(TinyAgnesEntity::new, MobCategory.CREATURE).sized(0.3f, 0.9f).build("tiny_agnes"));
    public static final Supplier<EntityType<TinyJensEntity>> TINY_JENS = ENTITIES.register("tiny_jens", () -> EntityType.Builder.of(TinyJensEntity::new, MobCategory.CREATURE).sized(0.3f, 0.9f).build("tiny_jens"));
    public static final Supplier<EntityType<TinyVuEntity>> TINY_VU = ENTITIES.register("tiny_vu", () -> EntityType.Builder.of(TinyVuEntity::new, MobCategory.CREATURE).sized(0.3f, 0.9f).build("tiny_vu"));
    public static final Supplier<EntityType<AgentEEEntity>> AGENT_EE = ENTITIES.register("agent_ee", () -> EntityType.Builder.of(AgentEEEntity::new, MobCategory.CREATURE).sized(0.6f, 1.8f).build("agent_ee"));
    public static final Supplier<EntityType<SmartCompanionEntity>> SMART_COMPANION = ENTITIES.register("smart_companion", () -> EntityType.Builder.of(SmartCompanionEntity::new, MobCategory.CREATURE).sized(0.6f, 1.8f).build("smart_companion"));
    
    // GeckoLib Сущности (Сложная геометрия)
    public static final Supplier<EntityType<WaterGuardianEntity>> WATER_GUARDIAN = ENTITIES.register("water_guardian", () -> EntityType.Builder.of(WaterGuardianEntity::new, MobCategory.MONSTER).sized(1.2f, 2.5f).build("water_guardian"));
    public static final Supplier<EntityType<MechaGirlEntity>> MECHA_GIRL = ENTITIES.register("mecha_girl", () -> EntityType.Builder.of(MechaGirlEntity::new, MobCategory.CREATURE).sized(0.8f, 1.9f).build("mecha_girl"));
    public static final Supplier<EntityType<GreenMechEntity>> GREEN_MECH = ENTITIES.register("green_mech", () -> EntityType.Builder.of(GreenMechEntity::new, MobCategory.CREATURE).sized(2.0f, 4.0f).build("green_mech"));
    public static final Supplier<EntityType<ChibiRangerEntity>> CHIBI_RANGER = ENTITIES.register("chibi_ranger", () -> EntityType.Builder.of(ChibiRangerEntity::new, MobCategory.CREATURE).sized(0.5f, 1.2f).build("chibi_ranger"));
    public static final Supplier<EntityType<KitsuneEntity>> KITSUNE = ENTITIES.register("kitsune", () -> EntityType.Builder.of(KitsuneEntity::new, MobCategory.CREATURE).sized(0.6f, 1.8f).build("kitsune"));
}
