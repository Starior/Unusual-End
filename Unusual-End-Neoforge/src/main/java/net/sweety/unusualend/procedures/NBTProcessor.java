package net.sweety.unusualend.procedures;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

public class NBTProcessor {
    public static void setNBTBoolean(ItemStack stack, String key, boolean value) {
        if (stack.has(DataComponents.CUSTOM_DATA))
            stack.get(DataComponents.CUSTOM_DATA).update(tag -> tag.putBoolean(key, value));
    }

    public static boolean getNBTBoolean(ItemStack stack, String key) {
        if (stack.has(DataComponents.CUSTOM_DATA))
            return stack.get(DataComponents.CUSTOM_DATA).copyTag().getBoolean(key);
        return false;
    }

    public static void setNBTDouble(ItemStack stack, String key, double value) {
        if (stack.has(DataComponents.CUSTOM_DATA))
            stack.get(DataComponents.CUSTOM_DATA).update(tag -> tag.putDouble(key, value));
    }
    public static double getNBTDouble(ItemStack stack, String key) {
        if (stack.has(DataComponents.CUSTOM_DATA))
            return stack.get(DataComponents.CUSTOM_DATA).copyTag().getDouble(key);
        return 0;
    }

    public static void setNBTString(ItemStack stack, String key, String value) {
        if (stack.has(DataComponents.CUSTOM_DATA))
            stack.get(DataComponents.CUSTOM_DATA).update(tag -> tag.putString(key, value));
    }
    public static String getNBTString(ItemStack stack, String key) {
        if (stack.has(DataComponents.CUSTOM_DATA))
            return stack.get(DataComponents.CUSTOM_DATA).copyTag().getString(key);
        return "";
    }
}