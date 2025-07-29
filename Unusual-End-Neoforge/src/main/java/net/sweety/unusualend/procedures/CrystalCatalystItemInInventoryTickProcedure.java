package net.sweety.unusualend.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.sweety.unusualend.init.UnusualEndMiscRegister;

public class CrystalCatalystItemInInventoryTickProcedure {
	public static void execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getDouble("cataCooldown") < 400) {
			itemstack.getOrCreateTag().putDouble("cataCooldown", (itemstack.getOrCreateTag().getDouble("cataCooldown") + 0.3));
			if (EnchantmentHelper.getItemEnchantmentLevel(UnusualEndMiscRegister.ARCANE_RECOVERY.get(), itemstack) != 0) {
				if (itemstack.getOrCreateTag().getDouble("cataCooldown") + 0.1 * itemstack.getEnchantmentLevel(UnusualEndMiscRegister.ARCANE_RECOVERY.get()) >= 400) {
					itemstack.getOrCreateTag().putDouble("cataCooldown", 400);
				} else {
					itemstack.getOrCreateTag().putDouble("cataCooldown", (itemstack.getOrCreateTag().getDouble("cataCooldown") + 0.1 * itemstack.getEnchantmentLevel(UnusualEndMiscRegister.ARCANE_RECOVERY.get())));
				}
			}
		} else {
			itemstack.getOrCreateTag().putDouble("cataCooldown", 400);
		}
	}
}
