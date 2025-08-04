
package net.sweety.unusualend.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;

public record BoloksWingsEnchantment() implements EnchantmentEntityEffect {
    public static final MapCodec<BoloksWingsEnchantment> CODEC = MapCodec.unit(BoloksWingsEnchantment::new);

    @Override
    public void apply(ServerLevel level, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (enchantedItemInUse.owner() instanceof Player player && Math.abs(player.getDeltaMovement().x()) + Math.abs(player.getDeltaMovement().y()) + Math.abs(player.getDeltaMovement().z()) > 1.8) {
            final Vec3 center = new Vec3(player.getX(), player.getY(), player.getZ());
            List<Entity> list = level.getEntitiesOfClass(Entity.class, new AABB(center, center).inflate(2.5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(center))).toList();
            for (Entity iterator : list) {
                if (player != iterator) {
                    iterator.invulnerableTime = 0;
                    iterator.hurt(new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.FLY_INTO_WALL)),
                            (float) ((Math.abs(player.getDeltaMovement().x()) + Math.abs(player.getDeltaMovement().y()) + Math.abs(player.getDeltaMovement().z()) + 1) * i));
                }
            }
            level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, player.position(), Vec2.ZERO, level, 4, "", Component.literal(""), level.getServer(), null).withSuppressedOutput(),
                    "particle dust_color_transition 0.067 0.608 0.522 1 0.737 0.902 0.922 ~ ~0.1 ~ 0.4 0.2 0.4 0 1 normal");
            level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, player.position(), Vec2.ZERO, level, 4, "", Component.literal(""), level.getServer(), null).withSuppressedOutput(),
                    "particle dust_color_transition 0.094 0.851 0.729 1.2 0.980 0.988 1.000 ~ ~0.1 ~ 0.4 0.2 0.4 0 1 normal");
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}