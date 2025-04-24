package com.blake.skyresourcereborn.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.blake.skyresourcereborn.registry.*;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("null")
public class ModItemTag extends ItemTagsProvider {

    @SuppressWarnings("deprecation")
    public ModItemTag(PackOutput o, CompletableFuture<Provider> p, CompletableFuture<TagLookup<Block>> b) {
        super(o, p, b);
    }

    @Override
    protected void addTags(Provider p) {
        tag(ModItemTags.CORE).add(
                ModBlocks.MULTIBLOCK_CORE.get().asItem());

        tag(ModItemTags.HATCHES).add(
                ModBlocks.ENERGY_INPUT.get().asItem(),
                ModBlocks.ITEM_INPUT.get().asItem(),
                ModBlocks.ITEM_OUTPUT.get().asItem());

        tag(ModItemTags.INPUT_HATCHES).add(
                ModBlocks.ENERGY_INPUT.get().asItem(),
                ModBlocks.ITEM_INPUT.get().asItem());

        tag(ModItemTags.OUTPUT_HATCHES).add(
                ModBlocks.ITEM_OUTPUT.get().asItem());
    }

}
