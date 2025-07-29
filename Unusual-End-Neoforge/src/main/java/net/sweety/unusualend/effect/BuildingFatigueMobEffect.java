
package net.sweety.unusualend.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.sweety.unusualend.procedures.BuildingFatigueOnEffectActiveTickProcedure;

public class BuildingFatigueMobEffect extends MobEffect {
	public BuildingFatigueMobEffect() {
		super(MobEffectCategory.HARMFUL, -13487566);
		this.addAttributeModifier(NeoForgeMod.BLOCK_REACH.value(), "5671a87c-50fc-4f29-b6e4-e39364233137", -100F, AttributeModifier.Operation.ADDITION);
	}

	@Override
	public String getDescriptionId() {
		return "effect.unusualend.disruption";
	}

	//@Override
	//public void applyEffectTick(LivingEntity entity, int amplifier) {
	//	BuildingFatigueOnEffectActiveTickProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
	//}
	@Override
	public void onEffectStarted(LivingEntity entity, int pAmplifier) {
		BuildingFatigueOnEffectActiveTickProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
		return true;
	}
}
