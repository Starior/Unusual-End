package net.sweety.unusualend.enchantment.categories;

import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.sweety.unusualend.init.UnusualendModItems;

public class SpearEnchant {
	public static final EnchantmentCategory WARPED_SPEAR = EnchantmentCategory.create("warped_spear", item -> item == UnusualendModItems.WARPED_SPEAR.get());
}
