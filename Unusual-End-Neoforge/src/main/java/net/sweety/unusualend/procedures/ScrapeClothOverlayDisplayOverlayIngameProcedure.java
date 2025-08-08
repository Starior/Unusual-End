package net.sweety.unusualend.procedures;

import net.minecraft.world.entity.Entity;
import net.sweety.unusualend.network.UnusualEndVariables;

public class ScrapeClothOverlayDisplayOverlayIngameProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		UnusualEndVariables.PlayerVariables variables = entity.getData(UnusualEndVariables.PLAYER_VARIABLES.get());
		return variables.scrapeOverlay > 0;
	}
}
