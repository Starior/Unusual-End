
package net.sweety.unusualend.item;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.sweety.unusualend.init.UnusualEndEnchantments;
import net.sweety.unusualend.init.UnusualEndItems;
import net.sweety.unusualend.init.UnusualEndMiscRegister;

import java.util.List;

public class WarpedSpearItem extends SwordItem {
    public WarpedSpearItem() {
        super(new Tier() {
            public int getUses() {
                return 1024;
            }

            public float getSpeed() {
                return 8f;
            }

            public float getAttackDamageBonus() {
                return 5f;
            }

            @Override
            public TagKey<Block> getIncorrectBlocksForDrops() {
                return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
            }

            public int getEnchantmentValue() {
                return 16;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of(new ItemStack(UnusualEndItems.LURKER_SPINE.get()), new ItemStack(UnusualEndItems.BOLOK_SCALE.get()));
            }
        }, new Item.Properties());
    }

    @Override
    public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, context, list, flag);
        list.add(Component.literal("\u00A77When Riding an Entity:"));
        list.add(Component.literal("\u00A79Swift Strikes (0:01)"));
        list.add(Component.literal("\u00A79Strength (0:01)"));
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if (selected) {
            if (EnchantmentHelper.getTagEnchantmentLevel(entity.level().registryAccess().holderOrThrow(UnusualEndEnchantments.JOUST), (entity instanceof LivingEntity living ? living.getMainHandItem() : ItemStack.EMPTY)) != 0) {
                if (entity.isPassenger()) {
                    if (entity instanceof LivingEntity living && !living.level().isClientSide()) {
                        living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 25, living.getMainHandItem().getEnchantmentLevel(entity.level().registryAccess().holderOrThrow(UnusualEndEnchantments.JOUST)) - 1,
                                false, false));
                        living.addEffect(new MobEffectInstance(UnusualEndMiscRegister.SWIFT_STRIKES, 25,
                                living.getMainHandItem().getEnchantmentLevel(entity.level().registryAccess().holderOrThrow(UnusualEndEnchantments.JOUST)) - 1, false, false));
                    }
                }
            }
        }
    }
}