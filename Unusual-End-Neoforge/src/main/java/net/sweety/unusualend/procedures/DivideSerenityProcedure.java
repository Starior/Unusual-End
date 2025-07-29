package net.sweety.unusualend.procedures;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.sweety.unusualend.init.UnusualEndMiscRegister;

public class DivideSerenityProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double level = 0;
		double ticks = 0;
		level = entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(UnusualEndMiscRegister.SERENITY.get()) ? _livEnt.getEffect(UnusualEndMiscRegister.SERENITY.get()).getAmplifier() : 0;
		ticks = (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(UnusualEndMiscRegister.SERENITY.get()) ? _livEnt.getEffect(UnusualEndMiscRegister.SERENITY.get()).getDuration() : 0) / 2d - 20;
		if (entity instanceof LivingEntity _entity)
			_entity.removeEffect(UnusualEndMiscRegister.SERENITY.get());
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(UnusualEndMiscRegister.SERENITY.get(), (int) ticks, (int) level, false, false));
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal((Component.translatable("text.unusualend.used_serenity").getString())), true);
	}
}
