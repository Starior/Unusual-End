package net.sweety.unusualend.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.sweety.unusualend.init.UnusualEndBlocks;

public class LensHardglassBlockDestroyedByExplosionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() < 0.95) {
			world.setBlock(BlockPos.containing(x, y, z), UnusualEndBlocks.LENS_HARDGLASS.get().defaultBlockState(), 3);
		} else {
			world.setBlock(BlockPos.containing(x, y, z), UnusualEndBlocks.CRACKED_LENS_HARDGLASS.get().defaultBlockState(), 3);
		}
	}
}
