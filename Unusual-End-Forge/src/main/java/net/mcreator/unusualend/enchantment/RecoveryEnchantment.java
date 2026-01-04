
package net.mcreator.unusualend.enchantment;

import net.mcreator.unusualend.ModEnchants;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class RecoveryEnchantment extends Enchantment {
	public RecoveryEnchantment(EquipmentSlot... slots) {
		super(Enchantment.Rarity.UNCOMMON, ModEnchants.ARCANE, slots);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}
}
