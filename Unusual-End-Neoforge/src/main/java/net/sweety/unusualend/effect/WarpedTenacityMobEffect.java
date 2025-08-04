
package net.sweety.unusualend.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.sweety.unusualend.init.UnusualEndMiscRegister;
import net.sweety.unusualend.procedures.WarpedTenacityOnEffectActiveTickProcedure;

public class WarpedTenacityMobEffect extends MobEffect {
    public WarpedTenacityMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -14376058);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, UnusualEndMiscRegister.getModEffectLocation("2871a77c-53fc-4f39-b6e4-e39364233124"), -0.03F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.GRAVITY, UnusualEndMiscRegister.getModEffectLocation("84fce326-4b50-11ee-be56-0242ac123301"), 0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, UnusualEndMiscRegister.getModEffectLocation("2511a77c-53fc-4f39-b6e4-e39364233119"), 0.15F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.ARMOR, UnusualEndMiscRegister.getModEffectLocation("4411a77c-53fc-4f39-b6e4-e39364233154"), 3.0F, AttributeModifier.Operation.ADD_VALUE);
        this.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, UnusualEndMiscRegister.getModEffectLocation("2311a77c-53fc-4f39-b6e4-e39364233175"), 2.0F, AttributeModifier.Operation.ADD_VALUE);
    }

    @Override
    public String getDescriptionId() {
        return "effect.unusualend.warped_tenacity";
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        WarpedTenacityOnEffectActiveTickProcedure.execute(entity.level(), entity);
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
