
package net.sweety.unusualend.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.sweety.unusualend.entity.WanderingPearlProjectileEntity;
import net.sweety.unusualend.procedures.WanderingPearlRangedItemUsedProcedure;

public class WanderingPearlItem extends Item {
    public WanderingPearlItem() {
        super(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON));
    }

    @Override
    public int getUseDuration(ItemStack itemstack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = InteractionResultHolder.success(entity.getItemInHand(hand));
        entity.startUsingItem(hand);
        return ar;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack itemstack, int count) {
        if (!level.isClientSide() && entity instanceof ServerPlayer player) {
            ItemStack stack = ProjectileWeaponItem.getHeldProjectile(entity, e -> e.getItem() == WanderingPearlProjectileEntity.PROJECTILE_ITEM.getItem());
            if (stack == ItemStack.EMPTY) {
                for (int i = 0; i < player.getInventory().items.size(); i++) {
                    ItemStack teststack = player.getInventory().items.get(i);
                    if (teststack != null && teststack.getItem() == WanderingPearlProjectileEntity.PROJECTILE_ITEM.getItem()) {
                        stack = teststack;
                        break;
                    }
                }
            }
            if (player.getAbilities().instabuild || stack != ItemStack.EMPTY) {
                WanderingPearlProjectileEntity projectile = WanderingPearlProjectileEntity.shoot(level, entity, level.getRandom());
                itemstack.hurtAndBreak(1, entity, entity.getEquipmentSlotForItem(stack));
                if (player.getAbilities().instabuild) {
                    projectile.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                } else {
                    if (stack.isDamageableItem()) {
                        stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
                    } else {
                        stack.shrink(1);
                        if (stack.isEmpty())
                            player.getInventory().removeItem(stack);
                    }
                }
                WanderingPearlRangedItemUsedProcedure.execute(entity, itemstack);
            }
            entity.releaseUsingItem();
        }
    }
}
