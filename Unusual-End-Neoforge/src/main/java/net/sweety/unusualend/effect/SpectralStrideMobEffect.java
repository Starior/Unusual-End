
package net.sweety.unusualend.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.sweety.unusualend.init.UnusualEndMiscRegister;

public class SpectralStrideMobEffect extends MobEffect {
    public SpectralStrideMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -4121136);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, UnusualEndMiscRegister.getModEffectLocation("9ed92810-4b50-11ee-be56-0242ac120002"), 0.08F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.GRAVITY, UnusualEndMiscRegister.getModEffectLocation("82fce226-4b50-11ee-be56-0242ac120002"), -0.35F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!(entity.getPersistentData().getDouble("ScrapClothOverlay") > 0)) {
            if (entity.level() instanceof ServerLevel level)
                level.sendParticles(ParticleTypes.REVERSE_PORTAL, entity.getX(), entity.getY() + 0.1, entity.getZ(), 1, 0.2, 0.2, 0.2, 0);
        }
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}