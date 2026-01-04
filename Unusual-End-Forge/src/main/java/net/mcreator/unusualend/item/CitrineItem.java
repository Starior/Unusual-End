
package net.mcreator.unusualend.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class CitrineItem extends Item {
	public CitrineItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
