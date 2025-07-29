package net.sweety.unusualend.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.sweety.unusualend.UnusualEnd;

public class EndstoneSproutsAdditionalPlacinggrowthConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return !(world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BlockTags.create(UnusualEnd.makeUEID("gloopstone_valid_placement")));
	}
}
