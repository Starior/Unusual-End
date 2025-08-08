
package net.sweety.unusualend.item;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;

public class SpectralAxeItem extends AxeItem {
	public SpectralAxeItem() {
		super(ModTiers.PEARLESCENT, new Item.Properties().attributes(AxeItem.createAttributes(ModTiers.PEARLESCENT, 5.0F, -3.0F)));
	}
}
