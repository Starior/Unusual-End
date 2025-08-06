package net.sweety.unusualend.procedures;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.init.UnusualEndBlocks;

import java.util.Map;

@EventBusSubscriber
public class StipPlanksProcedure {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getHand() != event.getEntity().getUsedItemHand())
            return;
        execute(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
    }

    private static void execute(LevelAccessor world, double x, double y, double z, LivingEntity entity) {
        if (entity == null)
            return;
        if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof AxeItem || (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() instanceof AxeItem
                && (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()) {
            if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(UnusualEnd.makeUEID("strippable")))) {
                if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof AxeItem) {
                    if (!(new Object() {
                        public boolean checkGamemode(Entity _ent) {
                            if (_ent instanceof ServerPlayer _serverPlayer) {
                                return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                            } else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
                                return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                        && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
                            }
                            return false;
                        }
                    }.checkGamemode(entity))) {
                        entity.getMainHandItem().hurtAndBreak(1, entity, entity.getEquipmentSlotForItem(entity.getMainHandItem()));
                    }
                } else {
                    if (entity instanceof LivingEntity _entity)
                        _entity.swing(InteractionHand.OFF_HAND, true);
                    if (!(new Object() {
                        public boolean checkGamemode(Entity _ent) {
                            if (_ent instanceof ServerPlayer _serverPlayer) {
                                return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                            } else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
                                return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                        && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
                            }
                            return false;
                        }
                    }.checkGamemode(entity))) {
                        entity.getOffhandItem().hurtAndBreak(1, entity, entity.getEquipmentSlotForItem(entity.getOffhandItem()));
                    }
                }
                if (entity instanceof LivingEntity _entity)
                    _entity.swing(InteractionHand.MAIN_HAND, true);
                if (world.isClientSide()) {
                    if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                            _level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1, 1);
                        } else {
                            _level.playLocalSound(x, y, z, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1, 1, false);
                        }
                    }
                }
                if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == UnusualEndBlocks.CHORUS_NEST_PLANKS.get()) {
                    {
                        BlockPos _bp = BlockPos.containing(x, y, z);
                        BlockState _bs = UnusualEndBlocks.STRIPPED_CHORUS_NEST_PLANKS.get().defaultBlockState();
                        BlockState _bso = world.getBlockState(_bp);
                        for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
                            Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                            if (_property != null && _bs.getValue(_property) != null)
                                try {
                                    _bs = _bs.setValue(_property, (Comparable) entry.getValue());
                                } catch (Exception e) {
                                }
                        }
                        world.setBlock(_bp, _bs, 3);
                    }
                }
                if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == UnusualEndBlocks.CHORUS_NEST_STAIRS.get()) {
                    {
                        BlockPos _bp = BlockPos.containing(x, y, z);
                        BlockState _bs = UnusualEndBlocks.STRIPPED_CHORUS_NEST_STAIRS.get().defaultBlockState();
                        BlockState _bso = world.getBlockState(_bp);
                        for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
                            Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                            if (_property != null && _bs.getValue(_property) != null)
                                try {
                                    _bs = _bs.setValue(_property, (Comparable) entry.getValue());
                                } catch (Exception e) {
                                }
                        }
                        world.setBlock(_bp, _bs, 3);
                    }
                }
                if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == UnusualEndBlocks.CHORUS_NEST_SLAB.get()) {
                    {
                        BlockPos _bp = BlockPos.containing(x, y, z);
                        BlockState _bs = UnusualEndBlocks.STRIPPED_CHORUS_NEST_SLAB.get().defaultBlockState();
                        BlockState _bso = world.getBlockState(_bp);
                        for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
                            Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                            if (_property != null && _bs.getValue(_property) != null)
                                try {
                                    _bs = _bs.setValue(_property, (Comparable) entry.getValue());
                                } catch (Exception e) {
                                }
                        }
                        world.setBlock(_bp, _bs, 3);
                    }
                }
                if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == UnusualEndBlocks.CHORUS_CANE_BLOCK.get()) {
                    {
                        BlockPos _bp = BlockPos.containing(x, y, z);
                        BlockState _bs = UnusualEndBlocks.STRIPPED_CHORUS_CANE_BLOCK.get().defaultBlockState();
                        BlockState _bso = world.getBlockState(_bp);
                        for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
                            Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                            if (_property != null && _bs.getValue(_property) != null)
                                try {
                                    _bs = _bs.setValue(_property, (Comparable) entry.getValue());
                                } catch (Exception e) {
                                }
                        }
                        world.setBlock(_bp, _bs, 3);
                    }
                }
                if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == UnusualEndBlocks.BLOOMING_CHORUS_CANE.get()) {
                    {
                        BlockPos _bp = BlockPos.containing(x, y, z);
                        BlockState _bs = UnusualEndBlocks.CHORUS_CANE.get().defaultBlockState();
                        BlockState _bso = world.getBlockState(_bp);
                        for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
                            Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                            if (_property != null && _bs.getValue(_property) != null)
                                try {
                                    _bs = _bs.setValue(_property, (Comparable) entry.getValue());
                                } catch (Exception e) {
                                }
                        }
                        world.setBlock(_bp, _bs, 3);
                    }
                }
            }
        }
    }
}
