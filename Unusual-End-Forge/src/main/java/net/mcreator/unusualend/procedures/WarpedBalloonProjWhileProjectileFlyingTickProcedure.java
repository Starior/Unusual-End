package net.mcreator.unusualend.procedures;

import net.mcreator.unusualend.init.UnusualendModParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;

public class WarpedBalloonProjWhileProjectileFlyingTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (UnusualendModParticleTypes.WARPED_BUBBLES.get()), x, y, z, 2, 0.2, 0.2, 0.2, 0);
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (UnusualendModParticleTypes.BOLOK_PARTICLE.get()), x, y, z, 1, 0.1, 0.1, 0.1, 0);
	}
}
