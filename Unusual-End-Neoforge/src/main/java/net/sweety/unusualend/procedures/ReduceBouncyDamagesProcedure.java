package net.sweety.unusualend.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.sweety.unusualend.init.UnusualendModBlocks;

@EventBusSubscriber
public class ReduceBouncyDamagesProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingDamageEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getSource(), event.getEntity(), event.getAmount());
		}
	}

	private static void execute(LivingHurtEvent event, LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, double amount) {
		if (damagesource == null || entity == null)
			return;
		if (entity instanceof Player) {
			if (damagesource.is(DamageTypes.FALL)) {
				if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == UnusualendModBlocks.BOUNCY_GLOOPSLATE.get()) {
					if (amount < 3) {
						entity.fallDistance = 0;
						if (event != null) {
							event.setCanceled(true);
						}
					}
				}
			}
		}
	}
}
