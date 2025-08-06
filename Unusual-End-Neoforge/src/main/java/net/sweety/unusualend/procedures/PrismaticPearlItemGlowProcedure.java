package net.sweety.unusualend.procedures;

import net.minecraft.world.item.ItemStack;

public class PrismaticPearlItemGlowProcedure {
	public static boolean execute(ItemStack stack) {
		return NBTProcessor.getNBTBoolean(stack,"LinkedMirror") || NBTProcessor.getNBTBoolean(stack,"LinkedTotem");
	}
}