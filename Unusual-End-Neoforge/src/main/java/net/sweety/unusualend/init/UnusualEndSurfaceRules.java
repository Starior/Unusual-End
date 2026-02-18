package net.sweety.unusualend.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.mixins.NoiseGeneratorSettingsAccess;

@EventBusSubscriber
public class UnusualEndSurfaceRules {
	private static final String NAMESPACE = "unusualend";

	@SubscribeEvent
	public static void init(ServerAboutToStartEvent event) {
		LevelStem levelStem = event.getServer().registryAccess().registryOrThrow(Registries.LEVEL_STEM).get(LevelStem.END);
		ChunkGenerator chunkGenerator = levelStem.generator();
		boolean hasEndBiomes = chunkGenerator.getBiomeSource().possibleBiomes().stream().anyMatch(biomeHolder -> biomeHolder.unwrapKey().orElseThrow().location().getNamespace().equals(NAMESPACE));
		if (hasEndBiomes && chunkGenerator instanceof NoiseBasedChunkGenerator generator) {
			NoiseGeneratorSettings noiseGeneratorSettings = generator.generatorSettings().value();
			if (!registerViaTerraBlender()) {
				registerSurfaceRulesDirect(UnusualEnd.makeUEID("gloopstone_lands"), noiseGeneratorSettings, UnusualEndBlocks.GLOOPSTONE.get().defaultBlockState(), UnusualEndBlocks.GLOOPSLATE.get().defaultBlockState());
				registerSurfaceRulesDirect(UnusualEnd.makeUEID("gloopstone_midlands"), noiseGeneratorSettings, UnusualEndBlocks.GLOOPSLATE.get().defaultBlockState(), UnusualEndBlocks.GLOOPSLATE.get().defaultBlockState());
				registerSurfaceRulesDirect(UnusualEnd.makeUEID("warped_reef"), noiseGeneratorSettings, Blocks.END_STONE.defaultBlockState(), Blocks.END_STONE.defaultBlockState());
			}
		}
	}

	/**
	 * Registers Unusual-End surface rules via TerraBlender API when TerraBlender is present.
	 * This ensures rules are included in TerraBlender's namespaced cache and work with Wover.
	 * @return true if TerraBlender was found and registration succeeded, false to use direct fallback
	 */
	private static boolean registerViaTerraBlender() {
		try {
			Class<?> surfaceRuleManagerClass = Class.forName("terrablender.api.SurfaceRuleManager");
			Class<?> ruleCategoryClass = Class.forName("terrablender.api.SurfaceRuleManager$RuleCategory");
			Object endCategory = Enum.valueOf(ruleCategoryClass.asSubclass(Enum.class), "END");

			SurfaceRules.RuleSource gloopstoneLands = biomeSurfaceRule(
				UnusualEnd.makeUEID("gloopstone_lands"),
				UnusualEndBlocks.GLOOPSTONE.get().defaultBlockState(),
				UnusualEndBlocks.GLOOPSLATE.get().defaultBlockState()
			);
			SurfaceRules.RuleSource gloopstoneMidlands = biomeSurfaceRule(
				UnusualEnd.makeUEID("gloopstone_midlands"),
				UnusualEndBlocks.GLOOPSLATE.get().defaultBlockState(),
				UnusualEndBlocks.GLOOPSLATE.get().defaultBlockState()
			);
			SurfaceRules.RuleSource warpedReef = biomeSurfaceRule(
				UnusualEnd.makeUEID("warped_reef"),
				Blocks.END_STONE.defaultBlockState(),
				Blocks.END_STONE.defaultBlockState()
			);
			SurfaceRules.RuleSource combined = SurfaceRules.sequence(gloopstoneLands, gloopstoneMidlands, warpedReef);

			surfaceRuleManagerClass.getMethod("addSurfaceRules", ruleCategoryClass, String.class, SurfaceRules.RuleSource.class)
				.invoke(null, endCategory, NAMESPACE, combined);
			UnusualEnd.LOGGER.info("Registered Unusual-End surface rules via TerraBlender API");
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		} catch (Exception e) {
			UnusualEnd.LOGGER.warn("TerraBlender present but Unusual-End surface rule registration failed, using direct fallback: {}", e.getMessage());
			return false;
		}
	}

	private static SurfaceRules.RuleSource biomeSurfaceRule(ResourceLocation biome, BlockState groundBlock, BlockState undergroundBlock) {
		return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				SurfaceRules.isBiome(ResourceKey.create(Registries.BIOME, biome)),
				SurfaceRules.sequence(
					SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(groundBlock)),
					SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.state(undergroundBlock))
				)
			)
		);
	}

	public static void registerSurfaceRulesDirect(ResourceLocation biome, NoiseGeneratorSettings noiseGeneratorSettings, BlockState groundBlock, BlockState undergroundBlock) {
		SurfaceRules.RuleSource newRule = SurfaceRules.sequence(
			biomeSurfaceRule(biome, groundBlock, undergroundBlock),
			noiseGeneratorSettings.surfaceRule()
		);
		((NoiseGeneratorSettingsAccess) (Object) noiseGeneratorSettings).addSurfaceRule(newRule);
	}
}
