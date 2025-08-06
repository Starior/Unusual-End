package net.sweety.unusualend.procedures;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import net.sweety.unusualend.UnusualEnd;

@EventBusSubscriber
public class WarpedReefFishProcedure {
    @SubscribeEvent
    public static void onPlayerFishItem(ItemFishedEvent event) {
        execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
    }

    private static void execute(ItemFishedEvent event, LevelAccessor level, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        if (level.getBiome(BlockPos.containing(x, y, z)).is(UnusualEnd.makeUEID("warped_reef"))) {
            if (event != null) {
                event.setCanceled(true);
            }
            if (!(entity instanceof ServerPlayer _plr1 && _plr1.level() instanceof ServerLevel
                    && _plr1.getAdvancements().getOrStartProgress(_plr1.server.getAdvancements().get(UnusualEnd.makeUEID("fish_in_warped_reef"))).isDone())) {
                if (entity instanceof ServerPlayer _player) {
                    AdvancementHolder _adv = _player.server.getAdvancements().get(UnusualEnd.makeUEID("fish_in_warped_reef"));
                    AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
                    if (!_ap.isDone()) {
                        for (String criteria : _ap.getRemainingCriteria())
                            _player.getAdvancements().award(_adv, criteria);
                    }
                }
            }
            if (!level.isClientSide() && level.getServer() != null) {
                for (ItemStack itemStack : level.getServer().reloadableRegistries().getLootTable(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.withDefaultNamespace("warped_reef_fishing")))
                        .getRandomItems(new LootParams.Builder((ServerLevel) level).create(LootContextParamSets.EMPTY))) {
                    if (level instanceof ServerLevel _level) {
                        ItemEntity entityToSpawn = new ItemEntity(_level, (entity.getX()), (entity.getY() + 0.5), (entity.getZ()), itemStack);
                        entityToSpawn.setPickUpDelay(1);
                        _level.addFreshEntity(entityToSpawn);
                    }
                }
            }
        }
    }
}
