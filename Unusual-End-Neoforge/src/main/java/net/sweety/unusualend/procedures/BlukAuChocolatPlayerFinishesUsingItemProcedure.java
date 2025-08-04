package net.sweety.unusualend.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.sweety.unusualend.configuration.UEConfig;
import net.sweety.unusualend.init.UnusualEndSounds;

public class BlukAuChocolatPlayerFinishesUsingItemProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double random = 0;
		if (UEConfig.CHOCOLAT_BLUK.get() == true) {
			if (world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY() + 2, entity.getZ()), UnusualEndSounds.FLUTE_FISHE.get(), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound((entity.getX()), (entity.getY() + 2), (entity.getZ()), UnusualEndSounds.FLUTE_FISHE.get(), SoundSource.PLAYERS, 1, 1, false);
					}
				}
			}
		}
	}
}