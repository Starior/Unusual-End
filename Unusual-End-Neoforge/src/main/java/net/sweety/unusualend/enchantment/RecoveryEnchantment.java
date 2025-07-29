
package net.sweety.unusualend.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.sweety.unusualend.enchantment.categories.ArcaneEnchant;

public class RecoveryEnchantment extends Enchantment {
	public RecoveryEnchantment(EquipmentSlot... slots) {
		super(Enchantment.Rarity.UNCOMMON, ArcaneEnchant.ARCANE, slots);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}
}
