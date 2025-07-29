package net.sweety.unusualend.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import net.sweety.unusualend.init.UnusualEndMiscRegister;

@Mod.EventBusSubscriber
public class CrystallizeDamagesProcedure {
    @SubscribeEvent
    public static void onEntityAttacked(LivingHurtEvent event) {
        if (event != null && event.getEntity() != null) {
            LivingEntity entity = event.getEntity();
            if (entity.hasEffect(UnusualEndMiscRegister.CRYSTALLIZATION.get())) {
                entity.getPersistentData().putDouble("CrystalDamages", (entity.getPersistentData().getDouble("CrystalDamages") + event.getAmount()));
                event.setCanceled(true);
            }
        }
    }
}
