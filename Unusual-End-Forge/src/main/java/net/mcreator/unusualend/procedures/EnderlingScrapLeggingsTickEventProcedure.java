package net.mcreator.unusualend.procedures;

import net.mcreator.unusualend.init.UnusualendModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EnderlingScrapLeggingsTickEventProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.isSprinting()) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(UnusualendModMobEffects.SPECTRAL_STRIDE.get(), 2, 0, false, false));
		}
	}
}
