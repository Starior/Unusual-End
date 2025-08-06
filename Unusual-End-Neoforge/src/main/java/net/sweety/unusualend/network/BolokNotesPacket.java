package net.sweety.unusualend.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.sweety.unusualend.UnusualEnd;

public record BolokNotesPacket(int id, int x, int y, int z) implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, BolokNotesPacket> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public BolokNotesPacket decode(FriendlyByteBuf buf) {
            int id = buf.readInt();
            int x = buf.readInt();
            int y = buf.readInt();
            int z = buf.readInt();
            return new BolokNotesPacket(id, x, y, z);
        }

        @Override
        public void encode(FriendlyByteBuf buf, BolokNotesPacket packet) {
            buf.writeInt(packet.id());
            buf.writeInt(packet.x());
            buf.writeInt(packet.y());
            buf.writeInt(packet.z());
        }
    };
    public static final ResourceLocation ID = UnusualEnd.makeUEID("bolok_notes_packet");
    public static final Type<BolokNotesPacket> TYPE = new Type<>(ID);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!context.player().level().hasChunkAt(new BlockPos(x, y, z)))
                return;
            if (id == 0)
                context.player().closeContainer();
        });
    }
}