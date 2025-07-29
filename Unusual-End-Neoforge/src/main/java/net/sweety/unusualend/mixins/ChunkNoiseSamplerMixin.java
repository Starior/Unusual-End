package net.sweety.unusualend.mixins;

import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.sweety.unusualend.endbiomes.SamplerHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(NoiseChunk.class)
public class ChunkNoiseSamplerMixin {
	@Unique
	private long seed;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void init(int horizontalSize, RandomState noiseConfig, int i, int j, NoiseSettings generationShapeConfig, DensityFunctions.BeardifierOrMarker arg, NoiseGeneratorSettings chunkGeneratorSettings, Aquifer.FluidPicker fluidLevelSampler,
			Blender blender, CallbackInfo ci) {
		seed = ((SamplerHooks) (Object) noiseConfig.sampler()).getSeed();
	}

	@Inject(method = "cachedClimateSampler", at = @At("RETURN"))
	private void createMultiNoiseSampler(NoiseRouter noiseRouter, List<Climate.ParameterPoint> list, CallbackInfoReturnable<Climate.Sampler> cir) {
		((SamplerHooks) (Object) cir.getReturnValue()).setSeed(seed);
	}
}
