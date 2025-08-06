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
            () -> AttachmentType.serializable(iAttachmentHolder -> new PlayerVariables(0, 0, 0, false, 0, 0, 0)).build());


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

            PlayerVariables original = event.getOriginal().getData(PLAYER_VARIABLES);

            PlayerVariables clone = new PlayerVariables(original.tpX, original.tpY, original.tpZ, original.isTeleporting, original.PlayerMusic, original.SpectralSwing, original.ScrapeOverlay);

            clone.PlayerMusic = original.PlayerMusic;
            event.getEntity().setData(PLAYER_VARIABLES, clone);
        }
    }


    public static class PlayerVariables implements INBTSerializable<CompoundTag> {
        public double tpX = 0;
        public double tpY = 0;
        public double tpZ = 0;
        public boolean isTeleporting = false;
        public double PlayerMusic = -1.0;

        public PlayerVariables(double tpX, double tpY, double tpZ, boolean isTeleporting, double playerMusic, double spectralSwing, double scrapeOverlay) {
            this.tpX = tpX;
            this.tpY = tpY;
            this.tpZ = tpZ;
            this.isTeleporting = isTeleporting;
            PlayerMusic = playerMusic;
            SpectralSwing = spectralSwing;
            ScrapeOverlay = scrapeOverlay;
        }

        public double SpectralSwing = 0;
        public double ScrapeOverlay = 0;


        public void syncPlayerVariables(Entity entity) {
            if (entity instanceof ServerPlayer serverPlayer)
                PacketDistributor.sendToServer(new PlayerVariablesSyncPacket(this));
        }

        @Override
        public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
            CompoundTag nbt = new CompoundTag();
            nbt.putDouble("tpX", tpX);
            nbt.putDouble("tpY", tpY);
            nbt.putDouble("tpZ", tpZ);
            nbt.putBoolean("isTeleporting", isTeleporting);
            nbt.putDouble("PlayerMusic", PlayerMusic);
            nbt.putDouble("SpectralSwing", SpectralSwing);
            nbt.putDouble("ScrapeOverlay", ScrapeOverlay);
            return nbt;
        }

        @Override
        public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
            tpX = tag.getDouble("tpX");
            tpY = tag.getDouble("tpY");
            tpZ = tag.getDouble("tpZ");
            isTeleporting = tag.getBoolean("isTeleporting");
            PlayerMusic = tag.getDouble("PlayerMusic");
            SpectralSwing = tag.getDouble("SpectralSwing");
            ScrapeOverlay = tag.getDouble("ScrapeOverlay");
        }
    }


    public record PlayerVariablesSyncPacket(PlayerVariables data) implements CustomPacketPayload {

        public static final ResourceLocation ID = UnusualEnd.makeUEID("player_variables_sync");

        public static final StreamCodec<FriendlyByteBuf, PlayerVariablesSyncPacket> STREAM_CODEC = new StreamCodec<>() {
            @Override
            public PlayerVariablesSyncPacket decode(FriendlyByteBuf buf) {
                double x = buf.readDouble();
                double y = buf.readDouble();
                double z = buf.readDouble();
                boolean isTeleporting = buf.readBoolean();
                double playerMusic = buf.readDouble();
                double spectralSwing = buf.readDouble();
                double scrapeOverlay = buf.readDouble();
                return new PlayerVariablesSyncPacket(new PlayerVariables(x, y, z, isTeleporting, playerMusic, spectralSwing, scrapeOverlay));
            }

            @Override
            public void encode(FriendlyByteBuf buf, PlayerVariablesSyncPacket packet) {
                buf.writeDouble(packet.data.tpX);
                buf.writeDouble(packet.data.tpY);
                buf.writeDouble(packet.data.tpZ);
                buf.writeBoolean(packet.data.isTeleporting);
                buf.writeDouble(packet.data.PlayerMusic);
                buf.writeDouble(packet.data.SpectralSwing);
                buf.writeDouble(packet.data.ScrapeOverlay);
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
