
package net.sweety.unusualend.world.features;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.sweety.unusualend.configuration.UEConfig;

public class RawPurpurFeatureFeature extends OreFeature {
	public RawPurpurFeatureFeature() {
		super(OreConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<OreConfiguration> context) {
		if (!UEConfig.RAW_PURPUR.get())
			return false;
		return super.place(context);
	}
}
