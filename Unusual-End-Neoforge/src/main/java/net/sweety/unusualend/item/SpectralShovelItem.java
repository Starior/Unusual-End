
package net.sweety.unusualend.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;

public class SpectralShovelItem extends ShovelItem {
	public SpectralShovelItem() {
		super(ModTiers.PEARLESCENT,  new Item.Properties().attributes(ShovelItem.createAttributes(ModTiers.PEARLESCENT, 1.5F, -3.0F)));
	}
}
