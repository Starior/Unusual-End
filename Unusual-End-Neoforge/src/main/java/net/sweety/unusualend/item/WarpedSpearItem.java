
package net.sweety.unusualend.item;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.sweety.unusualend.init.UnusualendModItems;
import net.sweety.unusualend.procedures.WarpedSpearToolInHandTickProcedure;

import java.util.function.Consumer;

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
                return Ingredient.of(new ItemStack(UnusualendModItems.LURKER_SPINE.get()), new ItemStack(UnusualendModItems.BOLOK_SCALE.get()));
            }
        }, new Item.Properties());
    }

    //	@Override
    //	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
    //		super.appendHoverText(itemstack, world, list, flag);
    //		list.add(Component.literal("\u00A77When Riding an Entity:"));
    //		list.add(Component.literal("\u00A79Swift Strikes (0:01)"));
    //		list.add(Component.literal("\u00A79Strength (0:01)"));
    //	}
    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if (selected)
            WarpedSpearToolInHandTickProcedure.execute(entity);
    }
}
