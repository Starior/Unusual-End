
package net.sweety.unusualend.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.sweety.unusualend.init.UnusualEndMiscRegister;

public class EndblobBallItem extends Item {
	public EndblobBallItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)
				.food((new FoodProperties.Builder()).effect(new MobEffectInstance(UnusualEndMiscRegister.ENDER_INFECTION.get(), 300, 0), 0.3F).nutrition(2).saturationMod(0.2f).alwaysEat().build()));
	}
}
