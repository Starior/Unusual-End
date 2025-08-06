package net.sweety.unusualend.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.sweety.unusualend.init.UnusualEndBlocks;

public class ChorusCaneBlockValidPlacementConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == UnusualEndBlocks.CHORUS_CANE.get() || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == UnusualEndBlocks.BLOOMING_CHORUS_CANE.get()
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == UnusualEndBlocks.PURPUR_EMBEDDED_END_STONE.get()
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BlockTags.create(ResourceLocation.parse("chorus_lib:end_stones")));
	}
}
