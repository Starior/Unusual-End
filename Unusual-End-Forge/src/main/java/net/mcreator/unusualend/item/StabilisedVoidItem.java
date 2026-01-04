
package net.mcreator.unusualend.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class StabilisedVoidItem extends Item {
	public StabilisedVoidItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
	}
}
