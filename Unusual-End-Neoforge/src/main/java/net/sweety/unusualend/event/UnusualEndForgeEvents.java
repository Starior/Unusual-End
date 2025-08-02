package net.sweety.unusualend.event;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.configuration.UEConfig;
import net.sweety.unusualend.entity.BolokEntity;
import net.sweety.unusualend.entity.EnderblobQueenEntity;
import net.sweety.unusualend.init.UnusualEndMiscRegister;
import net.sweety.unusualend.init.UnusualendModBlocks;
import net.sweety.unusualend.init.UnusualendModEntities;
import net.sweety.unusualend.init.UnusualendModItems;
import net.sweety.unusualend.procedures.*;
import net.sweety.unusualend.world.inventory.InfuserGUIMenu;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME)
public class UnusualEndForgeEvents {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        LivingEntity entity = event.getEntity();
        if (event.getHand() != event.getEntity().getUsedItemHand())
            return;
        if (event.getLevel().getBlockState(event.getPos()).is(UnusualendModBlocks.VOID_EXPLOSIVE.get())) {
            if (entity.getMainHandItem().is(ItemTags.create(UnusualEnd.makeUEID("explosive_igniter")))
                    || entity.getOffhandItem().is(ItemTags.create(UnusualEnd.makeUEID("explosive_igniter")))) {
                if (event != null)
                    event.setCanceled(true);
                if (entity.getMainHandItem().is(ItemTags.create(UnusualEnd.makeUEID("explosive_igniter"))))
                    entity.swing(InteractionHand.MAIN_HAND, true);
                else
                    entity.swing(InteractionHand.OFF_HAND, true);
                SpawnExplosiveProcedure.execute(entity.level(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player entity = event.getEntity();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        Level level = event.getEntity().level();
        if (entity.containerMenu instanceof InfuserGUIMenu)
            InfuserGUIWhileThisGUIIsOpenTickProcedure.execute(entity);

        if (EnchantmentHelper.getTagEnchantmentLevel(UnusualEndMiscRegister.BOLOKS_WINGS.get(), (entity .getItemBySlot(EquipmentSlot.CHEST))) != 0) {
            if (Math.abs(entity.getDeltaMovement().x()) + Math.abs(entity.getDeltaMovement().y()) + Math.abs(entity.getDeltaMovement().z()) > 1.8) {
                final Vec3 _center = new Vec3(event.player.getX(), event.player.getY(), event.player.getZ());
                List<Entity> list = level.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(2.5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
                for (Entity iterator : list) {
                    if (!(entity == iterator)) {
                        iterator.invulnerableTime = 0;
                        iterator.hurt(new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.FLY_INTO_WALL)),
                                (float) ((Math.abs(entity.getDeltaMovement().x()) + Math.abs(entity.getDeltaMovement().y()) + Math.abs(entity.getDeltaMovement().z()) + 1)
                                        * (entity.getItemBySlot(EquipmentSlot.CHEST).getEnchantmentLevel(UnusualEndMiscRegister.BOLOKS_WINGS.get()))));
                    }
                }
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, serverLevel, 4, "", Component.literal(""), serverLevel.getServer(), null).withSuppressedOutput(),
                            "particle dust_color_transition 0.067 0.608 0.522 1 0.737 0.902 0.922 ~ ~0.1 ~ 0.4 0.2 0.4 0 1 normal");
                    serverLevel.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, serverLevel, 4, "", Component.literal(""), serverLevel.getServer(), null).withSuppressedOutput(),
                            "particle dust_color_transition 0.094 0.851 0.729 1.2 0.980 0.988 1.000 ~ ~0.1 ~ 0.4 0.2 0.4 0 1 normal");
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        LivingEntity entity = event.getEntity();
        if (event.getEffect().equals(UnusualEndMiscRegister.CRYSTALLIZATION.get()))
            CrystallizationEffectExpiresProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
        if (event.getEffect().equals(UnusualEndMiscRegister.ENDER_INFECTION.get()))
            EnderInfectionEffectExpiresProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
    }

    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();
        Entity immediateSourceentity = event.getSource().getDirectEntity();
        Entity sourceEntity = event.getSource().getEntity();
        if (immediateSourceentity == null || sourceEntity == null)
            return;
        boolean swing;
        double swing_curve, original_curve, swing_offset, dis;
        if (sourceEntity instanceof LivingEntity livingEntity && livingEntity.getMainHandItem().is(UnusualendModItems.ANCIENT_SWORD.get())) {
            if (immediateSourceentity == sourceEntity) {
                swing_offset = 1.5;
                swing_curve = Mth.nextDouble(RandomSource.create(), -0.5, 0.5);
                original_curve = swing_curve;
                if (swing_curve > 0.1) {
                    swing = true;
                } else if (swing_curve < -0.1) {
                    swing = false;
                } else {
                    if (Math.random() < 0.5) {
                        swing = true;
                        swing_curve = Mth.nextDouble(RandomSource.create(), 0.2, 0.5);
                    } else {
                        swing = false;
                        swing_curve = Mth.nextDouble(RandomSource.create(), -0.5, -0.2);
                    }
                    original_curve = swing_curve;
                }
                for (int index0 = 0; index0 < 30; index0++) {
                    if (!sourceEntity.level().isClientSide() && sourceEntity.getServer() != null) {
                        sourceEntity.getServer().getCommands().performPrefixedCommand(
                                new CommandSourceStack(CommandSource.NULL, sourceEntity.position(), sourceEntity.getRotationVector(), sourceEntity.level() instanceof ServerLevel ? (ServerLevel) sourceEntity.level() : null, 4, sourceEntity.getName().getString(), sourceEntity.getDisplayName(),
                                        sourceEntity.level().getServer(), sourceEntity),
                                ("execute anchored eyes positioned ^" + swing_offset + " ^" + (swing_curve - 0.5) + " ^1.8 run particle minecraft:block unusualend:void_particles_block ~ ~ ~ 0.1 0.1 0.1 0 3"));
                    }
                    if (Math.random() < 0.1) {
                        if (!sourceEntity.level().isClientSide() && sourceEntity.getServer() != null) {
                            sourceEntity.getServer().getCommands()
                                    .performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, sourceEntity.position(), sourceEntity.getRotationVector(), sourceEntity.level() instanceof ServerLevel ? (ServerLevel) sourceEntity.level() : null, 4,
                                                    sourceEntity.getName().getString(), sourceEntity.getDisplayName(), sourceEntity.level().getServer(), sourceEntity),
                                            ("execute anchored eyes positioned ^" + swing_offset + " ^" + (swing_curve - 0.5) + " ^1.8 run particle minecraft:end_rod ~ ~ ~"));
                        }
                    }
                    if (Math.random() < 0.3) {
                        if (!sourceEntity.level().isClientSide() && sourceEntity.getServer() != null) {
                            sourceEntity.getServer().getCommands()
                                    .performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, sourceEntity.position(), sourceEntity.getRotationVector(), sourceEntity.level() instanceof ServerLevel ? (ServerLevel) sourceEntity.level() : null, 4,
                                                    sourceEntity.getName().getString(), sourceEntity.getDisplayName(), sourceEntity.level().getServer(), sourceEntity),
                                            ("execute anchored eyes positioned ^" + swing_offset + " ^" + (swing_curve - 0.5) + " ^1.8 run particle minecraft:squid_ink ~ ~ ~"));
                        }
                    }
                    swing_offset = swing_offset - 0.1;
                    if (swing)
                        swing_curve = swing_curve - original_curve / 10;
                    else
                        swing_curve = swing_curve - original_curve / 10;
                }
            }
            dis = Math.sqrt(Math.pow(entity.getX() - sourceEntity.getX(), 2) + Math.pow(entity.getY() - sourceEntity.getY(), 2) + Math.pow(entity.getZ() - sourceEntity.getZ(), 2));
            if (!entity.getType().is(Tags.EntityTypes.BOSSES))
                entity.setDeltaMovement(new Vec3((((sourceEntity.getX() - entity.getX()) / dis) * 1), (((sourceEntity.getY() - entity.getY()) / dis) * 1), (((sourceEntity.getZ() - entity.getZ()) / dis) * 1)));
        }
        if (sourceEntity instanceof EnderblobQueenEntity) {
            sourceEntity.setSprinting(true);
            swing_offset = 2;
            swing_curve = 1;
            original_curve = swing_curve;
            for (int index0 = 0; index0 < 30; index0++) {
                if (!sourceEntity.level().isClientSide() && sourceEntity.getServer() != null) {
                    sourceEntity.getServer().getCommands()
                            .performPrefixedCommand(
                                    new CommandSourceStack(CommandSource.NULL, sourceEntity.position(), sourceEntity.getRotationVector(), sourceEntity.level() instanceof ServerLevel ? (ServerLevel) sourceEntity.level() : null, 4, sourceEntity.getName().getString(),
                                            sourceEntity.getDisplayName(), sourceEntity.level().getServer(), sourceEntity),
                                    ("execute anchored eyes positioned ^" + swing_offset + " ^" + (swing_curve + 0.6) + " ^2 run particle minecraft:block unusualend:void_particles_block ~ ~ ~ 0.1 0.1 0.1 0 3"));
                }
                if (Math.random() < 0.1) {
                    if (!sourceEntity.level().isClientSide() && sourceEntity.getServer() != null) {
                        sourceEntity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, sourceEntity.position(), sourceEntity.getRotationVector(), sourceEntity.level() instanceof ServerLevel ? (ServerLevel) sourceEntity.level() : null, 4,
                                sourceEntity.getName().getString(), sourceEntity.getDisplayName(), sourceEntity.level().getServer(), sourceEntity), ("execute anchored eyes positioned ^" + swing_offset + " ^" + (swing_curve + 0.6) + " ^2 run particle minecraft:end_rod ~ ~ ~"));
                    }
                }
                if (Math.random() < 0.3) {
                    if (!sourceEntity.level().isClientSide() && sourceEntity.getServer() != null) {
                        sourceEntity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, sourceEntity.position(), sourceEntity.getRotationVector(), sourceEntity.level() instanceof ServerLevel ? (ServerLevel) sourceEntity.level() : null, 4,
                                        sourceEntity.getName().getString(), sourceEntity.getDisplayName(), sourceEntity.level().getServer(), sourceEntity),
                                ("execute anchored eyes positioned ^" + swing_offset + " ^" + (swing_curve + 0.6) + " ^2 run particle minecraft:squid_ink ~ ~ ~"));
                    }
                }
                swing_offset = swing_offset - 0.1;
                swing_curve = swing_curve - original_curve / 10;
            }
            UnusualEnd.queueServerWork(11, () -> sourceEntity.setSprinting(false));
        }
        double speed;
        double yaw;
        if (entity.isBlocking()) {
            if (entity.getMainHandItem().is(UnusualendModItems.ENDERBLOB_SHIELD.get()) || entity.getOffhandItem().is(UnusualendModItems.ENDERBLOB_SHIELD.get())) {
                Entity immediateSourceEntity = event.getSource().getDirectEntity();
                if ((!(immediateSourceEntity instanceof ServerPlayer) || UEConfig.SHIELD_PVP.get()) && immediateSourceEntity != null) {
                    speed = 0.8;
                    yaw = entity.getYRot();
                    immediateSourceEntity.setDeltaMovement(new Vec3((speed * Math.cos((yaw + 90) * (Math.PI / 180))), (immediateSourceEntity.getDeltaMovement().y() + 0.1), (speed * Math.sin((yaw + 90) * (Math.PI / 180)))));
                    if (entity instanceof ServerPlayer _player) {
                        AdvancementHolder _adv = _player.server.getAdvancements().get(UnusualEnd.makeUEID("forced_social_distancing"));
                        AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
                        if (!_ap.isDone()) {
                            for (String criteria : _ap.getRemainingCriteria())
                                _player.getAdvancements().award(_adv, criteria);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void addAttributeModifier(ItemAttributeModifierEvent event) {
        ItemStack itemstack = event.getItemStack();
        AttributeModifier modifier;
        if (itemstack.getMaxStackSize() == 1) {
            if (event.getSlotType() == EquipmentSlot.FEET && itemstack.getItem() == UnusualendModItems.WARPED_BOOTS.get()) {
                modifier = new AttributeModifier(UUID.fromString("f654bc63-b67e-40ee-b11c-2d8abaab9f3e"), "unusualend.BolokBootsBoost", 0.5D, AttributeModifier.Operation.MULTIPLY_BASE);
                event.addModifier(NeoForgeMod.SWIM_SPEED.value(), modifier);
            }
            if (event.getSlotType() == EquipmentSlot.MAINHAND && itemstack.getItem() == UnusualendModItems.BOUNCY_DAGGER.get()) {
                modifier = new AttributeModifier(UUID.fromString("94fe2560-d356-4499-110c-94c8bb6c1bb2"), "unusualend.ReachDaggerDebuff", -0.5D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.ENTITY_REACH.value(), modifier);
                modifier = new AttributeModifier(UUID.fromString("94fe2560-d376-4499-110c-64c8bb6c1bb3"), "unusualend.BouncyDagger", 2.0D, AttributeModifier.Operation.ADDITION);
                event.addModifier(Attributes.ATTACK_KNOCKBACK, modifier);
            }
            if (event.getSlotType() == EquipmentSlot.MAINHAND && itemstack.getItem() == UnusualendModItems.WARPED_SPEAR.get()) {
                modifier = new AttributeModifier(UUID.fromString("33fe2130-d356-4749-110c-94c8bb6c1bb8"), "unusualend.ReachSpearBuff", 2.0D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.ENTITY_REACH.value(), modifier);
            }
            if (event.getSlotType() == EquipmentSlot.CHEST && itemstack.getItem() == UnusualendModItems.WARPED_CHESTPLATE.get()) {
                modifier = new AttributeModifier(UUID.fromString("34fe2560-d356-4799-110c-99c8bb6c1bb2"), "unusualend.SlowDebuff", -0.05D, AttributeModifier.Operation.MULTIPLY_BASE);
                event.addModifier(Attributes.MOVEMENT_SPEED, modifier);
            }
            if (event.getSlotType() == EquipmentSlot.MAINHAND && itemstack.getItem() == UnusualendModItems.PEARLESCENT_SWORD.get()) {
                modifier = new AttributeModifier(UUID.fromString("574188c2-554a-11ee-8c99-0242ac120002"), "unusualend.ReachSpectralBuff", 0.5D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.ENTITY_REACH.value(), modifier);
                modifier = new AttributeModifier(UUID.fromString("e5c352ec-554a-11ee-8c99-0242ac120002"), "unusualend.ReachSpectralBuff", 1.5D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.BLOCK_REACH.value(), modifier);
            }
            if (event.getSlotType() == EquipmentSlot.MAINHAND && itemstack.getItem() == UnusualendModItems.PEARLESCENT_AXE.get()) {
                modifier = new AttributeModifier(UUID.fromString("674188c2-554a-11ee-8c99-0242ac120002"), "unusualend.ReachSpectralBuff", 0.5D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.ENTITY_REACH.value(), modifier);
                modifier = new AttributeModifier(UUID.fromString("65c352ec-554a-11ee-8c99-0242ac120002"), "unusualend.ReachSpectralBuff", 1.5D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.BLOCK_REACH.value(), modifier);
            }
            if (event.getSlotType() == EquipmentSlot.MAINHAND && itemstack.getItem() == UnusualendModItems.PEARLESCENT_PICKAXE.get()) {
                modifier = new AttributeModifier(UUID.fromString("584188c2-554a-11ee-8c99-0242ac120002"), "unusualend.ReachSpectralBuff", 0.5D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.ENTITY_REACH.value(), modifier);
                modifier = new AttributeModifier(UUID.fromString("e5c952ec-554a-11ee-8c99-0242ac120002"), "unusualend.ReachSpectralBuff", 1.5D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.BLOCK_REACH.value(), modifier);
            }
            if (event.getSlotType() == EquipmentSlot.MAINHAND && itemstack.getItem() == UnusualendModItems.PEARLESCENT_HOE.get()) {
                modifier = new AttributeModifier(UUID.fromString("574108c2-554a-11ee-8c99-0242ac120002"), "unusualend.ReachSpectralBuff", 0.5D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.ENTITY_REACH.value(), modifier);
                modifier = new AttributeModifier(UUID.fromString("e5c352fc-554a-11ee-8c99-0242ac120002"), "unusualend.ReachSpectralBuff", 1.5D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.BLOCK_REACH.value(), modifier);
            }
            if (event.getSlotType() == EquipmentSlot.MAINHAND && itemstack.getItem() == UnusualendModItems.PEARLESCENT_SHOVEL.get()) {
                modifier = new AttributeModifier(UUID.fromString("573188c2-554a-11ee-8c99-0242ac120002"), "unusualend.ReachSpectralBuff", 0.5D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.ENTITY_REACH.value(), modifier);
                modifier = new AttributeModifier(UUID.fromString("e5c352ec-554a-11fe-8c99-0242ac120002"), "unusualend.ReachSpectralBuff", 1.5D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.BLOCK_REACH.value(), modifier);
            }
            if (event.getSlotType() == EquipmentSlot.MAINHAND && itemstack.getItem() == UnusualendModItems.ANCIENT_SWORD.get()) {
                modifier = new AttributeModifier(UUID.fromString("573188c2-554a-11ee-8c99-0242ac128802"), "unusualend.ReachAncientBuff", 0.5D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.ENTITY_REACH.value(), modifier);
            }
            if (event.getSlotType() == EquipmentSlot.OFFHAND && itemstack.getItem() == UnusualendModItems.CITRINE_AMULET.get()) {
                modifier = new AttributeModifier(UUID.fromString("584182c5-332a-12ee-8c99-0242ac128802"), "unusualend.ExtraLuck", 1.0D, AttributeModifier.Operation.ADDITION);
                event.addModifier(Attributes.LUCK, modifier);
            }
            if (event.getSlotType() == EquipmentSlot.CHEST && itemstack.getItem() == UnusualendModItems.SCALE_HORSE_ARMOR.get()) {
                modifier = new AttributeModifier(UUID.fromString("784142c5-312a-12ee-8c49-0242ac128802"), "unusualend.HorseWaterBuff", 2.0D, AttributeModifier.Operation.MULTIPLY_BASE);
                event.addModifier(NeoForgeMod.SWIM_SPEED.value(), modifier);
                modifier = new AttributeModifier(UUID.fromString("704142c5-312a-12ee-8c49-0242ac125502"), "unusualend.HorseBuff", 1.0D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.STEP_HEIGHT.value(), modifier);
            }
            if (event.getSlotType() == EquipmentSlot.MAINHAND && itemstack.getItem() == UnusualendModItems.WARPED_ANCHOR.get()) {
                modifier = new AttributeModifier(UUID.fromString("0c948739-fac0-431d-854c-9ed62408760d"), "unusualend.AnchorDebuff", -0.5D, AttributeModifier.Operation.ADDITION);
                event.addModifier(NeoForgeMod.ENTITY_REACH.value(), modifier);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Entity sourceEntity = event.getSource().getEntity();
        if (entity instanceof Shulker) {
            if (sourceEntity instanceof BolokEntity) {
                if (Math.random() < 0.7) {
                    if (entity.level() instanceof ServerLevel _level) {
                        Entity entityToSpawn = UnusualendModEntities.SPUNKLER.get().spawn(_level, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), MobSpawnType.MOB_SUMMONED);
                        if (entityToSpawn != null) {
                            entityToSpawn.setYRot(entity.level().getRandom().nextFloat() * 360F);
                        }
                    }
                }
            }
        }
        if ((sourceEntity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == UnusualendModItems.CITRINE_AMULET.get()) {
            CitrineTalismanTriggerProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
        }
        if (ModList.get().isLoaded("curios")) {
            if (sourceEntity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(UnusualendModItems.CITRINE_AMULET.get(), lv).isPresent() : false) {
                CitrineTalismanTriggerProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
            }
        }
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        BlockState blockState = event.getState();
        Entity entity = event.getEntity();
        LevelAccessor level = event.getLevel();
        if (blockState.getBlock() == UnusualendModBlocks.BUILDING_INHIBITOR.get()) {
            if (!level.isClientSide()) {
                BlockPos _bp = BlockPos.containing(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
                BlockEntity _blockEntity = level.getBlockEntity(_bp);
                BlockState _bs = level.getBlockState(_bp);
                if (_blockEntity != null)
                    _blockEntity.getPersistentData().putString("Owner", (entity.getStringUUID()));
                if (level instanceof Level _level)
                    _level.sendBlockUpdated(_bp, _bs, _bs, 3);
            }
        }
    }

    @SubscribeEvent
    public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
        ItemStack itemstack = event.getItemStack();
        if (itemstack.is(UnusualendModItems.GOLEM_ORB.get()))
            event.setBurnTime(640000);
        else if (itemstack.is(UnusualendModBlocks.CHORUS_CANE.get().asItem()))
            event.setBurnTime(50);
        else if (itemstack.is(UnusualendModBlocks.BLOOMING_CHORUS_CANE.get().asItem()))
            event.setBurnTime(50);
        else if (itemstack.is(UnusualendModBlocks.CHORUS_CANE_BLOCK.get().asItem()))
            event.setBurnTime(400);
        else if (itemstack.is(UnusualendModBlocks.STRIPPED_CHORUS_CANE_BLOCK.get().asItem()))
            event.setBurnTime(400);
    }
    @SubscribeEvent
    public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity().level() instanceof ServerLevel level)
            level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, event.getEntity().position(), Vec2.ZERO, level, 4, "", Component.literal(""), level.getServer(), null).withSuppressedOutput(),
                    ("stopsound " + event.getEntity().getDisplayName().getString() + " music"));
    }
    @SubscribeEvent
    public static void onEntityAttacked(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity != null) {
            Level level = event.getEntity().level();
            double x = event.getEntity().getX();
            double y = event.getEntity().getY();
            double z = event.getEntity().getZ();
            double amount = event.getAmount();
            double X;
            double Y;
            double Z;
            if (entity.getItemBySlot(EquipmentSlot.HEAD).is(UnusualendModItems.CHORUS_HELMET.get())) {
                if (!entity.isShiftKeyDown()) {
                    if (Math.random() < UEConfig.CHORUS_HELMET_PROBABILITY_TO_TELEPORT.get() / 100) {
                        for (int index0 = 0; index0 < 1000; index0++) {
                            X = x + Mth.nextInt(RandomSource.create(), -5, 5);
                            Y = y + Mth.nextInt(RandomSource.create(), -2, 2);
                            Z = z + Mth.nextInt(RandomSource.create(), -5, 5);
                            if ((level.getBlockState(BlockPos.containing(X, Y, Z))).getBlock() == Blocks.CAVE_AIR || (level.getBlockState(BlockPos.containing(X, Y, Z))).getBlock() == Blocks.VOID_AIR
                                    || (level.getBlockState(BlockPos.containing(X, Y, Z))).getBlock() == Blocks.AIR) {
                                if (level.getBlockState(BlockPos.containing(X, Y - 1, Z)).canOcclude()) {
                                    {
                                        Entity _ent = entity;
                                        _ent.teleportTo(X, Y, Z);
                                        if (_ent instanceof ServerPlayer _serverPlayer)
                                            _serverPlayer.connection.teleport(X, Y, Z, _ent.getYRot(), _ent.getXRot());
                                    }
                                    if (level instanceof ServerLevel _level)
                                        _level.sendParticles(ParticleTypes.PORTAL, X, Y, Z, 30, 0.5, 1.5, 0.5, 0);
                                    if (!level.isClientSide()) {
                                        level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1, 1);
                                    } else
                                        level.playLocalSound(x, y, z, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1, 1, false);
                                    if (amount <= UEConfig.MAX_DAMAGE_CHORUS_HELMET_CAN_DODGE_WITH_TP_.get()) {
                                        if (!(entity instanceof ServerPlayer _plr18 && _plr18.level() instanceof ServerLevel
                                                && _plr18.getAdvancements().getOrStartProgress(_plr18.server.getAdvancements().get(UnusualEnd.makeUEID("use_chorus_helmet"))).isDone())) {
                                            if (entity instanceof ServerPlayer _player) {
                                                AdvancementHolder _adv = _player.server.getAdvancements().get(UnusualEnd.makeUEID("use_chorus_helmet"));
                                                AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
                                                if (!_ap.isDone()) {
                                                    for (String criteria : _ap.getRemainingCriteria())
                                                        _player.getAdvancements().award(_adv, criteria);
                                                }
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}