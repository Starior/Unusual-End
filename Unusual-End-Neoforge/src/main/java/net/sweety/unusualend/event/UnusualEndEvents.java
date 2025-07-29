package net.sweety.unusualend.event;

import net.minecraft.world.level.block.ComposterBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.brewing.BrewingRecipeRegistry;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.sweety.unusualend.block.entity.*;
import net.sweety.unusualend.configuration.UEConfig;
import net.sweety.unusualend.entity.*;
import net.sweety.unusualend.init.*;
import net.sweety.unusualend.jei_recipes.BolokTradingRecipe;
import net.sweety.unusualend.jei_recipes.InfuserRecipe;
import net.sweety.unusualend.recipes.brewing.*;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UnusualEndEvents {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(Capabilities.ItemHandler.ITEM, (stack, content) -> stack.getData(UnusualEndMiscRegister.BOLOK_NOTE_INVENTORY), UnusualendModItems.BOLOK_RESEARCH_NOTES.get());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.ANCIENT_PODIUM.get(), (block, side) -> ((AncientPodiumBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.PURPUR_TANK.get(), (block, side) -> ((PurpurTankBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.GLOOPSLATE_PEDESTRAL.get(), (block, side) -> ((GloopslatePedestralBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.BUILDING_INHIBITOR.get(), (block, side) -> ((BuildingInhibitorBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.FADING_BLOCK.get(), (block, side) -> ((FadingBlockBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.PEARLESCENT_INFUSER.get(), (block, side) -> ((PearlescentInfuserBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.WARPED_CHEST.get(), (block, side) -> ((WarpedChestBlockEntity) block).getHandler());
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UnusualendModBlockEntities.WARPING_WAYSTONE.get(), (block, side) -> ((WarpingWaystoneBlockEntity) block).getHandler());
    }
    @SubscribeEvent
    public static void register(FMLConstructModEvent event) {
        event.enqueueWork(() -> ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, UEConfig.SPEC, "unusualend-common.toml"));
        UnusualEndMiscRegister.SERIALIZERS.register("bolok_trading", () -> BolokTradingRecipe.Serializer.INSTANCE);
        UnusualEndMiscRegister.SERIALIZERS.register("infuser", () -> InfuserRecipe.Serializer.INSTANCE);
    }
    @SubscribeEvent
    public static void addComposterItems(FMLCommonSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.CHORUS_CANE_FLOWER.get().asItem(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.CHORUS_CANE.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.BLOOMING_CHORUS_CANE.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.CHORUS_FUNGUS.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.CHORUS_ROOTS.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.ENDSTONE_SPROUTS.get().asItem(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.PURPUR_GRASS.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.FLOWERING_PURPUR_GRASS.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.WARPED_MOSS.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModItems.CHORUS_PIE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModItems.CHORUS_PETAL.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.DRIPPING_GLOOPSTONE.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.GLOOPY_BUSH.get().asItem(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.SHINY_SPIREA.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModItems.WARPED_SPORES.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.WARPED_ALGAE.get().asItem(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.SMALL_WARPED_ALGAE.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.WARPED_SPROUTS.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.CRYSTAL_FLOWER.get().asItem(), 1f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModItems.WARPED_BERRIES.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.GLOOPY_TENDRILS.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.WARPED_BUSH.get().asItem(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.WARPED_SQUASH.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModItems.WARPED_SQUASH_WEDGE.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.SMALL_WARPED_SQUASH.get().asItem(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModItems.GLOOPILON_SLICE.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(UnusualendModBlocks.GLOOPILON_SEEDS.get().asItem(), 0.85f);

        event.enqueueWork(() -> {
            EnderblobEntity.init();
            EnderlingEntity.init();
            EnderTrapperEntity.init();
            EnderBugEntity.init();
            EndstoneGolemEntity.init();
            DraglingEntity.init();
            BolokEntity.init();
            EnderblobQueenEntity.init();
            BlockUpdaterEntity.init();
            SpunklerEntity.init();
            VoidCrackEntity.init();
            LargeBubbleEntity.init();
            WarpedJellyfishEntity.init();
            VoidBombEntity.init();
            EnderbulbEntity.init();
            SmallEnderbulbEntity.init();
            BlukEntity.init();
            SummonedDraglingEntity.init();
        });
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
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BrewingRecipeRegistry.addRecipe(new LevitationRecipeBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new AdvancedHastePotionBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new BuildingPotionRecipeBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new ChorusTeaBrewingBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new ClassicHeavinessPotionBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new EndInfectionPotionBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new HastePotionBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new HealthBoostPotionBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new InstantNightVisionBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new Regen2BrewBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new RegenBrewBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new ResistancePotionBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new SerenityPotionRecipeBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new StrengthPotionBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new SwiftStrikesPotionRecipeBrewingRecipe());
            BrewingRecipeRegistry.addRecipe(new WarpedPotionRecipeBrewingRecipe());
        });
    }
}