
package net.mcreator.unusualend.world.features;

import net.mcreator.unusualend.procedures.PurpurIsland1FeatureAdditionalGenerationConditionProcedure;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SimpleRandomSelectorFeature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;

public class PurpurIslandFeature extends SimpleRandomSelectorFeature {
	public PurpurIslandFeature() {
		super(SimpleRandomFeatureConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<SimpleRandomFeatureConfiguration> context) {
		WorldGenLevel world = context.level();
		int x = context.origin().getX();
		int y = context.origin().getY();
		int z = context.origin().getZ();
		if (!PurpurIsland1FeatureAdditionalGenerationConditionProcedure.execute())
			return false;
		return super.place(context);
	}
}
