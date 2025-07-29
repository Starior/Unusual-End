
package net.sweety.unusualend.world.features;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SimpleRandomSelectorFeature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.sweety.unusualend.configuration.UEConfig;

public class PurpurIsland1FeatureFeature extends SimpleRandomSelectorFeature {
	public PurpurIsland1FeatureFeature() {
		super(SimpleRandomFeatureConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<SimpleRandomFeatureConfiguration> context) {
		if (!UEConfig.PURPUR_ISLANDS.get())
			return false;
		return super.place(context);
	}
}
