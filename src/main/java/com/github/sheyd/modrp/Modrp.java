package com.github.sheyd.modrp;

import com.github.sheyd.modrp.blocks.ModBlocks;
import com.github.sheyd.modrp.entitys.ModEntitys;
import com.github.sheyd.modrp.entitys.Peanut;
import com.github.sheyd.modrp.entitys.ShyGuy;
import com.github.sheyd.modrp.items.ModArmorMaterials;
import com.github.sheyd.modrp.items.ModItems;
import com.github.sheyd.modrp.network.EntityFaceSeenC2SPayload;
import com.github.sheyd.modrp.network.EntitySeenC2SPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Modrp implements ModInitializer {

    public static final String MOD_ID = "modrp";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        //ModArmorMaterials.initialize();
        ModBlocks.initialize();
        ModEntitys.initialize();
        ModItems.initialize();

        //Peanut
        PayloadTypeRegistry.playC2S().register(
                EntitySeenC2SPayload.ID,
                EntitySeenC2SPayload.CODEC
        );

        ServerPlayNetworking.registerGlobalReceiver(
                EntitySeenC2SPayload.ID,
                (payload, context) -> {
                    context.server().execute(() -> {
                        ServerPlayerEntity player = context.player();
                        Entity e = player.getWorld().getEntityById(payload.entityId());

                        if (e instanceof Peanut statue) {
                            statue.markSeenBy(player);
                        }
                    });
                }
        );

        //ShyGuy
        PayloadTypeRegistry.playC2S().register(
                EntityFaceSeenC2SPayload.ID,
                EntityFaceSeenC2SPayload.CODEC
        );

        ServerPlayNetworking.registerGlobalReceiver(
                EntityFaceSeenC2SPayload.ID,
                (payload, context) -> {
                    context.server().execute(() -> {
                        ServerPlayerEntity player = context.player();
                        Entity e = player.getWorld().getEntityById(payload.entityId());

                        if (e instanceof ShyGuy shyguy) {
                            shyguy.onFaceSeenBy(player);
                        }
                    });
                }
        );



        LOGGER.info("Modrp initialized");
    }
}
