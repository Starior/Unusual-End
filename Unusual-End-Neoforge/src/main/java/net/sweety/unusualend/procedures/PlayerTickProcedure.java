package net.sweety.unusualend.procedures;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.sweety.unusualend.BiomeMusicLibrary;
import net.sweety.unusualend.entity.EnderblobQueenEntity;
import net.sweety.unusualend.entity.EndstoneGolemEntity;
import net.sweety.unusualend.network.UnusualEndVariables;

@EventBusSubscriber
public class PlayerTickProcedure {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        playMusic(event.getEntity().level(), event.getEntity());
        updateMusic(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
        overlayUpdate(event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
        onReturnMusic(event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
    }

    private static void playMusic(LevelAccessor world, Entity entity) {
        if (entity == null)
            return;
        double MusicID;
        if (world.isClientSide()) {
            UnusualEndVariables.PlayerVariables variables = entity.getData(UnusualEndVariables.PLAYER_VARIABLES.get());
            MusicID = variables.playerMusic;
            BiomeMusicLibrary.playTrack((int) MusicID);
        }
    }

    private static void updateMusic(Event event, LevelAccessor accessor, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        if (!accessor.isClientSide()) {
            if (!onReturnMusic(accessor, x, y, z, entity)) {
                UnusualEndVariables.PlayerVariables variables = entity.getData(UnusualEndVariables.PLAYER_VARIABLES.get());
                variables.playerMusic = -1;
                variables.syncPlayerVariables(entity);
            }
        }
    }

    private static void overlayUpdate(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        UnusualEndVariables.PlayerVariables variables = entity.getData(UnusualEndVariables.PLAYER_VARIABLES.get());
        if (variables.scrapeOverlay == 1) {
            variables.scrapeOverlay = 0;
            variables.syncPlayerVariables(entity);
            if (world instanceof ServerLevel _level)
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
                        ("team remove " + entity.getStringUUID()));
            if (world instanceof ServerLevel _level)
                _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
                        "particle dust_color_transition 0.827 0.216 0.741 1.5 0.047 0.047 0.047 ~ ~1 ~ 0.4 0.5 0.4 0 40 normal");
            if (world.isClientSide()) {
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.GENERIC_BURN, SoundSource.PLAYERS, (float) 0.3, 1);
                    } else {
                        _level.playLocalSound(x, y, z, SoundEvents.GENERIC_BURN, SoundSource.PLAYERS, (float) 0.3, 1, false);
                    }
                }
            }
            if (world instanceof ServerLevel _level)
                _level.sendParticles(ParticleTypes.POOF, x, (y + 1), z, 10, 0.5, 0.5, 0.5, 0);
        } else if (variables.scrapeOverlay > 0) {
            variables.scrapeOverlay = variables.scrapeOverlay - 1;
            variables.syncPlayerVariables(entity);
            if (!(entity instanceof LivingEntity living && living.hasEffect(MobEffects.INVISIBILITY))) {
                variables.scrapeOverlay = 1;
                variables.syncPlayerVariables(entity);
            }
        }
    }

    private static boolean onReturnMusic(LevelAccessor accessor, double x, double y, double z, Entity entity) {
        if (entity == null)
            return false;
        boolean music = false;
        UnusualEndVariables.PlayerVariables variables = entity.getData(UnusualEndVariables.PLAYER_VARIABLES.get());
        if (!accessor.getEntitiesOfClass(EndstoneGolemEntity.class, AABB.ofSize(new Vec3(x, y, z), 100, 100, 100), e -> true).isEmpty()) {
            variables.playerMusic = 0;
            variables.syncPlayerVariables(entity);
            music = true;
        } else if (!accessor.getEntitiesOfClass(EnderblobQueenEntity.class, AABB.ofSize(new Vec3(x, y, z), 100, 100, 100), e -> true).isEmpty()) {
            variables.playerMusic = 1;
            variables.syncPlayerVariables(entity);
            music = true;
        } else {
            variables.playerMusic = -1;
            variables.syncPlayerVariables(entity);
        }
        return music;
    }
}