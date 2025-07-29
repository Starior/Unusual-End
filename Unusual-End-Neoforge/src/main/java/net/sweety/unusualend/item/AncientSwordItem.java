
package net.sweety.unusualend.item;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.sweety.unusualend.init.UnusualendModItems;

import java.util.Comparator;
import java.util.List;

public class AncientSwordItem extends SwordItem {
	public AncientSwordItem() {
		super(new Tier() {
			public int getUses() {
				return 1024;
			}

			public float getSpeed() {
				return 8f;
			}

			public float getAttackDamageBonus() {
				return 4f;
			}

			public int getLevel() {
				return 3;
			}

			public int getEnchantmentValue() {
				return 16;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(UnusualendModItems.ANCIENT_SHARD.get()));
			}
		}, 3, -2.1f, new Item.Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("\u00A77On Hit:"));
		list.add(Component.literal("\u00A79Attract target"));
		list.add(Component.literal("\u00A77On Hit while Sneaking:"));
		list.add(Component.literal("\u00A79Create a Void Slash"));
	}

	@Override
	public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
		double xRadius, loop, zRadius, particleAmount;
		Level level = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		if (entity.isShiftKeyDown() && entity instanceof Player player) {
			if (!player.getCooldowns().isOnCooldown(itemstack.getItem())) {
				if (!player.isCreative()) {
					if (itemstack.hurt(1, RandomSource.create(), null)) {
						itemstack.shrink(1);
						itemstack.setDamageValue(0);
					}
				}
				player.getCooldowns().addCooldown(itemstack.getItem(), 200);
				loop = 0;
				particleAmount = 15;
				xRadius = 3;
				zRadius = 3;
				while (loop < particleAmount) {
					if (level instanceof ServerLevel serverLevel){
						double xV = Math.cos(((Math.PI * 2) / particleAmount) * loop) * xRadius;
						double zV = Math.sin(((Math.PI * 2) / particleAmount) * loop) * zRadius;
						serverLevel.sendParticles(ParticleTypes.SQUID_INK, (x + 0.5 + xV), (y + 1), (z + 0.5 + zV), 3, 0.3, 0.2, 0.3, 0.1);
						serverLevel.sendParticles(ParticleTypes.END_ROD, (x + 0.5 + xV), (y + 1), (z + 0.5 + zV), 3, 0.3, 0.2, 0.3, 0.1);
						serverLevel.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL, new Vec3((x + 0.5 + xV), (y + 1), (z + 0.5 + zV)), Vec2.ZERO,
										serverLevel, 4, "", Component.literal(""), serverLevel.getServer(), null).withSuppressedOutput(),
								"particle minecraft:block unusualend:void_particles_block ~ ~ ~ 0 0.05 0 0 5");
					}
					loop = loop + 1;
				}
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = level.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity iterator : _entfound) {
					if (iterator != entity && !(iterator instanceof TamableAnimal tamIsTamedBy && tamIsTamedBy.isOwnedBy(player))) {
						iterator.invulnerableTime = 0;
						iterator.hurt(new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC), entity), 12);
					}
				}
					if (!level.isClientSide())
						level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.RESPAWN_ANCHOR_SET_SPAWN, SoundSource.PLAYERS, 1, 2);
					else
						level.playLocalSound(x, y, z, SoundEvents.RESPAWN_ANCHOR_SET_SPAWN, SoundSource.PLAYERS, 1, 2, false);
			}
		}
		return super.onEntitySwing(itemstack, entity);
	}
}
