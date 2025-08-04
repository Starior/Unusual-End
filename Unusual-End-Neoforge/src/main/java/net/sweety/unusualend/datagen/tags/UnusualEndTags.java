package net.sweety.unusualend.datagen.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.sweety.unusualend.UnusualEnd;

public class UnusualEndTags {
    public static class Items {
        public static TagKey<Item> LEECHING_WAND_ENCHANTABLE = tag("leeching_wand_enchantable");
        public static TagKey<Item> ARCANE_ENCHANTABLE = tag("arcane_enchantable");
        public static TagKey<Item> SPEAR_ENCHANTABLE = tag("spear_enchantable");
        public static TagKey<Item> BOLOK_ENCHANTABLE = tag("bolok_enchantable");
        public static TagKey<Item> ELYTRA_ENCHANTABLE = tag("elytra_enchantable");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, UnusualEnd.makeUEID(name));
        }
    }
}