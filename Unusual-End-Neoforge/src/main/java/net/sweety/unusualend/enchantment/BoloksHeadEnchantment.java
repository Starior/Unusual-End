
package net.sweety.unusualend.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record BoloksHeadEnchantment() implements EnchantmentEntityEffect {
    public static final MapCodec<BoloksHeadEnchantment> CODEC = MapCodec.unit(BoloksHeadEnchantment::new);

    @Override
    public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (enchantedItemInUse.owner() instanceof Player player && player.isFallFlying()) {
            player.fallDistance = player.fallDistance / (i + 2);
            if (serverLevel.getBlockState(BlockPos.containing(
                    player.getX() + getXVector(player.getYRot()),
                    player.getY() + getYVector(player.getXRot()),
                    player.getZ() + getZVector(player.getYRot())
            )).isSolid() && !serverLevel.isClientSide()) {
                WindCharge windcharge = new WindCharge(player, serverLevel, player.position().x(), player.getEyePosition().y(), player.position().z());
                windcharge.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                serverLevel.addFreshEntity(windcharge);
            }
        }
    }

    private double getXVector(double yaw) {
        return 2 * Math.cos((yaw + 90) * (Math.PI / 180));
    }

    private double getYVector(double pitch) {
        return pitch * (-0.025) * 2;
    }

    private double getZVector(double yaw) {
        return 2 * Math.sin((yaw + 90) * (Math.PI / 180));
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}