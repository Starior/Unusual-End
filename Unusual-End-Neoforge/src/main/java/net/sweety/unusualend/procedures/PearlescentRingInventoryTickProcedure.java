package net.sweety.unusualend.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.sweety.unusualend.init.UnusualEndEnchantments;

public class PearlescentRingInventoryTickProcedure {
	public static void execute(ItemStack stack, Level level) {
		if (NBTProcessor.getNBTDouble(stack,"ringCooldown") < 400) {
			NBTProcessor.setNBTDouble(stack,"ringCooldown", NBTProcessor.getNBTDouble(stack,"ringCooldown") + 0.4);
			if (EnchantmentHelper.getTagEnchantmentLevel(level.holderOrThrow(UnusualEndEnchantments.ARCANE_RECOVERY), stack) != 0) {
				if (NBTProcessor.getNBTDouble(stack,"ringCooldown") + 0.2 * stack.getEnchantmentLevel(level.holderOrThrow(UnusualEndEnchantments.ARCANE_RECOVERY)) >= 400) {
					NBTProcessor.setNBTDouble(stack,"ringCooldown", 400);
				} else {
					NBTProcessor.setNBTDouble(stack,"ringCooldown", NBTProcessor.getNBTDouble(stack,"ringCooldown") + 0.2 * stack.getEnchantmentLevel(level.holderOrThrow(UnusualEndEnchantments.ARCANE_RECOVERY)));
				}
			}
		} else {
			NBTProcessor.setNBTDouble(stack,"ringCooldown", 400);
		}
	}
}
