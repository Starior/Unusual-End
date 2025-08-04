package net.sweety.unusualend.procedures;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.sweety.unusualend.init.UnusualEndBlocks;

@EventBusSubscriber
public class RightClickPotProcedure {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getHand() != event.getEntity().getUsedItemHand())
            return;
        execute(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
    }

    private static void execute(LevelAccessor world, double x, double y, double z, Player player) {
        if ((new ItemStack((world.getBlockState(BlockPos.containing(x, y, z))).getBlock())).getItem() == Blocks.FLOWER_POT.asItem()) {
            if (player.getMainHandItem().is(UnusualEndBlocks.CHORUS_FUNGUS.get().asItem())) {
                player.swing(InteractionHand.MAIN_HAND, true);
                if (!player.isCreative())
                    player.getMainHandItem().shrink(1);
                world.setBlock(BlockPos.containing(x, y, z), UnusualEndBlocks.FLOWER_POT_CHORUS_FUNGUS.get().defaultBlockState(), 3);
            }
            if (player.getMainHandItem().is(UnusualEndBlocks.CHORUS_ROOTS.get().asItem())) {
                player.swing(InteractionHand.MAIN_HAND, true);
                if (!player.isCreative())
                    player.getMainHandItem().shrink(1);
                world.setBlock(BlockPos.containing(x, y, z), UnusualEndBlocks.FLOWER_POT_CHORUS_ROOTS.get().defaultBlockState(), 3);
            }
            if ((player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == UnusualEndBlocks.SHINY_SPIREA.get().asItem()) {
                if (player instanceof LivingEntity _entity)
                    _entity.swing(InteractionHand.MAIN_HAND, true);
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
                }.checkGamemode(player))) {
                    (player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                }
                world.setBlock(BlockPos.containing(x, y, z), UnusualEndBlocks.FLOWER_POT_SHINY_SPIREA.get().defaultBlockState(), 3);
            }
            if ((player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == UnusualEndBlocks.WARPED_BUSH.get().asItem()) {
                if (player instanceof LivingEntity _entity)
                    _entity.swing(InteractionHand.MAIN_HAND, true);
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
                }.checkGamemode(player))) {
                    (player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                }
                world.setBlock(BlockPos.containing(x, y, z), UnusualEndBlocks.FLOWER_POT_WARPED_BUSH.get().defaultBlockState(), 3);
            }
            if ((player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == UnusualEndBlocks.BLOOMING_CHORUS_CANE.get().asItem()) {
                if (player instanceof LivingEntity _entity)
                    _entity.swing(InteractionHand.MAIN_HAND, true);
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
                }.checkGamemode(player))) {
                    (player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                }
                world.setBlock(BlockPos.containing(x, y, z), UnusualEndBlocks.FLOWER_POT_CANE.get().defaultBlockState(), 3);
            }
            if ((player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == UnusualEndBlocks.FLOWERING_PURPUR_GRASS.get().asItem()) {
                if (player instanceof LivingEntity _entity)
                    _entity.swing(InteractionHand.MAIN_HAND, true);
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
                }.checkGamemode(player))) {
                    (player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                }
                world.setBlock(BlockPos.containing(x, y, z), UnusualEndBlocks.FLOWER_POT_PURPUR_GRASS.get().defaultBlockState(), 3);
            }
            if ((player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == UnusualEndBlocks.CRYSTAL_FLOWER.get().asItem()) {
                if (player instanceof LivingEntity _entity)
                    _entity.swing(InteractionHand.MAIN_HAND, true);
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
                }.checkGamemode(player))) {
                    (player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                }
                world.setBlock(BlockPos.containing(x, y, z), UnusualEndBlocks.FLOWER_POT_CRYSTAL_FLOWER.get().defaultBlockState(), 3);
            }
            if ((player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == UnusualEndBlocks.GLOOPY_BUSH.get().asItem()) {
                if (player instanceof LivingEntity _entity)
                    _entity.swing(InteractionHand.MAIN_HAND, true);
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
                }.checkGamemode(player))) {
                    (player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                }
                world.setBlock(BlockPos.containing(x, y, z), UnusualEndBlocks.FLOWER_POT_GLOOPY_BUSH.get().defaultBlockState(), 3);
            }
            if ((player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == UnusualEndBlocks.GLOOPY_TENDRILS.get().asItem()) {
                if (player instanceof LivingEntity _entity)
                    _entity.swing(InteractionHand.MAIN_HAND, true);
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
                }.checkGamemode(player))) {
                    (player instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
                }
                world.setBlock(BlockPos.containing(x, y, z), UnusualEndBlocks.FLOWER_POT_GLOOPY_TENDRILS.get().defaultBlockState(), 3);
            }
        }
    }
}
