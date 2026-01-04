
package net.mcreator.unusualend.world.features;

import net.mcreator.unusualend.procedures.RawPurpurFeatureAdditionalGenerationConditionProcedure;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class RawPurpurFeature extends OreFeature {
	public RawPurpurFeature() {
		super(OreConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<OreConfiguration> context) {
		WorldGenLevel world = context.level();
		int x = context.origin().getX();
		int y = context.origin().getY();
		int z = context.origin().getZ();
		if (!RawPurpurFeatureAdditionalGenerationConditionProcedure.execute())
			return false;
		return super.place(context);
	}
}
