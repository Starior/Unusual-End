package net.sweety.unusualend.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.effect.*;
import net.sweety.unusualend.enchantment.*;
import net.sweety.unusualend.item.inventory.BolokResearchNotesInventoryCapability;
import net.sweety.unusualend.world.inventory.BolokNotesMenu;
import net.sweety.unusualend.world.inventory.BuildingInhibitorGUIMenu;
import net.sweety.unusualend.world.inventory.InfuserGUIMenu;
import net.sweety.unusualend.world.inventory.PurpurTankGUIMenu;

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
    public static final DeferredHolder<MobEffect, EnderInfectionMobEffect> ENDER_INFECTION = MOB_EFFECTS.register("ender_infection", () -> new EnderInfectionMobEffect());
    public static final DeferredHolder<MobEffect, BuildingFatigueMobEffect> DISRUPTION = MOB_EFFECTS.register("disruption", () -> new BuildingFatigueMobEffect());
    public static final DeferredHolder<MobEffect, HeavinessMobEffect> HEAVINESS = MOB_EFFECTS.register("heaviness", () -> new HeavinessMobEffect());
    public static final DeferredHolder<MobEffect, SpectralStrideMobEffect> SPECTRAL_STRIDE = MOB_EFFECTS.register("spectral_stride", () -> new SpectralStrideMobEffect());
    public static final DeferredHolder<MobEffect, CrystallizationMobEffect> CRYSTALLIZATION = MOB_EFFECTS.register("crystallization", () -> new CrystallizationMobEffect());
    public static final DeferredHolder<MobEffect, WarpedTenacityMobEffect> WARPED_TENACITY = MOB_EFFECTS.register("warped_tenacity", () -> new WarpedTenacityMobEffect());
    public static final DeferredHolder<MobEffect, SwiftStrikesMobEffect> SWIFT_STRIKES = MOB_EFFECTS.register("swift_strikes", () -> new SwiftStrikesMobEffect());
    public static final DeferredHolder<MobEffect, SerenityMobEffect> SERENITY = MOB_EFFECTS.register("serenity", () -> new SerenityMobEffect());
    public static final DeferredHolder<MobEffect, BreachLinkedMobEffect> BREACH_LINKED = MOB_EFFECTS.register("breach_linked", () -> new BreachLinkedMobEffect());

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT, UnusualEnd.MODID);
    public static final DeferredHolder<Enchantment, EverlastingEnchantment> EVERLASTING = ENCHANTMENTS.register("everlasting", () -> new EverlastingEnchantment());
    public static final DeferredHolder<Enchantment, BoloksFuryEnchantment> BOLOKS_FURY = ENCHANTMENTS.register("boloks_fury", () -> new BoloksFuryEnchantment());
    public static final DeferredHolder<Enchantment, BoloksWingsEnchantment> BOLOKS_WINGS = ENCHANTMENTS.register("boloks_wings", () -> new BoloksWingsEnchantment());
    public static final DeferredHolder<Enchantment, BoloksHeadEnchantment> BOLOKS_HEAD = ENCHANTMENTS.register("boloks_head", () -> new BoloksHeadEnchantment());
    public static final DeferredHolder<Enchantment, JoustEnchantment> JOUST = ENCHANTMENTS.register("joust", () -> new JoustEnchantment());
    public static final DeferredHolder<Enchantment, RecoveryEnchantment> ARCANE_RECOVERY = ENCHANTMENTS.register("arcane_recovery", () -> new RecoveryEnchantment());
    public static final DeferredHolder<Enchantment, BondingEnchantment> BONDING = ENCHANTMENTS.register("bonding", () -> new BondingEnchantment());
    public static final DeferredHolder<Enchantment, BenevolenceEnchantment> BENEVOLENCE = ENCHANTMENTS.register("benevolence", () -> new BenevolenceEnchantment());

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, UnusualEnd.MODID);
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BOLOK_PARTICLE = PARTICLES.register("bolok_particle", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PINK_FLAME = PARTICLES.register("pink_flame", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WARPED_BUBBLES = PARTICLES.register("warped_bubbles", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CITRINE_SHINE = PARTICLES.register("citrine_shine", () -> new SimpleParticleType(false));

    public static void register(IEventBus bus){
        SERIALIZERS.register(bus);
        PAINTINGS.register(bus);
        MENUS.register(bus);
        MOB_EFFECTS.register(bus);
        ENCHANTMENTS.register(bus);
        PARTICLES.register(bus);
    }
}