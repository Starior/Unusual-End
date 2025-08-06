package net.sweety.unusualend.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.sweety.unusualend.entity.DraglingEntity;
import net.sweety.unusualend.entity.EndstoneGolemEntity;

@EventBusSubscriber
public class GolemProtectDraglingsProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity(), event.getSource().getEntity());
		}
	}
	private static void execute(LivingIncomingDamageEvent event, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (entity instanceof DraglingEntity) {
			if (sourceentity instanceof EndstoneGolemEntity) {
				if (event != null) {
					event.setCanceled(true);
				}
				if (entity instanceof Mob _entity && (sourceentity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) instanceof LivingEntity _ent)
					_entity.setTarget(_ent);
			}
		}
	}
}
