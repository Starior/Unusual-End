package net.sweety.unusualend.network;


import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.sweety.unusualend.UnusualEnd;
import org.jetbrains.annotations.UnknownNullability;

import java.util.function.Supplier;

public class UnusualEndVariables {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, UnusualEnd.MODID);
    public static final Supplier<AttachmentType<PlayerVariables>> PLAYER_VARIABLES = ATTACHMENT_TYPES.register("player_variables",
            () -> AttachmentType.serializable(PlayerVariables::new).build());


    @EventBusSubscriber
    public static class EventBusVariableHandlers {
        @SubscribeEvent
        public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
            if (event.getEntity() instanceof ServerPlayer player)
                player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());
        }


        @SubscribeEvent

        public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {

            if (event.getEntity() instanceof ServerPlayer player)
                player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());

        }


        @SubscribeEvent

        public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {

            if (event.getEntity() instanceof ServerPlayer player)

                player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());

        }


        @SubscribeEvent

        public static void clonePlayer(PlayerEvent.Clone event) {

            PlayerVariables oldVars = event.getOriginal()
                    .getData(PLAYER_VARIABLES.get());
            PlayerVariables newVars = new PlayerVariables();
            newVars.tpX = oldVars.tpX;
            newVars.tpY = oldVars.tpY;
            newVars.tpZ = oldVars.tpZ;
            newVars.teleporting = oldVars.teleporting;
            newVars.playerMusic = oldVars.playerMusic;
            newVars.spectralSwing = oldVars.spectralSwing;
            newVars.scrapeOverlay = oldVars.scrapeOverlay;
            event.getEntity().setData(PLAYER_VARIABLES, newVars);
        }
    }


    public static class PlayerVariables implements INBTSerializable<CompoundTag> {
        public double tpX = 0;
        public double tpY = 0;
        public double tpZ = 0;
        public boolean teleporting = false;
        public double playerMusic = -1.0;

        public double spectralSwing = 0;
        public double scrapeOverlay = 0;


        public void syncPlayerVariables(Entity entity) {
            if (entity instanceof ServerPlayer serverPlayer)
                PacketDistributor.sendToPlayer(serverPlayer, new PlayerVariablesSyncPacket(this));
        }

        @Override
        public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
            CompoundTag nbt = new CompoundTag();
            nbt.putDouble("tpX", tpX);
            nbt.putDouble("tpY", tpY);
            nbt.putDouble("tpZ", tpZ);
            nbt.putBoolean("isTeleporting", teleporting);
            nbt.putDouble("PlayerMusic", playerMusic);
            nbt.putDouble("SpectralSwing", spectralSwing);
            nbt.putDouble("ScrapeOverlay", scrapeOverlay);
            return nbt;
        }

        @Override
        public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
            tpX = tag.getDouble("tpX");
            tpY = tag.getDouble("tpY");
            tpZ = tag.getDouble("tpZ");
            teleporting = tag.getBoolean("isTeleporting");
            playerMusic = tag.getDouble("PlayerMusic");
            spectralSwing = tag.getDouble("SpectralSwing");
            scrapeOverlay = tag.getDouble("ScrapeOverlay");
        }
    }


    public record PlayerVariablesSyncPacket(PlayerVariables data) implements CustomPacketPayload {

        public static final ResourceLocation ID = UnusualEnd.makeUEID("player_variables_sync");

        public static final StreamCodec<FriendlyByteBuf, PlayerVariablesSyncPacket> STREAM_CODEC = new StreamCodec<>() {
            @Override
            public PlayerVariablesSyncPacket decode(FriendlyByteBuf buf) {
                PlayerVariables variables = new PlayerVariables();
                double x = buf.readDouble();
                double y = buf.readDouble();
                double z = buf.readDouble();
                boolean isTeleporting = buf.readBoolean();
                double playerMusic = buf.readDouble();
                double spectralSwing = buf.readDouble();
                double scrapeOverlay = buf.readDouble();
                variables.scrapeOverlay = scrapeOverlay;
                variables.spectralSwing = spectralSwing;
                variables.playerMusic = playerMusic;
                variables.teleporting = isTeleporting;
                variables.tpZ = z;
                variables.tpY = y;
                variables.tpX = x;
                return new PlayerVariablesSyncPacket(variables);
            }

            @Override
            public void encode(FriendlyByteBuf buf, PlayerVariablesSyncPacket packet) {
                buf.writeDouble(packet.data.tpX);
                buf.writeDouble(packet.data.tpY);
                buf.writeDouble(packet.data.tpZ);
                buf.writeBoolean(packet.data.teleporting);
                buf.writeDouble(packet.data.playerMusic);
                buf.writeDouble(packet.data.spectralSwing);
                buf.writeDouble(packet.data.scrapeOverlay);
            }
        };

        public void handle(IPayloadContext context) {
            if (context.flow() == PacketFlow.CLIENTBOUND && this.data() != null) {
                context.enqueueWork(() -> {
                                    HolderLookup.Provider provider = context.player().level().registryAccess();
                                    CompoundTag nbt = this.data().serializeNBT(provider);
                                    context.player()
                                            .getData(PLAYER_VARIABLES)
                                            .deserializeNBT(provider, nbt);
                                }
                        )
                        .exceptionally(e -> {
                            context.disconnect(Component.translatable(e.getLocalizedMessage()));
                            return null;
                        });
            }
        }

        public static final Type<PlayerVariablesSyncPacket> TYPE = new Type<>(ID);

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }
}
