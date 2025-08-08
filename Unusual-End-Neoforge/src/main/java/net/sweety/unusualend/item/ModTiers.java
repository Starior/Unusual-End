package net.sweety.unusualend.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.sweety.unusualend.init.UnusualEndBlocks;
import net.sweety.unusualend.init.UnusualEndItems;

public class ModTiers {
    public static final Tier SPEAR = new Tier() {
        public int getUses() {
            return 1024;
        }

        public float getSpeed() {
            return 8f;
        }

        public float getAttackDamageBonus() {
            return 1f;
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
        }

        public int getEnchantmentValue() {
            return 16;
        }

        public Ingredient getRepairIngredient() {
            return Ingredient.of(UnusualEndItems.LURKER_SPINE.get(), UnusualEndItems.BOLOK_SCALE.get());
        }
    };
    public static final Tier DAGGER = new Tier() {
        public int getUses() {
            return 64;
        }

        public float getSpeed() {
            return 6f;
        }

        public float getAttackDamageBonus() {
            return 1f;
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_IRON_TOOL;
        }

        public int getEnchantmentValue() {
            return 5;
        }

        public Ingredient getRepairIngredient() {
            return Ingredient.of(UnusualEndItems.END_BLOB.get(), UnusualEndBlocks.ENDERBLOB_BLOCK.get());
        }
    };
    public static final Tier ANCHOR = new Tier() {
        public int getUses() {
            return 1561;
        }

        public float getSpeed() {
            return 7.5f;
        }

        public float getAttackDamageBonus() {
            return 1f;
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_IRON_TOOL;
        }

        public int getEnchantmentValue() {
            return 12;
        }

        public Ingredient getRepairIngredient() {
            return Ingredient.of(Blocks.IRON_BLOCK);
        }
    };
    public static final Tier PEARLESCENT = new Tier() {
        public int getUses() {
            return 1024;
        }

        public float getSpeed() {
            return 8f;
        }

        public float getAttackDamageBonus() {
            return 3f;
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
        }

        public int getEnchantmentValue() {
            return 16;
        }

        public Ingredient getRepairIngredient() {
            return Ingredient.of(UnusualEndItems.PEARLESCENT_INGOT.get());
        }
    };
}