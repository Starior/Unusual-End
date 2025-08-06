package net.sweety.unusualend.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.sweety.unusualend.init.UnusualEndEnchantments;
import net.sweety.unusualend.item.data.StringToDoubleData;

public class PearlescentRingInventoryTickProcedure {
	public static void execute(ItemStack stack, Level level) {
		if (StringToDoubleData.getData(stack,"ringCooldown") < 400) {
			StringToDoubleData.putData(stack,"ringCooldown", StringToDoubleData.getData(stack,"ringCooldown") + 0.4);
			if (EnchantmentHelper.getTagEnchantmentLevel(level.holderOrThrow(UnusualEndEnchantments.ARCANE_RECOVERY), stack) != 0) {
				if (StringToDoubleData.getData(stack,"ringCooldown") + 0.2 * stack.getEnchantmentLevel(level.holderOrThrow(UnusualEndEnchantments.ARCANE_RECOVERY)) >= 400) {
					StringToDoubleData.putData(stack,"ringCooldown", 400);
				} else {
					StringToDoubleData.putData(stack,"ringCooldown", StringToDoubleData.getData(stack,"ringCooldown") + 0.2 * stack.getEnchantmentLevel(level.holderOrThrow(UnusualEndEnchantments.ARCANE_RECOVERY)));
				}
			}
		} else {
			StringToDoubleData.putData(stack,"ringCooldown", 400);
		}
	}
}
