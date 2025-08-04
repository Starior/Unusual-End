package net.sweety.unusualend.init;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.effect.*;
import net.sweety.unusualend.enchantment.BoloksFuryEnchantment;
import net.sweety.unusualend.enchantment.BoloksHeadEnchantment;
import net.sweety.unusualend.enchantment.BoloksWingsEnchantment;
import net.sweety.unusualend.item.data.StringToDoubleData;
import net.sweety.unusualend.item.inventory.BolokResearchNotesInventoryCapability;
import net.sweety.unusualend.world.features.*;
import net.sweety.unusualend.world.inventory.BolokNotesMenu;
import net.sweety.unusualend.world.inventory.BuildingInhibitorGUIMenu;
import net.sweety.unusualend.world.inventory.InfuserGUIMenu;
import net.sweety.unusualend.world.inventory.PurpurTankGUIMenu;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class UnusualEndMiscRegister {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, "unusualend");

    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(BuiltInRegistries.PAINTING_VARIANT, UnusualEnd.MODID);
    public static final DeferredHolder<PaintingVariant, PaintingVariant> END_LANDSCAPE = PAINTINGS.register("end_landscape", () -> new PaintingVariant(16, 16));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> GLOOP = PAINTINGS.register("gloop", () -> new PaintingVariant(32, 16));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> STARRY_VOID = PAINTINGS.register("starry_void", () -> new PaintingVariant(48, 16));

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPE = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, UnusualEnd.MODID);
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<BolokResearchNotesInventoryCapability>> BOLOK_NOTE_INVENTORY = ATTACHMENT_TYPE.register("bolok_note_inventory", () -> AttachmentType.serializable(BolokResearchNotesInventoryCapability::new).build());

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, UnusualEnd.MODID);
    public static final DeferredHolder<MenuType<?>, MenuType<PurpurTankGUIMenu>> PURPUR_TANK_GUI = MENUS.register("purpur_tank_gui", () -> IMenuTypeExtension.create(PurpurTankGUIMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<BolokNotesMenu>> BOLOK_NOTES = MENUS.register("bolok_notes", () -> IMenuTypeExtension.create(BolokNotesMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<BuildingInhibitorGUIMenu>> BUILDING_INHIBITOR_GUI = MENUS.register("building_inhibitor_gui", () -> IMenuTypeExtension.create(BuildingInhibitorGUIMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<InfuserGUIMenu>> INFUSER_GUI = MENUS.register("infuser_gui", () -> IMenuTypeExtension.create(InfuserGUIMenu::new));

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, UnusualEnd.MODID);
    public static final DeferredHolder<MobEffect, EnderInfectionMobEffect> ENDER_INFECTION = MOB_EFFECTS.register("ender_infection", EnderInfectionMobEffect::new);
    public static final DeferredHolder<MobEffect, BuildingFatigueMobEffect> DISRUPTION = MOB_EFFECTS.register("disruption", BuildingFatigueMobEffect::new);
    public static final DeferredHolder<MobEffect, HeavinessMobEffect> HEAVINESS = MOB_EFFECTS.register("heaviness", HeavinessMobEffect::new);
    public static final DeferredHolder<MobEffect, SpectralStrideMobEffect> SPECTRAL_STRIDE = MOB_EFFECTS.register("spectral_stride", SpectralStrideMobEffect::new);
    public static final DeferredHolder<MobEffect, CrystallizationMobEffect> CRYSTALLIZATION = MOB_EFFECTS.register("crystallization", CrystallizationMobEffect::new);
    public static final DeferredHolder<MobEffect, WarpedTenacityMobEffect> WARPED_TENACITY = MOB_EFFECTS.register("warped_tenacity", WarpedTenacityMobEffect::new);
    public static final DeferredHolder<MobEffect, SwiftStrikesMobEffect> SWIFT_STRIKES = MOB_EFFECTS.register("swift_strikes", SwiftStrikesMobEffect::new);
    public static final DeferredHolder<MobEffect, SerenityMobEffect> SERENITY = MOB_EFFECTS.register("serenity", SerenityMobEffect::new);
    public static final DeferredHolder<MobEffect, BreachLinkedMobEffect> BREACH_LINKED = MOB_EFFECTS.register("breach_linked", BreachLinkedMobEffect::new);

    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, UnusualEnd.MODID);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> BOLOKS_FURY = ENCHANTMENT_EFFECTS.register("boloks_fury", () -> BoloksFuryEnchantment.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> BOLOKS_WINGS = ENCHANTMENT_EFFECTS.register("boloks_wings", () -> BoloksWingsEnchantment.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> BOLOKS_HEAD = ENCHANTMENT_EFFECTS.register("boloks_head", () -> BoloksHeadEnchantment.CODEC);

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, UnusualEnd.MODID);
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BOLOK_PARTICLE = PARTICLES.register("bolok_particle", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PINK_FLAME = PARTICLES.register("pink_flame", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WARPED_BUBBLES = PARTICLES.register("warped_bubbles", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CITRINE_SHINE = PARTICLES.register("citrine_shine", () -> new SimpleParticleType(false));

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, UnusualEnd.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<StringToDoubleData>> STRING_TO_DOUBLE_DATA = register("string_to_double_data",
            builder -> builder.persistent(StringToDoubleData.CODEC));

    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                           UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, UnusualEnd.MODID);
    public static final DeferredHolder<Feature<?>, PurpurIsland1FeatureFeature> PURPUR_ISLAND_1_FEATURE = FEATURES.register("purpur_island_1_feature", PurpurIsland1FeatureFeature::new);
    public static final DeferredHolder<Feature<?>, HighlandPlantsFeature> HIGHLAND_PLANTS = FEATURES.register("highland_plants", HighlandPlantsFeature::new);
    public static final DeferredHolder<Feature<?>, InfestedEndstoneFeature> INFESTED_ENDSTONE = FEATURES.register("infested_endstone", InfestedEndstoneFeature::new);
    public static final DeferredHolder<Feature<?>, RawPurpurFeatureFeature> RAW_PURPUR_FEATURE = FEATURES.register("raw_purpur_feature", RawPurpurFeatureFeature::new);
    public static final DeferredHolder<Feature<?>, WarpedPatchsFeature> WARPED_PATCHS = FEATURES.register("warped_patchs", WarpedPatchsFeature::new);

    public static ResourceLocation getModEffectLocation(String name) {
        return UnusualEnd.makeUEID("mob_effect_attribute_%s".formatted(name));
    }

    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
        PAINTINGS.register(bus);
        MENUS.register(bus);
        MOB_EFFECTS.register(bus);
        ENCHANTMENT_EFFECTS.register(bus);
        PARTICLES.register(bus);
        FEATURES.register(bus);
    }
}