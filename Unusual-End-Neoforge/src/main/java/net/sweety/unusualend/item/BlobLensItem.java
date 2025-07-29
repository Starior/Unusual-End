
package net.sweety.unusualend.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class BlobLensItem extends Item {
	public BlobLensItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
