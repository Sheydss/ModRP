package com.github.sheyd.modrp.network;

import com.github.sheyd.modrp.Modrp;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record EntitySeenC2SPayload(int entityId) implements CustomPayload {

    public static final Identifier CHANNEL_ID =
            Identifier.of(Modrp.MOD_ID, "entity_seen");

    public static final CustomPayload.Id<EntitySeenC2SPayload> ID =
            new CustomPayload.Id<>(CHANNEL_ID);

    public static final PacketCodec<RegistryByteBuf, EntitySeenC2SPayload> CODEC =
            PacketCodec.tuple(
                    PacketCodecs.VAR_INT, EntitySeenC2SPayload::entityId,
                    EntitySeenC2SPayload::new
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}