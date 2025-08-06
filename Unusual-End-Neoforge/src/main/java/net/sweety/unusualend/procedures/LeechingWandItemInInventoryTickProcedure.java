package net.sweety.unusualend.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.LevelAccessor;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.init.UnusualEndEnchantments;
import net.sweety.unusualend.item.data.StringToDoubleData;

public class LeechingWandItemInInventoryTickProcedure {
    public static void execute(LevelAccessor level, Entity entity, ItemStack stack) {
        if (entity == null)
            return;
        if (StringToDoubleData.getData(stack, "rayCooldown") < 400) {
            StringToDoubleData.putData(stack, "rayCooldown", StringToDoubleData.getData(stack, "rayCooldown") + 1);
            if (EnchantmentHelper.getTagEnchantmentLevel(level.holderOrThrow(UnusualEndEnchantments.ARCANE_RECOVERY), stack) != 0) {
                if (StringToDoubleData.getData(stack, "rayCooldown") + 0.25 * stack.getEnchantmentLevel(level.holderOrThrow(UnusualEndEnchantments.ARCANE_RECOVERY)) >= 400) {
                    StringToDoubleData.putData(stack, "rayCooldown", 400);
                } else
                    StringToDoubleData.putData(stack, "rayCooldown", StringToDoubleData.getData(stack, "rayCooldown") + 0.25 * stack.getEnchantmentLevel(level.holderOrThrow(UnusualEndEnchantments.ARCANE_RECOVERY)));
            }
        } else
            StringToDoubleData.putData(stack, "rayCooldown", 400);
        if (NBTProcessor.getNBTBoolean(stack, "rayEnd") && entity instanceof Player player) {
            UnusualEnd.queueServerWork(20, () -> {
                if (NBTProcessor.getNBTBoolean(stack, "rayEnd")) {
                    if (!player.isCreative())
                        stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
                    NBTProcessor.setNBTBoolean(stack, "rayEnd", false);
                    player.getCooldowns().addCooldown(stack.getItem(), 10);
                    StringToDoubleData.putData(stack, "rayCooldown", 0);
                }
            });
        }
    }
}
