
package net.sweety.unusualend.recipes.brewing;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
import net.sweety.unusualend.init.UnusualendModItems;

public class ChorusTeaBrewingBrewingRecipe implements IBrewingRecipe {

	@Override
	public boolean isInput(ItemStack input) {
		return Ingredient.of(new ItemStack(UnusualendModItems.CHORUS_JUICE.get())).test(input);
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return Ingredient.of(new ItemStack(UnusualendModItems.CHORUS_PETAL.get())).test(ingredient);
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		if (isInput(input) && isIngredient(ingredient)) {
			return new ItemStack(UnusualendModItems.CHORUS_TEA.get());
		}
		return ItemStack.EMPTY;
	}
}
