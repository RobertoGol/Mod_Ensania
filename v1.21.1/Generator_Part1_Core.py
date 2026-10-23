# -*- coding: utf-8 -*-
import os

base_java = os.path.join(".", "src", "main", "java", "com", "trendy", "bta")

def init_core():
    print(">>> ЧАСТЬ 1: Инициализация Ядра и GeckoLib...")
    
    # 1. ОБНОВЛЕНИЕ BUILD.GRADLE (Подключение GeckoLib)
    gradle_path = "build.gradle"
    if os.path.exists(gradle_path):
        with open(gradle_path, "r", encoding="utf-8") as f:
            content = f.read()
        
        # Добавляем репозиторий GeckoLib, если его нет
        if "software.bernie.geckolib" not in content:
            repo_block = """
repositories {
    maven { url 'https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/' }
    mavenCentral()
}"""
            content = content.replace("repositories {", repo_block, 1)
            
            # Добавляем зависимость
            dep_block = """
dependencies {
    implementation 'software.bernie.geckolib:geckolib-neoforge-1.21.1:4.6.6'
"""
            content = content.replace("dependencies {", dep_block, 1)
            
            with open(gradle_path, "w", encoding="utf-8") as f:
                f.write(content)
            print("[+] build.gradle обновлен (GeckoLib подключен).")
    else:
        print("[!] build.gradle не найден. Убедись, что запускаешь скрипт в корне проекта!")

    # 2. РЕЕСТРЫ (Подготовка к загрузке)
    files = {
        os.path.join(base_java, "registry", "ModEntities.java"): """package com.trendy.bta.registry;
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
"""
    }

    for path, content in files.items():
        os.makedirs(os.path.dirname(path), exist_ok=True)
        with open(path, "w", encoding="utf-8") as f:
            f.write(content)
            
    print(">>> ЧАСТЬ 1 ЗАВЕРШЕНА. Движок готов к загрузке сложных моделей.")

if __name__ == "__main__":
    init_core()