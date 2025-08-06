
package net.sweety.unusualend.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;
import net.sweety.unusualend.init.UnusualEndMiscRegister;

public class HeavinessMobEffect extends MobEffect {
    public HeavinessMobEffect() {
        super(MobEffectCategory.HARMFUL, -16751002);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, UnusualEndMiscRegister.getModEffectLocation("1311a77c-53fc-4f39-b6e4-e39364233123"), -0.10F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.GRAVITY, UnusualEndMiscRegister.getModEffectLocation("86fe2657-05ca-4b13-9e17-2ceb225def27"), 0.25F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.hasEffect(MobEffects.LEVITATION)) {
            entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()),
                    (entity.getDeltaMovement().y() - (entity.getEffect(UnusualEndMiscRegister.HEAVINESS).getAmplifier()) * 0.03),
                    (entity.getDeltaMovement().z())));
        }
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
