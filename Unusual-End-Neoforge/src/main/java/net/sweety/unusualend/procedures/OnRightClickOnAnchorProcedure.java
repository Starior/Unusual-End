package net.sweety.unusualend.procedures;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.configuration.UEConfig;
import net.sweety.unusualend.init.UnusualEndBlocks;
import net.sweety.unusualend.init.UnusualEndItems;

public class OnRightClickOnAnchorProcedure {
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

    public static void execute(Level level, double x, double y, double z, Player player) {
        if ((level.getBlockState(BlockPos.containing(x, y, z))).is(UnusualEndBlocks.TELEPORTATION_ANCHOR.get())) {
            ItemStack stack = player.getMainHandItem();
            if (stack.is(UnusualEndItems.PRISMATIC_MIRROR.get())) {
                if (UEConfig.CAN_USE_TELEPORTATION_ANCHOR.get()) {
                    if (getNBTBoolean(stack, "LinkedMirror")) {
                        setNBTBoolean(stack, "LinkedMirror", false);
                        setNBTDouble(stack, "TpX", 0);
                        setNBTDouble(stack, "TpY", 0);
                        setNBTDouble(stack, "TpZ", 0);
                        if (!level.isClientSide()) {
                            level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.GRINDSTONE_USE, SoundSource.NEUTRAL, 1, 1);
                        } else
                            level.playLocalSound(x, y, z, SoundEvents.GRINDSTONE_USE, SoundSource.NEUTRAL, 1, 1, false);
                        stack.set(DataComponents.CUSTOM_NAME, Component.literal(("\u00A7e" + Component.translatable("item.unusualend.prismatic_mirror").getString())));
                    } else {
                        if ((player.level().dimension()) == Level.OVERWORLD || (player.level().dimension()) == Level.NETHER || (player.level().dimension()) == Level.END) {
                            setNBTDouble(stack, "TpX", x + 0.5);
                            setNBTDouble(stack, "TpY", y + 1);
                            setNBTDouble(stack, "TpZ", z + 0.5);
                            setNBTBoolean(stack, "LinkedMirror", true);
                            setNBTString(stack, "TpW", ("" + (level instanceof Level _lvl ? _lvl.dimension() : (level instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD))));
                            stack.set(DataComponents.CUSTOM_NAME, Component.literal(("\u00A7e" + Component.translatable("item.unusualend.linked_prismatic_mirror").getString())));
                            player.swing(InteractionHand.MAIN_HAND, true);
                            if (!level.isClientSide()) {
                                level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1, 1);
                            } else
                                level.playLocalSound(x, y, z, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1, 1, false);
                            if (level instanceof ServerLevel _level)
                                _level.sendParticles(ParticleTypes.PORTAL, x, y, z, 20, 1.5, 1.5, 1.5, 0);
                            if (player instanceof ServerPlayer _player) {
                                AdvancementHolder _adv = _player.server.getAdvancements().get(UnusualEnd.makeUEID("hyperspace_jump"));
                                AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
                                if (!_ap.isDone()) {
                                    for (String criteria : _ap.getRemainingCriteria())
                                        _player.getAdvancements().award(_adv, criteria);
                                }
                            }
                        } else {
                            if (player instanceof Player _player && !_player.level().isClientSide())
                                _player.displayClientMessage(Component.literal((Component.translatable("text.unusualend.cant_host_anchor").getString())), true);
                        }
                    }
                } else {
                    if (!player.level().isClientSide())
                        player.displayClientMessage(Component.literal((Component.translatable("text.unusualend.no_anchor").getString())), true);
                }
            } else if (stack.is(UnusualEndItems.VOID_TOTEM.get())) {
                if (UEConfig.CAN_LINK_VOID_TOTEM.get()) {
                    if (getNBTBoolean(stack, "LinkedMirror")) {
                        setNBTBoolean(stack, "LinkedMirror", false);
                        setNBTDouble(stack, "TpX", 0);
                        setNBTDouble(stack, "TpY", 0);
                        setNBTDouble(stack, "TpZ", 0);
                        if (!level.isClientSide()) {
                            level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 1, 1);
                        } else
                            level.playLocalSound(x, y, z, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 1, 1, false);
                        stack.set(DataComponents.CUSTOM_NAME, Component.literal(("\u00A7e" + Component.translatable("item.unusualend.void_totem").getString())));
                    } else {
                        if ((player.level().dimension()) == Level.OVERWORLD || (player.level().dimension()) == Level.NETHER || (player.level().dimension()) == Level.END) {
                            setNBTDouble(stack, "TpX", x + 0.5);
                            setNBTDouble(stack, "TpY", y + 1);
                            setNBTDouble(stack, "TpZ", z + 0.5);
                            setNBTBoolean(stack, "LinkedMirror", true);
                            setNBTString(stack, "TpW", ("" + (level instanceof Level _lvl ? _lvl.dimension() : (level instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD))));
                            stack.set(DataComponents.CUSTOM_NAME, Component.literal(("\u00A7e" + Component.translatable("item.unusualend.linked_void_totem").getString())));
                            player.swing(InteractionHand.MAIN_HAND, true);
                            if (!level.isClientSide()) {
                                level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1, 1);
                            } else
                                level.playLocalSound(x, y, z, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1, 1, false);
                            if (level instanceof ServerLevel _level)
                                _level.sendParticles(ParticleTypes.PORTAL, x, y, z, 20, 1.5, 1.5, 1.5, 0);
                            if (player instanceof ServerPlayer _player) {
                                AdvancementHolder _adv = _player.server.getAdvancements().get(UnusualEnd.makeUEID("hyperspace_jump"));
                                AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
                                if (!_ap.isDone()) {
                                    for (String criteria : _ap.getRemainingCriteria())
                                        _player.getAdvancements().award(_adv, criteria);
                                }
                            }
                        } else {
                            if (!player.level().isClientSide())
                                player.displayClientMessage(Component.literal((Component.translatable("text.unusualend.cant_host_anchor").getString())), true);
                        }
                    }
                } else {
                    if (!player.level().isClientSide())
                        player.displayClientMessage(Component.literal((Component.translatable("text.unusualend.no_anchor").getString())), true);
                }
            } else if (stack.is(Items.TOTEM_OF_UNDYING)) {
                if (UEConfig.CAN_LINK_TOTEM.get()) {
                    if (getNBTBoolean(stack, "LinkedMirror")) {
                        setNBTBoolean(stack, "LinkedMirror", false);
                        setNBTDouble(stack, "TpX", 0);
                        setNBTDouble(stack, "TpY", 0);
                        setNBTDouble(stack, "TpZ", 0);
                        if (!level.isClientSide()) {
                            level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 1, 1);
                        } else
                            level.playLocalSound(x, y, z, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 1, 1, false);
                        stack.set(DataComponents.CUSTOM_NAME, Component.literal(("\u00A7e" + Component.translatable("item.minecraft.totem_of_undying").getString())));
                    } else {
                        if ((player.level().dimension()) == Level.OVERWORLD || (player.level().dimension()) == Level.NETHER || (player.level().dimension()) == Level.END) {
                            setNBTDouble(stack, "TpX", x + 0.5);
                            setNBTDouble(stack, "TpY", y + 1);
                            setNBTDouble(stack, "TpZ", z + 0.5);
                            setNBTBoolean(stack, "LinkedMirror", true);
                            setNBTString(stack, "TpW", ("" + (level instanceof Level _lvl ? _lvl.dimension() : (level instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD))));
                            stack.set(DataComponents.CUSTOM_NAME, Component.literal(("\u00A7e" + Component.translatable("item.unusualend.linked_totem_undying").getString())));
                            player.swing(InteractionHand.MAIN_HAND, true);
                            if (!level.isClientSide()) {
                                level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1, 1);
                            } else
                                level.playLocalSound(x, y, z, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1, 1, false);
                            if (level instanceof ServerLevel _level)
                                _level.sendParticles(ParticleTypes.PORTAL, x, y, z, 20, 1.5, 1.5, 1.5, 0);
                        } else {
                            if (!player.level().isClientSide())
                                player.displayClientMessage(Component.literal((Component.translatable("text.unusualend.cant_host_anchor").getString())), true);
                        }
                    }
                } else {
                    if (!player.level().isClientSide())
                        player.displayClientMessage(Component.literal((Component.translatable("text.unusualend.no_anchor").getString())), true);
                }
            } else {
                if (!player.level().isClientSide())
                    player.displayClientMessage(Component.literal((Component.translatable("text.unusualend.need_anchor_item").getString())), true);
            }
        }
    }
}
