package com.github.sheyd.modrp.network;

import com.github.sheyd.modrp.Modrp;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record EntityFaceSeenC2SPayload(int entityId) implements CustomPayload {
    public static final Identifier CHANNEL_ID =
            Identifier.of(Modrp.MOD_ID, "face_seen");

    public static final CustomPayload.Id<EntityFaceSeenC2SPayload> ID =
            new CustomPayload.Id<>(CHANNEL_ID);

    public static final PacketCodec<RegistryByteBuf, EntityFaceSeenC2SPayload> CODEC =
            PacketCodec.tuple(
                    PacketCodecs.VAR_INT, EntityFaceSeenC2SPayload::entityId,
                    EntityFaceSeenC2SPayload::new
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
