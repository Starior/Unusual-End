package net.sweety.unusualend.procedures;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.init.UnusualEndItems;

@EventBusSubscriber
public class FeedBolokFinProcedure {
    @SubscribeEvent
    public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
        if (event.getHand() != event.getEntity().getUsedItemHand())
            return;
        execute(event, event.getLevel(), event.getTarget(), event.getEntity());
    }

    private static void execute(PlayerInteractEvent.EntityInteract event, LevelAccessor level, Entity entity, Player player) {
        if (entity == null || player == null)
            return;
        if (entity instanceof Wolf wolf) {
            if (!(entity.getPersistentData().getBoolean("CooldownBolok"))) {
                if (player.getMainHandItem().is(UnusualEndItems.BOLOK_FIN.get())) {
                    if (wolf.isOwnedBy(player)) {
                        if (!player.isCreative())
                            player.getMainHandItem().shrink(1);
                        if (event != null)
                            event.setCanceled(true);
                        player.swing(InteractionHand.MAIN_HAND, true);
                        wolf.setHealth(wolf.getHealth() + 9);
                        if (!wolf.level().isClientSide())
                            wolf.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 12000, 0));
                        if (level instanceof ServerLevel _level)
                            _level.sendParticles(ParticleTypes.HEART, (entity.getX()), (entity.getY()), (entity.getZ()), 10, 0.3, 0.5, 0.3, 0);
                        entity.getPersistentData().putBoolean("CooldownBolok", true);
                        UnusualEnd.queueServerWork(200, () -> {
                            entity.getPersistentData().putBoolean("CooldownBolok", false);
                        });
                    }
                } else if (player.getMainHandItem().is(UnusualEndItems.COOKED_BOLOK_FIN.get())) {
                    if (wolf.isOwnedBy(player)) {
                        if (!player.isCreative())
                            player.getMainHandItem().shrink(1);
                        if (event != null)
                            event.setCanceled(true);
                        player.swing(InteractionHand.MAIN_HAND, true);
                        wolf.setHealth(wolf.getHealth() + 6);
                        if (!wolf.level().isClientSide())
                            wolf.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 24000, 1));
                        if (level instanceof ServerLevel _level)
                            _level.sendParticles(ParticleTypes.HEART, (entity.getX()), (entity.getY()), (entity.getZ()), 10, 0.3, 0.5, 0.3, 0);
                        entity.getPersistentData().putBoolean("CooldownBolok", true);
                        UnusualEnd.queueServerWork(200, () -> entity.getPersistentData().putBoolean("CooldownBolok", false));
                    }
                }
            }
        }
    }
}
