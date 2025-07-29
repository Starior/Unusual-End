
package net.sweety.unusualend.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class CrystallizationMobEffect extends MobEffect {
	public CrystallizationMobEffect() {
		super(MobEffectCategory.HARMFUL, -39169);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
		return true;
	}
}