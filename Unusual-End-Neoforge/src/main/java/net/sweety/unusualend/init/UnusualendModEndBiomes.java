package net.sweety.unusualend.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.configuration.UEConfig;
import net.sweety.unusualend.endbiomes.TheEndBiomes;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class UnusualendModEndBiomes {
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            double high = 0.82 * UEConfig.BIOME_SIZE.get();
            double mid = 0.82 * UEConfig.BIOME_SIZE.get() * 0.25;
            double small = 0.6 * UEConfig.BIOME_SIZE.get();
            TheEndBiomes.addHighlandsBiome(ResourceKey.create(Registries.BIOME, UnusualEnd.makeUEID("gloopstone_lands")), high);
            TheEndBiomes.addMidlandsBiome(ResourceKey.create(Registries.BIOME, UnusualEnd.makeUEID("gloopstone_lands")), ResourceKey.create(Registries.BIOME, UnusualEnd.makeUEID("gloopstone_midlands")), mid);
            TheEndBiomes.addSmallIslandsBiome(ResourceKey.create(Registries.BIOME, UnusualEnd.makeUEID("warped_reef")), small);
        });
    }
}