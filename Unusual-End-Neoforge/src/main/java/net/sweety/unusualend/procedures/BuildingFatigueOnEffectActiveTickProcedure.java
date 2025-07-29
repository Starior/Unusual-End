package net.sweety.unusualend.procedures;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.LevelAccessor;

public class BuildingFatigueOnEffectActiveTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Creeper) {
			{
				CompoundTag dataIndex = new CompoundTag();
				entity.saveWithoutId(dataIndex);
				dataIndex.putDouble("ExplosionRadius", 0);
				entity.load(dataIndex);
			}
			{
				CompoundTag dataIndex = new CompoundTag();
				entity.saveWithoutId(dataIndex);
				dataIndex.putDouble("Fuse", (-1));
				entity.load(dataIndex);
			}
			if (world instanceof ServerLevel level)
				level.sendParticles(ParticleTypes.DRAGON_BREATH, x, y, z, 5, 0.5, 0.5, 0.5, 0);
		}
	}
}
