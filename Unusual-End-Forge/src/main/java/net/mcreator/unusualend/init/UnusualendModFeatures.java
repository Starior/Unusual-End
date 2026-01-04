package net.mcreator.unusualend.init;

import net.mcreator.unusualend.UnusualEnd;
import net.mcreator.unusualend.world.features.*;
import net.mcreator.unusualend.world.features.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UnusualendModFeatures {
    public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, UnusualEnd.MODID);
    public static final RegistryObject<Feature<?>> PURPUR_ISLAND_1_FEATURE = REGISTRY.register("purpur_island_1_feature", PurpurIslandFeature::new);
    public static final RegistryObject<Feature<?>> HIGHLAND_PLANTS = REGISTRY.register("highland_plants", HighlandPlantsFeature::new);
    public static final RegistryObject<Feature<?>> INFESTED_ENDSTONE = REGISTRY.register("infested_endstone", InfestedEndstoneFeature::new);
    public static final RegistryObject<Feature<?>> RAW_PURPUR_FEATURE = REGISTRY.register("raw_purpur_feature", RawPurpurFeature::new);
    public static final RegistryObject<Feature<?>> STRUCTURE_FEATURE = REGISTRY.register("structure_feature", () -> new StructureFeature(StructureFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<?>> WARPED_PATCHES = REGISTRY.register("warped_patches", WarpedPatchsFeature::new);
}