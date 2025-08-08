
package net.sweety.unusualend.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

public class SpectralSwordItem extends SwordItem {
	public SpectralSwordItem() {
		super(ModTiers.PEARLESCENT,  new Item.Properties().attributes(SwordItem.createAttributes(ModTiers.PEARLESCENT, 3, -2.4F)));
	}
}
