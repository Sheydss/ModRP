package com.github.sheyd.modrp.items;

import com.github.sheyd.modrp.Modrp;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static void initialize() {

        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY)
            .register(itemgroup -> {
                itemgroup.add(ModItems.SUSPICIOUS_POWDER);
                itemgroup.add(ModItems.STEAL_INGOT);
                itemgroup.add(ModItems.STEAL_PICKAXE);
                itemgroup.add(ModItems.STEAL_HELMET);
            });

    }

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(Modrp.MOD_ID, id);

        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Modrp.MOD_ID, "item_group"));
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.SUSPICIOUS_POWDER))
            .displayName(Text.translatable("itemGroup.mod-rp"))
            .build();

    public static final Item SUSPICIOUS_POWDER = register(
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .alwaysEdible()
                        .nutrition(0)
                        .saturationModifier(0)
                        .snack()
                        .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 6 * 20, 1), 1.0f)
                        .build())),
            "suspicious_powder"
    );

    public static final Item STEAL_INGOT = register(
            new Item(new Item.Settings()), "steal_ingot"
    );

    public static final Item STEAL_PICKAXE = register(
            new PickaxeItem(ModToolMaterials.STEAL, new Item.Settings()), "steal_pickaxe"
    );

    public static final Item STEAL_HELMET = register(
            new ArmorItem(ModArmorMaterials.STEAL, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ModArmorMaterials.STEAL_DURABILITY_MULTIPLIER))), "steal_helmet"
    );
}