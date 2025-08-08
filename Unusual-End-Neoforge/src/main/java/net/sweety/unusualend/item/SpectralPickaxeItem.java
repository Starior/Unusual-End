
package net.sweety.unusualend.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;

public class SpectralPickaxeItem extends PickaxeItem {
	public SpectralPickaxeItem() {
		super(ModTiers.PEARLESCENT,  new Item.Properties().attributes(PickaxeItem.createAttributes(ModTiers.PEARLESCENT, 1.0F, -2.8F)));
	}
}
