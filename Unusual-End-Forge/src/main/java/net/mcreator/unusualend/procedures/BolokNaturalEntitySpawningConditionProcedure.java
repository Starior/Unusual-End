package net.mcreator.unusualend.procedures;

import net.mcreator.unusualend.init.UnusualendModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class BolokNaturalEntitySpawningConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == UnusualendModBlocks.WARPED_END_STONE.get() || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.END_STONE
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == UnusualendModBlocks.GNEISS.get();
	}
}
