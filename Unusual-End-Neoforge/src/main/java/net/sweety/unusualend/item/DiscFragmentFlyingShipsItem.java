
package net.sweety.unusualend.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import java.util.List;

public class DiscFragmentFlyingShipsItem extends Item {
	public DiscFragmentFlyingShipsItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.EAT;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.literal("\u00A77Music Disc - Flying Ships"));
	}
}
