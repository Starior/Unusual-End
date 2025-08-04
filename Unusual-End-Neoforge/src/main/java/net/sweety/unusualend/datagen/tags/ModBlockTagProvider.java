package net.sweety.unusualend.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.init.UnusualEndBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, UnusualEnd.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(UnusualEndBlocks.ANCIENT_ENCASED_END_STONE.get())
                .add(UnusualEndBlocks.ANCIENT_ENCASED_PURPUR.get())
                .add(UnusualEndBlocks.BUDDING_CITRINE.get())
                .add(UnusualEndBlocks.BUILDING_INHIBITOR.get())
                .add(UnusualEndBlocks.CENTRAL_PEDESTAL.get())
                .add(UnusualEndBlocks.BOLOK_SCALE_BLOCK.get())
                .add(UnusualEndBlocks.CHISELED_ENDSTONE_BUILDER.get())
                .add(UnusualEndBlocks.CHISELED_ENDSTONE_DRAGON.get())
                .add(UnusualEndBlocks.CHISELED_ENDSTONE_FRIENDSHIP.get())
                .add(UnusualEndBlocks.CHISELED_ENDSTONE_SCARED.get())
                .add(UnusualEndBlocks.CHISELED_ENDSTONE_US.get())
                .add(UnusualEndBlocks.CHISELED_ENDSTONE_WORK.get())
                .add(UnusualEndBlocks.CHISELED_POLISHED_PURPUR_TILES.get())
                .add(UnusualEndBlocks.CITRINE_BLOCK.get())
                .add(UnusualEndBlocks.ENDSTONE_TILES.get())
                .add(UnusualEndBlocks.ENDSTONE_PILLAR.get())
                .add(UnusualEndBlocks.CONDENSED_CITRINE_BLOCK.get())
                .add(UnusualEndBlocks.CITRINE_TOTEM.get())
                .add(UnusualEndBlocks.CITRINE_PILLAR.get())
                .add(UnusualEndBlocks.CITRINE_CLUSTER.get())
                .add(UnusualEndBlocks.CITRINE_BUD.get())
                .add(UnusualEndBlocks.CHISELED_POLISHED_WARPED_STONE.get())
                .add(UnusualEndBlocks.POLISHED_PURPUR_BRICKS.get())
                .add(UnusualEndBlocks.POLISHED_PURPUR.get())
                .add(UnusualEndBlocks.POLISHED_PURPUR_SLAB.get())
                .add(UnusualEndBlocks.POLISHED_PURPUR_STAIRS.get())
                .add(UnusualEndBlocks.POLISHED_GNEISS_STAIRS.get())
                .add(UnusualEndBlocks.POLISHED_GNEISS_SLAB.get())
                .add(UnusualEndBlocks.POLISHED_GNEISS.get())
                .add(UnusualEndBlocks.POLISHED_GLOOPSLATE_STAIRS.get())
                .add(UnusualEndBlocks.POLISHED_GLOOPSLATE_SLAB.get())
                .add(UnusualEndBlocks.POLISHED_GLOOPSLATE.get())
                .add(UnusualEndBlocks.POLISHED_CITRINE_TILES.get())
                .add(UnusualEndBlocks.SHINY_CRYSTAL_TILES.get())
                .add(UnusualEndBlocks.SHINY_CRYSTAL_TILES_SLAB.get())
                .add(UnusualEndBlocks.SHINY_CRYSTAL_TILE_STAIRS.get())
                .add(UnusualEndBlocks.SHINY_CRYSTAL_BRICKS.get())
                .add(UnusualEndBlocks.SHINY_CRYSTAL_BLOCK.get())
                .add(UnusualEndBlocks.ROOTED_RAW_PURPUR.get())
                .add(UnusualEndBlocks.RAW_PURPUR_BLOCK.get())
                .add(UnusualEndBlocks.RAW_PURPUR_BLOCK_SLAB.get())
                .add(UnusualEndBlocks.RAW_PURPUR_BLOCK_STAIRS.get())
                .add(UnusualEndBlocks.RAW_PURPUR_WALL.get())
                .add(UnusualEndBlocks.TELEPORTATION_ANCHOR.get())
                ;
    }
}