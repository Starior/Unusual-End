package net.sweety.unusualend.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.sweety.unusualend.init.UnusualEndEnchantments;

public class CrystalCatalystItemInInventoryTickProcedure {
    public static void execute(ItemStack stack, Level level) {
        if (NBTProcessor.getNBTDouble(stack, "cataCooldown") < 400) {
            NBTProcessor.setNBTDouble(stack, "cataCooldown", (NBTProcessor.getNBTDouble(stack, "cataCooldown") + 0.3));
            if (EnchantmentHelper.getTagEnchantmentLevel(level.holderOrThrow(UnusualEndEnchantments.ARCANE_RECOVERY), stack) != 0) {
                if (NBTProcessor.getNBTDouble(stack, "cataCooldown") + 0.1 * stack.getEnchantmentLevel(level.holderOrThrow(UnusualEndEnchantments.ARCANE_RECOVERY)) >= 400) {
                    NBTProcessor.setNBTDouble(stack, "cataCooldown", 400);
                } else {
                    NBTProcessor.setNBTDouble(stack, "cataCooldown", NBTProcessor.getNBTDouble(stack, "cataCooldown") + 0.1 * stack.getEnchantmentLevel(level.holderOrThrow(UnusualEndEnchantments.ARCANE_RECOVERY)));
                }
            }
        } else {
            NBTProcessor.setNBTDouble(stack, "cataCooldown", 400);
        }
    }
}
