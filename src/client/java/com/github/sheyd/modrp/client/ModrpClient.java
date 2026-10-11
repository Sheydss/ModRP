package com.github.sheyd.modrp.client;

import com.github.sheyd.modrp.blocks.ModBlocks;
import com.github.sheyd.modrp.client.entitys.peanut.PeanutModel;
import com.github.sheyd.modrp.client.entitys.peanut.PeanutRenderer;
import com.github.sheyd.modrp.client.entitys.shyguy.ShyGuyModel;
import com.github.sheyd.modrp.client.entitys.shyguy.ShyGuyRenderer;
import com.github.sheyd.modrp.entitys.ModEntitys;
import com.github.sheyd.modrp.Modrp;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModrpClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_PEANUT_LAYER = new EntityModelLayer(Identifier.of(Modrp.MOD_ID, "peanut"), "main");
    public static final EntityModelLayer MODEL_SHYGUY_LAYER = new EntityModelLayer(Identifier.of(Modrp.MOD_ID, "shyguy"), "main");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(
            ModBlocks.WHITE_POWDER,
            RenderLayer.getTranslucent()
        );

        EntityRendererRegistry.register(ModEntitys.PEANUT, PeanutRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_PEANUT_LAYER, PeanutModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntitys.SHYGUY, ShyGuyRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_SHYGUY_LAYER, ShyGuyModel::getTexturedModelData);
    }
}
