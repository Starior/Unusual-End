package net.sweety.unusualend.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.sweety.unusualend.init.UnusualEndMiscRegister;

public class WarpedAnchorLivingEntityIsHitWithToolProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.ANVIL_PLACE, SoundSource.NEUTRAL, (float) 0.1, (float) Mth.nextDouble(RandomSource.create(), 0.5, 0.8));
			} else {
				_level.playLocalSound(x, y, z, SoundEvents.ANVIL_PLACE, SoundSource.NEUTRAL, (float) 0.1, (float) Mth.nextDouble(RandomSource.create(), 0.5, 0.8), false);
			}
		}
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(UnusualEndMiscRegister.HEAVINESS.get(), 100, 1));
	}
}
