
package net.mcreator.unusualend.enchantment;

import net.mcreator.unusualend.ModEnchants;
import net.mcreator.unusualend.init.UnusualendModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.List;

public class BenevolenceEnchantment extends Enchantment {
	public BenevolenceEnchantment(EquipmentSlot... slots) {
		super(Enchantment.Rarity.COMMON, ModEnchants.LEECHING_WAND, slots);
	}

	public int getMinCost(int i) {
		return 31;
	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {
		return this != ench && !List.of(UnusualendModEnchantments.BONDING.get()).contains(ench);
	}

	@Override
	public boolean isTradeable() {
		return false;
	}
}
