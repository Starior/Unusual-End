package net.sweety.unusualend.procedures;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.init.UnusualEndMiscRegister;

public class SpunklerPlayerCollidesWithThisEntityProcedure {
    public static void execute(LevelAccessor world, Entity entity, Player player) {
        if (entity == null || player == null)
            return;
        double dis = 0;
        entity.hurt(entity.damageSources().generic(), 0.01f);
        player.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, UnusualEnd.makeUEID("spunkler_damages")))), 4);
        dis = Math.sqrt(Math.pow(player.getX() - entity.getX(), 2) + Math.pow(player.getY() - entity.getY(), 2) + Math.pow(player.getZ() - entity.getZ(), 2));
        if (!player.level().isClientSide()) {
            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10, 0));
            player.addEffect(new MobEffectInstance(UnusualEndMiscRegister.HEAVINESS, 10, 0));
        }
        player.setDeltaMovement(new Vec3((((player.getX() - entity.getX()) / dis) * 1.6), (((player.getY() - entity.getY()) / dis) * 1.6), (((player.getZ() - entity.getZ()) / dis) * 1.6)));
    }
}
