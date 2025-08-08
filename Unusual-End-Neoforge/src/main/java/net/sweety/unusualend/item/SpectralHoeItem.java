
package net.sweety.unusualend.item;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;

public class SpectralHoeItem extends HoeItem {
	public SpectralHoeItem() {
		super(ModTiers.PEARLESCENT,  new Item.Properties().fireResistant().attributes(HoeItem.createAttributes(ModTiers.PEARLESCENT, -3.0F, 0.0F)));
	}
}
