package net.mcreator.unusualend.procedures;

import net.mcreator.unusualend.network.UnusualendModVariables;
import net.minecraft.world.entity.Entity;

public class ScrapeClothOverlayDisplayOverlayIngameProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity.getCapability(UnusualendModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new UnusualendModVariables.PlayerVariables())).ScrapeOverlay > 0;
	}
}
