package net.sweety.unusualend.procedures;

import net.minecraft.world.item.ItemStack;

public class PrismaticPearlItemGlowProcedure {
	public static boolean execute(ItemStack stack) {
		return OnRightClickOnAnchorProcedure.getNBTBoolean(stack,"LinkedMirror") || OnRightClickOnAnchorProcedure.getNBTBoolean(stack,"LinkedTotem");
	}
}