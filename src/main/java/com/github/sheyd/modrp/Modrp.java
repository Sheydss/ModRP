package com.gihub.sheyd.modrp;

import net.fabricmc.api.ModInitializer;

public class Modrp implements ModInitializer {

    public static String MOD_ID = "modrp";

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModArmorMaterials.initialize();
        ModBlocks.initialize();
        ModEntitys.initialize();
    }
}
