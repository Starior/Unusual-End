package net.sweety.unusualend.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.world.features.*;

public class UnusualendModFeatures {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.FEATURE, UnusualEnd.MODID);
	public static final DeferredHolder<Feature<?>, PurpurIsland1FeatureFeature> PURPUR_ISLAND_1_FEATURE = REGISTRY.register("purpur_island_1_feature", PurpurIsland1FeatureFeature::new);
	public static final DeferredHolder<Feature<?>, HighlandPlantsFeature> HIGHLAND_PLANTS = REGISTRY.register("highland_plants", HighlandPlantsFeature::new);
	public static final DeferredHolder<Feature<?>, InfestedEndstoneFeature> INFESTED_ENDSTONE = REGISTRY.register("infested_endstone", InfestedEndstoneFeature::new);
	public static final DeferredHolder<Feature<?>, RawPurpurFeatureFeature> RAW_PURPUR_FEATURE = REGISTRY.register("raw_purpur_feature", RawPurpurFeatureFeature::new);
	public static final DeferredHolder<Feature<?>, WarpedPatchsFeature> WARPED_PATCHS = REGISTRY.register("warped_patchs", WarpedPatchsFeature::new);
}