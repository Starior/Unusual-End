
package net.sweety.unusualend.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.sweety.unusualend.UnusualEnd;
import net.sweety.unusualend.procedures.ConsumeCitrineProcedure;
import net.sweety.unusualend.procedures.ConsumePrismaticProcedure;
import net.sweety.unusualend.procedures.ConsumeShinyProcedure;

public record InfuserGUIPacket(int id, int x, int y, int z) implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, InfuserGUIPacket> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public InfuserGUIPacket decode(FriendlyByteBuf buf) {
            int id = buf.readInt();
            int x = buf.readInt();
            int y = buf.readInt();
            int z = buf.readInt();
            return new InfuserGUIPacket(id, x, y, z);
        }

        @Override
        public void encode(FriendlyByteBuf buf, InfuserGUIPacket packet) {
            buf.writeInt(packet.id());
            buf.writeInt(packet.x());
            buf.writeInt(packet.y());
            buf.writeInt(packet.z());
        }
    };
    public static final ResourceLocation ID = UnusualEnd.makeUEID("infuser_gui_packet");
    public static final Type<InfuserGUIPacket> TYPE = new Type<>(ID);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            Level level = player.level();
            if (!level.hasChunkAt(new BlockPos(x, y, z)))
                return;
            switch (id) {
                case 0 -> ConsumeCitrineProcedure.execute(level, x, y, z, player, 1);
                case 1 -> ConsumeShinyProcedure.execute(level, x, y, z, player, 1);
                case 2 -> ConsumePrismaticProcedure.execute(level, x, y, z, player, 1);
                case 3 -> ConsumeCitrineProcedure.execute(level, x, y, z, player, 4);
                case 4 -> ConsumeShinyProcedure.execute(level, x, y, z, player, 4);
                case 5 -> ConsumePrismaticProcedure.execute(level, x, y, z, player, 4);
                case 6 -> ConsumeCitrineProcedure.execute(level, x, y, z, player, 8);
                case 7 -> ConsumeShinyProcedure.execute(level, x, y, z, player, 8);
                case 8 -> ConsumePrismaticProcedure.execute(level, x, y, z, player, 8);
            }
        });
    }
}