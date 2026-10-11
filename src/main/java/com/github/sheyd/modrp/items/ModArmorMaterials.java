package com.github.sheyd.modrp.items;

import com.github.sheyd.modrp.Modrp;
import com.google.common.collect.Maps;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

//public class ModArmorMaterials {
//
//    public static void initialize() {};

//    public static final int STEAL_DURABILITY_MULTIPLIER = 10;
//
//    public static RegistryEntry<ArmorMaterial> registerMaterial(String id, Map<ArmorItem.Type, Integer> defensePoints, int enchantability, RegistryEntry<SoundEvent> equipSound, Supplier<Ingredient> repairIngredientSupplier, float toughness, float knockbackResistance, boolean dyeable) {
//        List<ArmorMaterial.Layer> layers = List.of(
//                new ArmorMaterial.Layer(Identifier.of(Modrp.MOD_ID, id), "", dyeable)
//        );
//        ArmorMaterial material = new ArmorMaterial(defensePoints, enchantability, equipSound, repairIngredientSupplier, layers, toughness, knockbackResistance);
//        material = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of(Modrp.MOD_ID, id), material);
//        return RegistryEntry.of(material);
//    }
//
//    public static final RegistryEntry<ArmorMaterial> STEAL = registerMaterial("steal",
//        Map.of(
//            ArmorItem.Type.HELMET, 3,
//            ArmorItem.Type.CHESTPLATE, 6,
//            ArmorItem.Type.LEGGINGS, 7,
//            ArmorItem.Type.BOOTS, 3
//        ),
//        5,
//        SoundEvents.ITEM_ARMOR_EQUIP_IRON,
//        () -> Ingredient.ofItems(ModItems.STEAL_INGOT),
//        0.0F,
//        0.0F,
//        false);

    public interface ModArmorMaterials {
        ArmorMaterial STEAL = new ArmorMaterial(15, createDefenseMap(3, 6, 7, 3, 5), 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, ItemTags.REPAIRS_IRON_ARMOR, EquipmentAssetKeys.IRON);

        static Map<EquipmentType, Integer> createDefenseMap(int bootsDefense, int leggingsDefense, int chestplateDefense, int helmetDefense, int bodyDefense) {
            return Maps.newEnumMap(Map.of(EquipmentType.BOOTS, bootsDefense, EquipmentType.LEGGINGS, leggingsDefense, EquipmentType.CHESTPLATE, chestplateDefense, EquipmentType.HELMET, helmetDefense, EquipmentType.BODY, bodyDefense));
        }
    }
//}
