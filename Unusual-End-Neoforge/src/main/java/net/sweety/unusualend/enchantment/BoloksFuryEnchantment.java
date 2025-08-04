
package net.sweety.unusualend.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.sweety.unusualend.init.UnusualEndMiscRegister;

public record BoloksFuryEnchantment() implements EnchantmentEntityEffect {
    public static final MapCodec<BoloksFuryEnchantment> CODEC = MapCodec.unit(BoloksFuryEnchantment::new);

    @Override
    public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (i > 0 && entity instanceof LivingEntity living) {
            if (living.getHealth() >= living.getMaxHealth() && enchantedItemInUse.owner() != null) {
                living.hurt(enchantedItemInUse.owner().damageSources().dragonBreath(), 2 * i);
                if (entity.level() instanceof ServerLevel level)
                    level.sendParticles(UnusualEndMiscRegister.BOLOK_PARTICLE.get(), entity.getX(), entity.getY() + 0.7f * entity.getBbHeight(), entity.getZ(), 30, 0.3, 0.5, 0.3, 0.5);
                if (!entity.level().isClientSide()) {
                    entity.level().playSound(null, entity.getOnPos(), SoundEvents.PLAYER_HURT_DROWN, SoundSource.HOSTILE, (float) 0.3,
                            (float) Mth.nextDouble(RandomSource.create(), 0.6, 1.2));
                } else {
                    entity.level().playLocalSound(entity, SoundEvents.PLAYER_HURT_DROWN, SoundSource.HOSTILE, (float) 0.3, (float) Mth.nextDouble(RandomSource.create(), 0.6, 1.2));
                }
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}