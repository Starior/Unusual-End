package net.sweety.unusualend.init;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IJeiBrewingRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.sweety.unusualend.UnusualEnd;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class UnusualendModBrewingRecipes implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return UnusualEnd.makeUEID("brewing_recipes");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		IVanillaRecipeFactory factory = registration.getVanillaRecipeFactory();
		List<IJeiBrewingRecipe> brewingRecipes = new ArrayList<>();
		ItemStack potion = new ItemStack(Items.POTION);
		ItemStack potion2 = new ItemStack(Items.POTION);
		List<ItemStack> ingredientStack = new ArrayList<>();
		List<ItemStack> inputStack = new ArrayList<>();
		ingredientStack.add(new ItemStack(UnusualEndItems.END_BLOB.get()));
		PotionUtils.setPotion(potion, Potions.AWKWARD);
		PotionUtils.setPotion(potion2, UnusualEndPotions.END_INFECTION.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.LURKER_SLUDGE.get()));
		PotionUtils.setPotion(potion, Potions.AWKWARD);
		PotionUtils.setPotion(potion2, UnusualEndPotions.LEVITATION.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.GOLEM_ORB.get()));
		PotionUtils.setPotion(potion, Potions.AWKWARD);
		PotionUtils.setPotion(potion2, UnusualEndPotions.BUILDING_POTION.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.SHINY_CRYSTAL.get()));
		PotionUtils.setPotion(potion, Potions.LONG_REGENERATION);
		PotionUtils.setPotion(potion2, UnusualEndPotions.REGENERATION.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.SHINY_CRYSTAL.get()));
		PotionUtils.setPotion(potion, Potions.STRONG_REGENERATION);
		PotionUtils.setPotion(potion2, UnusualEndPotions.REGENERATION_II.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.WARPED_SPORES.get()));
		PotionUtils.setPotion(potion, Potions.WATER);
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), new ItemStack(UnusualEndItems.WARPED_POTION.get())));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.BOLOK_SCALE.get()));
		PotionUtils.setPotion(potion, Potions.AWKWARD);
		PotionUtils.setPotion(potion2, UnusualEndPotions.HEAVINESS_POTION.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.CITRINE_CHUNK.get()));
		PotionUtils.setPotion(potion, Potions.AWKWARD);
		PotionUtils.setPotion(potion2, UnusualEndPotions.SERENITY_POTION.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.PRISMALITE_GEM.get()));
		PotionUtils.setPotion(potion, Potions.AWKWARD);
		PotionUtils.setPotion(potion2, UnusualEndPotions.SWIFT_STRIKES_POTION.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.BOLOK_SCALE.get()));
		inputStack.add(new ItemStack(UnusualEndItems.WARPED_POTION.get()));
		PotionUtils.setPotion(potion, UnusualEndPotions.RESISTANCE.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), List.copyOf(inputStack), potion.copy()));
		ingredientStack.clear();
		inputStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.SHINY_CRYSTAL.get()));
		inputStack.add(new ItemStack(UnusualEndItems.WARPED_POTION.get()));
		PotionUtils.setPotion(potion, UnusualEndPotions.HEALTH_BOOST.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), List.copyOf(inputStack), potion.copy()));
		ingredientStack.clear();
		inputStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.ENDERBULB_LENS.get()));
		PotionUtils.setPotion(potion, Potions.AWKWARD);
		PotionUtils.setPotion(potion2, Potions.LONG_NIGHT_VISION);
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.CHORUS_PETAL.get()));
		inputStack.add(new ItemStack(UnusualEndItems.CHORUS_JUICE.get()));
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), List.copyOf(inputStack), new ItemStack(UnusualEndItems.CHORUS_TEA.get())));
		inputStack.clear();
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.COOKED_BLUK.get()));
		inputStack.add(new ItemStack(UnusualEndItems.WARPED_POTION.get()));
		PotionUtils.setPotion(potion, UnusualEndPotions.STRENGTH.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), List.copyOf(inputStack), potion.copy()));
		ingredientStack.clear();
		inputStack.clear();
		ingredientStack.add(new ItemStack(UnusualEndItems.GLOOPILON_SLICE.get()));
		PotionUtils.setPotion(potion, Potions.AWKWARD);
		PotionUtils.setPotion(potion2, UnusualEndPotions.HASTE.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(Items.REDSTONE));
		PotionUtils.setPotion(potion, UnusualEndPotions.HASTE.get());
		PotionUtils.setPotion(potion2, UnusualEndPotions.ADVANCED_HASTE.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		registration.addRecipes(RecipeTypes.BREWING, brewingRecipes);
	}
}
