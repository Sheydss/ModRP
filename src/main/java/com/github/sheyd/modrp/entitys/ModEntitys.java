package com.github.sheyd.modrp.entitys;

import com.github.sheyd.modrp.Modrp;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntitys {

    public static final EntityType<Peanut> PEANUT = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Modrp.MOD_ID, "peanut"),
            EntityType.Builder.create(Peanut::new, SpawnGroup.CREATURE).dimensions(0.75F, 2.25F).build("peanut")
    );

    public static final EntityType<ShyGuy> SHYGUY = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Modrp.MOD_ID, "shyguy"),
            EntityType.Builder.create(ShyGuy::new, SpawnGroup.CREATURE).dimensions(0.75F, 3F).build("shyguy")
    );

    public static void initialize() {
        FabricDefaultAttributeRegistry.register(PEANUT, Peanut.createPeanutAttributes());
        FabricDefaultAttributeRegistry.register(SHYGUY, ShyGuy.createShyGuyAttributes());
    }

}
