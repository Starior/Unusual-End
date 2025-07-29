package net.sweety.unusualend.procedures;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.configuration.UEConfig;
import net.sweety.unusualend.init.UnusualEndMiscRegister;

public class WarpedInfusionPlayerFinishesUsingItemProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(UnusualEndMiscRegister.WARPED_TENACITY.get())) {
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(UnusualEndMiscRegister.WARPED_TENACITY.get()) ? _livEnt.getEffect(UnusualEndMiscRegister.WARPED_TENACITY.get()).getAmplifier() : 0) == 1) {
				if (!(entity instanceof ServerPlayer _plr2 && _plr2.level() instanceof ServerLevel
						&& _plr2.getAdvancements().getOrStartProgress(_plr2.server.getAdvancements().get(UnusualEnd.makeUEID("drink_max_warped_infusion"))).isDone())) {
					if (entity instanceof ServerPlayer _player) {
						AdvancementHolder _adv = _player.server.getAdvancements().get(UnusualEnd.makeUEID("drink_max_warped_infusion"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(UnusualEndMiscRegister.WARPED_TENACITY.get())
					? _livEnt.getEffect(UnusualEndMiscRegister.WARPED_TENACITY.get()).getAmplifier()
					: 0) >= UEConfig.MAX_WARPED_INFUSION.get() - 1) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(UnusualEndMiscRegister.WARPED_TENACITY.get(),
                            (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(UnusualEndMiscRegister.WARPED_TENACITY.get()) ? _livEnt.getEffect(UnusualEndMiscRegister.WARPED_TENACITY.get()).getDuration() : 0) + 1200,
							entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(UnusualEndMiscRegister.WARPED_TENACITY.get()) ? _livEnt.getEffect(UnusualEndMiscRegister.WARPED_TENACITY.get()).getAmplifier() : 0));
			} else {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(UnusualEndMiscRegister.WARPED_TENACITY.get(), 1200,
                            (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(UnusualEndMiscRegister.WARPED_TENACITY.get()) ? _livEnt.getEffect(UnusualEndMiscRegister.WARPED_TENACITY.get()).getAmplifier() : 0) + 1));
			}
		} else {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(UnusualEndMiscRegister.WARPED_TENACITY.get(), 1200, 0));
		}
		if (world.isClientSide()) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, (float) 0.9, (float) 1.5);
				} else {
					_level.playLocalSound(x, y, z, SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, (float) 0.9, (float) 1.5, false);
				}
			}
		}
	}
}
