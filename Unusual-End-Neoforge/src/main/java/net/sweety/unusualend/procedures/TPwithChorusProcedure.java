package net.sweety.unusualend.procedures;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.sweety.unusualend.configuration.UEConfig;
import net.sweety.unusualend.init.UnusualEndItems;
import net.sweety.unusualend.network.UnusualEndVariables;

public class TPwithChorusProcedure {
    public static void execute(LevelAccessor world, LivingEntity entity, ItemStack stack) {
        if (entity == null)
            return;
        if (entity.getMainHandItem().getItem() == UnusualEndItems.PRISMATIC_MIRROR.get()) {
            entity.swing(InteractionHand.MAIN_HAND, true);
        } else
            entity.swing(InteractionHand.OFF_HAND, true);
        if (NBTProcessor.getNBTBoolean(stack, "LinkedMirror")) {
            if (!NBTProcessor.getNBTString(stack, "TpW").equals("" + entity.level().dimension())) {
                if (NBTProcessor.getNBTString(stack, "TpW").equals("" + Level.OVERWORLD)) {
                    if (entity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
                        ResourceKey<Level> destinationType = Level.OVERWORLD;
                        if (_player.level().dimension() == destinationType)
                            return;
                        ServerLevel nextLevel = _player.server.getLevel(destinationType);
                        if (nextLevel != null) {
                            _player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
                            _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                            _player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
                            for (MobEffectInstance _effectinstance : _player.getActiveEffects())
                                _player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance, true));
                            _player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                        }
                    }
                }
                if (NBTProcessor.getNBTString(stack, "TpW").equals("" + Level.NETHER)) {
                    if (entity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
                        ResourceKey<Level> destinationType = Level.NETHER;
                        if (_player.level().dimension() == destinationType)
                            return;
                        ServerLevel nextLevel = _player.server.getLevel(destinationType);
                        if (nextLevel != null) {
                            _player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
                            _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                            _player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
                            for (MobEffectInstance _effectinstance : _player.getActiveEffects())
                                _player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance, true));
                            _player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                        }
                    }
                }
                if (NBTProcessor.getNBTString(stack, "TpW").equals("" + Level.END)) {
                    if (entity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
                        ResourceKey<Level> destinationType = Level.END;
                        if (_player.level().dimension() == destinationType)
                            return;
                        ServerLevel nextLevel = _player.server.getLevel(destinationType);
                        if (nextLevel != null) {
                            _player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
                            _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                            _player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
                            for (MobEffectInstance _effectinstance : _player.getActiveEffects())
                                _player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance, true));
                            _player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                        }
                    }
                }
            }
            entity.fallDistance = 0;
            entity.teleportTo(NBTProcessor.getNBTDouble(stack, "TpX"), NBTProcessor.getNBTDouble(stack, "TpY"), NBTProcessor.getNBTDouble(stack, "TpZ"));
            if (entity instanceof ServerPlayer _serverPlayer)
                _serverPlayer.connection.teleport(NBTProcessor.getNBTDouble(stack, "TpX"), NBTProcessor.getNBTDouble(stack, "TpY"), NBTProcessor.getNBTDouble(stack, "TpZ"), entity.getYRot(), entity.getXRot());
            if (entity instanceof Player _player)
                _player.getCooldowns().addCooldown(stack.getItem(), (int) (double) UEConfig.PRISMATIC_MIRROR.get());
            if (world instanceof ServerLevel _level)
                _level.sendParticles(ParticleTypes.PORTAL, (entity.getX()), (entity.getY()), (entity.getZ()), 50, 0.5, 1.5, 0.5, 0);
            if (world instanceof Level _level) {
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1, 1);
                } else {
                    _level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1, 1, false);
                }
            }
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
                stack.hurtAndBreak(1, entity, entity.getEquipmentSlotForItem(stack));
            }
            if (UEConfig.NEED_ANCHOR.get()) {
                entity.getPersistentData().putString("TargetDimension", NBTProcessor.getNBTString(stack, "TpW"));
                entity.getPersistentData().putDouble("TargetX", NBTProcessor.getNBTDouble(stack, "TpX") - 0.5);
                entity.getPersistentData().putDouble("TargetY", NBTProcessor.getNBTDouble(stack, "TpY"));
                entity.getPersistentData().putDouble("TargetZ", NBTProcessor.getNBTDouble(stack, "TpZ") - 0.5);
                UnusualEndVariables.PlayerVariables variables = entity.getData(UnusualEndVariables.PLAYER_VARIABLES.get());
                variables.isTeleporting = true;
                variables.syncPlayerVariables(entity);
            }
        } else {
            if ((new Object() {
                public String getValue() {
                    CompoundTag dataIndex = new CompoundTag();
                    entity.saveWithoutId(dataIndex);
                    return dataIndex.getString("Dimension");
                }
            }.getValue()).equals(new Object() {
                public String getValue() {
                    CompoundTag dataIndex = new CompoundTag();
                    entity.saveWithoutId(dataIndex);
                    return dataIndex.getString("SpawnDimension");
                }
            }.getValue()) || (new Object() {
                public String getValue() {
                    CompoundTag dataIndex = new CompoundTag();
                    entity.saveWithoutId(dataIndex);
                    return dataIndex.getString("SpawnDimension");
                }
            }.getValue()).equals("minecraft:the_end") || (new Object() {
                public String getValue() {
                    CompoundTag dataIndex = new CompoundTag();
                    entity.saveWithoutId(dataIndex);
                    return dataIndex.getString("SpawnDimension");
                }
            }.getValue()).equals("minecraft:the_nether") || (new Object() {
                public String getValue() {
                    CompoundTag dataIndex = new CompoundTag();
                    entity.saveWithoutId(dataIndex);
                    return dataIndex.getString("SpawnDimension");
                }
            }.getValue()).equals("minecraft:overworld")) {
                if (!(new Object() {
                    public String getValue() {
                        CompoundTag dataIndex = new CompoundTag();
                        entity.saveWithoutId(dataIndex);
                        return dataIndex.getString("Dimension");
                    }
                }.getValue()).equals(new Object() {
                    public String getValue() {
                        CompoundTag dataIndex = new CompoundTag();
                        entity.saveWithoutId(dataIndex);
                        return dataIndex.getString("SpawnDimension");
                    }
                }.getValue())) {
                    if ((new Object() {
                        public String getValue() {
                            CompoundTag dataIndex = new CompoundTag();
                            entity.saveWithoutId(dataIndex);
                            return dataIndex.getString("SpawnDimension");
                        }
                    }.getValue()).equals("minecraft:the_end")) {
                        if (entity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
                            ResourceKey<Level> destinationType = Level.END;
                            if (_player.level().dimension() == destinationType)
                                return;
                            ServerLevel nextLevel = _player.server.getLevel(destinationType);
                            if (nextLevel != null) {
                                _player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
                                _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                                _player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
                                for (MobEffectInstance _effectinstance : _player.getActiveEffects())
                                    _player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance, true));
                                _player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                            }
                        }
                    }
                    if ((new Object() {
                        public String getValue() {
                            CompoundTag dataIndex = new CompoundTag();
                            entity.saveWithoutId(dataIndex);
                            return dataIndex.getString("SpawnDimension");
                        }
                    }.getValue()).equals("minecraft:the_nether")) {
                        if (entity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
                            ResourceKey<Level> destinationType = Level.NETHER;
                            if (_player.level().dimension() == destinationType)
                                return;
                            ServerLevel nextLevel = _player.server.getLevel(destinationType);
                            if (nextLevel != null) {
                                _player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
                                _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                                _player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
                                for (MobEffectInstance _effectinstance : _player.getActiveEffects())
                                    _player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance, true));
                                _player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                            }
                        }
                    }
                    if ((new Object() {
                        public String getValue() {
                            CompoundTag dataIndex = new CompoundTag();
                            entity.saveWithoutId(dataIndex);
                            return dataIndex.getString("SpawnDimension");
                        }
                    }.getValue()).equals("minecraft:overworld")) {
                        if (entity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
                            ResourceKey<Level> destinationType = Level.OVERWORLD;
                            if (_player.level().dimension() == destinationType)
                                return;
                            ServerLevel nextLevel = _player.server.getLevel(destinationType);
                            if (nextLevel != null) {
                                _player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
                                _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                                _player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
                                for (MobEffectInstance _effectinstance : _player.getActiveEffects())
                                    _player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance, true));
                                _player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                            }
                        }
                    }
                }
                {
                    entity.teleportTo(
                            (((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
                                    ? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getX() : _player.level().getLevelData().getSpawnPos().getX())
                                    : 0) + 0.5),
                            ((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
                                    ? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getY() : _player.level().getLevelData().getSpawnPos().getY())
                                    : 0),
                            (((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
                                    ? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getZ() : _player.level().getLevelData().getSpawnPos().getZ())
                                    : 0) + 0.5));
                    if (entity instanceof ServerPlayer _serverPlayer)
                        _serverPlayer.connection.teleport(
                                (((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
                                        ? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getX() : _player.level().getLevelData().getSpawnPos().getX())
                                        : 0) + 0.5),
                                ((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
                                        ? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getY() : _player.level().getLevelData().getSpawnPos().getY())
                                        : 0),
                                (((entity instanceof ServerPlayer _player && !_player.level().isClientSide())
                                        ? ((_player.getRespawnDimension().equals(_player.level().dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getZ() : _player.level().getLevelData().getSpawnPos().getZ())
                                        : 0) + 0.5),
                                entity.getYRot(), entity.getXRot());
                }
                entity.fallDistance = 0;
                if (entity instanceof Player player)
                    player.getCooldowns().addCooldown(stack.getItem(), (int) (double) UEConfig.PRISMATIC_MIRROR.get());
                if (world instanceof ServerLevel _level)
                    _level.sendParticles(ParticleTypes.PORTAL, (entity.getX()), (entity.getY()), (entity.getZ()), 50, 0.5, 1.5, 0.5, 0);
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 1, 1);
                    } else {
                        _level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 1, 1, false);
                    }
                }
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
                    stack.hurtAndBreak(1, entity, entity.getEquipmentSlotForItem(stack));
                }
            } else {
                if (entity instanceof Player _player && !_player.level().isClientSide())
                    _player.displayClientMessage(Component.literal((Component.translatable("text.unusualend.not_linked").getString())), true);
            }
        }
    }
}
