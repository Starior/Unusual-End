package net.sweety.unusualend.enchantment.categories;

import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ElytraEnchant {
	public static final EnchantmentCategory ELYTRA = EnchantmentCategory.create("elytra", item -> item instanceof ElytraItem);
}
