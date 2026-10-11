package com.github.sheyd.modrp.blocks;

import com.github.sheyd.modrp.items.ModItems;
import com.github.sheyd.modrp.Modrp;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks  {

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModItems.CUSTOM_ITEM_GROUP_KEY).register(itemgroup -> {
            itemgroup.add(ModBlocks.STEAL_BLOCK);
            itemgroup.add(ModBlocks.WHITE_POWDER);
        });
    }

    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        Identifier id = Identifier.of(Modrp.MOD_ID, name);

        if(shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static final Block STEAL_BLOCK = register(new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.METAL).strength(4.0f).requiresTool()), "steal_block", true);

    public static final Block WHITE_POWDER = register(new WhitePowder(AbstractBlock.Settings.create().sounds(BlockSoundGroup.METAL).strength(4.0f).requiresTool()), "white_powder", true);
}
