package net.sweety.unusualend.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingUseTotemEvent;
import net.sweety.unusualend.configuration.UEConfig;
import net.sweety.unusualend.network.UnusualEndVariables;

@EventBusSubscriber
public class LinkedTotemTriggerProcedure {
    @SubscribeEvent
    public static void whenEntityUsesTotem(LivingUseTotemEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event.getEntity().level(), event.getEntity(), event.getTotem());
        }
    }

    private static void execute(LevelAccessor world, Entity entity, ItemStack stack) {
        if (entity == null)
            return;

        if (NBTProcessor.getNBTBoolean(stack, "LinkedTotem")) {
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
                            for (MobEffectInstance effectInstance : _player.getActiveEffects())
                                _player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), effectInstance, true));
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
            entity.setDeltaMovement(new Vec3(0, 0, 0));
            entity.fallDistance = 0;
            entity.teleportTo(NBTProcessor.getNBTDouble(stack,"TpX"), NBTProcessor.getNBTDouble(stack,"TpY"), NBTProcessor.getNBTDouble(stack,"TpZ"));
            if (entity instanceof ServerPlayer _serverPlayer)
                _serverPlayer.connection.teleport(NBTProcessor.getNBTDouble(stack,"TpX"), NBTProcessor.getNBTDouble(stack,"TpY"), NBTProcessor.getNBTDouble(stack,"TpZ"), entity.getYRot(), entity.getXRot());
            if (world instanceof ServerLevel _level)
                _level.sendParticles(ParticleTypes.PORTAL, (entity.getX()), (entity.getY()), (entity.getZ()), 50, 0.5, 1.5, 0.5, 0);
            if (world instanceof Level _level) {
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 1, 1);
                } else {
                    _level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 1, 1, false);
                }
            }
            if (UEConfig.NEED_ANCHOR.get()) {
                entity.getPersistentData().putString("TargetDimension", NBTProcessor.getNBTString(stack,"TpW"));
                entity.getPersistentData().putDouble("TargetX", NBTProcessor.getNBTDouble(stack,"TpX") - 0.5);
                entity.getPersistentData().putDouble("TargetY", NBTProcessor.getNBTDouble(stack,"TpY"));
                entity.getPersistentData().putDouble("TargetZ", NBTProcessor.getNBTDouble(stack,"TpZ") - 0.5);
                UnusualEndVariables.PlayerVariables variables = entity.getData(UnusualEndVariables.PLAYER_VARIABLES.get());
                variables.isTeleporting = true;
                variables.syncPlayerVariables(entity);
            }
        }
    }
}
