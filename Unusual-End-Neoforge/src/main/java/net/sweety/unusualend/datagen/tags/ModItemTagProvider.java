package net.sweety.unusualend.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.init.UnusualEndItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, UnusualEnd.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(UnusualEndTags.Items.SPEAR_ENCHANTABLE)
                .add(UnusualEndItems.WARPED_SPEAR.get());
        tag(UnusualEndTags.Items.ARCANE_ENCHANTABLE)
                .add(UnusualEndItems.CRYSTAL_CATALYST.get())
                .add(UnusualEndItems.LEECHING_WAND.get())
                .add(UnusualEndItems.ANCIENT_SWORD.get())
                .add(UnusualEndItems.PEARLESCENT_RING.get());
        BuiltInRegistries.ITEM.stream().filter(item ->
                item instanceof AxeItem || item instanceof SwordItem
                        || item instanceof TridentItem).forEach(item ->
                tag(UnusualEndTags.Items.BOLOK_ENCHANTABLE).add(item)
        );
        tag(UnusualEndTags.Items.BOLOK_ENCHANTABLE).add(UnusualEndItems.WARPED_ANCHOR.get());
        tag(UnusualEndTags.Items.LEECHING_WAND_ENCHANTABLE)
                .add(UnusualEndItems.LEECHING_WAND.get());
        tag(UnusualEndTags.Items.ELYTRA_ENCHANTABLE)
                .add(Items.ELYTRA);
    }
}