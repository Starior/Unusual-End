package net.sweety.unusualend.mixins;

import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NoiseGeneratorSettings.class)
public interface NoiseGeneratorSettingsAccess {
	@Accessor("surfaceRule")
	@Mutable
	void addSurfaceRule(SurfaceRules.RuleSource ruleSource);
}
