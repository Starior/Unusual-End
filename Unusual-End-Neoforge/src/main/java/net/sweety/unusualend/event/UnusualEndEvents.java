package net.sweety.unusualend.event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ComposterBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.sweety.unusualend.block.entity.*;
import net.sweety.unusualend.entity.*;
import net.sweety.unusualend.init.*;
import net.sweety.unusualend.jei_recipes.BolokTradingRecipe;
import net.sweety.unusualend.jei_recipes.InfuserRecipe;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class UnusualEndEvents {
    @SubscribeEvent
    public static void registerWanderingTrades(WandererTradesEvent event) {
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 5),

                new ItemStack(UnusualEndItems.ENDERFIREFLY_BUCKET.get()), 2, 5, 0.05f));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15),

                new ItemStack(UnusualEndItems.VOID_TOTEM.get()), 1, 5, 0.05f));
    }
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(Capabilities.ItemHandler.ITEM, (stack, content) -> stack.getData(UnusualEndMiscRegister.BOLOK_NOTE_INVENTORY), UnusualEndItems.BOLOK_RESEARCH_NOTES.get());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.ANCIENT_PODIUM.get(), (block, side) -> ((AncientPodiumBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.PURPUR_TANK.get(), (block, side) -> ((PurpurTankBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.GLOOPSLATE_PEDESTRAL.get(), (block, side) -> ((GloopslatePedestralBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.BUILDING_INHIBITOR.get(), (block, side) -> ((BuildingInhibitorBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.FADING_BLOCK.get(), (block, side) -> ((FadingBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.PEARLESCENT_INFUSER.get(), (block, side) -> ((PearlescentInfuserBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.WARPED_CHEST.get(), (block, side) -> ((WarpedChestBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.WARPING_WAYSTONE.get(), (block, side) -> ((WarpingWaystoneBlockEntity) block).getHandler());
    }
    @SubscribeEvent
    public static void register(FMLConstructModEvent event) {
        UnusualEndMiscRegister.SERIALIZERS.register("bolok_trading", () -> BolokTradingRecipe.Serializer.INSTANCE);
        UnusualEndMiscRegister.SERIALIZERS.register("infuser", () -> InfuserRecipe.Serializer.INSTANCE);
    }
    @SubscribeEvent
    public static void addComposterItems(FMLCommonSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.CHORUS_CANE_FLOWER.get().asItem(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.CHORUS_CANE.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.BLOOMING_CHORUS_CANE.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.CHORUS_FUNGUS.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.CHORUS_ROOTS.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.ENDSTONE_SPROUTS.get().asItem(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.PURPUR_GRASS.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.FLOWERING_PURPUR_GRASS.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.WARPED_MOSS.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndItems.CHORUS_PIE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndItems.CHORUS_PETAL.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.DRIPPING_GLOOPSTONE.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.GLOOPY_BUSH.get().asItem(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.SHINY_SPIREA.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndItems.WARPED_SPORES.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.WARPED_ALGAE.get().asItem(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.SMALL_WARPED_ALGAE.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.WARPED_SPROUTS.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.CRYSTAL_FLOWER.get().asItem(), 1f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndItems.WARPED_BERRIES.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.GLOOPY_TENDRILS.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.WARPED_BUSH.get().asItem(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.WARPED_SQUASH.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndItems.WARPED_SQUASH_WEDGE.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.SMALL_WARPED_SQUASH.get().asItem(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndItems.GLOOPILON_SLICE.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(UnusualEndBlocks.GLOOPILON_SEEDS.get().asItem(), 0.85f);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(UnusualendModEntities.ENDER_BLOB.get(), EnderblobEntity.createAttributes().build());
        event.put(UnusualendModEntities.UNDEAD_ENDERLING.get(), EnderlingEntity.createAttributes().build());
        event.put(UnusualendModEntities.ENDSTONE_TRAPPER.get(), EnderTrapperEntity.createAttributes().build());
        event.put(UnusualendModEntities.ENDER_FIREFLY.get(), EnderBugEntity.createAttributes().build());
        event.put(UnusualendModEntities.ENDSTONE_GOLEM.get(), EndstoneGolemEntity.createAttributes().build());
        event.put(UnusualendModEntities.DRAGLING.get(), DraglingEntity.createAttributes().build());
        event.put(UnusualendModEntities.BOLOK.get(), BolokEntity.createAttributes().build());
        event.put(UnusualendModEntities.ENDERBLOB_QUEEN.get(), EnderblobQueenEntity.createAttributes().build());
        event.put(UnusualendModEntities.BLOCK_UPDATER.get(), BlockUpdaterEntity.createAttributes().build());
        event.put(UnusualendModEntities.SPUNKLER.get(), SpunklerEntity.createAttributes().build());
        event.put(UnusualendModEntities.VOID_CRACK.get(), VoidCrackEntity.createAttributes().build());
        event.put(UnusualendModEntities.WARPED_BALLOON.get(), LargeBubbleEntity.createAttributes().build());
        event.put(UnusualendModEntities.GLUB.get(), WarpedJellyfishEntity.createAttributes().build());
        event.put(UnusualendModEntities.VOID_BOMB.get(), VoidBombEntity.createAttributes().build());
        event.put(UnusualendModEntities.ENDERBULB.get(), EnderbulbEntity.createAttributes().build());
        event.put(UnusualendModEntities.SMALL_ENDERBULB.get(), SmallEnderbulbEntity.createAttributes().build());
        event.put(UnusualendModEntities.BLUK.get(), BlukEntity.createAttributes().build());
        event.put(UnusualendModEntities.SUMMONED_DRAGLING.get(), SummonedDraglingEntity.createAttributes().build());
    }
}