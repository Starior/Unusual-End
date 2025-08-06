
package net.sweety.unusualend.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.sweety.unusualend.init.UnusualEndBlocks;

import java.util.List;

public class EnderblobShieldItem extends ShieldItem {
	public EnderblobShieldItem() {
		super(new Item.Properties().durability(224));
	}

	@Override
	public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
		return Ingredient.of(new ItemStack(UnusualEndBlocks.ENDERBLOB_BLOCK.get())).test(repairitem);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("lore.unusualend.when_block").withStyle(ChatFormatting.GRAY));
		list.add(Component.translatable("lore.unusualend.blob_shield_1").withStyle(ChatFormatting.BLUE));
		list.add(Component.translatable("lore.unusualend.blob_shield_2").withStyle(ChatFormatting.BLUE));
	}
}