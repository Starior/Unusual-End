package net.sweety.unusualend;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.handling.IPlayPayloadHandler;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import net.sweety.unusualend.configuration.UEConfig;
import net.sweety.unusualend.init.*;
import net.sweety.unusualend.network.UnusualendModVariables;
import net.sweety.unusualend.world.features.StructureFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Mod("unusualend")
public class UnusualEnd {
    public static final Logger LOGGER = LogManager.getLogger(UnusualEnd.class);
    public static final String MODID = "unusualend";

    public UnusualEnd(IEventBus bus, ModContainer container) {
        NeoForge.EVENT_BUS.addListener(this::tick);
        bus.addListener(this::registerNetworking);
        UnusualEndSounds.REGISTRY.register(bus);
        UnusualEndBlocks.REGISTRY.register(bus);
        UnusualendModBlockEntities.REGISTRY.register(bus);
        UnusualEndItems.REGISTRY.register(bus);
        UnusualendModEntities.REGISTRY.register(bus);
        UnusualendModTabs.REGISTRY.register(bus);
        StructureFeature.REGISTRY.register(bus);
        UnusualEndPotions.REGISTRY.register(bus);
        UnusualendModLootModifier.LOOT_MODIFIERS.register(bus);
        UnusualendModVariables.ATTACHMENT_TYPES.register(bus);
        UnusualEndMiscRegister.register(bus);
        container.registerConfig(ModConfig.Type.COMMON, UEConfig.SPEC, "unusualend-common.toml");
    }

    private static boolean networkingRegistered = false;
    private static final Map<ResourceLocation, NetworkMessage<?>> MESSAGES = new HashMap<>();

    private record NetworkMessage<T extends CustomPacketPayload>(FriendlyByteBuf.Reader<T> reader,
                                                                 IPlayPayloadHandler<T> handler) {
    }

    public static ResourceLocation makeUEID(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static <T extends CustomPacketPayload> void addNetworkMessage(ResourceLocation id, FriendlyByteBuf.Reader<T> reader, IPlayPayloadHandler<T> handler) {
        if (networkingRegistered)
            throw new IllegalStateException("Cannot register new network messages after networking has been registered");
        MESSAGES.put(id, new NetworkMessage<>(reader, handler));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})

    private void registerNetworking(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(MODID);
        MESSAGES.forEach((id, networkMessage) -> registrar.play(id, ((NetworkMessage) networkMessage).reader(), networkMessage.handler()));
        networkingRegistered = true;
    }

    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public static void queueServerWork(int tick, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
    }

    public void tick(ServerTickEvent.Post event) {
        List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
        workQueue.forEach(work -> {
            work.setValue(work.getValue() - 1);
            if (work.getValue() == 0)
                actions.add(work);
        });
        actions.forEach(e -> e.getKey().run());
        workQueue.removeAll(actions);
    }
}