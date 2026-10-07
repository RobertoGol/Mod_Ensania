package com.trendy.bta.registry;
import com.trendy.bta.TrendyBTA;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
public class ModDimensions {
    public static final ResourceKey<Level> MINING_DIM_KEY = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(TrendyBTA.MOD_ID, "mining_dim"));
    public static final ResourceKey<DimensionType> MINING_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(TrendyBTA.MOD_ID, "mining_dim_type"));
}
