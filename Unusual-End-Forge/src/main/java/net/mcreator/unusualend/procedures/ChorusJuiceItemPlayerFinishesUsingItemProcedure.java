package net.mcreator.unusualend.procedures;

import net.mcreator.unusualend.init.UnusualendModMobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class ChorusJuiceItemPlayerFinishesUsingItemProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity)
			_entity.removeEffect(UnusualendModMobEffects.ENDER_INFECTION.get());
	}
}
