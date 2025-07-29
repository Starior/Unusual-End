package net.sweety.unusualend.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.sweety.unusualend.UnusualEnd;

public class WarpedIslandsAreaProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return y > 30 && y < 100 && world.getBiome(BlockPos.containing(x, y, z)).is(UnusualEnd.makeUEID("warped_reef"));
	}
}
