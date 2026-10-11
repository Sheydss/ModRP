package com.gihub.sheyd.modrp.client;

import com.gihub.sheyd.modrp.ModBlocks;
import com.gihub.sheyd.modrp.ModEntitys;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModrpClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_ENTITYTEST_LAYER = new EntityModelLayer(Identifier.of("entitytest", "test"), "main");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(
            ModBlocks.WHITE_POWDER,
            RenderLayer.getTranslucent()
        );

        EntityRendererRegistry.INSTANCE.register(ModEntitys.TEST, (context) -> {
            return new ModEntityTestRenderer(context);
        });
        EntityModelLayerRegistry.registerModelLayer(MODEL_ENTITYTEST_LAYER, EntityTestModel::getTexturedModelData);
    }
}
